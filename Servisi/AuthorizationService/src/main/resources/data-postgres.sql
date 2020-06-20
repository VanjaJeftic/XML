 INSERT INTO PERMISSION (NAME) VALUES
 ('create_oglas'),
 ('read_oglas'),
 ('update_oglas'),
 ('delete_oglas');

 INSERT INTO role (NAME) VALUES
		('ROLE_admin'),('ROLE_agent'), ('ROLE_user');

 INSERT INTO PERMISSION_ROLE (PERMISSION_ID, ROLE_ID) VALUES
     (1,2), /*create-> agent je 2 */
     (1,3),
     (2,1), /* read admin */
     (2,2),
     (2,3),
     (3,2), /* update admin */
     (3,3),
     (4,2), /* delete admin */
     (4,3);            /*pkrish i psuranga su sifre*/
insert into users ( username,password, email, enabled, account_non_expired, credentials_non_expired, account_non_locked,nalogAktivan,ime, prezime, adresa, mesto,telefon) VALUES ( 'vanja','$2a$10$e3NBkbtr.Pf7wBP3AiSaWeOnUK3v7yPDHSFm9lMagCdcJ.tZkzbXC', 'v@jeftic.com', '1', '1', '1', '1',true,'Vanja', 'Jeftic','Bate Brkic 5', 'Novi Sad', 6586377);
insert into  users ( username,password, email, enabled, account_non_expired, credentials_non_expired, account_non_locked,nalogAktivan,ime, prezime, adresa, mesto,telefon) VALUES ( 'vesna', '$2a$10$e3NBkbtr.Pf7wBP3AiSaWeOnUK3v7yPDHSFm9lMagCdcJ.tZkzbXC','v@karaklic.com', '1', '1', '1', '1',true,'Vesna', 'Karaklic','Bate Brkic 6', 'Novi Sad', 6576377);
insert into  users (username,password, email, enabled, account_non_expired, credentials_non_expired, account_non_locked,nalogAktivan,ime, prezime, adresa, mesto,telefon) VALUES ( 'dijana', '$2a$10$e3NBkbtr.Pf7wBP3AiSaWeOnUK3v7yPDHSFm9lMagCdcJ.tZkzbXC','r@radic.com', '1', '1', '1', '1',true,'Dijana', 'Radic','Bate Brkic 7', 'Novi Sad', 6516377);
insert into  users ( username,password, email, enabled, account_non_expired, credentials_non_expired, account_non_locked,nalogAktivan,ime, prezime, adresa, mesto,telefon) VALUES ( 'goran', '$2a$10$e3NBkbtr.Pf7wBP3AiSaWeOnUK3v7yPDHSFm9lMagCdcJ.tZkzbXC','g@kuljanin.com', '1', '1', '1', '1',true,'Goran', 'Kuljanin','Bate Brkic 5', 'Novi Sad', 6586377);
insert into  users (username,password, email, enabled, account_non_expired, credentials_non_expired, account_non_locked,nalogAktivan,ime, prezime, adresa, mesto,telefon) VALUES ( 'user', '$2a$10$e3NBkbtr.Pf7wBP3AiSaWeOnUK3v7yPDHSFm9lMagCdcJ.tZkzbXC','kq@krishantha.com', '1', '1', '1', '1',true,'UserIme', 'UserPrezime','Bate Brkic 5', 'Novi Sad', 6586377);

 /*
 */


INSERT INTO ROLE_USER (ROLE_ID, USER_ID)
    VALUES
    (1, 1) /* krish-admin */,
    (2, 2) /* suranga-operatorr */ ,
    (3, 3),
    (2, 4),
    (3, 5);