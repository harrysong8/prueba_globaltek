package controlador;

import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.DetalleDAO;
import modelo.DetalleFactura;
import modelo.Factura;
import modelo.FacturaDAO;
import modelo.Producto;
import modelo.ProductoDAO;

/**
 * Servlet implementation class Controlador
 */
@WebServlet("/Controlador")
public class Controlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controlador() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("test");
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    Factura fc = new Factura();
    FacturaDAO fcDao = new FacturaDAO();
    Producto pr = new Producto();
    DetalleFactura df = new DetalleFactura();
    DetalleDAO dfcDao = new DetalleDAO();
    ProductoDAO prDao = new ProductoDAO();
    List<DetalleFactura> lista_df = new ArrayList<>();
    List<Producto> lista_pr = new ArrayList<>();
    int item;
    int codigo;
    String descripcion;
    int cantidad;
    int precio;
    int nrofactura = 0;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String menu = request.getParameter("menu");
    	String accion = request.getParameter("accion");
    	
    	if (menu.equals("inicio")) {     	
	    	switch (accion) {
	    		case "Listar":
	    			List lista = fcDao.listar();
	    			request.setAttribute("facturas", lista);
	    			lista_df.clear();
	    	    	request.getRequestDispatcher("factura.jsp").forward(request, response);
	    			break;   	
	    		case "Editar":
	    			nrofactura = Integer.parseInt(request.getParameter("factura"));	    			
	    			lista_pr = prDao.listar();
	    				    			
	    			fc = fcDao.listar(nrofactura);
	    			request.setAttribute("documento_fc", fc.getDocumentoCliente());	
					request.setAttribute("nombre_fc", fc.getNombreCliente());	
					request.setAttribute("fecha_fc", fc.getFecha());	
					request.setAttribute("tipo_pago_fc", fc.getTipodePago());
					request.setAttribute("descuento_fc", fc.getDescuento());		
	    			
					lista_df = dfcDao.listarDetalleFactura(nrofactura);					
					
	    			request.setAttribute("Nrofactura", nrofactura);	
	    			request.setAttribute("productos", lista_pr);
	    			request.setAttribute("lista_df", lista_df);

	    	    	request.getRequestDispatcher("EditarFactura.jsp").forward(request, response);
					break;			
	    		case "Eliminar":
	    			nrofactura = Integer.parseInt(request.getParameter("factura"));
	    			fcDao.eliminar(nrofactura);
	    	    	request.getRequestDispatcher("index.jsp").forward(request, response);
					break;			
		    	default: 
	    			throw new AssertionError();    			
	    	}
    	} else if (menu.equals("CrearFactura")) {     	
	    	switch (accion) {
	    		case "crear":
	    			lista_pr = prDao.listar();
	    			request.setAttribute("productos", lista_pr);
	    			
	    			nrofactura = Integer.parseInt(fcDao.numeroFactura())+1;
	    			request.setAttribute("Nrofactura", nrofactura);	    			
	    			
	    			request.setAttribute("lista_df", lista_df);
	    			request.getRequestDispatcher("RegistrarFactura.jsp").forward(request, response);
	    			break; 	
	    		case "agregar":
	    			lista_pr = prDao.listar();
	    			request.setAttribute("productos", lista_pr);	    			

	    			nrofactura = Integer.parseInt(fcDao.numeroFactura())+1;
	    			request.setAttribute("Nrofactura", nrofactura);	    					
	    			
	    			item = item+1;
	    			String codigo_nombre = request.getParameter("codigo_producto");
	    			String[] parts = codigo_nombre.split("/-/");	    			
	    			codigo = Integer.parseInt(parts[0]);
	    			descripcion = parts[1];
	    			cantidad = Integer.parseInt(request.getParameter("cantidad"));
	    			precio = Integer.parseInt(request.getParameter("precio"));
	    				    			
	    			df = new DetalleFactura();
	    			df.setItem(item);
	    			df.setIdProducto(codigo);
	    			df.setDescripcion(descripcion);
	    			df.setCantidad(cantidad);
	    			df.setPrecio_unitario(precio);
	    			
	    			lista_df.add(df);
	    			request.setAttribute("lista_df", lista_df);	
	    			request.getRequestDispatcher("RegistrarFactura.jsp").forward(request, response);
					break;			
	    		case "generarFactura": 
	    			nrofactura = Integer.parseInt(fcDao.numeroFactura())+1;
	    			request.setAttribute("Nrofactura", nrofactura);	    			 			
	    			
	    			lista_pr = prDao.listar();
	    			request.setAttribute("productos", lista_pr);
	    			
	    			int idfactura = Integer.parseInt(fcDao.IdFactura());	    			
	    			idfactura = idfactura+1;
	    			fc.setIdFactura(idfactura);
	    			fc.setNumeroFactura(Integer.parseInt(request.getParameter("numero_factura")));	

	    		    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
					java.util.Date util_StartDate = null;
					try {
						util_StartDate = formato.parse(request.getParameter("fecha"));
						fc.setFecha(util_StartDate);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					String MsjValidaciones = "";	
					
					// Se valida que la fecha ingresada no sea menor a la fecha actual
					ZoneId zona = ZoneId.systemDefault();
					Date fecha = Date.from(LocalDate.now().atStartOfDay(zona).toInstant());
					if (util_StartDate.compareTo(fecha) < 0) {	
						MsjValidaciones = "La fecha es menor que la fecha actual.";
						request.setAttribute("MsjValidaciones", MsjValidaciones);			
					}
					
					// Se valida que se haya ingresado al menos un detalle
					if (lista_df.size() == 0) {
						MsjValidaciones = "No se ha ingresado ningún producto";
						request.setAttribute("MsjValidaciones", MsjValidaciones);					
					    
					} 
					
					if (MsjValidaciones != "") {
						request.setAttribute("documento_fc", request.getParameter("documento_cliente"));	
						request.setAttribute("nombre_fc", request.getParameter("nombre_cliente"));	
						request.setAttribute("fecha_fc", request.getParameter("fecha"));	
						request.setAttribute("tipo_pago_fc", request.getParameter("tipo_Pago"));
						request.setAttribute("descuento_fc", request.getParameter("descuento"));						
						
						request.getRequestDispatcher("RegistrarFactura.jsp").forward(request, response);
					    break;
					}
					
//					// Se ingresa la factura
					fc.setTipodePago(request.getParameter("tipo_Pago"));
					fc.setDocumentoCliente(Integer.parseInt(request.getParameter("documento_cliente")));	
					fc.setNombreCliente(request.getParameter("nombre_cliente"));	    			
	    			fc.setSubtotal(0);
	    			fc.setDescuento(Double.parseDouble(request.getParameter("descuento")));
	    			fc.setIva(19);
	    			fc.setTotalDescuento(0);
	    			fc.setTotalImpuesto(0);
	    			fc.setTotal(0);
	    			fcDao.guardarFactura(fc);
	    			
	    			double subtotal = 0;
	    			double totalDescuento = 0;
	    			double totalImpuesto = 0;
	    			double total = 0;
	    			
	    			// se ingresa el detalle de la factura
	    			for (int i=0; i<lista_df.size(); i++) {
	    				df = new DetalleFactura();
	    				df.setIdFactura(idfactura);
		    			df.setIdProducto(lista_df.get(i).getIdProducto());		    			
		    			df.setCantidad(lista_df.get(i).getCantidad());
		    			df.setPrecio_unitario(lista_df.get(i).getPrecio_unitario());
		    			
		    			subtotal += (lista_df.get(i).getPrecio_unitario()*lista_df.get(i).getCantidad());
		    			
		    			dfcDao.guardarDetalleFactura(df);		    			
		    			 
	    			}
	    			
	    			// Se calculan los descuentos y totales
	    			totalDescuento = subtotal * (Double.parseDouble(request.getParameter("descuento"))/100);
	    			double iva2 = 0.19;
	    			totalImpuesto = (subtotal-totalDescuento) * iva2;
	    			total = (subtotal-totalDescuento) + totalImpuesto;
	    			
	    			// Se actualizan en la base de datos
	    			fc.setSubtotal(subtotal);
	    			fc.setTotalDescuento(totalDescuento);
	    			fc.setTotalImpuesto(totalImpuesto);
	    			fc.setTotal(total);
	    			fc.setIdFactura(idfactura);
	    			fcDao.ActualizarSaldoFactura(fc);
	    			
	    			// se limpia el arreglo del detalle de la factura
	    			lista_df.clear();
					request.getRequestDispatcher("index.jsp").forward(request, response);
					break;			
	    		case "eliminarItem":
	    			nrofactura = Integer.parseInt(fcDao.numeroFactura())+1; 
	    			request.setAttribute("Nrofactura", nrofactura);	    			 			
	    			
	    			lista_pr = prDao.listar();
	    			request.setAttribute("productos", lista_pr);
	    			
	    	    	int elemento = Integer.parseInt(request.getParameter("elemento"));	
	    			lista_df.remove(elemento);
	    			request.setAttribute("lista_df", lista_df);
	    			request.getRequestDispatcher("RegistrarFactura.jsp").forward(request, response);	    			
					break;			
		    	default: 
	    			throw new AssertionError();    			
	    	}
	    	
    	} else if (menu.equals("EditarFactura")) {     	
	    	switch (accion) {
    		case "agregar":
    			lista_pr = prDao.listar();
    			nrofactura = Integer.parseInt(request.getParameter("factura"));	 
    			
    			item = item+1;
    			String codigo_nombre = request.getParameter("codigo_producto");
    			String[] parts = codigo_nombre.split("/-/");	    			
    			codigo = Integer.parseInt(parts[0]);
    			descripcion = parts[1];
    			cantidad = Integer.parseInt(request.getParameter("cantidad"));
    			precio = Integer.parseInt(request.getParameter("precio"));
    				    			
    			df = new DetalleFactura();
    			df.setItem(item);
    			df.setIdProducto(codigo);
    			df.setDescripcion(descripcion);
    			df.setCantidad(cantidad);
    			df.setPrecio_unitario(precio);
    			
    			lista_df.add(df);
    			request.setAttribute("lista_df", lista_df);	
    			
    			fc = fcDao.listar(nrofactura);
    			request.setAttribute("documento_fc", fc.getDocumentoCliente());	
				request.setAttribute("nombre_fc", fc.getNombreCliente());	
				request.setAttribute("fecha_fc", fc.getFecha());	
				request.setAttribute("tipo_pago_fc", fc.getTipodePago());
				request.setAttribute("descuento_fc", fc.getDescuento());		
    			request.setAttribute("Nrofactura", nrofactura);	
    			request.setAttribute("productos", lista_pr);

    	    	request.getRequestDispatcher("EditarFactura.jsp").forward(request, response);   
				break;			
    		case "editarFactura": 
    			nrofactura = Integer.parseInt(request.getParameter("factura"));	 
    			
    			request.setAttribute("Nrofactura", nrofactura);	    			 			
    			
    			lista_pr = prDao.listar();
    			request.setAttribute("productos", lista_pr);
    			
    			fc.setIdFactura(nrofactura);
    			fc.setNumeroFactura(Integer.parseInt(request.getParameter("numero_factura")));	

    		    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date util_StartDate = null;
				try {
					util_StartDate = formato.parse(request.getParameter("fecha"));
					fc.setFecha(util_StartDate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				String MsjValidaciones = "";	
				
				// Se valida que la fecha ingresada no sea menor a la fecha actual
				ZoneId zona = ZoneId.systemDefault();
				Date fecha = Date.from(LocalDate.now().atStartOfDay(zona).toInstant());
				if (util_StartDate.compareTo(fecha) < 0) {	
					MsjValidaciones = "La fecha es menor que la fecha actual.";
					request.setAttribute("MsjValidaciones", MsjValidaciones);			
				}
				
				// Se valida que se haya ingresado al menos un detalle
				if (lista_df.size() == 0) {
					MsjValidaciones = "No se ha ingresado ningún producto";
					request.setAttribute("MsjValidaciones", MsjValidaciones);					
				    
				} 
				
				if (MsjValidaciones != "") {
					request.setAttribute("documento_fc", request.getParameter("documento_cliente"));	
					request.setAttribute("nombre_fc", request.getParameter("nombre_cliente"));	
					request.setAttribute("fecha_fc", request.getParameter("fecha"));	
					request.setAttribute("tipo_pago_fc", request.getParameter("tipo_Pago"));
					request.setAttribute("descuento_fc", request.getParameter("descuento"));						
					
					request.getRequestDispatcher("EditarFactura.jsp").forward(request, response);
				    break;
				}
				
//				// Se ingresa la factura
				fc.setTipodePago(request.getParameter("tipo_Pago"));
				fc.setDocumentoCliente(Integer.parseInt(request.getParameter("documento_cliente")));	
				fc.setNombreCliente(request.getParameter("nombre_cliente"));	    			
    			fc.setDescuento(Double.parseDouble(request.getParameter("descuento")));
    			fc.setIva(19);
    			fcDao.actualizarFactura(fc);
    			
    			double subtotal = 0;
    			double totalDescuento = 0;
    			double totalImpuesto = 0;
    			double total = 0;
    			
    			// se elimina el detalle de la factura actual
    			dfcDao.eliminar(nrofactura);
    			
    			// se ingresa el detalle de la factura
    			for (int i=0; i<lista_df.size(); i++) {
    				df = new DetalleFactura();
    				df.setIdFactura(nrofactura);
	    			df.setIdProducto(lista_df.get(i).getIdProducto());		    			
	    			df.setCantidad(lista_df.get(i).getCantidad());
	    			df.setPrecio_unitario(lista_df.get(i).getPrecio_unitario());
	    			
	    			subtotal += (lista_df.get(i).getPrecio_unitario()*lista_df.get(i).getCantidad());
	    			
	    			dfcDao.guardarDetalleFactura(df);		    			 
    			}
    			
    			// Se calculan los descuentos y totales
    			totalDescuento = subtotal * (Double.parseDouble(request.getParameter("descuento"))/100);
    			double iva2 = 0.19;    			
    			totalImpuesto = (subtotal-totalDescuento) * iva2;
    			total = (subtotal-totalDescuento) + totalImpuesto;
    			
    			// Se actualizan en la base de datos
    			fc.setSubtotal(subtotal);
    			fc.setTotalDescuento(totalDescuento);
    			fc.setTotalImpuesto(totalImpuesto);
    			fc.setTotal(total);
    			fc.setIdFactura(nrofactura);
    			fcDao.ActualizarSaldoFactura(fc);
    			
    			// se limpia el arreglo del detalle de la factura
    			lista_df.clear();
				request.getRequestDispatcher("index.jsp").forward(request, response);
				break;			
    		case "eliminarItem":
    			nrofactura = Integer.parseInt(request.getParameter("factura"));	 
    			
    	    	int elemento = Integer.parseInt(request.getParameter("elemento"));	
    			lista_df.remove(elemento);    	
    			   			
    			lista_pr = prDao.listar();
    				    			
    			fc = fcDao.listar(nrofactura);
    			request.setAttribute("documento_fc", fc.getDocumentoCliente());	
				request.setAttribute("nombre_fc", fc.getNombreCliente());	
				request.setAttribute("fecha_fc", fc.getFecha());	
				request.setAttribute("tipo_pago_fc", fc.getTipodePago());
				request.setAttribute("descuento_fc", fc.getDescuento());		
    			request.setAttribute("Nrofactura", nrofactura);	
    			request.setAttribute("productos", lista_pr);
    			request.setAttribute("lista_df", lista_df);

    	    	request.getRequestDispatcher("EditarFactura.jsp").forward(request, response);    	    	
    	    	
				break;			
	    	default: 
    			throw new AssertionError();    			
    	}
    	
	} 
    	    	
    	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	

}
