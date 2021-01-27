CREATE DATABASE Pruebas;


CREATE TABLE Productos (
	idProducto int NOT NULL PRIMARY KEY,  
	producto varchar(255) NOT NULL
); 

CREATE TABLE Facturas (
	idFactura int NOT NULL PRIMARY KEY,
	numeroFactura int NOT NULL,
	fecha date, 
	tipodePago varchar(20) NOT NULL,
	documentoCliente int NOT NULL,
	nombreCliente varchar(100) NOT NULL,
	subtotal decimal(20,2) NOT NULL,
	descuento decimal(10,2) NOT NULL,
	iva decimal(10,2) NOT NULL,
	totalDescuento decimal(20,2) NOT NULL,
	totalImpuesto decimal(20,2) NOT NULL, 
	total decimal(20,2) NOT NULL
);

CREATE TABLE Detalles (
	idDetalle int NOT NULL PRIMARY KEY,  
	idFactura int NOT NULL,  
	idProducto int NOT NULL,  
	cantidad int NOT NULL,  
	precio_unitario int NOT NULL, 
	FOREIGN KEY (idProducto) REFERENCES Productos(idProducto), 
	FOREIGN KEY (idFactura) REFERENCES Facturas(idFactura)
); 

CREATE SEQUENCE producto_seq
    START WITH 1  
    INCREMENT BY 1 ;  
GO  

CREATE SEQUENCE factura_seq
    START WITH 1  
    INCREMENT BY 1 ;  
GO  

CREATE SEQUENCE detalle_seq 
    START WITH 1  
    INCREMENT BY 1 ;  
GO  

insert into Productos values (NEXT VALUE FOR producto_seq, 'Camisa');
insert into Productos values (NEXT VALUE FOR producto_seq, 'pantalon');
insert into Productos values (NEXT VALUE FOR producto_seq, 'Zapatos');
insert into Productos values (NEXT VALUE FOR producto_seq, 'Tenis');
insert into Productos values (NEXT VALUE FOR producto_seq, 'Falda');
insert into Productos values (NEXT VALUE FOR producto_seq, 'Blusa');



