package modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import config.Conexion;

public class ProductoDAO {

	Conexion cn = new Conexion();
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	public List listar() {
		String sql = "select * from productos";
		List<Producto> lista = new ArrayList<>();
		try {
			con = cn.Conexion();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Producto pr = new Producto();
				pr.setIdProducto(rs.getInt(1));
				pr.setProducto(rs.getString(2));								
				lista.add(pr);
			}
		} catch (Exception e) {
			
		}	
		return lista;
	}
	
}
