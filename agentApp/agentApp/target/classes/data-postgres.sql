/*CREATE TABLE users(
	id serial PRIMARY KEY,
	username VARCHAR (255) UNIQUE NOT NULL,
	password VARCHAR (255) NOT NULL,
	lastname VARCHAR (255),
	firstname VARCHAR (255),
	adress VARCHAR (255),
	phonenumber VARCHAR (255),
	uloga VARCHAR (255),
	aktiviran BOOLEAN NOT NULL,
	enabled BOOLEAN NOT NULL,
	last_password_reset_date TIMESTAMP
);*/

/*CREATE TABLE authority(
	name VARCHAR (255)
);*/
/*
CREATE TABLE user_authority(
	authority_id  BIGINT,
	user_id  BIGINT
);*/

/*insert into users (firstname, password, lastname, username, adress, phonenumber, uloga, enabled, last_password_reset_date, aktiviran) values ('Mara', 'mara123', 'Maric', 'mara', 'Novi Sad', '064', 'ADMIN_K_C', true, '2017-10-01 21:58:58.508-07', true);*/
INSERT INTO authority (name) VALUES ('ROLE_AGENT');
INSERT INTO authority (name) VALUES ('ROLE_USER');
INSERT INTO authority (name) VALUES ('ROLE_ADMIN');
INSERT INTO user_authority (user_id, authority_id) VALUES (1, 1);

