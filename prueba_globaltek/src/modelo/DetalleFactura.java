package modelo;

import java.sql.Date;

public class DetalleFactura {
	int idDetalle;
	int idFactura;
	int idProducto;
	int cantidad;
	int precio_unitario;
	String descripcion;
	int item;
	
	public DetalleFactura() {
	}
	
	public DetalleFactura(int idDetalle, int idFactura, int idProducto, int cantidad, int precio_unitario, String descripcion, int item) {
		super();
		this.idDetalle = idDetalle;
		this.idFactura = idFactura;
		this.idProducto = idProducto;
		this.cantidad = cantidad;
		this.precio_unitario = precio_unitario;
		this.descripcion = descripcion;
		this.item = item;
	}
	public int getIdDetalle() {
		return idDetalle;
	}
	public void setIdDetalle(int idDetalle) {
		this.idDetalle = idDetalle;
	}
	public int getIdFactura() {
		return idFactura;
	}
	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}
	public int getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public int getPrecio_unitario() {
		return precio_unitario;
	}
	public void setPrecio_unitario(int precio_unitario) {
		this.precio_unitario = precio_unitario;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getItem() {
		return item;
	}
	public void setItem(int item) {
		this.item = item;
	}
}
