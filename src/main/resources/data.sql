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
INSERT INTO EXPEDITION(id,truck_id,driver_id,starting_place,start_odometer_reading,start_day) values (nextval('expedition_seq'),1,2,'Kolobrzeg',2999,'2015-02-04');
INSERT INTO EXPEDITION(id,truck_id,driver_id,starting_place,start_odometer_reading,start_day) values (nextval('expedition_seq'),3,3,'Berlin',456123,'2099-02-04');

insert INTO EXTRA_COST (id, expedition_id, description, cost, currency_code, date_of_purchase) values (nextval('e_cost_seq'),1, 'Windshield replacement', 370, 'EUR', '2019-08-06');
insert INTO EXTRA_COST (id, expedition_id, description, cost, currency_code, date_of_purchase) values (nextval('e_cost_seq'),1, 'Wash', 70, 'EUR', '2019-08-09');
insert INTO EXTRA_COST (id, expedition_id, description, cost, currency_code, date_of_purchase) values (nextval('e_cost_seq'),1, 'Tire replacement', 390, 'EUR', '2019-08-22');
insert INTO EXTRA_COST (id, expedition_id, description, cost, currency_code, date_of_purchase) values (nextval('e_cost_seq'),2, 'Highway toll', 220, 'PLN', '2019-09-02');
insert INTO EXTRA_COST (id, expedition_id, description, cost, currency_code, date_of_purchase) values (nextval('e_cost_seq'),2, 'Phone card', 50, 'PLN', '2019-09-05');
insert INTO EXTRA_COST (id, expedition_id, description, cost, currency_code, date_of_purchase) values (nextval('e_cost_seq'),1, 'Windshield replacement', 370, 'GBP', '2019-08-06');
insert INTO EXTRA_COST (id, expedition_id, description, cost, currency_code, date_of_purchase) values (nextval('e_cost_seq'),1, 'Wash', 70, 'GBP', '2019-08-09');
insert INTO EXTRA_COST (id, expedition_id, description, cost, currency_code, date_of_purchase) values (nextval('e_cost_seq'),1, 'Tire replacement', 390, 'GBP', '2019-08-22');

UPDATE DRIVER SET EXPEDITION_ID = 1 WHERE ID=1;

insert INTO TRUCK_PARTS (id, expedition_id, description, cost, currency_code, date_of_purchase) values (nextval('truck_parts_seq'),1, 'Rearview mirror', 270, 'EUR', '2019-08-21');
insert INTO TRUCK_PARTS (id, expedition_id, description, cost, currency_code, date_of_purchase) values (nextval('truck_parts_seq'),1, 'Cooler', 470, 'EUR', '2019-08-24');
insert INTO TRUCK_PARTS (id, expedition_id, description, cost, currency_code, date_of_purchase) values (nextval('truck_parts_seq'),1, 'Exhaust pipe', 590, 'EUR', '2019-08-29');
insert INTO TRUCK_PARTS (id, expedition_id, description, cost, currency_code, date_of_purchase) values (nextval('truck_parts_seq'),2, 'Timing belt', 280, 'PLN', '2019-09-01');
insert INTO TRUCK_PARTS (id, expedition_id, description, cost, currency_code, date_of_purchase) values (nextval('truck_parts_seq'),2, 'Master cylinder', 550, 'PLN', '2019-09-05');

insert into Fuel (id, expedition_id, liters, place_of_refueling, cost, currency_code, kilometers, payment_method, refueling_to_full, date_of_fueling) values (nextval('fuel_seq'), 1, 50, 'Warszawa', 50, 'PLN', 200, 'cash', false, '2019-08-28');
insert into Fuel (id, expedition_id, liters, place_of_refueling, cost, currency_code, kilometers, payment_method, refueling_to_full, date_of_fueling) values (nextval('fuel_seq'), 1, 45, 'Poznan', 50, 'PLN', 180, 'cash', false, '2019-08-30');
insert into Fuel (id, expedition_id, liters, place_of_refueling, cost, currency_code, kilometers, payment_method, refueling_to_full, date_of_fueling) values (nextval('fuel_seq'), 1, 70, 'Gorzow Wlkp.', 20, 'PLN', 250, 'cash', true, '2019-09-10');
insert into Fuel (id, expedition_id, liters, place_of_refueling, cost, currency_code, kilometers, payment_method, refueling_to_full, date_of_fueling) values (nextval('fuel_seq'), 1, 70, 'Berlin', 30, 'EUR', 250, 'cash', true, '2019-09-11');
insert into Fuel (id, expedition_id, liters, place_of_refueling, cost, currency_code, kilometers, payment_method, refueling_to_full, date_of_fueling) values (nextval('fuel_seq'), 1, 30, 'Shell', 40, 'EUR', 20, 'cash', true, '2019-09-13');

insert into FREIGHT_RATE (id, amount, currency_code, freight_company, freight_distance,expedition_id, date, city_departure,city_arrival) values (nextval('freight_seq'),100,'EUR','Big Company',789,1,'2019-08-07','Leszno','Poznań');
insert into FREIGHT_RATE (id, amount, currency_code, freight_company, freight_distance,expedition_id, date, city_departure,city_arrival) values (nextval('freight_seq'),700,'PLN','Bigger Company',1458,1,'2021-05-09','Wroclaw','Warszawa');
insert into FREIGHT_RATE (id, amount, currency_code, freight_company, freight_distance,expedition_id, date, city_departure,city_arrival) values (nextval('freight_seq'),300,'PLN','Brand',457,1,'2019-09-10','Kraków','Berlin');

insert into JOURNEY(id,expedition_id,start_date,end_date,start_place,end_place,meter_reading_departure,meter_reading_arrival,comments) values (nextval('journey_seq'),1,'2019-08-07','2019-08-26','Leszno','Poznań',14578,14687,'it was awesome');
insert into JOURNEY(id,expedition_id,start_date,end_date,start_place,end_place,meter_reading_departure,meter_reading_arrival,comments) values (nextval('journey_seq'),1,'2019-08-26','2019-08-30','Poznań','Helsinki',14687,14900,'');
insert into JOURNEY(id,expedition_id,start_date,end_date,start_place,end_place,meter_reading_departure,meter_reading_arrival,comments) values (nextval('journey_seq'),1,'2019-08-07','2019-08-26','Helsinki','Barcelona',14900,15500,'finally something warmer');
insert into JOURNEY(id,expedition_id,start_date,end_date,start_place,end_place,meter_reading_departure,meter_reading_arrival,comments) values (nextval('journey_seq'),2,'2099-01-02','2099-01-03','Poznań','Mars',4567854,5248965,'quick jump into another planet');
insert into JOURNEY(id,expedition_id,start_date,end_date,start_place,end_place,meter_reading_departure,meter_reading_arrival,comments) values (nextval('journey_seq'),2,'2099-01-04','2099-02-06','Mars','Earths Moon',5248965,6458965,'small crash with asteroid');

commit;