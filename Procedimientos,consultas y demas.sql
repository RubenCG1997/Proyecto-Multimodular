USE ProyectoFinal;
-- 5 consultas --

-- Muestra la suma total acumulado de las suscripciones con tipo individual
SELECT SUM(precio) AS TotalAcumulado
FROM Suscripciones
WHERE tipo = 'INDIVIDUAL';

-- Contar cuantas publicaciones tiene cada editorial
SELECT e.nombre AS NombreEditorial, COUNT(*) AS TotalPublicaciones
FROM Editoriales e
INNER JOIN Publicaciones p ON e.cif = p.fkEditorial
GROUP BY e.nombre
ORDER BY TotalPublicaciones DESC;

-- Lista las publicacion que tiene al menos una reseña con 5 estrellas --
SELECT distinct Publicaciones.titulo  AS NombrePublicacion
FROM Publicaciones 
INNER JOIN Resennas  ON Publicaciones.isbn = Resennas.pkfkPublicacion
WHERE Resennas.puntuacion = (SELECT MAX(puntuacion) FROM Resennas);

-- Saber quien sigue a pedro_sanchez --
SELECT Cuentas.username AS Seguidor
FROM CuentaSigueCuenta
INNER JOIN Cuentas ON CuentaSigueCuenta.idSeguidor = Cuentas.idCuenta
WHERE CuentaSigueCuenta.idSeguido = (SELECT idCuenta FROM Cuentas WHERE username = 'pedro_sanchez');

-- Autor mejor puntuado en las reseñas --
SELECT Autores.nombre, Autores.apellidos, AVG(Resennas.puntuacion) AS PromedioPuntuacion
FROM Autores 
INNER JOIN Publicaciones  ON Autores.nombre = Publicaciones.fkAutorNombre AND Autores.apellidos = Publicaciones.fkAutorApellidos
INNER JOIN Resennas  ON Publicaciones.isbn = Resennas.pkfkPublicacion
GROUP BY Autores.nombre, Autores.apellidos
ORDER BY PromedioPuntuacion DESC
LIMIT 1;


-- 2 actualizaciones y 1 borrado --

-- Actualiza el precio rebajandolo un 10%  si el tipo es individual--

UPDATE Suscripciones
SET precio = precio * 0.9
WHERE tipo = 'INDIVIDUAL';
SET SQL_SAFE_UPDATES = 1;

-- Actualiza las donde se encuentre aventura por èpica
SET SQL_SAFE_UPDATES = 0;
UPDATE Listas
SET nombre = REPLACE(nombre, 'aventura', 'èpica')
WHERE nombre LIKE '%aventura%';

-- Elimina las reseñas de harry poter con puntuacion menor a 2 -- 
DELETE FROM Resennas
WHERE pkfkPublicacion IN (SELECT isbn FROM Publicaciones WHERE titulo LIKE '%Harry Potter%')
AND puntuacion < 2;

-- 2 Vistas

-- Vista que muestra el usuario y la cantidad de seguido y seguidores --
CREATE VIEW SeguidosSeguidores AS
SELECT Cuentas.username ,
       (SELECT COUNT(*) FROM CuentaSigueCuenta WHERE idSeguidor = Cuentas.idCuenta) AS Seguidos,
       (SELECT COUNT(*) FROM CuentaSigueCuenta WHERE idSeguido = Cuentas.idCuenta) AS Seguidores
FROM Cuentas ;
Select*from SeguidosSeguidores;

-- Vista que muestra la puntuacion de la reseña,el usuario, el titulo de la publicacion --

CREATE VIEW ResennasDetalle AS
SELECT Resennas.puntuacion AS Puntuacion,
       Cuentas.username AS Usuario,
       Publicaciones.titulo AS TituloPublicacion
FROM Resennas 
INNER JOIN Cuentas ON Resennas.pkfkCuenta = Cuentas.idCuenta
INNER JOIN Publicaciones ON Resennas.pkfkPublicacion = Publicaciones.isbn;

-- 3 Procedimientos --

-- Elimina reseña --
DELIMITER $$

DROP PROCEDURE IF EXISTS EliminarResenna;
CREATE PROCEDURE EliminarResenna(
    IN p_pkfk_cuenta INT,
    IN p_pkfk_publicacion VARCHAR(13)
)
BEGIN
    DELETE FROM Resennas
    WHERE pkfkCuenta = p_pkfk_cuenta AND pkfkPublicacion = p_pkfk_publicacion;
END$$

DELIMITER ;

-- Descuento de suscripciones --

DELIMITER $$
DROP PROCEDURE IF EXISTS AplicarDescuentoSuscripcionesIndividual;
CREATE PROCEDURE AplicarDescuentoSuscripcionesIndividual()
BEGIN
    UPDATE Suscripciones
    SET precio = precio * 0.9
    WHERE tipo = 'INDIVIDUAL';
END$$
DELIMITER ;
CALL AplicarDescuentoSuscripcionesIndividual();

-- Metodo para agregar usuario --
DROP FUNCTION IF EXISTS AgregarUsuario;
DELIMITER $$
CREATE FUNCTION AgregarUsuario(
    p_dni VARCHAR(9),
    p_nombre VARCHAR(20),
    p_apellidos VARCHAR(40),
    p_email VARCHAR(40),
    p_contrasenna VARCHAR(40),
    p_fecha_de_nacimiento DATE
) RETURNS VARCHAR(255)
DETERMINISTIC
BEGIN
    DECLARE msg VARCHAR(255);

    IF EXISTS (SELECT 1 FROM Usuarios WHERE dni = p_dni) THEN
        SET msg = 'El usuario con este DNI ya existe';
    ELSE
        INSERT INTO Usuarios (dni, nombre, apellidos, email, contrasenna, fecha_de_nacimiento)
        VALUES (p_dni, p_nombre, p_apellidos, p_email, p_contrasenna, p_fecha_de_nacimiento);
        SET msg = 'Usuario agregado exitosamente';
    END IF;

    RETURN msg;
END $$
DELIMITER ;


-- Disparadores 

-- Poner en mayuscula el nombre y apellido del usuario antes de ser insertado --
DROP TRIGGER IF EXISTS BeforeInsertUsuario;
DELIMITER $$

CREATE TRIGGER BeforeInsertUsuario
BEFORE INSERT ON Usuarios
FOR EACH ROW
BEGIN
    SET NEW.nombre = CONCAT(UPPER(LEFT(NEW.nombre, 1)), LOWER(SUBSTRING(NEW.nombre, 2)));
    SET NEW.apellidos = CONCAT(UPPER(LEFT(NEW.apellidos, 1)), LOWER(SUBSTRING(NEW.apellidos, 2)));
END $$

DELIMITER ;

-- Despues de insertat al usuario muestra un mensjae indicando que se ha insertado --
DROP TRIGGER IF EXISTS AfterInsertUsuario;
DELIMITER $$

CREATE TRIGGER AfterInsertUsuario
AFTER INSERT ON Usuarios
FOR EACH ROW
BEGIN
    DECLARE mensaje VARCHAR(255);
    SET mensaje = CONCAT('Se ha insertado un nuevo usuario con DNI: ', NEW.dni);
    SELECT mensaje;
END $$

DELIMITER ;