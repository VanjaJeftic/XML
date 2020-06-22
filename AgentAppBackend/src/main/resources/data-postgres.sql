insert into users (firstname, password, lastname, username, adress, city, country, phonenumber, uloga, enabled, last_password_reset_date, aktiviran,email,maticnibroj,nazivfirme) values ('Vanja', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Jeftic', 'vanja', 'DJ', 'Novi Sad', 'RS', '064', 'ADMIN', true, '2017-10-01 21:58:58.508-07', true,'a@gmail.com',null,null);
insert into users (firstname, password, lastname, username, adress, city, country, phonenumber, uloga, enabled, last_password_reset_date, aktiviran,email,maticnibroj,nazivfirme) values ('Vesna', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Karaklic', 'vesna', 'Koste R. 2', 'Novi Sad', 'RS', '0641234', 'AGENT', true, '2017-10-01 21:58:58.508-07', true,'c@gmail.com',null,null);
insert into users (firstname, password, lastname, username, adress, city, country, phonenumber, uloga, enabled, last_password_reset_date, aktiviran,email,maticnibroj,nazivfirme) values ('Goran', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Kuljanin', 'goran', 'Kosovska 1', 'Novi Sad', 'RS', '0643344', 'AGENT', true, '2017-10-01 21:58:58.508-07', true,'b@gmail.com',null,null);
insert into users (firstname, password, lastname, username, adress, city, country, phonenumber, uloga, enabled, last_password_reset_date, aktiviran,email,maticnibroj,nazivfirme) values ('Dijana', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Radic', 'dijana', 'S. Bajica 4', 'Novi Sad', 'RS', '0644455', 'USER', true, '2017-10-01 21:58:58.508-07', true,'d@gmail.com',null,null);
insert into users (firstname, password, lastname, username, adress, city, country, phonenumber, uloga, enabled, last_password_reset_date, aktiviran,email,maticnibroj,nazivfirme) values ('User', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'User', 'user', 'Milsevska 7', 'Novi Sad', 'RS', '0645566', 'USER', true, '2017-10-01 21:58:58.508-07', true,'e@gmail.com',null,null);

insert into markavozila ( naziv) values ( 'BMW');
insert into markavozila ( naziv) values ( 'Fiat');
insert into markavozila ( naziv) values ( 'Tesla');
insert into markavozila ( naziv) values ( 'Mercedes');

insert into modelvozila ( naziv, id_marke) values ( 'M5','Fiat');
insert into modelvozila ( naziv,id_marke) values ( 'R8','Tesla');

insert into klasavozila ( naziv) values ( 'SUV');
insert into klasavozila ( naziv) values ( 'old tajmer');
insert into klasavozila ( naziv) values ( 'gradski auto');

insert into tipgoriva ( naziv) values ( 'benzin');
insert into tipgoriva ( naziv) values ( 'dizel');
insert into tipgoriva ( naziv) values ( 'plin');


insert into vrstamenjaca ( naziv) values ( 'manuelni');
insert into vrstamenjaca ( naziv) values ( 'automatski');
insert into vrstamenjaca ( naziv) values ( 'poluautomatski');

INSERT INTO AUTHORITY (name) VALUES ('ROLE_ADMIN');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_AGENT');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_USER');

 INSERT INTO PERMISSION (NAME) VALUES
  ('log_USER'),
 ('log_AGENT'),
 ('log_ADMIN'),
 ('create_oglas'),
 ('read_oglas'),
 ('update_oglas'),
 ('delete_oglas'),
  ('create_vozilo'),
 ('read_vozilo'),
 ('update_vozilo'),
 ('delete_vozilo'),
  ('create_sifrarnik'),
 ('read_sifrarnik'),
 ('update_sifrarnik'),
 ('delete_sifrarnik'),
  ('create_zahtev'),
 ('read_zahtev'),
 ('update_zahtev'),
 ('delete_zahtev'),
  ('create_komentar'),
 ('read_komentar'),
 ('update_komentar'),
 ('delete_komentar'),
 ('create_poruka'),
 ('read_poruka'),
 ('update_poruka'),
 ('delete_poruka'),
 ('create_izvestaj'),
 ('read_izvestaj'),
 ('update_izvestaj'),
 ('delete_izvestaj');
 INSERT INTO PERMISSION_AUTHORITY (PERMISSION_ID, AUTHORITY_ID) VALUES
     (3,1),
     (2,2),
     (1,3),
 	 (4,2), /*create-> agent je 2 */
     (4,3),
     (5,1), /* read admin */
     (5,2),
     (5,3),
     (6,2), /* update admin */
     (6,3),
     (7,2), /* delete admin */
     (7,3),  
     (8,2),
     (8,3),
     (9,2),
     (9,3),
     (10,2),
     (10,3),
     (11,2),
     (11,3),
     (9,1),
    (12,1),
    (13,1),
    (14,1),
    (15,1),
    (13,2),
    (13,3),
    (16,3),
    (17,3),
    (18,3),
    (19,3),
    (17,2),
    (17,1),
    (20,1),
    (20,2),
    (20,3),
    (21,1),
    (21,2),
    (21,3),
    (22,1),
    (22,2),
    (22,3),
    (23,1),
    (23,2),
    (23,3),
    (24,2),
    (24,3),
    (25,2),
    (25,3),
    (26,2),
    (26,3),
    (27,2),
    (27,3),
    (25,1),
    (28,2),
    (28,3),
    (29,2),
    (29,3),
    (30,2),
    (30,3),
    (31,2),
    (31,3),
    (29,1);
    
    

INSERT INTO USER_AUTHORITY  (user_id, authority_id) VALUES (1, 1);
INSERT INTO USER_AUTHORITY  (user_id, authority_id) VALUES (2, 2);
INSERT INTO USER_AUTHORITY  (user_id, authority_id) VALUES (3, 2);
INSERT INTO USER_AUTHORITY  (user_id, authority_id) VALUES (4, 3);
INSERT INTO USER_AUTHORITY  (user_id, authority_id) VALUES (5, 3);


insert into korisnik (user_id, aktivan, blokiran, uklonjen, odbijenizahtevi) values (4, true, false, false, 0);
insert into korisnik (user_id, aktivan, blokiran, uklonjen, odbijenizahtevi) values (5, true, false, false, 0);
insert into korisnik (user_id, aktivan, blokiran, uklonjen, odbijenizahtevi) values (3, true, false, false, 0);


insert into vozilo (user_id, cdw, klasavozila_id, markavozila_id, modelvozila_id, tipgoriva_id, vrstamenjaca_id) values (2, false, 1, 1, 1, 1, 1);
insert into vozilo (user_id, cdw, klasavozila_id, markavozila_id, modelvozila_id, tipgoriva_id, vrstamenjaca_id) values (3, true, 1, 1, 2, 1, 2);
insert into vozilo (user_id, cdw, klasavozila_id, markavozila_id, modelvozila_id, tipgoriva_id, vrstamenjaca_id) values (4, true, 2, 2, 2, 2, 2);
insert into vozilo (user_id, cdw, klasavozila_id, markavozila_id, modelvozila_id, tipgoriva_id, vrstamenjaca_id) values (4, true, 2, 2, 2, 2, 2);

insert into oglas (vozilo_id, mesto, slobodanod, slobodando,cena) values (1, 'Novi Sad','2020-06-01T17:09:42.411', '2020-06-15T17:09:42.411',99);
insert into oglas (vozilo_id, mesto, slobodanod, slobodando,cena) values (1, 'Beograd','2020-06-01T17:09:42.411', '2020-06-15T17:09:42.411',99);
insert into oglas (vozilo_id, mesto, slobodanod, slobodando,cena) values (2, 'Ugljevik','2020-06-01T17:09:42.411', '2020-06-15T17:09:42.411',99);
insert into oglas (vozilo_id, mesto, slobodanod, slobodando,cena) values (2, 'Bijeljina','2020-08-01T17:09:42.411', '2020-08-15T17:09:42.411',9988);
insert into oglas (vozilo_id, mesto, slobodanod, slobodando,cena) values (3, 'Sarajevo','2020-06-01T17:09:42.411', '2020-06-15T17:09:42.411',9988);

insert into zahtev (oglas_id, podnosilac_id, bundle, bundle_id, status, preuzimanje, povratak) values (1, 1, false, 12, 'PENDING', '2020-06-24T19:00', '2020-06-26T20:00');
insert into zahtev (oglas_id, podnosilac_id, bundle, bundle_id, status, preuzimanje, povratak) values (1, 2, false, 13, 'PENDING', '2020-06-22T19:00', '2020-06-25T20:00');
insert into zahtev (oglas_id, podnosilac_id, bundle, bundle_id, status, preuzimanje, povratak) values (1, 2, false, 14, 'PENDING', '2020-06-28T19:00', '2020-06-30T20:00');

insert into zahtev (oglas_id, podnosilac_id, bundle, bundle_id, status, preuzimanje, povratak) values (1, 2, false, 15, 'ACCEPTED', '2020-06-22T19:00', '2020-06-25T20:00');
insert into zahtev (oglas_id, podnosilac_id, bundle, bundle_id, status, preuzimanje, povratak) values (2, 2, false, 16, 'ACCEPTED', '2020-06-20T19:00', '2020-06-24T20:00');

insert into zahtev (oglas_id, podnosilac_id, bundle, bundle_id, status, preuzimanje, povratak) values (1, 1, true, 17, 'PENDING', '2020-06-29T19:00', '2020-06-30T20:00');
insert into zahtev (oglas_id, podnosilac_id, bundle, bundle_id, status, preuzimanje, povratak) values (2, 1, true, 17, 'PENDING', '2020-06-01T19:00', '2020-06-03T20:00');

insert into izvestaj (predjeniKm, komentar, vozilo_id, zahtev_id) values ('125', 'Vozilo vraceno na vrijeme', 1, 4);

insert into komentar (oglas_id, korisnik_id, datum, ocena,sadrzaj,odgovor_id,odobren,odbijen,usernameusera) values (1,4,'2020-06-22T19:00',7,'Komentar za oglas 1 test', 1, false, false,'dijana');
insert into komentar (oglas_id, korisnik_id, datum, ocena, sadrzaj,odgovor_id,odobren,odbijen,usernameusera) values (2,4,'2020-06-22T19:00',9,'Komentar test 2', 1, false, false,'dijana');
insert into komentar (oglas_id, korisnik_id, datum, ocena, sadrzaj,odgovor_id,odobren,odbijen,usernameusera) values (1,4,'2020-06-22T19:00',9,'Komentar za oglas 1', 1, true, false, 'dijana');