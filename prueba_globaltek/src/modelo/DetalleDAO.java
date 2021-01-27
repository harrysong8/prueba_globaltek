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
		String sql = "select * from facturas";
		List<Factura> lista = new ArrayList<>();
		try {
			con = cn.Conexion();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Factura fc = new Factura();
				fc.setIdFactura(rs.getInt(1));
				fc.setNumeroFactura(rs.getInt(2));
				fc.setFecha(rs.getDate(3));
				fc.setTipodePago(rs.getString(4));
				fc.setDocumentoCliente(rs.getInt(5));
				fc.setNombreCliente(rs.getString(6));
				fc.setSubtotal(rs.getDouble(7));
				fc.setDescuento(rs.getDouble(8));
				fc.setIva(rs.getDouble(9));
				fc.setTotalDescuento(rs.getDouble(10));
				fc.setTotalImpuesto(rs.getDouble(11));
				fc.setTotal(rs.getDouble(12));
								
				lista.add(fc);
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
//	public void eliminar(int id) {
//		
//	}
}
