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
insert into users (id, username,password, email, enabled, account_non_expired, credentials_non_expired, account_non_locked) VALUES ('1', 'krish','{bcrypt}$2a$10$ODGwrk2ufy5d7T6afmACwOA/6j6rvXiP5amAMt1YjOQSdEw44QdqG', 'k@krishantha.com', '1', '1', '1', '1');
insert into  users (id, username,password, email, enabled, account_non_expired, credentials_non_expired, account_non_locked) VALUES ('2', 'suranga', '{bcrypt}$2a$10$wQ8vZl3Zm3.zDSIcZEYym.bGq3fPMJXH9k.Vhudcfr6O6KQwDPSt6','k@krishantha.com', '1', '1', '1', '1');
insert into  users (id, username,password, email, enabled, account_non_expired, credentials_non_expired, account_non_locked) VALUES ('3', 'nuwan', '{bcrypt}$2a$10$wQ8vZl3Zm3.zDSIcZEYym.bGq3fPMJXH9k.Vhudcfr6O6KQwDPSt6','k@krishantha.com', '1', '1', '1', '1');

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