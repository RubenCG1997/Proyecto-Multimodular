USE ProyectoFinal;

-- Inserción de datos en la tabla Usuarios --
INSERT INTO Usuarios (dni, nombre, apellidos, email, contrasenna, fecha_de_nacimiento, rol, estado) VALUES
('12345678A', 'Juan', 'Pérez', 'juan.perez@example.com', '111111', '1980-01-01', 1, 0),
('23456789B', 'María', 'González', 'maria.gonzalez@example.com','11111', '1990-02-02', 1, 0),
('34567890C', 'Carlos', 'Sánchez', 'carlos.sanchez@example.com','11111', '1985-03-03', 1, 0);

-- Inserción de datos en la tabla Suscripciones --
INSERT INTO Suscripciones (tipo, cuota, precio) VALUES 
('INDIVIDUAL', 'MENSUAL', 9.99),
('DUO', 'ANUAL', 19.99),
('FAMILIAR', 'ANUAL', 29.99);

-- Inserción de datos en la tabla Autores --
INSERT INTO Autores (nombre, apellidos, fecha_de_nacimiento, biografía, estado) VALUES
('Gabriel', 'García Márquez', '1927-03-06', 'Autor colombiano de fama mundial.', true),
('Isabel', 'Allende', '1942-08-02', 'Autora chilena de renombre internacional.', true),
('J.K.', 'Rowling', '1965-07-31', 'Autora británica conocida por la saga de Harry Potter.', true);

-- Inserción de datos en la tabla Editoriales --
INSERT INTO Editoriales (cif, nombre, direccion, telefono, cp, estado) VALUES
('A12345678', 'Editorial Planeta', 'Calle Falsa 123', '123456789', '28080', true),
('B23456789', 'Penguin Random House', 'Avenida Siempre Viva 742', '234567890', '08080', true),
('C34567890', 'HarperCollins', 'Paseo de la Reforma 100', '345678901', '06060', true);

-- Inserción de datos en la tabla Cuentas --
INSERT INTO Cuentas (username, fecha_de_creacion, fkUsuario, fkSuscripciones) VALUES
('juanp', '2023-01-01',  '12345678A', 1),
('mariag', '2023-02-01', '23456789B', 2),
('carloss', '2023-03-01', '34567890C', 3);

-- Inserción de datos en la tabla Publicaciones --
INSERT INTO Publicaciones (isbn, titulo, fecha_de_lanzamiento, estado, fkAutorNombre, fkAutorApellidos, fkEditorial) VALUES
('9781234567897', 'Cien Años de Soledad', '1967-05-30', true, 'Gabriel', 'García Márquez', 'A12345678'),
('9782345678908', 'La Casa de los Espíritus', '1982-01-01', true, 'Isabel', 'Allende', 'B23456789'),
('9783456789019', 'Harry Potter y la Piedra Filosofal', '1997-06-26', true, 'J.K.', 'Rowling', 'C34567890');


-- Inserción de datos en la tabla Comics --
INSERT INTO Comics (pkfkPublicacion, color) VALUES
('9781234567897', true),
('9782345678908', false);

-- Inserción de datos en la tabla Ebooks --
INSERT INTO Ebooks (pkfkPublicacion, formato) VALUES
('9783456789019', 'EPUB');

-- Inserción de datos en la tabla Listas --
INSERT INTO Listas (nombre, pkfkCuenta, estado) VALUES
('Favoritos', 1, true),
('Por Leer', 2, true),
('Leídos', 3, true);

-- Inserción de datos en la tabla Resennas --
INSERT INTO Resennas (pkfkCuenta, pkfkPublicacion, opinion, puntuacion, estado) VALUES
(1, '9781234567897', 'Excelente libro', 5, true),
(2, '9782345678908', 'Muy bueno', 4, true),
(3, '9783456789019', 'Fascinante', 5, true);

-- Inserción de datos en la tabla ListaContienePublicaciones --
INSERT INTO ListaContienePublicaciones (pkfkPublicacion, pkfkNombreLista, pkfkCuentaLista) VALUES
('9781234567897', 'Favoritos', 1),
('9782345678908', 'Por Leer', 2),
('9783456789019', 'Leídos', 3);

-- Inserción de datos en la tabla Megusta --
INSERT INTO Megusta (pkfkCuenta, pkfkNombreLista, pkfkCuentaLista) VALUES
(1, 'Favoritos', 1),
(2, 'Por Leer', 2),
(3, 'Leídos', 3);

-- Inserción de datos en la tabla CuentaSigueCuenta --
INSERT INTO CuentaSigueCuenta (idSeguido, idSeguidor, fecha) VALUES
(1, 2, '2023-01-01'),
(2, 3, '2023-02-01'),
(3, 1, '2023-03-01');

-- Inserción de datos en la tabla CuentaLeePublicacion --
INSERT INTO CuentaLeePublicacion (pkfkCuenta, pkfkPublicacion, porcentaje) VALUES
(1, '9781234567897', 100),
(2, '9782345678908', 50),
(3, '9783456789019', 75);

