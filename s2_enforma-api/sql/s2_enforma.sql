

--delete from ClienteEntity_QuejasYReclamosEntity;
--delete from DietaTipoEntity_CalificacionEntity;
--delete from DietaTipoEntity_ClienteEntity;
--delete from DietaTipoEntity_ComidaTipoEntity;
--delete from TarjetaPrepagoEntity_PagoEntity;
delete from CalificacionEntity;

-- delete from ClienteEntity_QuejasYReclamosEntity;
delete from QuejasYReclamosEntity;
--delete from DietaTipoEntity_ClienteEntity;
--delete from DietaTipoEntity_CalificacionEntity;
--delete from DietaTipoEntity_ComidaTipoEntity;
-- delete from TarjetaPrepagoEntity_PagoEntity;
delete from CalificacionEntity;
delete from PagoEntity;
delete from DietaTipoEntity;
delete from DomicilioEntity;
delete from TarjetaPrepagoEntity;
delete from AdministradorEntity;
delete from ComidaTipoEntity;
delete from ClienteEntity;




insert into DomicilioEntity (id, fecha, lugarEntrega, costo) values (100, '2019-01-29 19:27:39', 'b', 36.96 );
insert into DomicilioEntity (id, fecha, lugarEntrega, costo) values (200, '2019-05-20 20:30:39', 'a', 40.2 );
--insert into DomicilioEntity (id, fecha, lugarEntrega, costo, pago_id) values (78, '2019-05-20 20:30:39', 'a', 40000,56);


insert into TarjetaPrepagoEntity (id, numTarjetaPrepago, saldo, puntos) values (100, '12345', 12.12, 200.2 );
insert into TarjetaPrepagoEntity (id, numTarjetaPrepago, saldo, puntos) values (200, '54321', 21.21, 300.2 );


insert into ClienteEntity (id, nombre, edad, peso, objetivos, gluten, lactosa, userName, contrasenia) values (100, 'Juanse', 25, 86.5, 'Tonificar', 0, 1, 'Juanse', 'contraseña1');
insert into ClienteEntity (id, nombre, edad, peso, objetivos, gluten, lactosa, userName, contrasenia) values (200, 'Panis', 20, 62.5, 'bajar de peso', 0, 0, 'Panis', 'contraseña2');
insert into ClienteEntity (id, nombre, edad, peso, objetivos, gluten, lactosa, userName, contrasenia) values (300, 'Sofi', 25, 86.5, 'Tonificar', 0, 1, 'Sofi', 'contraseña3');
insert into ClienteEntity (id, nombre, edad, peso, objetivos, gluten, lactosa, userName, contrasenia) values (400, 'Nacho', 20, 62.5, 'bajar de peso', 0, 0, 'Nacho', 'contraseña4');
insert into ClienteEntity (id, nombre, edad, peso, objetivos, gluten, lactosa, userName, contrasenia) values (500, 'Jimmy', 25, 86.5, 'Tonificar', 0, 1, 'Jimmy', 'contraseña5');
insert into ClienteEntity (id, nombre, edad, peso, objetivos, gluten, lactosa, userName, contrasenia) values (600, 'Julio', 20, 62.5, 'bajar de peso', 0, 0, 'Julio', 'contraseña6'); 


insert into DietaTipoEntity (id, nombre, caloriasMax, caloriasMin, cantidadGrasa, cantidadAzucar, cantidadFibra, tieneGluten, tieneLactosa) values (100, 'Detox', 25, 20, 50, 50, 50, 1, 0);
insert into DietaTipoEntity (id, nombre, caloriasMax, caloriasMin, cantidadGrasa, cantidadAzucar, cantidadFibra, tieneGluten, tieneLactosa) values (200, 'Dieta2', 30, 50, 20, 90, 50, 0, 0);

insert into ComidaTipoEntity (id, nombre, momentoDelDia,  calorias , menu) values (100,'Fusion', 'Desayuno', 300 , 'huevo,arepa,queso y chocolate' );
insert into ComidaTipoEntity (id, nombre, momentoDelDia,  calorias , menu) values (200,'Reto', 'Almuerzo', 400 , 'buriitoooototototototo' );
insert into ComidaTipoEntity (id, nombre, momentoDelDia,  calorias , menu) values (37,'LA COSOTA', 'Desayuno', 350 , 'choizoooo' );
insert into ComidaTipoEntity (id, nombre, momentoDelDia,  calorias , menu) values (38,'E PESTU A LA RUBBY', 'Almuerzo', 300 , 'E LU TRae TUDU' );
insert into ComidaTipoEntity (id, nombre, momentoDelDia,  calorias , menu) values (300,'LOLA', 'Desayuno', 300 , 'Chiguiro' );
insert into ComidaTipoEntity (id, nombre, momentoDelDia,  calorias , menu) values (400,'ROSA', 'Almuerzo', 450 , 'pan' );

insert into AdministradorEntity (id, nombre, contrasena, username) values (100, 'Arri Ondricek', 'contraseña1', 'AdminJuanse');
insert into AdministradorEntity (id, nombre, contrasena, username) values (200, 'Brandise Nary', 'contraseña2', 'AdminPanis');
insert into AdministradorEntity (id, nombre, contrasena, username) values (300, 'Karel Tellenbrook', 'contraseña3', 'AdminSofi');
insert into AdministradorEntity (id, nombre, contrasena, username) values (400, 'Arri Ondricek', 'contraseña4', 'AdminNacho');
insert into AdministradorEntity (id, nombre, contrasena, username) values (500, 'Brandise Nary', 'contraseña5', 'AdminJimmy');
insert into AdministradorEntity (id, nombre, contrasena, username) values (600, 'Karel Tellenbrook', 'contraseña6', 'AdminJulio');

insert into PagoEntity (id, monto, esprepago, estadopago) values (100, 2, 0, 'Ok');
insert into PagoEntity (id, monto,numerotarjeta, esprepago, estadopago) values (200, 4,1234, 1, 'Ok');
--insert into PagoEntity (id, monto,numerotarjeta, esprepago, estadopago, orden_id) values (56, 40000,1234, 1, 'Ok', 78);

insert into CalificacionEntity(id, puntaje, comentario, fecha, cliente_id, dietaTipo_id) values (100, 5, 'Excelente', '2019-10-10 05:31:02', 100, 100);
insert into CalificacionEntity(id, puntaje, comentario, fecha, cliente_id, dietaTipo_id) values (200, 3, 'Regular', '2019-10-10 05:32:02', 200, 200);
-- insert into CalificacionEntity(id, puntaje, comentario, fecha) values (300, 1, 'Malo', '2019-10-10 05:33:02');
-- insert into CalificacionEntity(id, puntaje, comentario, fecha) values (400, 5, '', '2019-10-10 05:34:02');

insert into QuejasYReclamosEntity(id, asunto, descripcion, fecha, cliente_id, domicilio_id) values (100, 'Demora en la entrega', 'Se demoro bastante', '2019-10-10 05:31:02', 100, 100);
insert into QuejasYReclamosEntity(id, asunto, descripcion, fecha, cliente_id, domicilio_id) values (200, 'Comida fria', 'Ya no tenia ni sabor', '2019-10-10 05:32:02', 200, 200);
-- insert into QuejasYReclamosEntity(id, asunto, descripcion, fecha) values (300, 'Lo pedi sin un ingrediente que traia', 'Especifique que sin salsa y me lo trajeron con salsa', '2019-10-10 05:33:02');

insert into DomicilioEntity (id, fecha, lugarEntrega, costo, cliente_id, comidaTipo_id) values (896, '2019-01-29 19:27:39', 'b', 356.6, 400, 200 );
insert into DomicilioEntity (id, fecha, lugarEntrega, costo, cliente_id, comidaTipo_id) values (45, '2019-01-29 19:27:39', 'b', 356.6, 300, 100 );
insert into DomicilioEntity (id, fecha, lugarEntrega, costo, cliente_id, comidaTipo_id) values (36, '2019-01-29 19:27:39', 'b', 356.6, 500, 200 );
insert into DomicilioEntity (id, fecha, lugarEntrega, costo, cliente_id, comidaTipo_id) values (786, '2019-01-29 19:27:39', 'b', 356.6, 200, 100 );

insert into PagoEntity (id, monto, esprepago, estadopago, orden_id) values (56, 2, 0, 'Ok',896);
insert into PagoEntity (id, monto,numerotarjeta, esprepago, estadopago, orden_id) values (87, 4,1234, 1, 'Ok',45);
insert into PagoEntity (id, monto, esprepago, estadopago, orden_id) values (657, 2, 0, 'Ok',36);
insert into PagoEntity (id, monto,numerotarjeta, esprepago, estadopago, orden_id) values (456, 4,1234, 1, 'Ok',786);


insert into TarjetaPrepagoEntity (id, numTarjetaPrepago, saldo, puntos, cliente_id) values (6556, '12345', 12.12, 200.2, 200 );
insert into TarjetaPrepagoEntity (id, numTarjetaPrepago, saldo, puntos, cliente_id) values (2074470, '54321', 21.21, 300.2, 300 );
insert into TarjetaPrepagoEntity (id, numTarjetaPrepago, saldo, puntos, cliente_id) values (65533, '12345', 12.12, 200.2, 400 );
insert into TarjetaPrepagoEntity (id, numTarjetaPrepago, saldo, puntos, cliente_id) values (87470, '54321', 21.21, 300.2, 500 );

insert into PagoEntity (id, monto,numerotarjeta, esprepago, estadopago, tarjetaprepago_id) values (56387, 4,1234, 1, 'Ok',6556);
insert into PagoEntity (id, monto, esprepago, estadopago, tarjetaprepago_id) values (465, 2, 0, 'Ok',2074470);
insert into PagoEntity (id, monto,numerotarjeta, esprepago, estadopago, tarjetaprepago_id) values (3567, 4,1234, 1, 'Ok',2074470);

