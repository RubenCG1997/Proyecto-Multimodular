package proyecto;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import proyecto.Suscripciones.CUOTA;
import proyecto.Suscripciones.TIPO;

public class APP {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int eleccion;
		do {
			UsuariosDAO bd = new UsuariosDAO();
			 eleccion = MenuPrincipal();
			switch(eleccion) {
				case 1:
					System.out.println("Creando Usuario");
					System.out.println("===============");
					Usuarios usuario = crearUsuario();
					
					if(bd.UsuarioExiste(usuario.getDni(), usuario.getEmail())){
						System.out.println("El correo o dni ya existe, no se puede crear el usuario");
					}
					else {
						if(!usuario.isRol()) {
							System.out.println("Creando Cuenta");
							System.out.println("==============");
							CuentasDAO bdCuenta = new CuentasDAO();
							Cuentas cuenta = crearCuentas(usuario.getDni());
							bd.create(usuario);
							bdCuenta.create(cuenta);
						}
					}

				break;
				case 2: 
					System.out.println("Inicio de sesión");
					System.out.println("================");
					Usuarios usuarioConectado = introduceDatos();
					if(usuarioConectado == null) {
						System.out.println("Email o contraseña incorrecta");
					}
					else {
						if(usuarioConectado.isRol()) {
							int elecMenuAdmin;
							do {
								elecMenuAdmin = MenuAdministrador();
								switch(elecMenuAdmin) {
								case 1:
									int elecMenuAutor;
									 Autores autor;
									do {
										AutoresDAO bdAutor = new AutoresDAO();
										elecMenuAutor = MenuCrudAutores();
										switch(elecMenuAutor) {
										case 1:
											  autor = crearAutor();
											  bdAutor.create(autor); 
										break;
										case 2:
											  ArrayList<Autores>lista = new ArrayList<>();
											  lista = bdAutor.read(buscadorNombre(), buscadorApellidos());
											  if(!lista.isEmpty()) {
												 System.out.println(lista.toString()); 
											  }
										break;
										case 3:
											  autor = elecAutor(bdAutor.read(buscadorNombre(), buscadorApellidos()));
											  if(autor != null) {
													 System.out.println(autor.toString()); 
													 bdAutor.update(modiAutor(autor));
												 }
										break;
										case 4:
											  autor = elecAutor(bdAutor.read(buscadorNombre(), buscadorApellidos()));
											  if(autor!=null) {
												  System.out.println("Eliminando Autor ");
												  System.out.println(autor.toString());
												  bdAutor.delete(autor.getNombre(), autor.getApellidos());
											  }
										break;
										case 5:System.out.println("Volviendo al menú de administrador");break;
										default:System.out.println("Opción incorrecta");
										}
									}
									while(elecMenuAutor!=5);
								break;
								case 2:
									int elecMenuEdi;
									Editoriales editorial;
									do {
										EditorialesDAO bdEditorial = new EditorialesDAO();
										elecMenuEdi = MenuCrudEditoriales();
										switch(elecMenuEdi) {
										case 1:
											  editorial = creaEditorial();
											  bdEditorial.create(editorial);
										break;
										case 2:
											  ArrayList<Editoriales>lista = new ArrayList<>();
											  lista = bdEditorial.read(buscadorCif());
											  if(!lista.isEmpty()) {
												 System.out.println(lista.toString()); 
											  }
										break;
										case 3:
											 editorial = elecEditorial(bdEditorial.read(buscadorCif()));
											 if(editorial!=null) {
												 System.out.println(editorial.toString());
												 bdEditorial.update(modiEditorial(editorial));
											 } 
										break;
										case 4:
											 editorial = elecEditorial(bdEditorial.read(buscadorCif()));
											 if(editorial!=null) {
												 System.out.println("Eliminando editorial");
												 System.out.println(editorial.toString());
												 bdEditorial.delete(editorial.getCif());
											 }
										break;
										case 5:System.out.println("Volviendo al menú de administrador");break;
										default:System.out.println("Elección incorrecta");
										}
									}
									while(elecMenuEdi!=5);
								break;
								case 3:
									int elecMenuPubli;
									ComicsDAO bdComic = new ComicsDAO();
									EbooksDAO bdEbook = new EbooksDAO();
									do {
										
										elecMenuPubli = MenuCrudPublicaciones();
										Publicaciones publicacion;
										switch(elecMenuPubli) {
										case 1:
											publicacion = creaPublicacion();
											if(publicacion instanceof Comics) {
												bdComic.create((Comics) publicacion);
											}
											else {
												bdEbook.create((Ebooks) publicacion);
										}
										break;
										case 2:
											ArrayList<Publicaciones>lista= new ArrayList<>();
											String isbn = buscaIsbn();
											lista = bdComic.read(isbn,lista);
											lista =	bdEbook.read(isbn,lista);
											if(lista!=null) {
												System.out.println(lista.toString());
											}
											else {
												System.out.println("No se encontraron publicaciones");
											}
										break;
										case 3:
											 ArrayList<Publicaciones>listaMod= new ArrayList<>();
											 String isbnMod = buscaIsbn();
											 lista = bdComic.read(isbnMod,listaMod);
											 lista = bdEbook.read(isbnMod,listaMod);
											 Publicaciones publicacionSeleccionada = elecPublicaciones(listaMod);
											 if(publicacionSeleccionada instanceof Comics) {
												 Comics modificado = (Comics) modiPublicaciones(publicacionSeleccionada);
												 bdComic.update((Comics)modificado);
												 
											 }
											 else {
												 Ebooks modificado = (Ebooks) modiPublicaciones(publicacionSeleccionada);
												 bdEbook.update((Ebooks)modificado);
											 }
										break;
										case 4:
											 ArrayList<Publicaciones>listaBorr= new ArrayList<>();
											 String isbnBorr = buscaIsbn();
											 lista = bdComic.read(isbnBorr,listaBorr);
											 lista = bdEbook.read(isbnBorr,listaBorr);
											 Publicaciones publicacionSeleccionadaBorrar = elecPublicaciones(listaBorr);
											 if(publicacionSeleccionadaBorrar!=null) {
												 if(publicacionSeleccionadaBorrar instanceof Comics) {
													 System.out.println("Comic eliminado");
													 bdComic.delete(publicacionSeleccionadaBorrar.getIsbn()); 
												 }
												 else {
													 System.out.println("Ebook eliminado");
													 bdEbook.delete(publicacionSeleccionadaBorrar.getIsbn());
												 }
											 }
											
										break;
										case 5:System.out.println("Volviendo al menú de administrador");break;
										default:System.out.println("Elección incorrecta");
										}
									}
									while(elecMenuPubli!=5);
								break;
								case 4:
									SuscripcionesDAO bdSuscripciones = new SuscripcionesDAO();
									int elecSuscrip;
									do {
										elecSuscrip = datosSuscripciones();
										switch(elecSuscrip) {
										case 1:
											System.out.println("Total de suscripciones");
											bdSuscripciones.infoTotal().stream()
											.sorted(Comparator.comparing(array->array[0]))
										    .map(array -> String.join(" ", array))
										    .forEach(System.out::println);
										break;
										case 2:
											System.out.println("Ver cantidad de suscripciones por tipo-cuota");
											Map<String,Integer>listado = new HashMap<>();
											listado=bdSuscripciones.cantidadSuscripcionesPorTipoYCuota();
											System.out.println(listado.toString());
										break;
										case 3:System.out.println("Volviendo al menú del administrador"); break;
										default:System.out.println("Opcion incorrecta");
										}
									}
									while(elecSuscrip!=3);
								break;
								case 5:
									bd.update(modificar(usuarioConectado));
								break;
								case 6:System.out.println("Cerrando sesión");break;
								default:System.out.println("Opción incorrecta");break;
								}
							}while(elecMenuAdmin!=6);
							
						}
						else {
							int elecMenuUsu;
							do {
								elecMenuUsu = MenuUsuario();
								switch(elecMenuUsu) {
								case 1: break;
								case 2: break;
								case 3: break;
								case 4: break;
								case 5: System.out.println("Cerrando sesión");break;
								default:System.out.println("Opción incorrecta");break;
								}
							}while(elecMenuUsu!=5);
						}
					}
				break;
				case 3:
					System.out.println("Saliendo.......");
				break;
			}
				
		}
		while(eleccion!=3);
		
	}

	//Menu principal
	private static int MenuPrincipal() {
		
	    var sc = new Scanner(System.in);
	    int eleccion = 0;
	    
	        	System.out.println("Menu Principal");
				System.out.println("==============");
				System.out.println("1.Crear Usuario");
				System.out.println("2.Iniciar Sesión");
				System.out.println("3.Salir");
				System.out.println("==============");
				System.out.println("Introduce una opción: ");
	            eleccion = sc.nextInt();
	            
	            return eleccion;
	}
	//Menu de usuario
	private static int MenuUsuario() {
	
		int eleccion;
		Scanner sc = new Scanner(System.in);
		System.out.println("Menu Principal del Usuario");
		System.out.println("==============");
		System.out.println("1.Opciones con publicaciones");
		System.out.println("2.Interacción con otras cuentas");
		System.out.println("3.Opciones con listas");
		System.out.println("4.Administrar cuenta");
		System.out.println("5.Salir");
		System.out.println("==============");
		System.out.println("Introduce una opción: ");
        eleccion = sc.nextInt();
        
        return eleccion;
	}
	//Menu de administrador
	private static int MenuAdministrador() {
		
		int eleccion;
		Scanner sc = new Scanner(System.in);
		System.out.println("Menu Principal del Administrador");
		System.out.println("==============");
		System.out.println("1.CRUD Autores");
		System.out.println("2.CRUD Editoriales");
		System.out.println("3.CRUD Publicaciones");
		System.out.println("4.Información sobre las suscripciones");
		System.out.println("5.Modificar información de la cuent");
		System.out.println("6.Salir");
		System.out.println("==============");
		System.out.println("Introduce una opción: ");
        eleccion = sc.nextInt();
        
        return eleccion;
	}	
	//Crea usuario
	private static Usuarios crearUsuario() {
		
		Scanner sc = new Scanner(System.in);
		
		final boolean  estado = true;//Siempre que se crea un usuario el estado es activo
		final boolean admin = true;//El rol de un administrador es true
		final boolean usuNormal = false;// El rol de un usuario normal es false
		boolean rol = false;
		int eleccion;
		
		do {
			System.out.println("Pulsa 1 para usuario o pulsa 2 para administrador");
			eleccion = sc.nextInt();
			switch(eleccion) {
			case 1 : rol = usuNormal;break;
			case 2 : rol = admin;break;
			default: System.out.println("Error al introducir la elección");break;
			}
		}
		while(eleccion!=1 && eleccion!=2);
		sc.nextLine();//Limnpia buffer
		System.out.println("Introduce el dni");
		String dni = sc.nextLine();
		System.out.println("Introduce tu nombre");
		String nombre = sc.nextLine().toLowerCase();
		System.out.println("Introduce tus apellidos");
		String apellidos = sc.nextLine().toLowerCase();
		System.out.println("Introduce tu correo electrónico");
		String email = sc.nextLine().toLowerCase();
		System.out.println("Introduce una contraseña");
		String contrasenna = sc.nextLine();
		System.out.println("Introduce tu fecha de nacimiento siguiendo el siguiente formato: (aaaa-mm-dd)");
		String fNac = sc.nextLine();
		
		Usuarios usuario = new Usuarios(dni,nombre,apellidos,email,contrasenna,fNac,rol,estado);
		
		return usuario;
		
	}
	//Crea cuenta
	private static Cuentas crearCuentas(String dni) {
		
		SuscripcionesDAO bd = new SuscripcionesDAO();
		CuentasDAO bdCuenta = new CuentasDAO();
		
		Scanner sc = new Scanner(System.in);
		String username;
		do {
			System.out.println("Introduce el username");
			 username = sc.nextLine().toLowerCase();
		}
		while(bdCuenta.usernameExiste(username));
		
		infoSuscripciones();
		Suscripciones suscripcion = crearSuscripcion();
		int idSuscripcion = bd.obtenerID();
		
		Cuentas cuenta = new Cuentas(username,dni,idSuscripcion);
		return cuenta;
		
	}
	//Muestra la información de las suscripciones, la cuota y el precio
	private static void infoSuscripciones(){
		
			System.out.println("================================");
		 	System.out.println("Opciones de Tipo de Suscripción:");
	        System.out.println("1. Individual");
	        System.out.println("2. Duo");
	        System.out.println("3. Familiar");

	        System.out.println("\nOpciones de Cuota de Suscripción:");
	        System.out.println("1. Mensual");
	        System.out.println("2. Anual");

	        System.out.println("\nPrecios de Suscripción:");
	        System.out.println("Individual Mensual: 8.99");
	        System.out.println("Individual Anual: 89.99");
	        System.out.println("Duo Mensual: 14.99");
	        System.out.println("Duo Anual: 169.99");
	        System.out.println("Familiar Mensual: 17.99");
	        System.out.println("Familiar Anual: 199.99");
	        
	        System.out.println("================================");
		
	}
	//Crear suscripcion
	private static Suscripciones crearSuscripcion() {
		
		SuscripcionesDAO bd = new SuscripcionesDAO();
		TIPO tipo = tipo();
		CUOTA cuota = cuota();
		Suscripciones suscripcion = new Suscripciones(tipo,cuota);
		bd.create(suscripcion);
		return suscripcion;
		
	}
	//Elegir tipo de suscripción
	private static TIPO tipo () {
		
		Scanner sc = new Scanner(System.in);
		String elecTipo;
		TIPO tipo = null;
		do {
				System.out.println("Introduzca el tipo");
			 	elecTipo = sc.next().toUpperCase();
				switch(elecTipo) {
				case "INDIVIDUAL" :  tipo= TIPO.INDIVIDUAL ;break;
				case "DUO" :  tipo = TIPO.DUO;break;
				case "FAMILIAR" : tipo = TIPO.FAMILIAR; break;
				default : System.out.println("Tipo Incorrecto");break;
				}
		}
		while(!elecTipo.equals("INDIVIDUAL")&& !elecTipo.equals("DUO") && !elecTipo.equals("FAMILIAR"));
		
		return tipo;
	}	
	//Elegir una cuota de suscripción
	private static CUOTA cuota(){

		Scanner sc = new Scanner(System.in);
		String elecCuota;
		CUOTA cuota = null;
		do {
				System.out.println("Introduzca la cuota");
				elecCuota = sc.next().toUpperCase();
				switch(elecCuota) {
				case "MENSUAL" :  cuota= CUOTA.MENSUAL ;break;
				case "ANUAL" :  cuota = CUOTA.ANUAL;break;
				default : System.out.println("Tipo Incorrecto");break;
				}
		}
		while(!elecCuota.equals("MENSUAL")&& !elecCuota.equals("ANUAL"));
		
		return cuota;
	}
	//Introduce email y contrasenna y devuelve el usuario
	private static Usuarios introduceDatos() {
		UsuariosDAO bd = new UsuariosDAO();
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce tu correo electrónico");
		String email = sc.nextLine().toLowerCase();
		System.out.println("Introduce tu contraseña");
		String contraseña = sc.nextLine();
		Usuarios usuario = bd.inicioSesion(email, contraseña);
		return usuario;
	}
	//Menú CRUD de Autores
	private static int MenuCrudAutores() {
		
		int eleccion;
		Scanner sc = new Scanner(System.in);
		System.out.println("Menu Crud Autor");
		System.out.println("==============");
		System.out.println("1.Añadir autor");
		System.out.println("2.Mostrar información del autor");
		System.out.println("3.Modificar información del autor");
		System.out.println("4.Eliminar Autor");
		System.out.println("5.Salir");
		System.out.println("==============");
		System.out.println("Introduce una opción: ");
        eleccion = sc.nextInt();
        
        return eleccion;
	}
	//crea Autor
	private static Autores crearAutor() {
		
		final boolean estado = true; //Un autor siempre que se cree esta visible en la base de datos
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce el  nombre");
		String nombre = sc.nextLine().toLowerCase();
		System.out.println("Introduce el/los apellidos");
		String apellidos = sc.nextLine().toLowerCase();
		System.out.println("Introduce tu fecha de nacimiento siguiendo el siguiente formato: (aaaa-mm-dd)");
		String fNac = sc.nextLine();
		System.out.println("Introduce una biografía");
		String biografia = sc.nextLine().toLowerCase();
		
		Autores autor = new Autores(nombre,apellidos,fNac,biografia,estado);
		return autor;
	}
	//Buscador de nombre
	private static String buscadorNombre() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Buscador de autores");
		System.out.println("Introduce el nombre del autor");
		String nombre = sc.nextLine();
		return nombre;
	}
	//Buscador de apellidos
	private static String buscadorApellidos() {

		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce el/los apellidos del autor");
		String apellidos = sc.nextLine();
		return apellidos;
	}
	//Selecciona Autor de una lista
	private static Autores elecAutor(ArrayList<Autores>lista) {
		Scanner sc = new Scanner(System.in);
		
		Autores autor = null;
		if(lista.isEmpty()) {
			System.out.println("No se encontró ningún autor");
		}
		else {
			if(lista.size()==1) {
				autor = lista.get(0);
			}
			else {
				System.out.println("Se encontraron varios autores");
				System.out.println(lista.toString());
				int eleccion;
				do {
					System.out.println("Selecciona el autor entre 1 y "+lista.size());
					eleccion = sc.nextInt();
					if(eleccion<1 || eleccion>lista.size()) {
						System.out.println("elección incorrecta");
					}
					else {
						autor = lista.get(eleccion - 1);
					}
				}
				while(eleccion<1 || eleccion>lista.size());
			}
		}
		return autor;
		
	}
	//Modificar información de los autores
	private static Autores modiAutor(Autores autor) {
		
		Scanner sc = new Scanner(System.in);
		
		String biografia;
		boolean estado;	
		System.out.println("Modifica la biografía, si no pulsa enter");
		biografia=sc.nextLine();
		System.out.println("Modifica el estado, true o false");
		estado = sc.nextBoolean();

		if(biografia.isEmpty()) {
			biografia = autor.getBiografia();
		}
		  
	        autor.setBiografia(biografia);
	        autor.setEstado(estado);
		System.out.println(autor.toString());
		
		return autor;	
	}
	//Menú CRUD de Editoriales
	private static int MenuCrudEditoriales() {
		int eleccion;
		Scanner sc = new Scanner(System.in);
		System.out.println("Menu Crud Editoriales");
		System.out.println("==============");
		System.out.println("1.Añadir editorial");
		System.out.println("2.Mostrar información de la editorial");
		System.out.println("3.Modificar información de la editorial");
		System.out.println("4.Eliminar editorial");
		System.out.println("5.Salir");
		System.out.println("==============");
		System.out.println("Introduce una opción: ");
        eleccion = sc.nextInt();
        
        return eleccion;
	}
	//Crea Editorial
	private static Editoriales creaEditorial() {
		
		Scanner sc = new Scanner(System.in);
		
		final boolean estado = true; //Una editorial siempre que se cree esta visible en la base de datos
		System.out.println("Introduce el cif");
		String cif = sc.nextLine().toLowerCase();
		System.out.println("Introduce el nombre");
		String nombre =sc.nextLine().toLowerCase();
		System.out.println("Introduce la direccion");
		String direccion = sc.nextLine().toLowerCase();
		System.out.println("Introduce el teléfono");
		String telefono = sc.nextLine().toLowerCase();
		System.out.println("Introduce el código postal");
		String cp = sc.nextLine().toLowerCase();
		
		Editoriales editorial = new Editoriales(cif,nombre,direccion,telefono,cp,estado);
		return editorial;
	}
	//Buscador de cif
	private static String buscadorCif() {
		Scanner sc = new Scanner(System.in);{
			System.out.println("Introduce el cif");
			String cif = sc.nextLine().toLowerCase();
			return cif;
		}
	}
	//Selecciona editorial de una lista
	private static Editoriales elecEditorial(ArrayList<Editoriales>lista) {

		Scanner sc = new Scanner(System.in);
		
		Editoriales editorial = null;
		if(lista.isEmpty()) {
			System.out.println("No se encontró ninguna editorial");
		}
		else {
			if(lista.size()==1) {
				editorial = lista.get(0);
			}
			else {
				System.out.println("Se encontraron varias editoriales");
				System.out.println(lista.toString());
				int eleccion;
				do {
					System.out.println("Selecciona la editorial entre 1 y "+lista.size());
					eleccion = sc.nextInt();
					if(eleccion<1 || eleccion>lista.size()) {
						System.out.println("elección incorrecta");
					}
					else {
						editorial = lista.get(eleccion - 1);
					}
				}
				while(eleccion<1 || eleccion>lista.size());
			}
		}
		return editorial;
		
	}
	//Modificar información de una editorial
	private static Editoriales modiEditorial(Editoriales editorial) {
		
		Scanner sc = new Scanner(System.in);
		
		String nombre,direccion,telefono,cp;
		boolean estado;	
		System.out.println("Modifica el nombre, si no pulsa enter");
		nombre=sc.nextLine().toLowerCase();
		System.out.println("Modifica la direccion, si no pulsa enter");
		direccion=sc.nextLine().toLowerCase();
		System.out.println("Modifica el telefono,si no pulsa enter");
		telefono=sc.nextLine().toLowerCase();
		System.out.println("Modifica el cp, si no pulsa enter");
		cp=sc.nextLine().toLowerCase();
		System.out.println("Modifica el estado, pon true o false");
		estado = sc.nextBoolean();

		if(nombre.isEmpty()) {
			nombre = editorial.getNombre();
		}
		if(direccion.isEmpty()) {
			direccion = editorial.getDireccion();
		}
		if(telefono.isEmpty()) {
			telefono = editorial.getTelefono();
		}
		if(cp.isEmpty()) {
			cp = editorial.getCp();
		}
		
		editorial.setNombre(nombre);
		editorial.setDireccion(direccion);
		editorial.setTelefono(telefono);
		editorial.setCp(cp);
		editorial.setEstado(estado);
	 
		System.out.println(editorial.toString());
		
		return editorial;	
	}
	//Menú CRUD de publicaciones
	private static int MenuCrudPublicaciones() {

		int eleccion;
		Scanner sc = new Scanner(System.in);
		System.out.println("Menu Crud Publicaciones");
		System.out.println("==============");
		System.out.println("1.Añadir Publicacion");
		System.out.println("2.Mostrar información de la publicacion");
		System.out.println("3.Modificar información de la publicacion");
		System.out.println("4.Eliminar publicacion");
		System.out.println("5.Salir");
		System.out.println("==============");
		System.out.println("Introduce una opción: ");
        eleccion = sc.nextInt();
        
        return eleccion;
	}
	//crear publicacion
	private static Publicaciones creaPublicacion() {
		
		
		Scanner sc = new Scanner(System.in);
		
		
		String eleccion;
		do {
			System.out.println("¿qué desea añadir? (comic/ebook)");
			eleccion=sc.nextLine().toLowerCase().trim();
			if(!eleccion.equals("comic") && !eleccion.equals("ebook")) {
				System.out.println("Respuesta incorrecta");
			}
		}
		while(!eleccion.equals("comic") && !eleccion.equals("ebook"));
		
		System.out.println("Introduce el isbn");
		String isbn = sc.nextLine().toLowerCase();
		System.out.println("Introduc el titulo");
		String titulo = sc.nextLine().toLowerCase();
		System.out.println("Introduce la fecha de lanzamiento siguiendo el siguiente formato (aaaa-mm-dd)");
		String fLan = sc.nextLine();
		System.out.println("Introduce si esta publico o no (true/false");
		boolean estado = sc.nextBoolean();
		sc.nextLine();
		System.out.println("Introduce el nombre del autor");
		String nombre = sc.nextLine().toLowerCase();
		System.out.println("Introduce los apellidos dle autor");
		String apellidos = sc.nextLine().toLowerCase();
		System.out.println("Introduce el cif de la editorial");
		String cif = sc.nextLine().toLowerCase();
		
		if(eleccion.equals("comic")) {
			Comics comic ;
			System.out.println("Introduce si tiene color (true/false)");
			boolean color =sc.nextBoolean();
			comic = new Comics(isbn,titulo,fLan,estado,nombre,apellidos,cif,color);
			return comic;
			
		}
		else {
			Ebooks ebook;
		
			System.out.println("Introduce el formato del ebook");
			String formato = sc.nextLine().toLowerCase();
			ebook = new Ebooks(isbn,titulo,fLan,estado,nombre,apellidos,cif,formato);
			return ebook;
		}
		
		
	}
	//Buscar publicacion por isbn
	private static String buscaIsbn() {

		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce el isbn");
		String isbn = sc.nextLine().toLowerCase().trim();
		return isbn;
	}
	//Elegir publicacion
	private static Publicaciones elecPublicaciones(ArrayList<Publicaciones>lista) {

		Scanner sc = new Scanner(System.in);
		
		Publicaciones publicacion = null;
		if(lista.isEmpty()) {
			System.out.println("No se encontró ninguna publicacion");
		}
		else {
			if(lista.size()==1) {
				publicacion = lista.get(0);
			}
			else {
				System.out.println("Se encontraron varias publicacion");
				System.out.println(lista.toString());
				int eleccion;
				do {
					System.out.println("Selecciona la publicacion entre 1 y "+lista.size());
					eleccion = sc.nextInt();
					if(eleccion<1 || eleccion>lista.size()) {
						System.out.println("elección incorrecta");
					}
					else {
						publicacion = lista.get(eleccion - 1);
					}
				}
				while(eleccion<1 || eleccion>lista.size());
			}
			
			if(publicacion instanceof Comics) {
				Comics comic = (Comics) publicacion;
				return comic;
			}
			else {
				Ebooks ebook = (Ebooks) publicacion;
				return ebook;
			}
		}
		return publicacion;
		
	}
	//Modificar publicacion
	private static Publicaciones modiPublicaciones(Publicaciones publicacion) {
		Scanner sc = new Scanner(System.in);
		System.out.println(publicacion.toString());
		String titulo,nombreAutor,apellidoAutor,cif;
		boolean estado;	
		System.out.println("Modifica el titulo, si no pulsa enter");
		titulo=sc.nextLine().toLowerCase();
		System.out.println("Modifica el estado, pon true o false");
		estado = sc.nextBoolean();
		sc.nextLine();
		System.out.println("Modifica el nombre del autor, si no pulsa enter");
		nombreAutor=sc.nextLine().toLowerCase();
		System.out.println("Modifica los apellidos del autor, si no pulsa enter");
		apellidoAutor=sc.nextLine().toLowerCase();
		System.out.println("Modifica el cif de la editorial,si no pulsa enter");
		cif=sc.nextLine().toLowerCase();
		
		if(titulo.isEmpty()) {
			titulo = publicacion.getTitulo();
		}
		if(nombreAutor.isEmpty()) {
			nombreAutor = publicacion.getFkAutorNombre();
		}
		if(apellidoAutor.isEmpty()) {
			apellidoAutor = publicacion.getFkAutorApellidos();
		}
		if(cif.isEmpty()) {
			cif = publicacion.getFkEditorial();
		}
		
		if(publicacion instanceof Comics) {
			System.out.println("Modifica el color, pon true o false");
			boolean color = sc.nextBoolean();
			Comics comic = new Comics(publicacion.getIsbn(),titulo,publicacion.getFecha_de_lanzamiento(),estado,nombreAutor,apellidoAutor,cif, color);
			System.out.println(comic.toString());
			return comic;
		}
		else {
			System.out.println("Modifica el formato, si no pulsa enter");
			String formato = sc.nextLine().toLowerCase();
			if(formato.isEmpty()) {
				formato = ((Ebooks) publicacion).getFormato();
			}
			Ebooks ebook = new Ebooks(publicacion.getIsbn(),titulo,publicacion.getFecha_de_lanzamiento(),estado,nombreAutor,apellidoAutor,cif, formato);
			System.out.println(ebook.toString());
			return ebook;
			
		}
	}
	//Menu de información sobre las sucripciones
	private static int datosSuscripciones() {

		int eleccion;
		Scanner sc = new Scanner(System.in);
		System.out.println("Menu Datos Suscrpciones");
		System.out.println("==============");
		System.out.println("1.Mostrar listado completo");
		System.out.println("2.Mostrar según tipo y/o cuota");
		System.out.println("3.Salir");
		System.out.println("==============");
		System.out.println("Introduce una opción: ");
        eleccion = sc.nextInt();
        
        return eleccion;
				
	}
	//Modifica usuario
	private static Usuarios modificar(Usuarios usuario) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Modifica la cuenta");
		String nombre,apellidos,email,contrasenna;
		boolean estado;
		boolean rol;
		System.out.println("Modifica el nombre, si no pulsa enter");
		nombre=sc.nextLine().toLowerCase();
		System.out.println("Modifica los apellidos, si no pulsa enter");
		apellidos=sc.nextLine().toLowerCase();
		System.out.println("Modifica el telefono,si no pulsa enter");
		email=sc.nextLine().toLowerCase();
		System.out.println("Modifica la contrasenna, si no pulsa enter");
		contrasenna=sc.nextLine().toLowerCase();

		if(nombre.isEmpty()) {
			nombre = usuario.getNombre();
		}
		if(apellidos.isEmpty()) {
			apellidos = usuario.getApellidos();
		}
		if(email.isEmpty()) {
			email = usuario.getEmail();
		}
		if(contrasenna.isEmpty()) {
			contrasenna = usuario.getContrasenna();
		}
		
		usuario.setNombre(nombre);
		usuario.setApellidos(apellidos);
		usuario.setEmail(email);
		usuario.setContrasenna(contrasenna);
	 
		System.out.println(usuario.toString());	
	
		return usuario;
	}
}	
	

