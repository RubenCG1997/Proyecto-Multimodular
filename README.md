# Proyecto-Multimodular

Biblioteca Online inspirada en Spotify.

## Objetivos

El proyecto consiste en desarrollar una aplicación que permite a los usuarios interactuar con publicaciones y entre ellos. Los usuarios se diferencian según su rol. Si el rol es de administrador, este se encarga del mantenimiento de la aplicación, mientras que el usuario normal tiene las funciones mencionadas anteriormente.

## Funcionalidades

### Menú principal:
- Crear usuario y/o cuenta
- Iniciar sesión
- Salir

### Menú de Usuario:
- Interactuar con las publicaciones
- Interactuar con otras cuentas
- Gestionar listas
- Administrar la cuenta
- Salir

### Menú de Administrador:
- CRUD de autores, editoriales y publicaciones
- Gestionar información de suscripciones
- Salir

## Clases y Definiciones

- **Usuario**: Acceso a la base de datos mediante clase DAO con el mismo nombre, además de diferentes comprobaciones para poder crear un Usuario que se irán especificando en futuras entregas.
- **Suscripciones**: Acceso a la base de datos mediante clase DAO con el mismo nombre, con comprobaciones para la creación de suscripciones.
- **Cuenta**: Acceso a la base de datos mediante clase DAO con el mismo nombre, con comprobaciones para la creación de cuentas.
- **CuentaSigueCuenta**: Acceso a la base de datos mediante clase DAO con el mismo nombre, con comprobaciones para la creación de relaciones entre cuentas.
- **Megusta**: Acceso a la base de datos mediante clase DAO con el mismo nombre, con comprobaciones para la creación de "me gusta".
- **Lista**: Acceso a la base de datos mediante clase DAO con el mismo nombre, con comprobaciones para la creación de listas.
- **CuentaLeePublicacion**: Acceso a la base de datos mediante clase DAO con el mismo nombre, con comprobaciones para la creación de registros de lectura.
- **ListaContienePublicaciones**: Acceso a la base de datos mediante clase DAO con el mismo nombre, con comprobaciones para la creación de listas que contienen publicaciones.
- **Reseñas**: Acceso a la base de datos mediante clase DAO con el mismo nombre, con comprobaciones para la creación de reseñas.
- **Autores**: Acceso a la base de datos mediante clase DAO con el mismo nombre, con comprobaciones para la creación de autores.
- **Publicaciones**: No tiene DAO.
- **Editoriales**: Acceso a la base de datos mediante clase DAO con el mismo nombre, con comprobaciones para la creación de editoriales.
- **Comics**: Acceso a la base de datos mediante clase DAO con el mismo nombre, con comprobaciones para la creación de cómics.
- **Ebooks**: Acceso a la base de datos mediante clase DAO con el mismo nombre, con comprobaciones para la creación de ebooks.
