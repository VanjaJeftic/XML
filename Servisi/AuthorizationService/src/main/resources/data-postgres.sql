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
insert into users (id, username,password, email, enabled, account_non_expired, credentials_non_expired, account_non_locked,nalogAktivan) VALUES ('1', 'krish','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'k@krishantha.com', '1', '1', '1', '1',true);
insert into  users (id, username,password, email, enabled, account_non_expired, credentials_non_expired, account_non_locked,nalogAktivan) VALUES ('2', 'suranga', '$2a$12$l5RRveYgubpdAK5h42f2xu8qpVf6619DtbY90JneL/NXVsBeIzL1e','k1@krishantha.com', '1', '1', '1', '1',true);
insert into  users (id, username,password, email, enabled, account_non_expired, credentials_non_expired, account_non_locked,nalogAktivan) VALUES ('3', 'nuwan', '{bcrypt}$2a$10$wQ8vZl3Zm3.zDSIcZEYym.bGq3fPMJXH9k.Vhudcfr6O6KQwDPSt6','k2@krishantha.com', '1', '1', '1', '1',true);

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