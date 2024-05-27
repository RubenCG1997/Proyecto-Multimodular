package proyecto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EditorialesDAO {
	
	private Connection conexion;
    private final String USUARIO = "palProyecto";
    private final String PASSWORD = "123456789";
    private final String MAQUINA = "localhost";
    private final String BD = "proyectofinal";
    
	public EditorialesDAO() {
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
    // Inserta un usuario en la tabla Usuarios
    public void create(Editoriales editorial) {
        if (editorial != null) {
            String sql = "INSERT INTO Editoriales (cif, nombre, direccion, telefono, cp, estado )"
            		+ " VALUES(?, ?, ?, ?, ?, ?)";

            try {
                PreparedStatement sentencia = conexion.prepareStatement(sql);
                sentencia.setString(1, editorial.getCif());
                sentencia.setString(2, editorial.getNombre());
                sentencia.setString(3, editorial.getDireccion());
                sentencia.setString(4, editorial.getTelefono());
                sentencia.setString(5, editorial.getCp());
                sentencia.setBoolean(6, editorial.isEstado());
                sentencia.executeUpdate();
               
            } catch (SQLException ex) {
                System.out.println("Error al insertar la editorial en la base de datos");
                
            }
        } 
    }
    //Muesta la información de la editorial
    public Editoriales read(String cif) {

    	Editoriales editorial = null;
    	String sql = "SELECT * FROM Editoriales WHERE cif=?";
    	
    	try {
    			PreparedStatement sentencia = conexion.prepareStatement(sql);
    			sentencia.setString(1, cif);
    			ResultSet rs = sentencia.executeQuery();
    			if (rs.next()) {
    					String nombre = rs.getString("nombre");
    					String direccion = rs.getString("direccion");
    					String telefono = rs.getString("telefono");
    					String cp = rs.getString("cp");
    					boolean estado = rs.getBoolean("estado");
    				
    					editorial = new Editoriales(cif,nombre,direccion,telefono,cp,estado);
    				}
    		} catch (SQLException ex) {
    			System.out.println("Error al consultar la editorial");
    		}
    		return editorial;
    	}
    //Modifica editorial de la tabla editoriales
    public void update(Editoriales editorial) {
    	if(editorial != null) {
    		String sql = "UPDATE Editoriales SET nombre=?, direccion=?, telefono=?, cp=?, estado=?"
    				+ " WHERE cif=?";
    		try {
        		PreparedStatement sentencia = conexion.prepareStatement(sql);
        		sentencia.setString(1, editorial.getNombre());
        		sentencia.setString(2, editorial.getDireccion());
        		sentencia.setString(3, editorial.getTelefono());
        		sentencia.setString(4, editorial.getCp());
        		sentencia.setBoolean(5, editorial.isEstado());
        		sentencia.setString(6, editorial.getCif());
        		sentencia.executeUpdate();
        	}
    		catch(SQLException ex) {
    			System.out.println("La editorial que se quiere modificar no existe");
    		}
    	}
    	
    }
    
    public void delete (String cif) {
    	String sql = "DELETE FROM Editoriales WHERE cif =?";
    	try {
    		PreparedStatement sentencia = conexion.prepareStatement(sql);
    		sentencia.setString(1, cif);
    		sentencia.executeUpdate();
    	}
    	catch(SQLException ex) {
    		System.out.println("Error al eliminar la editorial");
    	}
    }

}
