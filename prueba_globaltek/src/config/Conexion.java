package config;

import java.sql.Connection;
import java.sql.DriverManager;
import com.microsoft.sqlserver.jdbc.SQLServerDriver;

public class Conexion {
	
	Connection con;
	String url = "jdbc:sqlserver://localhost\\SQLPRUEBAS;databaseName=pruebas;";
	String user = "sa";
	String pass = "4dm1n123456.";
		
	public Connection Conexion() {
		try {
			DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());       		       
			con = DriverManager.getConnection(url, user, pass);
		} catch (Exception e) {
			System.out.println("error en conexion: "+e);
		}
		return con;
	}
}