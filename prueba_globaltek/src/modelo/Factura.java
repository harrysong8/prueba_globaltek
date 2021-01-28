package modelo;

import java.util.Date;

public class Factura {

	int idFactura;
	int numeroFactura;
	Date fecha;
	String tipodePago;
	int documentoCliente;
	String nombreCliente;
	double subtotal;
	double descuento;
	double iva;
	double totalDescuento;
	double totalImpuesto;
	double total;
	
	public Factura() {
	}

	public Factura(int idFactura, int numeroFactura, Date fecha, String tipodePago, int documentoCliente,
			String nombreCliente, double subtotal, double descuento, double iva, double totalDescuento,
			double totalImpuesto, double total) {
		super();
		this.idFactura = idFactura;
		this.numeroFactura = numeroFactura;
		this.fecha = fecha;
		this.tipodePago = tipodePago;
		this.documentoCliente = documentoCliente;
		this.nombreCliente = nombreCliente;
		this.subtotal = subtotal;
		this.descuento = descuento;
		this.iva = iva;
		this.totalDescuento = totalDescuento;
		this.totalImpuesto = totalImpuesto;
		this.total = total;
	}
	
	public int getIdFactura() {
		return idFactura;
	}
	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}
	public int getNumeroFactura() {
		return numeroFactura;
	}
	public void setNumeroFactura(int numeroFactura) {
		this.numeroFactura = numeroFactura;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getTipodePago() {
		return tipodePago;
	}
	public void setTipodePago(String tipodePago) {
		this.tipodePago = tipodePago;
	}
	public int getDocumentoCliente() {
		return documentoCliente;
	}
	public void setDocumentoCliente(int documentoCliente) {
		this.documentoCliente = documentoCliente;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public double getDescuento() {
		return descuento;
	}
	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}
	public double getIva() {
		return iva;
	}
	public void setIva(double iva) {
		this.iva = iva;
	}
	public double getTotalDescuento() {
		return totalDescuento;
	}
	public void setTotalDescuento(double totalDescuento) {
		this.totalDescuento = totalDescuento;
	}
	public double getTotalImpuesto() {
		return totalImpuesto;
	}
	public void setTotalImpuesto(double totalImpuesto) {
		this.totalImpuesto = totalImpuesto;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}	
}
