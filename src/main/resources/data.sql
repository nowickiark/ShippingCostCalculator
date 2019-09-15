insert into BORDERS (id, country_from, city_from, country_to, city_to) values (nextval('bord_seq'), 'Poland', 'Zgorzelec', 'Germany', 'Gorlitz');

insert into ROLE (id, user_authority) values (nextval('role_seq'), 'DRIVER');
insert into ROLE (id, user_authority) values (nextval('role_seq'), 'SPEDYTOR');
insert into ROLE (id, user_authority) values (nextval('role_seq'), 'ADMIN');

insert INTO USER (username, password, role_id) values ('admin', '$2a$10$IPJizmA0rxJBq9incE/PWub6B2nIDGm3z/2cIz6hAs/hgvKlQ5F9q',  1);
insert INTO USER (username, password, role_id) values ('driver', '$2a$04$XxXZPlgjR/lDqWAptAIC7OoNvxW5DLeb/P/iKU8JONKY5fh5WREFi', 1);
insert INTO USER (username, password, role_id) values ('spedytor', '$2a$04$eS3GoHUm.PE/qX4gt01ze.XYtgjYI0k/SCjbim9DiVCWR75oQvmra', 2);

insert into DRIVER(id,first_name,surname,phone_number,personal_id_number, user_username) values (nextval('driver_seq'),'AREK','NOWY','666987','123','admin');
insert into DRIVER(id,first_name,surname,phone_number,personal_id_number, user_username) values (nextval('driver_seq'),'JAN','KOWALSKI','545787','234','driver');
insert into DRIVER(id,first_name,surname,phone_number,personal_id_number, user_username) values (nextval('driver_seq'),'STEFAMN','BURCZYMUCHA','687517','3987','spedytor');

INSERT INTO TRUCK(id,plate_number,brand,model,horse_Power) values (nextval('truck_seq'),'PLE 1234','MAN','BDG',123);
INSERT INTO TRUCK(id,plate_number,brand,model,horse_Power) values (nextval('truck_seq'),'PO 7458','SCANIA','2HG',785);
INSERT INTO TRUCK(id,plate_number,brand,model,horse_Power) values (nextval('truck_seq'),'DW 7821','MERCEDES','BIG',456);

INSERT INTO EXPEDITION(id,truck_id,driver_id,starting_place,start_odometer_reading,start_day) values (nextval('expedition_seq'),2,1,'Leszno',1457,'2005-02-04');
INSERT INTO EXPEDITION(id,truck_id,driver_id,starting_place,start_odometer_reading,start_day) values (nextval('expedition_seq'),1,1,'Poznań',204754,'2015-02-04');
INSERT INTO EXPEDITION(id,truck_id,driver_id,starting_place,start_odometer_reading,start_day) values (nextval('expedition_seq'),3,1,'Wrocław',6325478,'2099-02-04');

insert INTO EXTRA_COST (id, expedition_id, description, cost, currency_code, date_of_purchase) values (nextval('e_cost_seq'),1, 'Windshield replacement', 370, 'EUR', '2019-08-06');
insert INTO EXTRA_COST (id, expedition_id, description, cost, currency_code, date_of_purchase) values (nextval('e_cost_seq'),1, 'Wash', 70, 'EUR', '2019-08-09');
insert INTO EXTRA_COST (id, expedition_id, description, cost, currency_code, date_of_purchase) values (nextval('e_cost_seq'),1, 'Tire replacement', 390, 'EUR', '2019-08-22');
insert INTO EXTRA_COST (id, expedition_id, description, cost, currency_code, date_of_purchase) values (nextval('e_cost_seq'),2, 'Highway toll', 220, 'PLN', '2019-09-02');
insert INTO EXTRA_COST (id, expedition_id, description, cost, currency_code, date_of_purchase) values (nextval('e_cost_seq'),2, 'Phone card', 50, 'PLN', '2019-09-05');


UPDATE DRIVER SET EXPEDITION_ID = 1 WHERE ID=1;


commit;