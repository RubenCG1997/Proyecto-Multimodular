DROP DATABASE IF EXISTS ProyectoFinal;
CREATE DATABASE ProyectoFinal CHARACTER SET utf8mb4;
USE ProyectoFinal;

-- Creación de la tabla Usuarios --

CREATE TABLE Usuarios(
dni varchar(9) primary key,
nombre varchar(20) not null,
apellidos varchar(40) not null,
email varchar(40) not null unique,
contrasenna varchar(40) not null,
fecha_de_nacimiento date not null,
rol boolean not null default false,
estado boolean not null default true
);

-- Creación de la tabla Suscripciones

CREATE TABLE Suscripciones(
idSuscripción int primary key auto_increment,
tipo ENUM('INDIVIDUAL','DUO','FAMILIAR') NOT NULL,
cuota ENUM('MENSUAL','ANUAL') not null,
precio double not null
);

-- Creacion de la tabla Autores

CREATE TABLE Autores(
nombre varchar(20) not null,
apellidos varchar(40) not null,
fecha_de_nacimiento date not null,
biografía varchar(150),
estado boolean not null default true,
primary key(nombre,apellidos)
);

-- Creación de la tabla Editorial --

CREATE TABLE Editoriales(
cif varchar(9) primary key,
nombre varchar (30) unique not null,
direccion varchar(30) unique not null,
telefono varchar(9) unique not null,
cp varchar(5) not null,
estado boolean not null default true
);

-- Creación de la tabla Cuentas -- 

CREATE TABLE Cuentas(
idCuenta int primary key auto_increment,
username varchar(20) not null unique,
fecha_de_creacion date not null,
fkUsuario varchar(9) not null,
FOREIGN KEY(fkUsuario) REFERENCES Usuarios(dni)
ON DELETE CASCADE
ON UPDATE CASCADE,
fkSuscripciones int not null,
FOREIGN KEY(fkSuscripciones) REFERENCES Suscripciones(idSuscripción)
ON DELETE CASCADE
ON UPDATE CASCADE
);

-- Creación de la tabla Publicaciones --

CREATE TABLE Publicaciones(
isbn varchar(13) primary key,
titulo varchar(50) not null,
fecha_de_lanzamiento date not null,
estado boolean not null default true,
fkAutorNombre varchar(20) not null,
fkAutorApellidos varchar(40) not null,
FOREIGN KEY(fkAutorNombre,fkAutorApellidos) REFERENCES Autores(nombre,apellidos)
ON DELETE CASCADE
ON UPDATE CASCADE,
fkEditorial varchar(9)not null,
FOREIGN KEY(fkEditorial) REFERENCES Editoriales(cif)
ON DELETE CASCADE
ON UPDATE CASCADE
);

-- Creación de la tabla Comic --

CREATE TABLE Comics(
pkfkPublicacion varchar(13) primary key,
color  boolean not null default true,
FOREIGN KEY (pkfkPublicacion) REFERENCES Publicaciones(isbn)
ON DELETE CASCADE
ON UPDATE CASCADE
);

-- Creacion de la tabla ebooks

CREATE TABLE Ebooks(
pkfkPublicacion varchar(13) primary key,
formato varchar(20) not null,
FOREIGN KEY (pkfkPublicacion) REFERENCES Publicaciones(isbn)
ON DELETE CASCADE
ON UPDATE CASCADE
);

-- Creacion de la tabla Listas

CREATE TABLE Listas(
nombre varchar (60),
pkfkCuenta int,
estado boolean not null default true,
primary key(nombre,pkfkCuenta),
Foreign key (pkfkCuenta) REFERENCES Cuentas(idCuenta)
ON DELETE CASCADE
ON UPDATE CASCADE
);

-- Creacion de la reseñas

CREATE TABLE Resennas(
pkfkCuenta int,
pkfkPublicacion varchar(13),
opinion varchar(100),
puntuacion int not null check(puntuacion >= 1 And puntuacion <=5),
estado boolean not null default true,
primary key(pkfkCuenta,pkfkPublicacion),
Foreign key (pkfkCuenta) REFERENCES Cuentas(idCuenta)
ON DELETE CASCADE
ON UPDATE CASCADE,
Foreign key (pkfkPublicacion) REFERENCES Publicaciones(isbn)
ON DELETE CASCADE
ON UPDATE CASCADE
);

-- Creacion de la tabla ListaContienePublicaciones

CREATE TABLE ListaContienePublicaciones (
pkfkPublicacion varchar(13),
pkfkNombreLista varchar(50),
pkfkCuentaLista int,
PRIMARY KEY(pkfkPublicacion, pkfkNombreLista, pkfkCuentaLista),
FOREIGN KEY(pkfkPublicacion) REFERENCES Publicaciones(isbn)
ON DELETE CASCADE
ON UPDATE CASCADE,
FOREIGN KEY(pkfkNombreLista, pkfkCuentaLista) REFERENCES Listas(nombre, pkfkCuenta)
ON DELETE CASCADE
ON UPDATE CASCADE
);


-- Creación de la tabla Megusta

CREATE TABLE Megusta(
pkfkCuenta int,
pkfkNombreLista varchar(50),
pkfkCuentaLista int,
PRIMARY KEY(pkfkCuenta, pkfkNombreLista, pkfkCuentaLista),
FOREIGN KEY(pkfkCuenta) REFERENCES Cuentas(idCuenta),
FOREIGN KEY(pkfkNombreLista, pkfkCuentaLista) REFERENCES Listas(nombre, pkfkCuenta)
ON DELETE CASCADE
ON UPDATE CASCADE
);

-- Creación de la tabla CuentaSigueCuenta

CREATE TABLE CuentaSigueCuenta(
idSeguido int,
idSeguidor int,
fecha date,
primary key(idSeguido,idSeguidor),
foreign key (idSeguido) REFERENCES Cuentas(idCuenta)
ON DELETE CASCADE
ON UPDATE CASCADE,
foreign key (idSeguidor) REFERENCES Cuentas(idCuenta)
ON DELETE CASCADE
ON UPDATE CASCADE
);

-- Creación de la tabla CuentaLeePublicacion

CREATE TABLE CuentaLeePublicacion(
pkfkCuenta int,
pkfkPublicacion varchar(13),
porcentaje int unsigned not null check(porcentaje >=0 and porcentaje <= 100),
primary key(pkfkCuenta,pkfkPublicacion),
foreign key (pkfkCuenta) REFERENCES Cuentas(idCuenta)
ON DELETE CASCADE
ON UPDATE CASCADE,
foreign key (pkfkPublicacion) REFERENCES Publicaciones(isbn)
ON DELETE CASCADE
ON UPDATE CASCADE
);

select*from CuentaSigueCuenta;

