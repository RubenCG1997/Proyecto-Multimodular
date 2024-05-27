package proyecto;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ComicsDAO {
	
	private Connection conexion;
    private final String USUARIO = "palProyecto";
    private final String PASSWORD = "123456789";
    private final String MAQUINA = "localhost";
    private final String BD = "proyectofinal";
    
	public ComicsDAO() {
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
    
    // Inserta una comic en la tabla comic y publicaciones
    public void create(Comics comic) {
        if (comic != null) {
            
            String sql = "INSERT INTO Publicaciones (isbn, titulo, fecha_de_lanzamiento, estado, fkAutorNombre, fkAutorApellidos, fkEditorial) VALUES (?, ?, ?, ?, ?, ?, ?)";
            String sql2 = "INSERT INTO Comics (pkfkPublicacion, color) VALUES (?, ?)";
                    
            try {
                // Insertar en la tabla Publicaciones
                PreparedStatement sentencia = conexion.prepareStatement(sql);
                sentencia.setString(1, comic.getIsbn());
                sentencia.setString(2, comic.getTitulo());
                sentencia.setDate(3, Date.valueOf(comic.getFecha_de_lanzamiento()));
                sentencia.setBoolean(4, comic.isEstado());
                sentencia.setString(5, comic.getFkAutorNombre());
                sentencia.setString(6, comic.getFkAutorApellidos());
                sentencia.setString(7, comic.getFkEditorial());
                sentencia.executeUpdate();

                // Insertar en la tabla Comics
                PreparedStatement sentencia2 = conexion.prepareStatement(sql2);
                sentencia2.setString(1, comic.getIsbn());
                sentencia2.setBoolean(2, comic.isColor());
                sentencia2.executeUpdate();
                

            } catch (SQLException ex) {
                System.out.println("Error al insertar el comic en la base de datos: ");
               
            }
        } 
    }
    
    //Muestra información del comic
    public Comics read(String pkfkPublicacion) {

    	Comics comic = null;
    	String sql = "SELECT Publicaciones.*, Comics.color FROM Publicaciones INNER JOIN Comics ON Publicaciones.isbn = Comics.pkfkPublicacion WHERE Comics.pkfkPublicacion=?";
    	
    	try {
    			PreparedStatement sentencia = conexion.prepareStatement(sql);
    			sentencia.setString(1, pkfkPublicacion);
    			ResultSet rs = sentencia.executeQuery();
    			if (rs.next()) {
    				
    					String isbn = rs.getString("isbn");
    					String titulo = rs.getString("titulo");
    					String fecha_de_lanzamiento = rs.getString("fecha_de_lanzamiento");
    					boolean estado = rs.getBoolean("estado");
    					String fkAutorNombre = rs.getString("fkAutorNombre");
    					String fkAutorApellidos = rs.getString("fkAutorApellidos");
    					String fkEditorial = rs.getString("fkEditorial");
    					boolean color = rs.getBoolean("color");
    				
    					comic = new Comics(isbn,titulo,fecha_de_lanzamiento,estado,fkAutorNombre,fkAutorApellidos,fkEditorial,color);
    					
    				}
    		} catch (SQLException ex) {
    			System.out.println("Error al consultar el comic");
    		}
    		return comic;
    }
    
    //Modificar comic
    public void update(Comics comic) {
    	if(comic != null) {
    	    String sql = "UPDATE Publicaciones INNER JOIN Comics ON Publicaciones.isbn = Comics.pkfkPublicacion"
    	            + " SET Publicaciones.titulo=?, Publicaciones.fecha_de_lanzamiento=?, Publicaciones.estado=?, Publicaciones.fkAutorNombre=?, Publicaciones.fkAutorApellidos=?,"
    	            + " Publicaciones.fkEditorial=?, Comics.color=?"
    	            + " WHERE Comics.pkfkPublicacion=?";
    		try {
        		PreparedStatement sentencia = conexion.prepareStatement(sql);
        		sentencia.setString(1, comic.getTitulo());
        		sentencia.setDate(2, Date.valueOf(comic.getFecha_de_lanzamiento()));
        		sentencia.setBoolean(3, comic.isEstado());
        		sentencia.setString(4, comic.getFkAutorNombre());
        		sentencia.setString(5, comic.getFkAutorApellidos());
        		sentencia.setString(6, comic.getFkEditorial());
        		sentencia.setBoolean(7, comic.isColor());
        		sentencia.setString(8, comic.getIsbn());
        		sentencia.executeUpdate();
        	}
    		catch(SQLException ex) {
    			System.out.println("El comic que se quiere modificar no existe");
    		}
    	}
    	
    }
    
    //Borra comic 
    public void delete (String isbn) {
    	String sql = "DELETE FROM Publicaciones WHERE isbn =?";
    	try {
    		PreparedStatement sentencia = conexion.prepareStatement(sql);
    		sentencia.setString(1,isbn);
    		sentencia.executeUpdate();
    	}
    	catch(SQLException ex) {
    		System.out.println("Error al eliminar el comic");
    	}
    }
    
    
    
    


    
    

}
