package proyecto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
               System.out.println("Editorial añadida");
            } catch (SQLException ex) {
                System.out.println("La editorial cuyo cif,nombre,direccion o telefono ya existe,no se puede añadir a la base de datos");
                
            }
        } 
    }
    //Muesta la información de la editorial
    public ArrayList<Editoriales> read(String cif) {
    	ArrayList<Editoriales>lista= new ArrayList<>();
    	Editoriales editorial = null;
    	String sql = "SELECT * FROM Editoriales WHERE cif Like ?";
    	
    	try {
    			PreparedStatement sentencia = conexion.prepareStatement(sql);
    			sentencia.setString(1, cif+"%");
    			ResultSet rs = sentencia.executeQuery();
    			while (rs.next()) {
    					String cifEdi = rs.getString("cif");
    					String nombre = rs.getString("nombre");
    					String direccion = rs.getString("direccion");
    					String telefono = rs.getString("telefono");
    					String cp = rs.getString("cp");
    					boolean estado = rs.getBoolean("estado");
    				
    					editorial = new Editoriales(cifEdi,nombre,direccion,telefono,cp,estado);
    					lista.add(editorial);
    				}
    		} catch (SQLException ex) {
    			System.out.println("Error al buscar la/s editorial/es");
    		}
    		return lista;
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
    			System.out.println("No se pudo modificar la editorial");
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
