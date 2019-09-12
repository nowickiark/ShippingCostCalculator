insert into BORDERS (id, country_from, city_from, country_to, city_to) values (nextval('bord_seq'), 'Poland', 'Zgorzelec', 'Germany', 'Gorlitz');

insert into ROLE (id, authority) values (nextval('role_seq'), 'DRIVER');
insert into ROLE (id, authority) values (nextval('role_seq'), 'SPEDYTOR');
insert into ROLE (id, authority) values (nextval('role_seq'), 'ADMIN');

insert INTO USER (username, password, role_id) values ('admin', '$2a$10$IPJizmA0rxJBq9incE/PWub6B2nIDGm3z/2cIz6hAs/hgvKlQ5F9q',  1);
insert INTO USER (username, password, role_id) values ('user', '$2a$10$KvxPudrieuxpEgxw3e4yPOuYK59PgfQshx3RaVUTCpbKB82DC/0RC', 1);
insert INTO USER (username, password, role_id) values ('super', '$2a$10$KvxPudrieuxpEgxw3e4yPOuYK59PgfQshx3RaVUTCpbKB82DC/0RC', 1);

insert into DRIVER(id,first_name,surname,phone_number,personal_id_number, user_username) values (nextval('driver_seq'),'AREK','NOWY','666987','123','admin');
insert into DRIVER(id,first_name,surname,phone_number,personal_id_number, user_username) values (nextval('driver_seq'),'JAN','KOWALSKI','545787','234','user');
insert into DRIVER(id,first_name,surname,phone_number,personal_id_number, user_username) values (nextval('driver_seq'),'STEFAMN','BURCZYMUCHA','687517','3987','super');



commit;