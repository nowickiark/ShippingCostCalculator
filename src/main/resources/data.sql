INSERT INTO TRUCK(id,plate_number,brand,model,horse_Power) values (nextval('truck_seq'),'PLE 1234','MAN','BDG',123);
INSERT INTO TRUCK(id,plate_number,brand,model,horse_Power) values (nextval('truck_seq'),'PO 7458','SCANIA','2HG',785);
INSERT INTO TRUCK(id,plate_number,brand,model,horse_Power) values (nextval('truck_seq'),'DW 7821','MERCEDES','BIG',456);

INSERT INTO EXPEDITION(id,truck_id,starting_place,start_odometer_reading,start_day) values (nextval('expedition_seq'),2,'Leszno',1457,'2005-02-04');
INSERT INTO EXPEDITION(id,truck_id,starting_place,start_odometer_reading,start_day) values (nextval('expedition_seq'),1,'Poznań',204754,'2015-02-04');
INSERT INTO EXPEDITION(id,truck_id,starting_place,start_odometer_reading,start_day) values (nextval('expedition_seq'),3,'Wrocław',6325478,'2099-02-04');