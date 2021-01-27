package config;

import java.sql.Connection;
import java.sql.DriverManager;
import com.microsoft.sqlserver.jdbc.SQLServerDriver;

public class Conexion {
	
	Connection con;
	String url2 = "jdbc:sqlserver://localhost\\SQLPRUEBAS:1433;databaseName=pruebas";
	String url3 = "jdbc:sqlserver://localhost:<port>;databaseName=AdventureWorks;user=<user>;password=<password>";
	String url = "jdbc:sqlserver://localhost\\SQLPRUEBAS;databaseName=pruebas;";
//				"databaseName=pruebas;user=sa;password=4dm1n123456.;"; 
	String user = "sa";
	String pass = "4dm1n123456.";
	
//	localhost\SQLPRUEBAS
//	sa
//	4dm1n123456.
//	pruebas
	
	public Connection Conexion() {
		try {
			//Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());       
			// DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());   		       
			con = DriverManager.getConnection(url, user, pass);
		} catch (Exception e) {
			System.out.println("error en conexion: "+e);
		}
		return con;
	}
}