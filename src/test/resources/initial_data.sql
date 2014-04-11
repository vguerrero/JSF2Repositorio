insert into rol (nombre) values ('user');
insert into rol (nombre) values ('admin');
insert into rol (nombre) values ('secretary');
insert into rol (nombre) values ('other role');

insert into Usuario (fechacrea,fechaedita,nombre,password,usuariocrea,usuarioedita) values ('2013-01-03','2013-01-03','admin','MRgS3jdbvH1TJ_Tv3muCww','admin','admin');
insert into Usuario (fechacrea,fechaedita,nombre,password,usuariocrea,usuarioedita) values ('2013-01-03','2013-01-03','admin1','MRgS3jdbvH1TJ_Tv3muCww','admin','admin');
insert into Usuario (fechacrea,fechaedita,nombre,password,usuariocrea,usuarioedita) values ('2013-01-03','2013-01-03','admin2','MRgS3jdbvH1TJ_Tv3muCww','admin','admin');
insert into Usuario (fechacrea,fechaedita,nombre,password,usuariocrea,usuarioedita) values ('2013-01-03','2013-01-03','secretaria','MRgS3jdbvH1TJ_Tv3muCww','admin','admin');

--roles x usuario
insert into RolesxUsuario(UsuarioId,RolId) values(1,1);
insert into RolesxUsuario(UsuarioId,RolId) values(1,2);
insert into RolesxUsuario(UsuarioId,RolId) values(2,1);
insert into RolesxUsuario(UsuarioId,RolId) values(2,2);
insert into RolesxUsuario(UsuarioId,RolId) values(3,1);
insert into RolesxUsuario(UsuarioId,RolId) values(3,2);
insert into RolesxUsuario(UsuarioId,RolId) values(4,1);
insert into RolesxUsuario(UsuarioId,RolId) values(4,3);

