package proyecto;

import java.util.Scanner;

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
						bd.create(usuario);
						if(!usuario.isRol()) {
							System.out.println("Creando Cuenta");
							System.out.println("==============");
						}
					}

				break;
				case 2:
					System.out.println("Inicio de sesión");
					System.out.println("================");
					
					
					
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
	
	/*public static Cuentas crearCuentas(String dni) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Introduce el username");
		String username = sc.nextLine();
		infoSuscripciones();
		
		
	}*/
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
	
	/*private TIPO tipo(int eleccion) {
		
		
		
	}*/
}
