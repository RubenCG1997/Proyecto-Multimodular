package proyecto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MeGustaDAO {
	private Connection conexion;
    private final String USUARIO = "palProyecto";
    private final String PASSWORD = "123456789";
    private final String MAQUINA = "localhost";
    private final String BD = "proyectofinal";
    
	public MeGustaDAO() {
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
    
    // Inserta un me gusta en una lista
    public void create(MeGusta mg) {
        if (mg != null) {
            
            String sql = "INSERT INTO Megusta (pkfkCuenta,pkfkNombreLista,pkfkCuentaLista) VALUES (?, ?, ?)";

            try {
                
                PreparedStatement sentencia = conexion.prepareStatement(sql);
                sentencia.setInt(1, mg.getPkfkCuenta());
                sentencia.setString(2, mg.getPkfkNombreLista());
                sentencia.setInt(3, mg.getPkfkCuentaLista());
                sentencia.executeUpdate();


            } catch (SQLException ex) {
                System.out.println("Error al insertar me gusta en la base de datos: ");
               
            }
        } 
    }
    
    //Muestra Me gusta
    public MeGusta read(int pkfkCuenta,String pkfkNombreLista,int pkfkCuentaLista) {

    	MeGusta mg = null;
    	String sql = "SELECT*from MeGusta WHERE pkfkCuenta = ? AND pkfkNombreLista = ? AND pkfkCuentaLista = ?";
    	
    	try {
    			PreparedStatement sentencia = conexion.prepareStatement(sql);
    			sentencia.setInt(1, pkfkCuenta);
    			sentencia.setString(2, pkfkNombreLista);
    			sentencia.setInt(3, pkfkCuentaLista);
    			
    			ResultSet rs = sentencia.executeQuery();
    			if (rs.next()) {
    					
    					int cuenta = rs.getInt("pkfkCuenta");
    					String nombreLista = rs.getString("pkfkNombreLista");
    					int numCuenta = rs.getInt("pkfkCuentaLista");
    					mg = new MeGusta(cuenta,nombreLista,numCuenta);
    					
    				}
    		} catch (SQLException ex) {
    			System.out.println("Error al consultar megusta");
    		}
    		return mg;
    }
    
    //Eliminar Me gusta
    public void delete (int pkfkCuenta,String pkfkNombreLista,int pkfkCuentaLista) {
    	String sql = "DELETE FROM MeGusta WHERE pkfkCuenta = ? AND pkfkNombreLista = ? AND pkfkCuentaLista = ?";
    	try {
    		PreparedStatement sentencia = conexion.prepareStatement(sql);
    		sentencia.setInt(1, pkfkCuenta);
    		sentencia.setString(2, pkfkNombreLista);
    		sentencia.setInt(3, pkfkCuentaLista);
    		sentencia.executeUpdate();
    	}
    	catch(SQLException ex) {
    		System.out.println("Error al eliminar el me gusta");
    	}
    }
}
