SET MODE MySQL;



DROP TABLE INVENTARIO IF EXISTS;

CREATE TABLE INVENTARIO  (
    inventario_id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(80) NOT null,
    marca VARCHAR(30) NOT null,
    precio double(3) not null,
    cant_stock int(5) NOT NULL,
    estado varchar(15) NOT NULL,
    porcentaje_desc int(3) NOT null
);

DROP TABLE CARRITO IF EXISTS;

CREATE SEQUENCE car_sequence;

CREATE TABLE CARRITO  (
    carrito_id INT AUTO_INCREMENT PRIMARY KEY,
    inventario_id int NOT NULL,
    precio_total double(3) ,
    cantidad int NOT null ,
    estado varchar(15) NOT NULL
    
);


    










