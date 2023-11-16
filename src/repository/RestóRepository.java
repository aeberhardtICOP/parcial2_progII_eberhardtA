package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import model.Resto;

public class RestóRepository {
	private Conexion conexion = new Conexion();
	  
	  private Connection connection;
	  private PreparedStatement preparedStatement;
	  private ResultSet resultSet;

	  public void guardarResto(Resto resto) {

	    String sql = "INSERT INTO Resto (nombre, direccion, localidad) VALUES (?, ?, ?)";
	    
	    try {
	      connection = conexion.conectar();
	      preparedStatement = connection.prepareStatement(sql);
	      preparedStatement.setString(1, resto.getNombre());
	      preparedStatement.setString(2, resto.getDireccion());
	      preparedStatement.setString(3, resto.getLocalidad());
	      preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	      e.printStackTrace();
	    } finally {
	      cerrarRecursos();
	    }
	  }
	  
	  public HashMap<Long, Resto> traerAMemoria() {

		  HashMap<Long, Resto> restaurantes = new HashMap<>();

		  String sql = "SELECT * FROM Resto";

		  try {
		    connection = conexion.conectar();
		    preparedStatement = connection.prepareStatement(sql);
		    resultSet = preparedStatement.executeQuery();
		    while (resultSet.next()) {
		      Resto resto = new Resto();
		      resto.setId(resultSet.getLong("id_resto"));
		      resto.setNombre(resultSet.getString("nombre"));
		      resto.setDireccion(resultSet.getString("direccion"));
		      resto.setLocalidad(resultSet.getString("localidad"));

		      restaurantes.put(resto.getId(), resto);
		    }
		  } catch (SQLException e) {
		    e.printStackTrace();
		  } finally {
		    cerrarRecursos();
		  }

		  return restaurantes;
		}

	  public Resto buscarRestoPorId(long idResto) {
	    
	    Resto resto = null;
	    
	    String sql = "SELECT * FROM Resto WHERE id_resto = ?";
	    
	    try {
	      connection = conexion.conectar();
	      preparedStatement = connection.prepareStatement(sql);
	      preparedStatement.setLong(1, idResto);
	      resultSet = preparedStatement.executeQuery();
	      if (resultSet.next()) {
	        resto = new Resto();
	        resto.setId(resultSet.getLong("id_resto"));
	        resto.setNombre(resultSet.getString("nombre"));
	        resto.setDireccion(resultSet.getString("direccion"));
	        resto.setLocalidad(resultSet.getString("localidad")); 
	      }
	    } catch (SQLException e) {
	      e.printStackTrace();
	    } finally {
	      cerrarRecursos();  
	    }
	    
	    return resto;
	  }
	  
	  public long ultimoIdResto() {

		  long id = 0;

		  String sql = "SELECT MAX(id_resto) AS ultimo_id FROM resto";

		  try {
		    connection = conexion.conectar();
		    preparedStatement = connection.prepareStatement(sql);
		    resultSet = preparedStatement.executeQuery();

		    if (resultSet.next()) {
		      id = resultSet.getLong("ultimo_id");  
		    }

		  } catch (SQLException e) {
		    e.printStackTrace();
		  } finally {
		    cerrarRecursos();
		  }

		  return id;

		}

	  private void cerrarRecursos() {
	    try {
	      if(resultSet != null) resultSet.close();
	      if(preparedStatement != null) preparedStatement.close();
	      if(connection != null) connection.close();
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	  }

}
