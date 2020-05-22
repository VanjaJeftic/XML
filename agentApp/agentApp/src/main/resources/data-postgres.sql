insert into users (firstname, password, lastname, username, adress, phonenumber, uloga, enabled, last_password_reset_date, aktiviran) values ('Mara', 'mara123', 'Maric', 'mara', 'Novi Sad', '064', 'ADMIN_K_C', true, '2017-10-01 21:58:58.508-07', true);
INSERT INTO authority (name) VALUES ('ROLE_AGENT');
INSERT INTO authority (name) VALUES ('ROLE_USER');
INSERT INTO authority (name) VALUES ('ROLE_ADMIN');
INSERT INTO user_authority (user_id, authority_id) VALUES (1, 1);

