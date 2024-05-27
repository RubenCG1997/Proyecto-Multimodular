package proyecto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ListaContienePublicacionesDAO {

	private Connection conexion;
    private final String USUARIO = "palProyecto";
    private final String PASSWORD = "123456789";
    private final String MAQUINA = "localhost";
    private final String BD = "proyectofinal";
    
	public ListaContienePublicacionesDAO() {
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
    
    // Inserta un libro en una lista
    public void create(ListaContienePublicaciones addLibro) {
        if (addLibro != null) {
            
            String sql = "INSERT INTO ListaContienePublicaciones (pkfkPublicacion,pkfkNombreLista,pkfkCuentaLista) VALUES (?, ?, ?)";

            try {
                
                PreparedStatement sentencia = conexion.prepareStatement(sql);
                sentencia.setString(1, addLibro.getPkfkPublicacion());
                sentencia.setString(2, addLibro.getPkfkNombreLista());
                sentencia.setInt(3, addLibro.getPkfkCuentaLista());
                sentencia.executeUpdate();


            } catch (SQLException ex) {
                System.out.println("Error al insertar la resenna en la base de datos: ");
               
            }
        } 
    }
    
    //Muestra información de una lista y sus libros
    public ListaContienePublicaciones read(String pkfkPublicacion,String pkfkNombreLista,int pkfkCuenta) {

    	ListaContienePublicaciones lista = null;
    	String sql = "SELECT *from ListaContienePublicaciones WHERE pkfkPublicacion=? AND pkfkNombreLista=? AND pkfkCuentaLista=?";
    	
    	try {
    			PreparedStatement sentencia = conexion.prepareStatement(sql);
    			sentencia.setString(1, pkfkPublicacion);
    			sentencia.setString(2, pkfkNombreLista);
    			sentencia.setInt(3, pkfkCuenta);
    			
    			ResultSet rs = sentencia.executeQuery();
    			if (rs.next()) {
    					
    					String isbn = rs.getString("pkfkPublicacion");
    					String nombreLista = rs.getString("pkfkNombreLista");
    					int numCuenta = rs.getInt("pkfkCuentaLista");
    					lista = new ListaContienePublicaciones(isbn,nombreLista,numCuenta);
    					
    				}
    		} catch (SQLException ex) {
    			System.out.println("Error al consultar la lsita");
    		}
    		return lista;
    }
        
    //Borra libro de la lista
    public void delete (String isbn,String nombre, int listaCuenta) {
    	String sql = "DELETE FROM ListaContienePublicaciones WHERE pkfkPublicacion=? AND pkfkNombreLista=? AND pkfkCuentaLista=?";
    	try {
    		PreparedStatement sentencia = conexion.prepareStatement(sql);
    		sentencia.setString(1, isbn);
    		sentencia.setString(2, nombre);
    		sentencia.setInt(3, listaCuenta);
    		sentencia.executeUpdate();
    	}
    	catch(SQLException ex) {
    		System.out.println("Error al eliminar la el libro de la list");
    	}
    }
    
    
    
    
}
