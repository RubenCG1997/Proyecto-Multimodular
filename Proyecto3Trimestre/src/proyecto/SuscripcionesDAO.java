package proyecto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SuscripcionesDAO {

    private Connection conexion;
    private final String USUARIO = "palProyecto";
    private final String PASSWORD = "123456789";
    private final String MAQUINA = "localhost";
    private final String BD = "proyectofinal";
    
	public SuscripcionesDAO() {
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
    public void create(Suscripciones suscripcion) {
        if (suscripcion != null) {
            String sql = "INSERT INTO Suscripciones ( tipo, cuota, precio )"
            		+ " VALUES(?, ?, ?)";

            try {
                PreparedStatement sentencia = conexion.prepareStatement(sql);
                sentencia.setString(1, suscripcion.getTipo().name());//Se obtiene el nombre del enum
                sentencia.setString(2, suscripcion.getCuota().name());//Se obtiene el nombre del enum
                sentencia.setDouble(3, suscripcion.getPrecio());
                sentencia.executeUpdate();
               
            } catch (SQLException ex) {
                System.out.println("Error al insertar la suscripcion en la base de datos");
                
            }
        } 
    }
   
    //Muestra una suscrpcion en la tabla suscripciones
    public Suscripciones read(int id) {


    	
    	Suscripciones suscripciones = null;
    	String sql = "SELECT * FROM Suscripciones WHERE idSuscripción=?";
    	
    	try {
    			PreparedStatement sentencia = conexion.prepareStatement(sql);
    			sentencia.setInt(1, id);
    			ResultSet rs = sentencia.executeQuery();
    			if (rs.next()) {
    				
    					String tipoString = rs.getString("tipo");
    					String cuotaString = rs.getString("cuota");
    					
    					//Convertimos los string en el enum
    					Suscripciones.TIPO tipo = Suscripciones.TIPO.valueOf(tipoString);
    					Suscripciones.CUOTA cuota = Suscripciones.CUOTA.valueOf(cuotaString);
    					suscripciones = new Suscripciones(tipo,cuota);
    				}
    		} catch (SQLException ex) {
    			System.out.println("Error al consultar la suscripcion");
    		}
    		return suscripciones;
    	}
    
    //Modifica usuario de la tabla usuario???????
    public void update(Suscripciones suscripcion) {
    	if(suscripcion != null) {
    		String sql = "UPDATE Suscripciones SET tipo=?, cuota=?, precio=?"
    				+ " WHERE idSuscripción=?";
    		try {
        		PreparedStatement sentencia = conexion.prepareStatement(sql);
        		sentencia.setString(1, suscripcion.getTipo().name());
        		sentencia.setString(2,suscripcion.getCuota().name());
        		sentencia.setDouble(3, suscripcion.getPrecio());
        		sentencia.executeUpdate();
        	}
    		catch(SQLException ex) {
    			System.out.println("La suscripcion que se quiere modificar no existe");
    		}
    	}
    	
    }
    
    //Borra usuario de la tabla usuario
    public void delete (int id) {
    	String sql = "DELETE FROM Suscripciones WHERE idSuscripción =?";
    	try {
    		PreparedStatement sentencia = conexion.prepareStatement(sql);
    		sentencia.setInt(1, id);
    		sentencia.executeUpdate();
    	}
    	catch(SQLException ex) {
    		System.out.println("Error al eliminar al usuario");
    	}
    }
}


