package modelo;

import java.sql.Date;

public class Producto {

	int idProducto;
	String producto;
	
	public Producto() {
	}

	public Producto(int idProducto, String producto) {
		super();
		this.idProducto = idProducto;
		this.producto = producto;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}
	
}