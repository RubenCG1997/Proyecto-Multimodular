package proyecto;

import java.util.Scanner;

import proyecto.Suscripciones.CUOTA;
import proyecto.Suscripciones.TIPO;

public class APP {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int eleccion;
		do {
			 eleccion = MenuPrincipal();
			switch(eleccion) {
				case 1:
					System.out.println("Creando Usuario");
					System.out.println("===============");
					UsuariosDAO bd = new UsuariosDAO();
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
								case 1:break;
								case 2:break;
								case 3:break;
								case 4:break;
								case 5:break;
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
	
	private static int MenuAdministrador() {
		
		int eleccion;
		Scanner sc = new Scanner(System.in);
		System.out.println("Menu Principal del Administrador");
		System.out.println("==============");
		System.out.println("1.CRUD Autores");
		System.out.println("2.CRUD Editoriales");
		System.out.println("3.CRUD Publicaciones");
		System.out.println("4.Información sobre las suscripciones");
		System.out.println("5.Administrar cuenta");
		System.out.println("6.Salir");
		System.out.println("==============");
		System.out.println("Introduce una opción: ");
        eleccion = sc.nextInt();
        
        return eleccion;
	}
	
	//Crea usuario
	public static Usuarios crearUsuario() {
		
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
	public static Cuentas crearCuentas(String dni) {
		
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
	public static Usuarios introduceDatos() {
		UsuariosDAO bd = new UsuariosDAO();
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce tu correo electrónico");
		String email = sc.nextLine().toLowerCase();
		System.out.println("Introduce tu contraseña");
		String contraseña = sc.nextLine();
		Usuarios usuario = bd.inicioSesion(email, contraseña);
		return usuario;
	}
	

}
	
	

