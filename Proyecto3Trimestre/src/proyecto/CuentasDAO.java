package proyecto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CuentasDAO {
	
	private Connection conexion;
    private final String USUARIO = "palProyecto";
    private final String PASSWORD = "123456789";
    private final String MAQUINA = "localhost";
    private final String BD = "proyectofinal";
    
	public CuentasDAO() {
		this.conexion = conectar();
	}
	
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
    
    // Inserta una cuenta en la tabla cuentas
    public void create(Cuentas cuenta) {
        if (cuenta != null) {
            String sql = "INSERT INTO Cuentas ( username, fecha_de_creacion,fkUsuario, fkSuscripciones )"
            		+ " VALUES(?, now(), ?, ?)";

            try {
                PreparedStatement sentencia = conexion.prepareStatement(sql);
                sentencia.setString(1, cuenta.getUsername());
                sentencia.setString(2,cuenta.getFkUsuario());
                sentencia.setInt(3, cuenta.getFkSuscripcion());
                sentencia.executeUpdate();
               
            } catch (SQLException ex) {
                System.out.println("Error al insertar la cuenta en la base de datos");
                
            }
        } 
    }
    //Muesta la información de la cuenta
    public Cuentas read(String id) {

    	Cuentas cuentas = null;
    	String sql = "SELECT * FROM Cuentas WHERE fkUsuario=?";
    	
    	try {
    			PreparedStatement sentencia = conexion.prepareStatement(sql);
    			sentencia.setString(1, id);
    			ResultSet rs = sentencia.executeQuery();
    			if (rs.next()) {
    				
    					String username = rs.getString("username");
    					String fkUsuario = rs.getString("fkUsuario");
    					int fkSuscripciones = rs.getInt("fkSuscripciones");
    				
    					cuentas = new Cuentas(username,fkUsuario,fkSuscripciones);
    				}
    		} catch (SQLException ex) {
    			System.out.println("Error al consultar la cuenta");
    		}
    		return cuentas;
    	}
    //Modifica la informacion de la cuenta
    public void update(Cuentas cuenta) {
    	if(cuenta != null) {
    		String sql = "UPDATE Cuentas SET username=?,"
    				+ " WHERE fkUsuario=?";
    		try {
        		PreparedStatement sentencia = conexion.prepareStatement(sql);
        		sentencia.setString(1, cuenta.getUsername());
        		sentencia.setString(2, cuenta.getFkUsuario());
        		sentencia.executeUpdate();
        	}
    		catch(SQLException ex) {
    			System.out.println("La cuenta que se quiere modificar no existe");
    		}
    	}
    	
    }
    
    //Borra usuario de la tabla usuario
    public void delete (int id) {
    	String sql = "DELETE FROM Cuentas WHERE idCuenta =?";
    	try {
    		PreparedStatement sentencia = conexion.prepareStatement(sql);
    		sentencia.setInt(1, id);
    		sentencia.executeUpdate();
    	}
    	catch(SQLException ex) {
    		System.out.println("Error al eliminar la cuenta");
    	}
    }
    
    //Comprueba si el usuario existe en la lista creada pasando el dni o email
    public boolean usernameExiste(String username) {
    	
    	boolean existe = false;
    	String sql = "SELECT username FROM Cuentas WHERE username=?";
    	try {
    		PreparedStatement sentencia = conexion.prepareStatement(sql);
    		sentencia.setString(1,username);
    		ResultSet rs = sentencia.executeQuery(); 
    		if(rs.next()) {
    			existe =true;
    		}
    		if(existe) {
    			System.out.println("El username existe,pon otro");
    		}
    	}catch(SQLException ex){
    		System.out.println("Error al buscar el username");
    	}
    	return existe;
    	 	
    }
    
}
