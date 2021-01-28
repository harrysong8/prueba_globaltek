<%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Prueba</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-info">
		
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link" href="Controlador?menu=inicio&accion=Listar">Inicio</a></li>
			</ul>
		</div>
	</nav>
	
	<br><br>
	
	<div class="card">
		<div class="card-body">
			<div class="form-group">
				<label>Listado de facturas</label>			
				<div style="float:right;">
					<a href="Controlador?menu=CrearFactura&accion=crear" class="btn btn-success">Crear Factura</a>
				</div>
			</div><br>
			<table class="table table-hover table-striped">
				<thead>
					<tr>
						<th>numero Factura</th>
						<th>fecha</th>
						<th>tipo de Pago</th>
						<th>documento Cliente</th>
						<th>nombre Cliente</th>
						<th>subtotal</th>
						<th>descuento</th>
						<th>iva</th>
						<th>total Descuento</th>
						<th>total Impuesto</th>
						<th>total</th>
						<th>Acciones</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="fcs" items="${facturas}" >
						<tr>
							<td>${fcs.getNumeroFactura()}</td>
							<td>${fcs.getFecha()}</td>
							<td>${fcs.getTipodePago()}</td>
							<td>${fcs.getDocumentoCliente()}</td>
							<td>${fcs.getNombreCliente()}</td>
							<td>${fcs.getSubtotal()}</td>
							<td>${fcs.getDescuento()}</td>
							<td>${fcs.getIva()}</td>
							<td>${fcs.getTotalDescuento()}</td>
							<td>${fcs.getTotalImpuesto()}</td>
							<td>${fcs.getTotal()}</td>
							<td>
								<div class="d-flex">
									<a href="Controlador?menu=inicio&accion=Editar&factura=${fcs.getIdFactura()}" class="btn btn-warning">Editar</a>&nbsp;&nbsp;
									<a href="Controlador?menu=inicio&accion=Eliminar&factura=${fcs.getIdFactura()}" class="btn btn-danger">Eliminar</a>
								</div>
							</td>
						</tr>
					</c:forEach>					
				</tbody>		
			</table>
		</div>		
	</div>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js" integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF" crossorigin="anonymous"></script>
	
</body>
</html>