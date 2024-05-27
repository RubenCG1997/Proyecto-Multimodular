package proyecto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CuentaLeePublicacionDAO {
    private Connection conexion;
    private final String USUARIO = "palProyecto";
    private final String PASSWORD = "123456789";
    private final String MAQUINA = "localhost";
    private final String BD = "proyectofinal";
    
    public CuentaLeePublicacionDAO() {
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
    
    // Inserta una cuenta que lee una publicación
    public void create(CuentaLeePublicacion clp) {
        if (clp != null) {
            String sql = "INSERT INTO CuentaLeePublicacion (pkfkCuenta, pkfkPublicacion, porcentaje) VALUES (?, ?, ?)";
            
            try {
                PreparedStatement sentencia = conexion.prepareStatement(sql);
                sentencia.setInt(1, clp.getPkfkCuenta());
                sentencia.setString(2, clp.getPkfkPublicacion());
                sentencia.setInt(3, clp.getPorcentaje());
                sentencia.executeUpdate();
            } catch (SQLException ex) {
                System.out.println("Error al insertar cuenta lee publicación en la base de datos");
            }
        }
    }
    
    // Lee una cuenta que lee una publicación
    public CuentaLeePublicacion read(int pkfkCuenta, String pkfkPublicacion) {
        CuentaLeePublicacion clp = null;
        String sql = "SELECT * FROM CuentaLeePublicacion WHERE pkfkCuenta = ? AND pkfkPublicacion = ?";
        
        try {
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, pkfkCuenta);
            sentencia.setString(2, pkfkPublicacion);
            
            ResultSet rs = sentencia.executeQuery();
            if (rs.next()) {
                clp = new CuentaLeePublicacion(
                    rs.getInt("pkfkCuenta"),
                    rs.getString("pkfkPublicacion"),
                    rs.getInt("porcentaje")
                );
            }
        } catch (SQLException ex) {
            System.out.println("Error al consultar cuenta lee publicación");
        }
        return clp;
    }
    
    // Actualiza una cuenta que lee una publicación
    public void update(CuentaLeePublicacion clp) {
        if (clp != null) {
            String sql = "UPDATE CuentaLeePublicacion SET porcentaje = ? WHERE pkfkCuenta = ? AND pkfkPublicacion = ?";
            
            try {
                PreparedStatement sentencia = conexion.prepareStatement(sql);
                sentencia.setInt(1, clp.getPorcentaje());
                sentencia.setInt(2, clp.getPkfkCuenta());
                sentencia.setString(3, clp.getPkfkPublicacion());
                sentencia.executeUpdate();
            } catch (SQLException ex) {
                System.out.println("Error al actualizar cuenta lee publicación en la base de datos");
            }
        }
    }
    
    // Elimina una cuenta que lee una publicación
    public void delete(int pkfkCuenta, String pkfkPublicacion) {
        String sql = "DELETE FROM CuentaLeePublicacion WHERE pkfkCuenta = ? AND pkfkPublicacion = ?";
        
        try {
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, pkfkCuenta);
            sentencia.setString(2, pkfkPublicacion);
            sentencia.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error al eliminar cuenta lee publicación de la base de datos");
        }
    }
}
