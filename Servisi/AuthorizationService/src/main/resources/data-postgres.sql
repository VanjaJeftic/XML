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
insert into users (id, username,password, email, enabled, account_non_expired, credentials_non_expired, account_non_locked,nalogAktivan) VALUES ('1', 'krish','$2a$10$e3NBkbtr.Pf7wBP3AiSaWeOnUK3v7yPDHSFm9lMagCdcJ.tZkzbXC', 'k@krishantha.com', '1', '1', '1', '1',true);
insert into  users (id, username,password, email, enabled, account_non_expired, credentials_non_expired, account_non_locked,nalogAktivan) VALUES ('2', 'suranga', '$2a$10$e3NBkbtr.Pf7wBP3AiSaWeOnUK3v7yPDHSFm9lMagCdcJ.tZkzbXC','kw@krishantha.com', '1', '1', '1', '1',true);
insert into  users (id, username,password, email, enabled, account_non_expired, credentials_non_expired, account_non_locked,nalogAktivan) VALUES ('3', 'nuwan', '$2a$10$e3NBkbtr.Pf7wBP3AiSaWeOnUK3v7yPDHSFm9lMagCdcJ.tZkzbXC','kq@krishantha.com', '1', '1', '1', '1',true);

 /*
 passowrds:
 krish - kpass
 suranga - spass
 nuwan - spass
 */


INSERT INTO ROLE_USER (ROLE_ID, USER_ID)
    VALUES
    (1, 1) /* krish-admin */,
    (2, 2) /* suranga-operatorr */ ,
    (3, 3);