package repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import model.Reserva;

public class ReservaRepository {
	private Conexion conexion = new Conexion();
	  
	  private Connection connection;
	  private PreparedStatement preparedStatement;
	  private ResultSet resultSet;
	  private MesaRepository mesa;
	  
	  public ReservaRepository(MesaRepository mesa) {
		  this.mesa = mesa;
	  }

	  public void guardarReserva(Reserva reserva) {
	    String sql = "INSERT INTO Reserva (fecha, nombre, apellido, comensales, nro_mesa) VALUES (?, ?, ?, ?, ?)";
	    
	    try {
	      connection = conexion.conectar();
	      preparedStatement = connection.prepareStatement(sql);
	      java.util.Date fechaUtil = reserva.getFecha();
	      java.sql.Date fechaSql = new java.sql.Date(fechaUtil.getTime());
	      preparedStatement.setDate(1, fechaSql);
	      preparedStatement.setString(2, reserva.getNombre());
	      preparedStatement.setString(3, reserva.getApellido());
	      preparedStatement.setInt(4, reserva.getCantidadComensales());
	      preparedStatement.setLong(5, reserva.getMesa().getNroMesa());
	      preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	      e.printStackTrace();
	    } finally {
	      cerrarRecursos();
	    }
	  }
	  
	  public HashMap<Long, Reserva> traerAMemoria(Long idResto) {
		  
		  HashMap<Long, Reserva> reservas = new HashMap<>();

		  String sql = "SELECT r.* " + 
	               "FROM reserva r " +
	               "INNER JOIN mesa m ON r.nro_mesa = m.nro_mesa " +  
	               "WHERE m.id_resto = ?";

		  try {
		    connection = conexion.conectar();
		    preparedStatement = connection.prepareStatement(sql);
		    preparedStatement.setLong(1, idResto);
		    resultSet = preparedStatement.executeQuery();
		    while (resultSet.next()) {
		      Reserva reserva = new Reserva();
		      reserva.setId(resultSet.getLong("id_reserva"));
		      reserva.setFecha(resultSet.getDate("fecha"));
		      reserva.setNombre(resultSet.getString("nombre"));
		      reserva.setApellido(resultSet.getString("apellido"));
		      reserva.setCantidadComensales(resultSet.getInt("comensales"));
		      reserva.setMesa(mesa.buscarMesaPorId(resultSet.getLong("nro_mesa")));
		      reservas.put(reserva.getId(), reserva);
		    }
		  } catch (SQLException e) {
		    e.printStackTrace();
		  } finally {
		    cerrarRecursos();
		  }

		  return reservas;
		  
		}


	  public long ultimoIdReserva() {

		  long id = 0;

		  String sql = "SELECT MAX(id_reserva) AS ultimo_id FROM reserva";

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
