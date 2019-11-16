

--delete from ClienteEntity_QuejasYReclamosEntity;
--delete from DietaTipoEntity_CalificacionEntity;
--delete from DietaTipoEntity_ClienteEntity;
--delete from DietaTipoEntity_ComidaTipoEntity;
--delete from TarjetaPrepagoEntity_PagoEntity;
delete from CalificacionEntity;
delete from QuejasYReclamosEntity;

delete from ComidaTipoEntity;
delete from PagoEntity;
delete from ClienteEntity;
delete from DietaTipoEntity;
delete from DomicilioEntity;
delete from TarjetaPrepagoEntity;
delete from AdministradorEntity;




insert into DomicilioEntity (id, fecha, lugarEntrega, costo) values (100, '2019-01-29 19:27:39', 'b', 36.96 );
insert into DomicilioEntity (id, fecha, lugarEntrega, costo) values (200, '2019-05-20 20:30:39', 'a', 40.2 );
--insert into DomicilioEntity (id, fecha, lugarEntrega, costo, pago_id) values (78, '2019-05-20 20:30:39', 'a', 40000,56);


insert into TarjetaPrepagoEntity (id, numTarjetaPrepago, saldo, puntos) values (100, '12345', 12.12, 200.2 );
insert into TarjetaPrepagoEntity (id, numTarjetaPrepago, saldo, puntos) values (200, '54321', 21.21, 300.2 );


insert into ClienteEntity (id, nombre, edad, peso, objetivos, gluten, lactosa, userName, contrasenia) values (100, 'Juan', 25, 86.5, 'Tonificar', 0, 1, 'Jjuan', '987654321');
insert into ClienteEntity (id, nombre, edad, peso, objetivos, gluten, lactosa, userName, contrasenia) values (200, 'María', 20, 62.5, 'bajar de peso', 0, 0, 'mmaria', '123456789'); 


insert into DietaTipoEntity (id, nombre, caloriasMax, caloriasMin, cantidadGrasa, cantidadAzucar, cantidadFibra, tieneGluten, tieneLactosa) values (100, 'Detox', 25, 20, 50, 50, 50, 1, 0);
insert into DietaTipoEntity (id, nombre, caloriasMax, caloriasMin, cantidadGrasa, cantidadAzucar, cantidadFibra, tieneGluten, tieneLactosa) values (200, 'Dieta2', 30, 50, 20, 90, 50, 0, 0);

insert into ComidaTipoEntity (id, nombre, momentoDelDia,  calorias , menu) values (100,'Fusion', 'Desayuno', 300 , 'huevo,arepa,queso y chocolate' );
insert into ComidaTipoEntity (id, nombre, momentoDelDia,  calorias , menu) values (200,'Reto', 'Almuerzo', 400 , 'buriitoooototototototo' );

insert into AdministradorEntity (id, nombre, contrasena, username) values (100, 'Arri Ondricek', '09231yb370', 'Arri');
insert into AdministradorEntity (id, nombre, contrasena, username) values (200, 'Brandise Nary', '16144yv107', 'Brandise');
insert into AdministradorEntity (id, nombre, contrasena, username) values (300, 'Karel Tellenbrook', '97237ke365', 'Karel');

insert into PagoEntity (id, monto, esprepago, estadopago) values (100, 2, 0, 'Ok');
insert into PagoEntity (id, monto,numerotarjeta, esprepago, estadopago) values (200, 4,1234, 1, 'Ok');
--insert into PagoEntity (id, monto,numerotarjeta, esprepago, estadopago, orden_id) values (56, 40000,1234, 1, 'Ok', 78);

insert into CalificacionEntity(id, puntaje, comentario, fecha, cliente_id, dietaTipo_id) values (100, 5, 'Excelente', '2019-10-10 05:31:02', 100, 100);
insert into CalificacionEntity(id, puntaje, comentario, fecha, cliente_id, dietaTipo_id) values (200, 3, 'Regular', '2019-10-10 05:32:02', 200, 200);
-- insert into CalificacionEntity(id, puntaje, comentario, fecha) values (300, 1, 'Malo', '2019-10-10 05:33:02');
-- insert into CalificacionEntity(id, puntaje, comentario, fecha) values (400, 5, '', '2019-10-10 05:34:02');

insert into QuejasYReclamosEntity(id, asunto, descripcion, fecha, cliente_id) values (100, 'Demora en la entrega', 'Se demoro bastante', '2019-10-10 05:31:02', 100);
insert into QuejasYReclamosEntity(id, asunto, descripcion, fecha, cliente_id) values (200, 'Comida fria', 'Ya no tenia ni sabor', '2019-10-10 05:32:02', 200);
-- insert into QuejasYReclamosEntity(id, asunto, descripcion, fecha) values (300, 'Lo pedi sin un ingrediente que traia', 'Especifique que sin salsa y me lo trajeron con salsa', '2019-10-10 05:33:02');