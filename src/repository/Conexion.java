package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	private String url = "jdbc:postgresql://localhost:5432/parcial2_progII";
	private String usuario = "postgres";
	private String contraseña = "ale301201";

	public Connection conectar() {
		try {
			Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
			return conexion;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean ping(String user, String password) {
		try {
			Connection connection = DriverManager.getConnection(url, user, password);
			connection.close();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
		System.out.println("Usuario establecido! " + this.usuario);
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
		System.out.println("Contraseña establecida! " + this.contraseña);
	}

}
