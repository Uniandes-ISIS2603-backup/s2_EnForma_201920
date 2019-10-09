delete from AdministradorEntity;
delete from CalificacionEntity;
delete from ClienteEntity;
delete from ComidaTipoEntity;
delete from DietaTipoEntity;
delete from DomicilioEntity;
delete from PagoEntity;
delete from QuejasYReclamosEntity;
delete from TarjetaPrepagoEntity;



insert into DomicilioEntity (id, fecha, lugarEntrega, costo) values (100, '4/7/1965', 12.12 );
insert into DomicilioEntity (id, fecha, lugarEntrega, costo) values (200, '10/7/1965', 20.12 );

insert into TarjetaPrepagoEntity (id, numTarjetaPrepago, saldo, puntos) values (100, '12345', 12.12, 200.2 );
insert into TarjetaPrepagoEntity (id, numTarjetaPrepago, saldo, puntos) values (200, '54321', 21.21, 300.2 );


insert into ClienteEmtity (nombre, edad, peso, objetivos, gluten, lactosa, userName, contrasenia) values ('Juan', 25, 86.5, 'Tonificar', false, true, 'Jjuan', '987654321');
insert into ClienteEmtity (nombre, edad, peso, objetivos, gluten, lactosa, userName, contrasenia) values ('María', 20, 62.5, 'bajar de peso', false, false, 'mmaria', '123456789'); 