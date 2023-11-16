package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import excepciones.MesaConReservasException;
import model.Mesa;

public class MesaRepository {
	 private Conexion conexion = new Conexion();
	  
	  private Connection connection;
	  private PreparedStatement preparedStatement;
	  private ResultSet resultSet;

	  public void guardarMesa(Mesa mesa, Long idResto) {
	    
	    String sql = "INSERT INTO Mesa (estado, capacidad, consumo, id_resto) VALUES (?, ?, ?, ?)";
	    
	    try {
	      connection = conexion.conectar();
	      preparedStatement = connection.prepareStatement(sql);
	      preparedStatement.setString(1, mesa.enQueEstadoEstoy());
	      preparedStatement.setInt(2, mesa.getCapacidad());
	      preparedStatement.setInt(3, (int) mesa.getConsumo());
	      preparedStatement.setLong(4, idResto);
	      preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	      e.printStackTrace();
	    } finally {
	      cerrarRecursos(); 
	    }
	  }
	  
	  public HashMap<Long, Mesa> traerAMemoria(Long idResto) {

		  HashMap<Long, Mesa> mesas = new HashMap<>();

		  String sql = "SELECT * FROM Mesa WHERE id_resto = ?";
		  
		  try {
		    connection = conexion.conectar();
		    preparedStatement = connection.prepareStatement(sql);
		    preparedStatement.setLong(1, idResto);
		    resultSet = preparedStatement.executeQuery();
		    while (resultSet.next()) {
		      Mesa mesa = new Mesa();
		      mesa.setNroMesa(resultSet.getLong("nro_mesa"));
		      mesa.setEstado(resultSet.getString("estado"));
		      mesa.setCapacidad(resultSet.getInt("capacidad"));
		      mesa.setConsumo(resultSet.getInt("consumo"));

		      mesas.put(mesa.getNroMesa(), mesa);
		    }
		  } catch (SQLException e) {
		    e.printStackTrace();
		  } finally {
		    cerrarRecursos();
		  }

		  return mesas;
		}
	  
	  public Mesa buscarMesaPorId(Long nroMesa) {
		  String sql = "SELECT * FROM mesa WHERE nro_mesa = ?;";
		  Mesa m = new Mesa();
		  try {
		    connection = conexion.conectar();
		    preparedStatement = connection.prepareStatement(sql);
		    preparedStatement.setLong(1, nroMesa);
		    resultSet = preparedStatement.executeQuery();
		    while(resultSet.next()) {
		    
		    	m.setNroMesa(resultSet.getLong("nro_mesa"));
		    	m.setEstado(resultSet.getString("estado"));
		    	m.setCapacidad(resultSet.getInt("capacidad"));
		    	m.setConsumo(resultSet.getInt("consumo"));
		    }
		  }catch(SQLException e) {
			  e.printStackTrace();
			  return null;
		  }finally {
			  cerrarRecursos();
		  }
		return m;
	  }
	  
	  public long ultimoIdMesa() {

		  long id = 0;
		  
		  String sql = "SELECT MAX(nro_mesa) AS ultimo_id FROM mesa";

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
	  
	  public void eliminarMesa(long idMesa) throws MesaConReservasException {

		  String sql = "SELECT COUNT(*) AS cantidad FROM Reserva WHERE nro_mesa = ?";
		  
		  try {
			connection = conexion.conectar();
		    preparedStatement = connection.prepareStatement(sql);
		    preparedStatement.setLong(1, idMesa);

		    ResultSet rs = preparedStatement.executeQuery();
		    if (rs.next() && rs.getInt("cantidad") > 0) {
		      throw new MesaConReservasException("La mesa tiene reservas asociadas"); 
		    }

		    sql = "DELETE FROM mesa WHERE nro_mesa = ?";
		    preparedStatement = connection.prepareStatement(sql);
		    preparedStatement.setLong(1, idMesa);
		    preparedStatement.executeUpdate();

		  } catch (MesaConReservasException e) {
		    throw e;
		  } catch (SQLException e) {
		    e.printStackTrace();
		  }finally {
			  cerrarRecursos();
		  }

		}
	  
	  public void editarEstadoMesa(long idMesa, String nuevoEstado) {

		  String sql = "UPDATE mesa SET estado = ? WHERE nro_mesa = ?";
		  
		  Connection conn = null;  
		  PreparedStatement stmt = null;

		  try {
		    conn = conexion.conectar();
		    stmt = conn.prepareStatement(sql);
		    stmt.setString(1, nuevoEstado);
		    stmt.setLong(2, idMesa);
		    stmt.executeUpdate();
		  } catch (SQLException e) {
		    e.printStackTrace();
		  } finally {
		    cerrarRecursos();
		  }
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