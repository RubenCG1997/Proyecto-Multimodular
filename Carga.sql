USE ProyectoFinal;

-- Inserción de datos en la tabla Usuarios --

INSERT INTO Usuarios (dni, nombre, apellidos, email, contrasenna, fecha_de_nacimiento, rol, estado)
VALUES 
('555555555', 'Pedro', 'Sánchez', 'pedro@example.com', 'contraseña123', '1993-08-25', false, true),
('666666666', 'Laura', 'González', 'laura@example.com', 'contraseña456', '1995-06-10', false, true),
('777777777', 'Manuel', 'Díaz', 'manuel@example.com', 'contraseña789', '1987-03-17', false, true),
('888888888', 'Sara', 'Martínez', 'sara@example.com', 'contraseñaABC', '1990-11-20', false, true),
('999999999', 'Marta', 'Fernández', 'marta@example.com', 'contraseñaDEF', '1980-09-12', false, true),
('101010101', 'David', 'López', 'david@example.com', 'contraseñaGHI', '1983-04-30', false, true),
('121212121', 'Eva', 'Torres', 'eva@example.com', 'contraseñaJKL', '1998-12-05', false, true),
('131313131', 'Alejandro', 'Ruiz', 'alejandro@example.com', 'contraseñaMNO', '1982-07-15', false, true),
('141414141', 'Carmen', 'Sánchez', 'carmen@example.com', 'contraseñaPQR', '1976-02-28', false, true),
('151515151', 'Paula', 'Gómez', 'paula@example.com', 'contraseñaSTU', '1996-10-08', false, true),
('161616161', 'María', 'López', 'maria@example.com', 'contraseña123', '1993-08-25', true, true),
('171717171', 'Javier', 'García', 'javier@example.com', 'contraseña456', '1995-06-10', true, true),
('181818181', 'Sofía', 'Martínez', 'sofia@example.com', 'contraseña789', '1987-03-17', true, true);

-- Inserción de suscripciones --
INSERT INTO Suscripciones (tipo, cuota, precio)
VALUES 
('INDIVIDUAL', 'MENSUAL', 9.99),
('DUO', 'MENSUAL', 14.99),
('FAMILIAR', 'MENSUAL', 19.99),
('INDIVIDUAL', 'ANUAL', 99.99),
('DUO', 'ANUAL', 149.99),
('FAMILIAR', 'ANUAL', 199.99),
('INDIVIDUAL', 'MENSUAL', 12.99),
('DUO', 'MENSUAL', 17.99),
('FAMILIAR', 'MENSUAL', 22.99),
('INDIVIDUAL', 'ANUAL', 119.99);

-- Inserciones de cuentas --
INSERT INTO Cuentas (username, fecha_de_creacion, fkUsuario, fkSuscripciones) VALUES
('pedro_sanchez', '2024-05-29', '555555555', 1),
('laura_gonzalez', '2024-05-29', '666666666', 2),
('manuel_diaz', '2024-05-29', '777777777', 3),
('sara_martinez', '2024-05-29', '888888888', 4),
('marta_fernandez', '2024-05-29', '999999999', 5),
('david_lopez', '2024-05-29', '101010101', 6),
('eva_torres', '2024-05-29', '121212121', 7),
('alejandro_ruiz', '2024-05-29', '131313131', 8),
('carmen_sanchez', '2024-05-29', '141414141', 9),
('paula_gomez', '2024-05-29', '151515151', 10);
-- Inserciones de autores de novelas y mangas --

INSERT INTO Autores (nombre, apellidos, fecha_de_nacimiento, biografía, estado)
VALUES 
('J.K.', 'Rowling', '1965-07-31', 'Escritora británica conocida por la serie de Harry Potter', true), 
('Stephen', 'King', '1947-09-21', 'Escritor estadounidense famoso por sus novelas de terror', true), 
('George', 'R.R. Martin', '1948-09-20', 'Escritor estadounidense conocido por la serie de novelas "Canción de hielo y fuego"', true), 
('Haruki', 'Murakami', '1949-01-12', 'Escritor japonés reconocido por sus novelas surrealistas y misteriosas', true), 
('Masashi', 'Kishimoto', '1974-11-08', 'Mangaka japonés creador del manga y anime Naruto', true), 
('Eiichiro', 'Oda', '1975-01-01', 'Mangaka japonés famoso por crear el manga One Piece', true), 
('Akira', 'Toriyama', '1955-04-05', 'Mangaka japonés famoso por crear el manga Dragon Ball', true), 
('Gabriel', 'García Márquez', '1927-03-06', 'Escritor y premio Nobel colombiano conocido por "Cien años de soledad"', true);

-- Inserciones de editoriales --

INSERT INTO Editoriales (cif, nombre, direccion, telefono, cp, estado)
VALUES 
('123456789', 'Planeta', 'Calle Mayor 123', '123456789', '28001', true),
('987654321', 'Norma Editorial', 'Avenida Diagonal 456', '987654321', '08006', true),
('111111111', 'Shueisha', 'Tokyo 789', '111111111', '18101', true),
('222222222', 'Kodansha', 'Tokyo 456', '222222222', '10150', true),
('333333333', 'Shogakukan', 'Tokyo 123', '333333333', '10101', true),
('444444444', 'VIZ Media', 'San Francisco 789', '444444444', '94107', true);

-- Inserciones de publicaciones --

INSERT INTO Publicaciones (isbn, titulo, fecha_de_lanzamiento, fkAutorNombre, fkAutorApellidos, fkEditorial)
VALUES 
('9788499898759', 'Cien años de soledad', '1967-05-30', 'Gabriel', 'García Márquez', '123456789'),
('9780545582889', 'Harry Potter y la piedra filosofal', '1997-06-26', 'J.K.', 'Rowling', '987654321'),
('9780307743657', 'It', '1986-09-15', 'Stephen', 'King', '444444444'),
('9780143039446', 'Kafka en la orilla', '2002-09-12', 'Haruki', 'Murakami', '111111111'),
('9780451524935', 'El resplandor', '1977-01-28', 'Stephen', 'King', '444444444'),
('9788478884455', 'Harry Potter y el prisionero de Azkaban', '1999-07-08', 'J.K.', 'Rowling', '987654321'),
('9780804171009', 'One Piece: East Blue', '1997-12-24', 'Eiichiro', 'Oda', '987654321'),
('9788478884453', 'Harry Potter y el prisionero de Azkaban', '1999-07-08', 'J.K.', 'Rowling', '987654321'),
('9780439064866', 'Harry Potter y la cámara secreta', '1998-06-02', 'J.K.', 'Rowling', '987654321'),
('9780439554930', 'Harry Potter y el cáliz de fuego', '2000-07-08', 'J.K.', 'Rowling', '987654321'),
('9780439784542', 'Harry Potter y la orden del fénix', '2003-06-21', 'J.K.', 'Rowling', '987654321'),
('9788498382672', 'Harry Potter y la piedra filosofal', '1998-07-02', 'J.K.', 'Rowling', '987654321'),
('9784088727821', 'Dragon Ball', '1985-12-03', 'Akira', 'Toriyama', '123456789'),
('9784088727838', 'Dragon Ball Z', '1989-02-25', 'Akira', 'Toriyama', '123456789'),
('9784088727845', 'Dragon Ball GT', '1996-04-01', 'Akira', 'Toriyama', '123456789'),
('9784088727852', 'Dragon Ball Super', '2015-06-20', 'Akira', 'Toriyama', '123456789'),
('9784088715927', 'One Piece: Romance Dawn', '1997-07-19', 'Eiichiro', 'Oda', '987654321'),
('9784088716078', 'One Piece: Loguetown', '1999-02-17', 'Eiichiro', 'Oda', '987654321'),
('9784088716245', 'One Piece: Baroque Works', '2000-09-13', 'Eiichiro', 'Oda', '987654321'),
('9784088716405', 'One Piece: Skypiea', '2002-04-01', 'Eiichiro', 'Oda', '987654321'),
('9784088736303', 'One Piece: Water 7', '2006-06-12', 'Eiichiro', 'Oda', '987654321'),
('9784088736334', 'One Piece: Enies Lobby', '2006-11-08', 'Eiichiro', 'Oda', '987654321');


-- Inserciones directas en la tabla Comics para las publicaciones japonesas --
INSERT INTO Comics (pkfkPublicacion, color)
VALUES 
('9784088727821', true), -- Dragon Ball
('9784088727838', true), -- Dragon Ball Z
('9784088727845', true), -- Dragon Ball GT
('9784088727852', true), -- Dragon Ball Super
('9784088715927', true), -- One Piece: Romance Dawn
('9784088716078', true), -- One Piece: Loguetown
('9784088716245', true), -- One Piece: Baroque Works
('9784088716405', true), -- One Piece: Skypiea
('9784088736303', true), -- One Piece: Water 7
('9784088736334', true); -- One Piece: Enies Lobby

-- Inserciones directas en la tabla Ebooks para las publicaciones no japonesas --
INSERT INTO Ebooks (pkfkPublicacion, formato)
VALUES 
('9788499898759', 'PDF'), -- Cien años de soledad
('9780545582889', 'PDF'), -- Harry Potter y la piedra filosofal
('9780307743657', 'PDF'), -- It
('9780143039446', 'PDF'), -- Kafka en la orilla
('9780451524935', 'PDF'), -- El resplandor
('9788478884455', 'PDF'), -- Harry Potter y el prisionero de Azkaban
('9780804171009', 'PDF'), -- One Piece: East Blue
('9780439064866', 'PDF'), -- Harry Potter y la cámara secreta
('9780439554930', 'PDF'), -- Harry Potter y el cáliz de fuego
('9780439784542', 'PDF'), -- Harry Potter y la orden del fénix
('9788498382672', 'PDF'); -- Harry Potter y la piedra filosofal

-- Insert de listas --
INSERT INTO Listas (nombre, pkfkCuenta, estado) 
VALUES
('Aventuras en el Reino de las Letras', 1, true),
('Mundos entre Páginas', 1, true),
('Travesías Literarias', 2, true),
('Sueños Encuadernados', 2, true),
('Viajes a Otros Universos', 3, true),
('Explorando la Imaginación', 3, true),
('Mundos de Papel', 4, true),
('Páginas de Aventura', 4, true),
('Senderos Literarios', 5, true),
('Historias que Inspiran', 5, true),
('Rutas Literarias', 6, true),
('Mares de Palabras', 6, true),
('Aventuras Encuadernadas', 7, true),
('Exploraciones en la Biblioteca', 7, true),
('Viajes a lo Desconocido', 8, true),
('Maratón Literario', 8, true),
('Travesías entre Títulos', 9, true),
('Mundos por Descubrir', 9, true),
('Explorando Nuevos Horizontes', 10, true);

-- Insert de listaContienePublicaciones
INSERT INTO ListaContienePublicaciones(pkfkPublicacion,pkfkNombreLista,pkfkCuentaLista)
VALUES
('9788499898759','Aventuras en el Reino de las Letras', 1),
('9788498382672','Aventuras en el Reino de las Letras', 1),
('9788498382672','Mundos por Descubrir' , 9),
('9788499898759','Mundos por Descubrir', 9),
('9788499898759','Maratón Literario', 8),
('9788498382672','Maratón Literario' , 8);

-- Insert de Me gusta --
INSERT INTO Megusta (pkfkCuenta, pkfkNombreLista, pkfkCuentaLista)
VALUES
(2,  'Aventuras en el Reino de las Letras', 1),
(3,   'Aventuras en el Reino de las Letras', 1),
(10,  'Mundos por Descubrir', 9),
(4,  'Mundos por Descubrir', 9),
(5,  'Mundos por Descubrir', 9);

-- Insert de cuenta sigue cuenta
INSERT INTO CuentaSigueCuenta (idSeguido, idSeguidor, fecha)
VALUES
(1, 2, '2024-05-29'),
(1, 3, '2024-05-29'),
(1, 4, '2024-05-29'),
(1, 5, '2024-05-29'),
(1, 6, '2024-05-29'),
(1, 7, '2024-05-29'),
(1, 8, '2024-05-29'),
(1, 9, '2024-05-29'),
(1, 10, '2024-05-29'),
(2, 1, '2024-05-29'),
(2, 3, '2024-05-29'),
(2, 4, '2024-05-29'),
(2, 5, '2024-05-29'),
(2, 6, '2024-05-29'),
(2, 7, '2024-05-29'),
(2, 8, '2024-05-29'),
(2, 9, '2024-05-29'),
(2, 10, '2024-05-29'),
(3, 1, '2024-05-29'),
(3, 2, '2024-05-29'),
(3, 4, '2024-05-29'),
(3, 5, '2024-05-29'),
(3, 6, '2024-05-29'),
(3, 7, '2024-05-29'),
(3, 8, '2024-05-29'),
(3, 9, '2024-05-29'),
(3, 10, '2024-05-29'),
(4, 1, '2024-05-29'),
(4, 2, '2024-05-29'),
(4, 3, '2024-05-29'),
(4, 5, '2024-05-29'),
(4, 6, '2024-05-29'),
(4, 7, '2024-05-29'),
(4, 8, '2024-05-29'),
(4, 9, '2024-05-29'),
(4, 10, '2024-05-29'),
(5, 1, '2024-05-29'),
(5, 2, '2024-05-29'),
(5, 3, '2024-05-29'),
(5, 4, '2024-05-29'),
(5, 6, '2024-05-29'),
(5, 7, '2024-05-29'),
(5, 8, '2024-05-29'),
(5, 9, '2024-05-29'),
(5, 10, '2024-05-29');

-- Insercion de reseñas --
INSERT INTO Resennas (pkfkCuenta, pkfkPublicacion, opinion, puntuacion, estado)
VALUES 
(1, '9788499898759', 'Excelente novela clásica, una obra maestra', 5, true),
(2, '9788499898759', 'Excelente novela clásica, una bella obra maestra', 5, true),
(3, '9788499898759', 'Excelente novela clásica, una bella obra magistral', 5, true),
(1, '9780545582889', 'El libro que me inició en el mundo mágico, me encantó', 5, true),
(1, '9784088727838', 'La secuela de Dragon Ball, con nuevos personajes y desafíos', 4, true),
(1, '9784088727845', 'La última serie de Dragon Ball, con una historia nostálgica', 4, true),
(2, '9780307743657', 'Un libro de terror que te mantiene en vilo hasta el final', 4, true),
(4, '9780143039446', 'Una novela surrealista y fascinante', 5, true),
(5, '9780451524935', 'Un clásico del terror que nunca pasa de moda', 4, true),
(6, '9788478884455', 'La segunda aventura de Harry Potter, llena de acción y misterio', 5, true);

