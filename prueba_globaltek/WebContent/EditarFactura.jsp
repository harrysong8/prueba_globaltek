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
				<li class="nav-item active"><a class="nav-link "href="Controlador?menu=inicio&accion=Listar">Inicio</a></li>
			</ul>
		</div>
	</nav>
	
	
	<br><br>
	<div class="d-flex">
		<div class="col-sm-4" style="margin: 0 auto;">
			<div class="card"> 				
				<form action="Controlador?menu=EditarFactura&accion=agregar&factura=${Nrofactura}" method="POST" >
					<div class="card-body">	
						<div class="form-group">
							<label>Datos del producto</label>
						</div>
						           
						<div class="form-group d-flex">							
							<div class="col-sm-6 d-flex">
								<select class="form-select" id="codigo_producto" name="codigo_producto" required >
									<option value="">--Seleccione--</option>  
									<c:forEach var="prs" items="${productos}" >
										<option value="${prs.getIdProducto()}/-/${prs.getProducto()}">${prs.getProducto()}</option>								
									</c:forEach>					
								</select>	
							</div>&nbsp;&nbsp;
							<div class="col-sm-6">
								<input type="number" name="cantidad" class="form-control" placeholder="Cantidad" min="1" required>
							</div>
						</div>	
						<br>
						<div class="form-group d-flex">		
							<div class="col-sm-6 d-flex">
								<input type="number" name="precio" class="form-control" placeholder="Precio" min="1" required>
							</div>&nbsp;&nbsp;	
							<div class="col-sm-6 d-flex">
								<button type="submit" name="accion" value="Agregar" class="btn btn-outline-primary">Agregar Producto</button>
							</div>						
						</div>
							
					</div>
				</form>
				<form action="Controlador?menu=EditarFactura&accion=editarFactura&factura=${Nrofactura}" method="POST" name="formulario" id="formulario" >
					<div class="card-body">
						<div class="form-group">
							<label>Datos del cliente</label>
						</div>
						<div class="form-group d-flex">
							<div class="col-sm-6 d-flex">
								<input type="number" name="documento_cliente" id="documento_cliente" value="${documento_fc}" class="form-control" placeholder="Documento Cliente" required >
							</div>&nbsp;&nbsp;
							<div class="col-sm-6">
								<input type="text" name="nombre_cliente" id="nombre_cliente" value="${nombre_fc}" class="form-control" placeholder="Nombre Cliente" required>
							</div>				
						</div>
						<br>
						<div class="form-group">
							<label>Datos Factura</label>
						</div>
						<div class="form-group d-flex">
							<div class="col-sm-6 d-flex">
								<input type="number" name="numero_factura" value="${Nrofactura}" readonly id="numero_factura" class="form-control" placeholder="Numero Factura" required>
							</div>&nbsp;&nbsp;
							<div class="col-sm-6">
								<input type="date" name="fecha" id="fecha" value="${fecha_fc}" class="form-control" placeholder="fecha" required>
							</div>				
						</div>
						<br>
						<div class="form-group d-flex">
							<div class="col-sm-6 d-flex">								
								<select class="form-select" id="tipo_Pago" name="tipo_Pago" required>
									<option value="">--Tipo de Pago--</option>
									<option value="Contado" <c:if test="${tipo_pago_fc eq 'Contado'}">selected</c:if> >Contado</option>								
									<option value="Credito" <c:if test="${tipo_pago_fc eq 'Credito'}">selected</c:if> >Credito</option>     
								</select>		
							</div>&nbsp;&nbsp;
							<div class="col-sm-6">
								<input type="number" name="descuento" value="${descuento_fc}" id="descuento" class="form-control" placeholder="Descuento" maxlength="3" min="0" max="100" required>
							</div>				
						</div>
						<br>
						<input type="hidden" name="mensaje" value="${MsjValidaciones}" id="mensaje" > 
					</div>
				</form>
				
			</div>
		</div>
		<div class="col-sm-7" style="margin: 0 auto;">
			<div class="card">
				<div class="card-body">
					<table class="table table-hover table-striped">
						<thead>
							<tr>
								<th>Nro</th>
								<th>Codigo</th>
								<th>Descripcion</th>
								<th>Precio</th>
								<th>Cantidad</th>
								<th>Acciones</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="df" items="${lista_df}" varStatus="loop" >
								<tr>
									<td>${loop.index + 1}</td>
									<td>${df.getIdProducto()}</td>
									<td>${df.getDescripcion()}</td>
									<td>${df.getPrecio_unitario()}</td>
									<td>${df.getCantidad()}</td>
									<td>
										<a href="Controlador?menu=EditarFactura&accion=eliminarItem&elemento=${loop.index}&factura=${df.getIdFactura()}" class="btn btn-danger">Eliminar</a>
									</td>
								</tr>
							</c:forEach>					
						</tbody>		
					</table>					
				</div>
				<div class="card-footer">
					<div>
						<a href="#" onclick="validar()" class="btn btn-success">Editar Factura</a>
						<a href="Controlador?menu=inicio&accion=Listar" class="btn btn-danger">Cancelar</a>
					</div>
				</div>
				
			</div>
		</div>
	</div>
	
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js" integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF" crossorigin="anonymous"></script>
	<script >		
		$( document ).ready(function() {
			var mensaje = document.getElementById('mensaje').value;
	        if (mensaje.length > 0) {
	        	alert(mensaje);
	        }
	    });
	
		function validar() { 
			var documento = document.getElementById('documento_cliente').value;
			var nombre = document.getElementById('nombre_cliente').value;
			var fecha = document.getElementById('fecha').value;
			var tipo_Pago = document.getElementById('tipo_Pago').value;
			var descuento = document.getElementById('descuento').value;
			
			if (documento.length == 0) {
				alert('por favor ingresa el Documento del cliente!');
				$("#documento_cliente").focus();
				return;
			}
			if (documento < 0) {
				alert('El Documento del cliente no puede ser menor a cero!');
				$("#documento_cliente").focus();
				return;
			}
			
			if (nombre.length == 0) {
				alert('por favor ingresa el Nombre del cliente!');
				$("#nombre_cliente").focus();
				return;
			}

			if (fecha.length == 0) {
				alert('por favor seleccione la fecha!');
				$("#fecha").focus();
				return;
			}

			if (tipo_Pago.length == 0) {
				alert('por favor seleccione el tipo de pago!');
				$("#tipo_Pago").focus();
				return;
			}

			if (descuento.length == 0) {
				alert('por favor ingrese el descuento!');
				$("#descuento").focus();
				return;
			}
			if (descuento < 0) {
				alert('El descuento no puede ser menor a cero!');
				$("#descuento").focus();
				return;
			}
			
			document.formulario.submit();
		}
	</script>
</body>
</html>