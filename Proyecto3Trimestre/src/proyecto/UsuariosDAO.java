package proyecto;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class UsuariosDAO {

    private Connection conexion;
    private final String USUARIO = "palProyecto";
    private final String PASSWORD = "123456789";
    private final String MAQUINA = "localhost";
    private final String BD = "proyectofinal";
     
	public UsuariosDAO() {
		conexion = conectar();
	}
    // Establece una conexión con el SGBD y la devuelve
    private Connection conectar() {
        Connection con = null;
        String url = "jdbc:mysql://" + MAQUINA + "/" + BD;

        try {
            con = DriverManager.getConnection(url, USUARIO, PASSWORD);
            
        } catch (SQLException ex) {
            System.out.println("Error al conectar a la base de datos");
           
        }

        return con;
    }
    
    // Inserta un usuario en la tabla Usuarios
    public void create(Usuarios usuario) {
        if (usuario != null) {
            String sql = "INSERT INTO Usuarios (dni, nombre, apellidos, email, contrasenna, fecha_de_nacimiento, rol, estado )"
            		+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

            try {
                PreparedStatement sentencia = conexion.prepareStatement(sql);
                sentencia.setString(1, usuario.getDni());
                sentencia.setString(2, usuario.getNombre());
                sentencia.setString(3, usuario.getApellidos());
                sentencia.setString(4, usuario.getEmail());
                sentencia.setString(5, usuario.getContrasenna());
                sentencia.setDate(6, Date.valueOf(usuario.getFecha_de_nacimiento()));
                sentencia.setBoolean(7, usuario.isRol());
                sentencia.setBoolean(8, usuario.isEstado());
                sentencia.executeUpdate();
                
                if(usuario.isRol()==true) {
                	System.out.println("Administrador creado correctamente");
                }
                else {
                	System.out.println("Usuario creado correctamente");
                }
            } catch (SQLException ex) {
                System.out.println("Error al crear el usuario");
                
            }
        } 
    }
    
    //Muestra alumno de la tabla alumnos
    public Usuarios read(String dni) {
    	
    	Usuarios usuario = null;
    	String sql = "SELECT * FROM Usuarios WHERE dni=?";
    	
    	try {
    			PreparedStatement sentencia = conexion.prepareStatement(sql);
    			sentencia.setString(1, dni);
    			ResultSet rs = sentencia.executeQuery();
    			if (rs.next()) {
    				
    					String nombre = rs.getString("nombre");
    					String apellidos = rs.getString("apellidos");
    					String email = rs.getString("email");
    					String contrasenna = rs.getString("contrasenna");
    					String fecha_de_nacimiento = rs.getString("fecha_de_nacimiento");
    					boolean estado = rs.getBoolean("estado");
    					boolean rol = rs.getBoolean("rol");
    					
    					usuario = new Usuarios(dni, nombre, apellidos, email, contrasenna, fecha_de_nacimiento, rol, estado);
    				}
    		} catch (SQLException ex) {
    			System.out.println("Error al consultar un usuario");
    		}
    		return usuario;
    	}
    
    //Modifica usuario de la tabla usuario
    public void update(Usuarios usuario) {
    	if(usuario != null) {
    		String sql = "UPDATE Usuarios SET nombre=?, apellidos=?, email=?, contrasenna=?, fecha_de_nacimiento=?, estado=?, rol=?"
    				+ " WHERE dni=?";
    		try {
        		PreparedStatement sentencia = conexion.prepareStatement(sql);
        		sentencia.setString(1, usuario.getNombre());
        		sentencia.setString(2, usuario.getApellidos());
        		sentencia.setString(3, usuario.getEmail());
        		sentencia.setString(4, usuario.getContrasenna());
        		sentencia.setDate(5, Date.valueOf(usuario.getFecha_de_nacimiento()));
        		sentencia.setBoolean(6, usuario.isEstado());
        		sentencia.setBoolean(7, usuario.isRol());
        		sentencia.setString(8, usuario.getDni());
        		sentencia.executeUpdate();
        	}
    		catch(SQLException ex) {
    			System.out.println("El usuario que se quiere modificar tiene datos en comun con otro, no se puede cambiar");
    		}
    	}
    	
    }
    //Borra usuario de la tabla usuario
    public void delete (String dni) {

    	String sql = "DELETE FROM Usuarios WHERE dni =?";
    	try {
    		PreparedStatement sentencia = conexion.prepareStatement(sql);
    		sentencia.setString(1, dni);
    		sentencia.executeUpdate();
    	}
    	catch(SQLException ex) {
    		System.out.println("Error al eliminar al usuario");
    	}
    }
   
    //Crear Array con los datos del dni y usuario 
    private ArrayList<Usuarios>listaDniEmail(String dni, String email){
    	
    	ArrayList<Usuarios>listaCompara = new ArrayList<>();
    	Usuarios usuario = null;
    	String sql = "Select * FROM Usuarios WHERE dni=? OR email=?";
    	
    	try {
    		 PreparedStatement sentencia = conexion.prepareStatement(sql);
             sentencia.setString(1, dni);
             sentencia.setString(2, email);
             
             ResultSet rs = sentencia.executeQuery(); 
    		
    		while(rs.next()) {

    			String id = rs.getString("dni");
    			String nombre = rs.getString("nombre");
    			String apellidos = rs.getString("apellidos");
    			String correo = rs.getString("email");
    			String contrasenna = rs.getString("contrasenna");
				String fNacimiento = rs.getString("fecha_de_nacimiento");
				boolean rol = rs.getBoolean("rol");
				boolean estado = rs.getBoolean("estado");
				
				usuario = new Usuarios(id, nombre, apellidos, correo, contrasenna,fNacimiento,rol,estado);
				listaCompara.add(usuario);
    		}
    	}
    	catch(SQLException ex) {
    		System.out.println("Error al crear la lista");
    	}
    	
		return listaCompara;
    }
    
    //Comprueba si el usuario existe en la lista creada pasando el dni o email
    public boolean UsuarioExiste(String dni,String email) {
    	
    	ArrayList<Usuarios> lista = listaDniEmail(dni,email);
    	boolean existe = false;
    	for (Usuarios usuarios : lista) {
			
    		if(usuarios.getDni().equals(dni)|| usuarios.getEmail().equals(email)) {
    			existe = true;
    		}
    		
		}
    	
		return existe;
    	
    }
    
    //Devuelve usuario si existe pasandole el email y la contraseña
    public Usuarios inicioSesion(String email,String contrasenna) {
    	Usuarios usuario = null;
    	String sql = "SELECT * FROM USUARIOS WHERE email=? AND contrasenna=?";
    	try {
    		PreparedStatement sentencia = conexion.prepareStatement(sql);
    		sentencia.setString(1, email);
    		sentencia.setString(2,contrasenna);
			ResultSet rs = sentencia.executeQuery();
			if(rs.next()) {
				String dni = rs.getString("dni");
				String nombre = rs.getString("nombre");
				String apellidos = rs.getString("apellidos");
				String fecha_de_nacimiento = rs.getString("fecha_de_nacimiento");
				boolean estado = rs.getBoolean("estado");
				boolean rol = rs.getBoolean("rol");
				usuario = new Usuarios(dni,nombre,apellidos,email,contrasenna,fecha_de_nacimiento,rol,estado);
			}
    	}
    	catch(SQLException ex) {
    		System.out.println("No se encontró ningún usuario");
    	}
    	return usuario;
    }


 
}
