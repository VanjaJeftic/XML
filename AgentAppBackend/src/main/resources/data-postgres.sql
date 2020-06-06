insert into users (firstname, password, lastname, username, adress, city, country, phonenumber, uloga, enabled, last_password_reset_date, aktiviran) values ('Goranr', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Kuljanin', 'vntm01101913@gmail.com', 'DJ', 'Novi Sad', 'RS', '064', 'ADMIN_K_C', true, '2017-10-01 21:58:58.508-07', true);
insert into users (firstname, password, lastname, username, adress, city, country, phonenumber, uloga, enabled, last_password_reset_date, aktiviran) values ('Milos', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Majstorovic', 'goku.kuljanin@gmail.com', 'Kosovska 1', 'Novi Sad', 'RS', '0643344', 'User', true, '2017-10-01 21:58:58.508-07', false);
insert into users (firstname, password, lastname, username, adress, city, country, phonenumber, uloga, enabled, last_password_reset_date, aktiviran) values ('Ana', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Andric', 'ana@gmail.com', 'Koste R. 2', 'Novi Sad', 'RS', '0641234', 'PACIJENT', true, '2017-10-01 21:58:58.508-07', true);
insert into users (firstname, password, lastname, username, adress, city, country, phonenumber, uloga, enabled, last_password_reset_date, aktiviran) values ('Vesna', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Vesnaa', 'vesna@gmail.com', 'S. Bajica 4', 'Novi Sad', 'RS', '0644455', 'PACIJENT', true, '2017-10-01 21:58:58.508-07', true);
insert into users (firstname, password, lastname, username, adress, city, country, phonenumber, uloga, enabled, last_password_reset_date, aktiviran) values ('Marko', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Markovic', 'lekar', 'Milsevska 7', 'Novi Sad', 'RS', '0645566', 'LEKAR', true, '2017-10-01 21:58:58.508-07', true);
insert into users (firstname, password, lastname, username, adress, city, country, phonenumber, uloga, enabled, last_password_reset_date, aktiviran) values ('Uros', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Urosevic', 'uros@gmail.com', 'Micurinova 6', 'Ruma', 'RS', '0641122', 'PACIJENT', true, '2017-10-01 21:58:58.508-07', true);
insert into users (firstname, password, lastname, username, adress, city, country, phonenumber, uloga, enabled, last_password_reset_date, aktiviran) values ('Laza', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Lazic', 'laza@gmail.com', 'Cankareva 4', 'S. Mitrovica', 'RS', '0645566', 'PACIJENT', true, '2017-10-01 21:58:58.508-07', true);
insert into users (firstname, password, lastname, username, adress, city, country, phonenumber, uloga, enabled, last_password_reset_date, aktiviran) values ('Admin', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Klinike', 'adminrent', 'Koadminrentpernikova 73', 'S. Mitrovica', 'RS', '0647744', 'ADMIN_K', true, '2017-10-01 21:58:58.508-07', true);
insert into users (firstname, password, lastname, username, adress, city, country, phonenumber, uloga, enabled, last_password_reset_date, aktiviran) values ('Nemanja', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Pavlovic', 'nemanja@gmail.com', 'Panonska 17', 'Novi Sad', 'RS', '0641287', 'LEKAR', true, '2017-10-01 21:58:58.508-07', true);

insert into users (firstname, password, lastname, username, adress, city, country, phonenumber, uloga, enabled, last_password_reset_date, aktiviran) values ('Nebojsa', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Milosevic', 'milos@gmail.com', 'Cirpanova 2', 'Novi Sad', 'RS', '0641287', 'LEKAR', true, '2017-10-01 21:58:58.508-07', true);
insert into users (firstname, password, lastname, username, adress, city, country, phonenumber, uloga, enabled, last_password_reset_date, aktiviran) values ('Srdjan', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Ivanovic', 'srdjan@gmail.com', 'Almaska', 'Novi Sad', 'RS', '0641287', 'LEKAR', true, '2017-10-01 21:58:58.508-07', true);

INSERT INTO AUTHORITY (name) VALUES ('ROLE_ADMIN');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_AGENT');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_USER');

INSERT INTO USER_AUTHORITY  (user_id, authority_id) VALUES (3, 1);
INSERT INTO USER_AUTHORITY  (user_id, authority_id) VALUES (1, 3);
INSERT INTO USER_AUTHORITY  (user_id, authority_id) VALUES (8, 2);
INSERT INTO USER_AUTHORITY  (user_id, authority_id) VALUES (5, 1);
INSERT INTO USER_AUTHORITY  (user_id, authority_id) VALUES (10, 1);
INSERT INTO USER_AUTHORITY  (user_id, authority_id) VALUES (11, 1);

insert into korisnik (user_id, aktivan, blokiran, uklonjen, odbijenizahtevi) values (1, true, false, false, 0);
insert into korisnik (user_id, aktivan, blokiran, uklonjen, odbijenizahtevi) values (4, true, false, false, 0);
insert into korisnik (user_id, aktivan, blokiran, uklonjen, odbijenizahtevi) values (5, true, false, false, 0);

insert into klasavozila (naziv) values ('klasa1');
insert into klasavozila (naziv) values ('klasa2');

insert into markavozila (naziv) values ('marka1');
insert into markavozila (naziv) values ('marka2');

insert into modelvozila (naziv) values ('model1');
insert into modelvozila (naziv) values ('model2');

insert into tipgoriva (naziv) values ('Tip Goriva 1');
insert into tipgoriva (naziv) values ('Tip Goriva 2');

insert into vrstamenjaca (naziv) values ('Vrsta menjaca 1');
insert into vrstamenjaca (naziv) values ('Vrsta menjaca 2');

insert into vozilo (user_id, cdw, klasavozila_id, markavozila_id, modelvozila_id, tipgoriva_id, vrstamenjaca_id) values (1, false, 1, 1, 1, 1, 1);
insert into vozilo (user_id, cdw, klasavozila_id, markavozila_id, modelvozila_id, tipgoriva_id, vrstamenjaca_id) values (1, true, 1, 1, 2, 1, 2);
insert into vozilo (user_id, cdw, klasavozila_id, markavozila_id, modelvozila_id, tipgoriva_id, vrstamenjaca_id) values (2, true, 2, 2, 2, 2, 2);

insert into oglas (vozilo_id, mesto, slobodanod, slobodando,cena) values (1, 'Novi Sad','2020-06-01T17:09:42.411', '2020-06-15T17:09:42.411',99);
insert into oglas (vozilo_id, mesto, slobodanod, slobodando,cena) values (1, 'Beograd','2020-06-01T17:09:42.411', '2020-06-15T17:09:42.411',99);
insert into oglas (vozilo_id, mesto, slobodanod, slobodando,cena) values (2, 'Ugljevik','2020-06-01T17:09:42.411', '2020-06-15T17:09:42.411',99);
insert into oglas (vozilo_id, mesto, slobodanod, slobodando,cena) values (2, 'Bijeljina','2020-08-01T17:09:42.411', '2020-08-15T17:09:42.411',9988);
insert into oglas (vozilo_id, mesto, slobodanod, slobodando,cena) values (3, 'Sarajevo','2020-06-01T17:09:42.411', '2020-06-15T17:09:42.411',9988);

insert into zahtev (oglas_id, podnosilac_id, bundle, bundle_id) values (1, 1, false, 1);
insert into zahtev (oglas_id, podnosilac_id, bundle, bundle_id) values (1, 2, false, 1);