CREATE DATABASE aerolinea_simulacro;

use aerolinea_simulacro;

CREATE TABLE avion(
	id_avion int primary key auto_increment,
    modelo varchar(255) not null,
    capacidad int not null
);

CREATE TABLE pasajero(
	id_pasajero int primary key auto_increment,
    nombre varchar(255) not null,
    apellido varchar(255) not null,
    documento_identidad varchar(255) not null
);

CREATE TABLE vuelo(
	id_vuelo int primary key auto_increment,
    destino varchar(255),
    fecha_salida DATE,
    hora_salida time,
    id_avion int,
    CONSTRAINT fk_id_avion foreign key(id_avion) REFERENCES avion(id_avion) on delete cascade 
);

CREATE TABLE reservacion(
	id_reservacion int primary key auto_increment,
    id_pasajero int,
    id_vuelo int,
    fecha_reservacion DATE,
    asiento varchar(255),
    CONSTRAINT fk_id_pasajero foreign key(id_pasajero) REFERENCES pasajero(id_pasajero) on delete cascade,
    CONSTRAINT fk_id_vuelo foreign key(id_vuelo) REFERENCES vuelo(id_vuelo) on delete cascade
);

-- Insertar Aviones
INSERT INTO avion(modelo,capacidad) VALUES("Dornier 328",30),
("Saab 340",20);

-- Insertar Pasajeros
INSERT INTO pasajero(nombre, apellido, documento_identidad) VALUES("Santiago","Echeverry", "1214753265"),
("Laura","Montoya","1234567891");

-- Insertar vuelo
INSERT INTO vuelo(destino, fecha_salida, hora_salida, id_avion) VALUES("Isnos","2024-07-07","10:45:00",1),
("Manizales","2024-07-30","20:45:00",2);

-- Insertar reservacion
INSERT INTO reservacion(id_pasajero, id_vuelo, fecha_reservacion, asiento) VALUES(1,1,"2024-05-05",10),(2,2,"2024-04-03", 4);

