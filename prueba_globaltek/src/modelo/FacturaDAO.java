package modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import config.Conexion;

public class FacturaDAO {

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
	
	public String IdFactura() {
		String idFactura = "";
		String sql = "select max(idFactura) from Facturas";
		try {
			con = cn.Conexion();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				idFactura = rs.getString(1);
			}
		} catch (Exception e) {
			
		}
		if (idFactura == null)
			return "0";
		
		return idFactura;
	}
	
	public int guardarFactura(Factura fc) {
		String sql = "insert into facturas (idFactura, numeroFactura, fecha, tipodePago, documentoCliente, nombreCliente, subtotal, descuento, iva, totalDescuento, totalImpuesto, total) "
				+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
		try {
			con = cn.Conexion();
			ps = con.prepareStatement(sql);
			ps.setInt(1, fc.getIdFactura());
			ps.setInt(2, fc.getNumeroFactura());
			ps.setDate(3, fc.getFecha());						
			ps.setString(4, fc.getTipodePago());
			ps.setInt(5, fc.getDocumentoCliente());
			ps.setString(6, fc.getNombreCliente());
			ps.setDouble(7, fc.getSubtotal());
			ps.setDouble(8, fc.getDescuento());
			ps.setDouble(9, fc.getIva());		
			ps.setDouble(10, fc.getTotalDescuento());						
			ps.setDouble(11, fc.getTotalImpuesto());						
			ps.setDouble(12, fc.getTotal());						
			ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("error guardarFactura: "+e);
		}
		return r;
	}
	
	public int ActualizarSaldoFactura(Factura fc) {
		String sql = 	"update	facturas " + 
						"set 	subtotal  = ?, " + 
						"		totalDescuento = ?, " + 
						"		totalImpuesto = ?, " + 
						"		total = ? " + 
						"where	idFactura = ? ";
		try {
			con = cn.Conexion();
			ps = con.prepareStatement(sql);
			ps.setDouble(1, fc.getSubtotal());
			ps.setDouble(2, fc.getTotalDescuento());						
			ps.setDouble(3, fc.getTotalImpuesto());						
			ps.setDouble(4, fc.getTotal());
			ps.setInt(5, fc.getIdFactura());
			ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("error ActualizarSaldoFactura: "+e);
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
