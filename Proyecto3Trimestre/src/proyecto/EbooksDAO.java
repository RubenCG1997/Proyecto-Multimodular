package proyecto;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EbooksDAO {
	
	private Connection conexion;
    private final String USUARIO = "palProyecto";
    private final String PASSWORD = "123456789";
    private final String MAQUINA = "localhost";
    private final String BD = "proyectofinal";
    
	public EbooksDAO() {
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
    public void create(Ebooks ebook) {
        if (ebook != null) {
            
            String sql = "INSERT INTO Publicaciones (isbn, titulo, fecha_de_lanzamiento, estado, fkAutorNombre, fkAutorApellidos, fkEditorial) VALUES (?, ?, ?, ?, ?, ?, ?)";
            String sql2 = "INSERT INTO Ebooks (pkfkPublicacion, formato) VALUES (?, ?)";
                    
            try {
                
                PreparedStatement sentencia = conexion.prepareStatement(sql);
                sentencia.setString(1, ebook.getIsbn());
                sentencia.setString(2, ebook.getTitulo());
                sentencia.setDate(3, Date.valueOf(ebook.getFecha_de_lanzamiento()));
                sentencia.setBoolean(4, ebook.isEstado());
                sentencia.setString(5, ebook.getFkAutorNombre());
                sentencia.setString(6, ebook.getFkAutorApellidos());
                sentencia.setString(7, ebook.getFkEditorial());
                sentencia.executeUpdate();

               
                PreparedStatement sentencia2 = conexion.prepareStatement(sql2);
                sentencia2.setString(1, ebook.getIsbn());
                sentencia2.setString(2, ebook.getFormato());
                sentencia2.executeUpdate();
                

            } catch (SQLException ex) {
                System.out.println("Error al insertar el comic en la base de datos: ");
               
            }
        } 
    }
    
    //Muestra información del ebook
    public Ebooks read(String pkfkPublicacion) {

    	Ebooks ebook = null;
    	String sql = "SELECT Publicaciones.*, Ebooks.formato FROM Publicaciones INNER JOIN Ebooks ON Publicaciones.isbn = Ebooks.pkfkPublicacion WHERE Ebooks.pkfkPublicacion=?";
    	
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
    					String formato = rs.getString("formato");
    				
    					ebook = new Ebooks(isbn,titulo,fecha_de_lanzamiento,estado,fkAutorNombre,fkAutorApellidos,fkEditorial,formato);
    					
    				}
    		} catch (SQLException ex) {
    			System.out.println("Error al consultar el comic");
    		}
    		return ebook;
    }
    
    //Modificar comic
    public void update(Ebooks ebook) {
    	if(ebook != null) {
    	    String sql = "UPDATE Publicaciones INNER JOIN Ebooks ON Publicaciones.isbn = Ebooks.pkfkPublicacion"
    	            + " SET Publicaciones.titulo=?, Publicaciones.fecha_de_lanzamiento=?, Publicaciones.estado=?, Publicaciones.fkAutorNombre=?, Publicaciones.fkAutorApellidos=?,"
    	            + " Publicaciones.fkEditorial=?, Ebooks.color=?"
    	            + " WHERE Ebooks.pkfkPublicacion=?";
    		try {
        		PreparedStatement sentencia = conexion.prepareStatement(sql);
        		sentencia.setString(1, ebook.getTitulo());
        		sentencia.setDate(2, Date.valueOf(ebook.getFecha_de_lanzamiento()));
        		sentencia.setBoolean(3, ebook.isEstado());
        		sentencia.setString(4, ebook.getFkAutorNombre());
        		sentencia.setString(5, ebook.getFkAutorApellidos());
        		sentencia.setString(6, ebook.getFkEditorial());
        		sentencia.setString(7, ebook.getFormato());
        		sentencia.setString(8, ebook.getIsbn());
        		sentencia.executeUpdate();
        	}
    		catch(SQLException ex) {
    			System.out.println("El comic que se quiere modificar no existe");
    		}
    	}
    	
    }
    
    //Borra ebook 
    public void delete (String isbn) {
    	String sql = "DELETE FROM Publicaciones WHERE isbn =?";
    	try {
    		PreparedStatement sentencia = conexion.prepareStatement(sql);
    		sentencia.setString(1,isbn);
    		sentencia.executeUpdate();
    	}
    	catch(SQLException ex) {
    		System.out.println("Error al eliminar el ebook ");
    	}
    }
}