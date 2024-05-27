package proyecto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ListasDAO {

	private Connection conexion;
    private final String USUARIO = "palProyecto";
    private final String PASSWORD = "123456789";
    private final String MAQUINA = "localhost";
    private final String BD = "proyectofinal";
    
	public ListasDAO() {
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
    
    // Inserta una lista en la tabla lista
    public void create(Listas lista) {
        if (lista != null) {
            
            String sql = "INSERT INTO Listas (nombre,pkfkCuenta,estado) VALUES (?, ?, ?)";
                    
            try {
                
                PreparedStatement sentencia = conexion.prepareStatement(sql);
                sentencia.setString(1, lista.getNombre());
                sentencia.setInt(2, lista.getPkfkCuenta());
                sentencia.setBoolean(3, lista.isEstado());
                sentencia.executeUpdate();

                

            } catch (SQLException ex) {
                System.out.println("Error al insertar el comic en la base de datos: ");
               
            }
        } 
    }
    
    //Muestra información de la Lista
    public Listas read(String nombre,int pkfkCuenta) {

    	Listas lista = null;
    	String sql = "SELECT *from Listas WHERE nombre=? AND pkfkCuenta=?";
    	
    	try {
    			PreparedStatement sentencia = conexion.prepareStatement(sql);
    			sentencia.setString(1, nombre);
    			sentencia.setInt(2,pkfkCuenta);
    			ResultSet rs = sentencia.executeQuery();
    			if (rs.next()) {
    				
    					String nombreLista = rs.getString("nombre");
    					int idCuenta = rs.getInt("pkfkCuenta");
    					boolean estado = rs.getBoolean("estado");

    					lista = new Listas(nombreLista,idCuenta,estado);
    					
    				}
    		} catch (SQLException ex) {
    			System.out.println("Error al consultar la lista");
    		}
    		return lista;
    }
    
    //Modificar Lista
    public void update(Listas lista) {
    	if(lista != null) {
    	    String sql = "UPDATE Listas SET estado=? WHERE nombre=? AND pkfkCuenta=? ";

    		try {
        		PreparedStatement sentencia = conexion.prepareStatement(sql);
        		sentencia.setBoolean(1, lista.isEstado());
        		sentencia.setString(2, lista.getNombre());
        		sentencia.setInt(3, lista.getPkfkCuenta());
        		

        		sentencia.executeUpdate();
        	}
    		catch(SQLException ex) {
    			System.out.println("La lista que se quiere modificar no existe");
    		}
    	}
    	
    }
    
    //Borrar lista
    public void delete (String nombre,int pkfkCuenta) {
    	String sql = "DELETE FROM Listas WHERE nombre =? AND pkfkCuenta=?" ;
    	try {
    		PreparedStatement sentencia = conexion.prepareStatement(sql);
    		sentencia.setString(1, nombre);
    		sentencia.setInt(2, pkfkCuenta);
    		sentencia.executeUpdate();
    	}
    	catch(SQLException ex) {
    		System.out.println("Error al eliminar la lista");
    	}
    }
    
    
   
    
    
}
