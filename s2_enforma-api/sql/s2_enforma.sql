delete from PagoEntity;
delete from ClienteEntity;
delete from DietaTipoEntity;
delete from DomicilioEntity;
delete from TarjetaPrepagoEntity;
delete from ComidaTipoEntity;
delete from AdministradorEntity;




insert into DomicilioEntity (id, fecha, lugarEntrega, costo) values (100, '2019-01-29 19:27:39', 'b', 36.96 );
insert into DomicilioEntity (id, fecha, lugarEntrega, costo) values (200, '2019-05-20 20:30:39', 'a', 40.2 );
insert into DomicilioEntity (id, fecha, lugarEntrega, costo, pago_id) values (78, '2019-05-20 20:30:39', 'a', 40000,56);


insert into TarjetaPrepagoEntity (id, idTarjetaPrepago, saldo, puntos) values (100, '12345', 12.12, 200.2 );
insert into TarjetaPrepagoEntity (id, idTarjetaPrepago, saldo, puntos) values (200, '54321', 21.21, 300.2 );


insert into ClienteEntity (id, nombre, edad, peso, objetivos, gluten, lactosa, userName, contrasenia) values (100, 'Juan', 25, 86.5, 'Tonificar', 0, 1, 'Jjuan', '987654321');
insert into ClienteEntity (id, nombre, edad, peso, objetivos, gluten, lactosa, userName, contrasenia) values (200, 'María', 20, 62.5, 'bajar de peso', 0, 0, 'mmaria', '123456789'); 


insert into DietaTipoEntity (id, nombre, caloriasMax, caloriasMin, cantidadGrasa, cantidadAzucar, cantidadFibra, tieneGluten, tieneLactosa) values (100, 'Detox', 25, 20, 50, 50, 50, 1, 0);
insert into DietaTipoEntity (id, nombre, caloriasMax, caloriasMin, cantidadGrasa, cantidadAzucar, cantidadFibra, tieneGluten, tieneLactosa) values (200, 'Dieta2', 30, 50, 20, 90, 50, 0, 0);

insert into ComidaTipoEntity (id, nombre, momentoDelDia,  calorias , menu) values (100,'Fusion', 'Desayuno', 300 , 'huevo,arepa,queso y chocolate' );
insert into ComidaTipoEntity (id, nombre, momentoDelDia,  calorias , menu) values (200,'Reto', 'Almuerzo', 400 , 'buriitoooototototototo' );

insert into AdministradorEntity (id, nombre, contrasena, username) values (100, 'Arri Ondricek', '09231yb370', 'Arri');
insert into AdministradorEntity (id, nombre, contrasena, username) values (200, 'Brandise Nary', '16144yv107', 'Brandise');
insert into AdministradorEntity (id, nombre, contrasena, username) values (300, 'Karel Tellenbrook', '97237ke365', 'Karel');

insert into PagoEntity (id, monto, esprepago, estadopago, orden_id) values (100, 20000, 0, 'Ok',200);
insert into PagoEntity (id, monto,numerotarjeta, esprepago, estadopago, orden_id) values (200, 40000,1234, 1, 'Ok',100);
insert into PagoEntity (id, monto,numerotarjeta, esprepago, estadopago, orden_id) values (56, 40000,1234, 1, 'Ok', 78);

