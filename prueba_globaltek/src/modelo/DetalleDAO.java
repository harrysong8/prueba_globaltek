package modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import config.Conexion;

public class DetalleDAO {

	Conexion cn = new Conexion();
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	int r;
	
	public List listar() {
		String sql = "select * from detalles";
		List<DetalleFactura> lista = new ArrayList<>();
		try {
			con = cn.Conexion();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				DetalleFactura dfc = new DetalleFactura();
				dfc.setIdDetalle(rs.getInt(1));
				dfc.setIdFactura(rs.getInt(2));
				dfc.setIdProducto(rs.getInt(3));
				dfc.setCantidad(rs.getInt(4));
				dfc.setPrecio_unitario(rs.getInt(5));
				dfc.setDescripcion(rs.getString(6));

				lista.add(dfc);
			}
		} catch (Exception e) {
			
		}	
		return lista;
	}
	

	public List listarDetalleFactura(int id) {
		String sql = "select d.iddetalle, d.idfactura, d.idProducto, d.cantidad, d.precio_unitario, p.producto " + 
				"from detalles d " + 
				"inner join Productos p on (d.idProducto = p.idProducto) " + 
				"where idfactura = "+id;
		List<DetalleFactura> lista = new ArrayList<>();
		try {
			con = cn.Conexion();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				DetalleFactura dfc = new DetalleFactura();
				dfc.setIdDetalle(rs.getInt(1));
				dfc.setIdFactura(rs.getInt(2));
				dfc.setIdProducto(rs.getInt(3));
				dfc.setCantidad(rs.getInt(4));
				dfc.setPrecio_unitario(rs.getInt(5));
				dfc.setDescripcion(rs.getString(6));

				lista.add(dfc);
			}
		} catch (Exception e) {
			
		}	
		return lista;
	}
	
	public int guardarDetalleFactura(DetalleFactura dfc) {
		String sql = "insert into Detalles (idDetalle, idFactura, idProducto, cantidad, precio_unitario) values (NEXT VALUE FOR detalle_seq, ?, ?, ?, ?)";
		try {
			con = cn.Conexion();
			ps = con.prepareStatement(sql);
			ps.setInt(1, dfc.getIdFactura());
			ps.setInt(2, dfc.getIdProducto());
			ps.setInt(3, dfc.getCantidad());
			ps.setInt(4, dfc.getPrecio_unitario());					
			ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("error guardarDetalleFactura: "+e);
		}
		return r;
	}
	
//	public int agregar() {
//		
//	}
//	public int actualizar() {
//		
//	}
	public void eliminar(int id) {
		String sql = "delete from Detalles where idFactura="+id;
		try {
			con = cn.Conexion();
			ps = con.prepareStatement(sql);							
			ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("error guardarDetalleFactura: "+e);
		}
	}
}
