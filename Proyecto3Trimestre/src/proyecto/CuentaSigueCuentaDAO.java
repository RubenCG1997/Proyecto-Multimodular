package proyecto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CuentaSigueCuentaDAO {

	private Connection conexion;
    private final String USUARIO = "palProyecto";
    private final String PASSWORD = "123456789";
    private final String MAQUINA = "localhost";
    private final String BD = "proyectofinal";
    
	public CuentaSigueCuentaDAO() {
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
    
    // cuenta sigue a cuenta
    public void create(CuentaSigueCuenta follow) {
        if (follow != null) {
            
            String sql = "INSERT INTO CuentaSigueCuenta (idSeguido,idSeguidor,fecha) VALUES (?, ?, now())";

            try {
                
                PreparedStatement sentencia = conexion.prepareStatement(sql);
                sentencia.setInt(1, follow.getIdSeguido());
                sentencia.setInt(2, follow.getIdSeguidor());
                sentencia.executeUpdate();


            } catch (SQLException ex) {
                System.out.println("Error al insertar seguir en la base de datos: ");
               
            }
        } 
    }
    //Dejar de seguir
    public void delete (int idSeguido,int idSeguidor) {
    	String sql = "DELETE FROM CuentaSigueCuenta WHERE idSeguido = ? AND idSeguidor = ?";
    	try {
    		PreparedStatement sentencia = conexion.prepareStatement(sql);
    		sentencia.setInt(1, idSeguido);
    		sentencia.setInt(2, idSeguidor);
    		sentencia.executeUpdate();
    	}
    	catch(SQLException ex) {
    		System.out.println("Error al dejar de seguir");
    	}
    }
    public void contarSeguidosSeguidores(int idCuenta) {
        String sql = "SELECT COUNT(*) AS seguidos FROM CuentaSigueCuenta WHERE idSeguidor = ?";
        String sql2 = "SELECT COUNT(*) AS seguidores FROM CuentaSigueCuenta WHERE idSeguido = ?";

        try {
            // Contar seguidos
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, idCuenta);
            ResultSet rs = sentencia.executeQuery();
            if (rs.next()) {
                int seguidos = rs.getInt("seguidos");
                System.out.println("Cantidad de seguidos: " + seguidos);
            }

            // Contar seguidores
            PreparedStatement sentencia2 = conexion.prepareStatement(sql2);
            sentencia2.setInt(1, idCuenta);
            ResultSet rs2 = sentencia2.executeQuery();
            if (rs2.next()) {
                int seguidores = rs2.getInt("seguidores");
                System.out.println("Cantidad de seguidores: " + seguidores);
            }

        } catch (SQLException ex) {
            System.out.println("Error al contar seguidos y seguidores: ");
        }
    }
}
