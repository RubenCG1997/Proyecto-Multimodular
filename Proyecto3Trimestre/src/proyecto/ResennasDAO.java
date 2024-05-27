package proyecto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResennasDAO {
	
	private Connection conexion;
    private final String USUARIO = "palProyecto";
    private final String PASSWORD = "123456789";
    private final String MAQUINA = "localhost";
    private final String BD = "proyectofinal";
    
	public ResennasDAO() {
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
    
    // Inserta una resenna en la tabla resenna
    public void create(Resennas resenna) {
        if (resenna != null) {
            
            String sql = "INSERT INTO Resennas (pkfkCuenta,pkfkPublicacion,opinion,puntuacion,estado) VALUES (?, ?, ?, ?, ?)";
                    
            try {
                
                PreparedStatement sentencia = conexion.prepareStatement(sql);
                sentencia.setInt(1, resenna.getPkfkCuenta());
                sentencia.setString(2, resenna.getPkfkPublicacion());
                sentencia.setString(3, resenna.getOpinion());
                sentencia.setInt(4, resenna.getPuntuacion());
                sentencia.setBoolean(5, resenna.isEstado());
                sentencia.executeUpdate();

                

            } catch (SQLException ex) {
                System.out.println("Error al insertar la resenna en la base de datos: ");
               
            }
        } 
    }
    
    //Muestra información de la resenna
    public Resennas read(int pkfkCuenta,String pkfkPublicacion) {

    	Resennas resenna = null;
    	String sql = "SELECT *from Resennas WHERE pkfkCuenta=? AND pkfkPublicacion=?";
    	
    	try {
    			PreparedStatement sentencia = conexion.prepareStatement(sql);
    			sentencia.setInt(1, pkfkCuenta);
    			sentencia.setString(2,pkfkPublicacion);
    			ResultSet rs = sentencia.executeQuery();
    			if (rs.next()) {
    					
    					int numCuenta = rs.getInt("pkfkCuenta");
    					String isbn = rs.getString("pkfkPublicacion");
    					String opinion = rs.getString("opinion");
    					int puntuacion = rs.getInt("puntuacion");
    					boolean estado = rs.getBoolean("estado");

    					 resenna = new Resennas(numCuenta,isbn,opinion,puntuacion,estado);
    					
    				}
    		} catch (SQLException ex) {
    			System.out.println("Error al consultar la resenna");
    		}
    		return resenna;
    }
    
    //Modifica resenna de la tabla resenna
    public void update(Resennas resenna) {
    	if(resenna != null) {
    		String sql = "UPDATE Resennas SET opinion=?, puntuacion=?, estado=?"
    				+ " WHERE pkfkCuenta=? AND pkfkPublicacion=?";
    		try {
        		PreparedStatement sentencia = conexion.prepareStatement(sql);
        		sentencia.setString(1, resenna.getOpinion());
        		sentencia.setInt(2, resenna.getPuntuacion());
        		sentencia.setBoolean(3, resenna.isEstado());
        		sentencia.setInt(4, resenna.getPkfkCuenta());
        		sentencia.setString(5, resenna.getPkfkPublicacion());
        		sentencia.executeUpdate();
        	}
    		catch(SQLException ex) {
    			System.out.println("La resenna que se quiere modificar no existe");
    		}
    	}
    	
    }
    
    //Borra Resenna
    public void delete (int cuenta,String isbn) {
    	String sql = "DELETE FROM Resennas WHERE pkfkCuenta=? AND pkfkPublicacion=?";
    	try {
    		PreparedStatement sentencia = conexion.prepareStatement(sql);
    		sentencia.setInt(1, cuenta);
    		sentencia.setString(2, isbn);
    		sentencia.executeUpdate();
    	}
    	catch(SQLException ex) {
    		System.out.println("Error al eliminar la editorial");
    	}
    }
    
    
    
    
}
