package controlador;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String menu = request.getParameter("menu");
    	String accion = request.getParameter("accion");
    	
    	if (menu.equals("inicio")) {     	
	    	switch (accion) {
	    		case "Listar":
	    			List lista = fcDao.listar();
	    			request.setAttribute("facturas", lista);
	    			break;    			
	    		case "Agregar":
	    			
					break;			
	    		case "Editar":
	    			
					break;			
	    		case "Eliminar":
	    			
					break;			
		    	default: 
	    			throw new AssertionError();    			
	    	}
	    	request.getRequestDispatcher("index.jsp").forward(request, response);
    	} else if (menu.equals("CrearFactura")) {     	
	    	switch (accion) {
	    		case "crear":
	    			lista_pr = prDao.listar();
	    			request.setAttribute("productos", lista_pr);
	    			request.setAttribute("lista_df", lista_df);
	    			break;    			
	    		case "agregar":
	    			lista_pr = prDao.listar();
	    			request.setAttribute("productos", lista_pr);
	    			
	    			item = item+1;
	    			String codigo_nombre = request.getParameter("codigo_producto");;
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
					break;			
	    		case "generarFactura":
	    			int idfactura = Integer.parseInt(fcDao.IdFactura());
	    			
	    			idfactura = idfactura+1;
	    			fc.setIdFactura(idfactura);
	    			fc.setNumeroFactura(Integer.parseInt(request.getParameter("numero_factura")));	

	    		    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
					java.util.Date util_StartDate;
					try {
						util_StartDate = formato.parse(request.getParameter("fecha"));
						java.sql.Date sql_StartDate = new java.sql.Date( util_StartDate.getTime() );	    	            
		    			fc.setFecha(sql_StartDate);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					// Se ingresa la factura
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
	    			
	    			System.out.println("subtotal: "+subtotal);
	    			System.out.println("totalDescuento: "+totalDescuento);
	    			System.out.println("totalImpuesto: "+totalImpuesto);
	    			System.out.println("total: "+total);
	    			
	    			// Se actualizan en la base de datos
	    			fc.setSubtotal(subtotal);
	    			fc.setTotalDescuento(totalDescuento);
	    			fc.setTotalImpuesto(totalImpuesto);
	    			fc.setTotal(total);
	    			fc.setIdFactura(idfactura);
	    			fcDao.ActualizarSaldoFactura(fc);
	    			
	    			// se limpia el arreglo del detalle de la factura
	    			lista_df.clear();
					break;			
	    		case "Eliminar":
	    			
					break;			
		    	default: 
	    			throw new AssertionError();    			
	    	}
	    	request.getRequestDispatcher("RegistrarFactura.jsp").forward(request, response);
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
