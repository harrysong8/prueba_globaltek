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
				<form action="Controlador?menu=CrearFactura&accion=agregar" method="POST" >
					<div class="card-body">	
						<div class="form-group">
							<label>Datos del producto</label>
						</div>
						<div class="form-group d-flex">							
							<div class="col-sm-6 d-flex">
								<select class="form-select" id="codigo_producto" name="codigo_producto" >
									<option value="-1">--Seleccione--</option>  
									<c:forEach var="prs" items="${productos}" >
										<option value="${prs.getIdProducto()}/-/${prs.getProducto()}">${prs.getProducto()}</option>								
									</c:forEach>					
								</select>	
							</div>&nbsp;&nbsp;
							<div class="col-sm-6">
								<input type="number" name="cantidad" class="form-control" placeholder="Cantidad">
							</div>
						</div>	
						<br>
						<div class="form-group d-flex">		
							<div class="col-sm-6 d-flex">
								<input type="number" name="precio" class="form-control" placeholder="Precio">
							</div>&nbsp;&nbsp;	
							<div class="col-sm-6 d-flex">
								<button type="submit" name="accion" value="Agregar" class="btn btn-outline-primary">Agregar Producto</button>
							</div>						
						</div>
							
					</div>
				</form>
				<form action="Controlador?menu=CrearFactura&accion=generarFactura" method="POST" name="formulario" >
					<div class="card-body">
						<div class="form-group">
							<label>Datos del cliente</label>
						</div>
						<div class="form-group d-flex">
							<div class="col-sm-6 d-flex">
								<input type="text" name="documento_cliente" class="form-control" placeholder="Documento Cliente">
							</div>&nbsp;&nbsp;
							<div class="col-sm-6">
								<input type="text" name="nombre_cliente" class="form-control" placeholder="Nombre Cliente">
							</div>				
						</div>
						<br>
						<div class="form-group">
							<label>Datos Factura</label>
						</div>
						<div class="form-group d-flex">
							<div class="col-sm-6 d-flex">
								<input type="text" name="numero_factura" class="form-control" placeholder="Numero Factura">
							</div>&nbsp;&nbsp;
							<div class="col-sm-6">
								<input type="date" name="fecha" class="form-control" placeholder="fecha">
							</div>				
						</div>
						<br>
						<div class="form-group d-flex">
							<div class="col-sm-6 d-flex">								
								<select class="form-select" id="tipo_Pago" name="tipo_Pago" >
									<option value="-1">--Tipo de Pago--</option>
									<option value="Contado">Contado</option>								
									<option value="Credito">Credito</option>     
								</select>		
							</div>&nbsp;&nbsp;
							<div class="col-sm-6">
								<input type="text" name="descuento" class="form-control" placeholder="Descuento">
							</div>				
						</div>
						<br>
						
							
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
							<c:forEach var="df" items="${lista_df}" >
								<tr>
									<td>${df.getItem()}</td>
									<td>${df.getIdProducto()}</td>
									<td>${df.getDescripcion()}</td>
									<td>${df.getPrecio_unitario()}</td>
									<td>${df.getCantidad()}</td>
									<td>
										<a href="#" class="btn btn-danger">Eliminar</a>
									</td>
								</tr>
							</c:forEach>					
						</tbody>		
					</table>					
				</div>
				<div class="card-footer">
					<div>
						<a href="#" onclick="document.formulario.submit()" class="btn btn-success">Crear Factura</a>
						<a href="Controlador?menu=inicio&accion=Listar" class="btn btn-danger">Cancelar</a>
					</div>
				</div>
				
			</div>
		</div>
	</div>
	
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js" integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF" crossorigin="anonymous"></script>
</body>
</html>