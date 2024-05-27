package proyecto;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AutoresDAO {
	
		private Connection conexion;
	    private final String USUARIO = "palProyecto";
	    private final String PASSWORD = "123456789";
	    private final String MAQUINA = "localhost";
	    private final String BD = "proyectofinal";
	    
		public AutoresDAO() {
			this.conexion = conectar();
		}
	    
	    private Connection conectar() {
	        Connection con = null;
	        String url = "jdbc:mysql://" + MAQUINA + "/" + BD;

	        try {
	            con = DriverManager.getConnection(url, USUARIO, PASSWORD);
	            System.out.println("Conexión exitosa a la base de datos");
	        } catch (SQLException ex) {
	            System.out.println("Error al conectar a la base de datos");
	           
	        }

	        return con;
	    }
	    
	    // Inserta una suscripcion en la tabla suscripciones
	    public void create(Autores autor) {
	        if (autor != null) {
	            String sql = "INSERT INTO Autores ( nombre, apellidos, fecha_de_nacimiento, biografía, estado )"
	            		+ " VALUES(?, ?, ?, ?, ?)";

	            try {
	                PreparedStatement sentencia = conexion.prepareStatement(sql);
	                sentencia.setString(1, autor.getNombre());
	                sentencia.setString(2, autor.getApellidos());
	                sentencia.setDate(3, Date.valueOf(autor.getFecha_de_nacimiento()));
	                sentencia.setString(4, autor.getBiografia());
	                sentencia.setBoolean(5, autor.isEstado());
	                sentencia.executeUpdate();
	               
	            } catch (SQLException ex) {
	                System.out.println("Error al insertar el/la autor/a en la base de datos");
	                
	            }
	        } 
	    }
	    
	    //Muesta la información del autor
	    public Autores read(String nombre, String apellidos) {

	    	Autores autor = null;
	    	String sql = "SELECT * FROM Autores WHERE nombre=? AND apellidos=?";
	    	
	    	try {
	    			PreparedStatement sentencia = conexion.prepareStatement(sql);
	    			sentencia.setString(1, nombre);
	    			sentencia.setString(2, apellidos);
	    			ResultSet rs = sentencia.executeQuery();
	    			if (rs.next()) {
	    				
	    					String fecha_de_nacimineto = rs.getString("fecha_de_nacimiento");
	    					String biografia = rs.getString("biografía");
	    					boolean estado = rs.getBoolean("estado");
	    				
	    					autor = new Autores(nombre,apellidos,fecha_de_nacimineto,biografia,estado);
	    				}
	    		} catch (SQLException ex) {
	    			System.out.println("Error al consultar el/la autor/a");
	    		}
	    		return autor;
	    	}
	    
	    public void update(Autores autor) {
	    	if(autor != null) {
	    		String sql = "UPDATE Autores SET biografía=? ,estado=?"
	    				+ " WHERE nombre=? AND apellidos=?";
	    		try {
	        		PreparedStatement sentencia = conexion.prepareStatement(sql);
	        		sentencia.setString(1, autor.getBiografia());
	        		sentencia.setBoolean(2, autor.isEstado());
	        		sentencia.setString(3, autor.getNombre());
	        		sentencia.setString(4, autor.getApellidos());
	        		sentencia.executeUpdate();
	        	}
	    		catch(SQLException ex) {
	    			System.out.println("El/la autor/a que se quiere modificar no existe");
	    		}
	    	}
	    	
	    }
	    
	    //Borra usuario de la tabla usuario
	    public void delete (String nombre,String apellidos) {
	    	String sql = "DELETE FROM Autores WHERE nombre =? AND apellidos=?";
	    	try {
	    		PreparedStatement sentencia = conexion.prepareStatement(sql);
	    		sentencia.setString(1, nombre);
	    		sentencia.setString(2, apellidos);
	    		sentencia.executeUpdate();
	    	}
	    	catch(SQLException ex) {
	    		System.out.println("Error al eliminar al usuario");
	    	}
	    }
	    

}
