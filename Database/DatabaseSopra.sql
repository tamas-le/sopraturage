CREATE DATABASE Sopraturage
	CHARACTER SET 'utf8'
	COLLATE 'utf8_bin';



CREATE TABLE Postcodes (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	postcode CHAR(5) NOT NULL UNIQUE,
	city VARCHAR(255)
);


CREATE TABLE Addresses (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	num INT UNSIGNED NOT NULL,
	way_type enum("rue", "avenue", "chemin", "allée", "boulevard", "route", "ruelle") NOT NULL,
	way_name VARCHAR(255) NOT NULL,
	id_postcode INT UNSIGNED NOT NULL,
	FOREIGN KEY(id_postcode) REFERENCES Postcodes(id),
	CONSTRAINT unique_address UNIQUE (num, way_type, way_name, id_postcode)
);


CREATE TABLE Homes (
	id INT UNSIGNED NOT NULL PRIMARY KEY,
	FOREIGN KEY(id) REFERENCES Addresses(id)
);


CREATE TABLE Workplaces (
	id INT UNSIGNED NOT NULL PRIMARY KEY,
	FOREIGN KEY(id) REFERENCES Addresses(id)
);



CREATE TABLE Users (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	surname CHAR(13) NOT NULL,
	ename VARCHAR(255) NOT NULL,
	email VARCHAR(255) NOT NULL,
	epassword CHAR(32) NOT NULL,
	phone_number CHAR(10) NOT NULL,
	workplace INT UNSIGNED NOT NULL,
	home INT UNSIGNED NOT NULL,
	is_a_driver BOOLEAN NOT NULL DEFAULT FALSE,
	accept_notifications BOOLEAN NOT NULL DEFAULT TRUE,
	working_on_monday BOOLEAN NOT NULL DEFAULT FALSE,
	working_on_tuesday BOOLEAN NOT NULL DEFAULT FALSE,
	working_on_wednesday BOOLEAN NOT NULL DEFAULT FALSE,
	working_on_thursday BOOLEAN NOT NULL DEFAULT FALSE,
	working_on_friday BOOLEAN NOT NULL DEFAULT FALSE,
	CONSTRAINT email_unique UNIQUE (email),
	FOREIGN KEY(workplace) REFERENCES Workplaces(id),
	FOREIGN KEY(home) REFERENCES Homes(id)
);

CREATE TABLE Administrators (
	id INT UNSIGNED NOT NULL PRIMARY KEY,
	FOREIGN KEY(id) REFERENCES Users(id)
);


CREATE TABLE Sessions (
	id INT UNSIGNED NOT NULL,
	time_stamp_connection TIMESTAMP,
	time_stamp_deconnection TIMESTAMP,
	FOREIGN KEY(id) REFERENCES Users(id),
	PRIMARY KEY(id, time_stamp_connection)
);



INSERT INTO Postcodes (postcode, city)
	 VALUES ('31400', 'Toulouse'),
	 		('31770', 'Colomiers'),
			('30100', 'Alès');


INSERT INTO Addresses (num, way_type, way_name, id_postcode)
	 VALUES ('17', 'avenue', "Colonel Roche", (SELECT id FROM Postcodes WHERE postcode = "31400")),
	 		('37', 'chemin', "Ramassiers", (SELECT id FROM Postcodes WHERE postcode = "31770")),
	 		('378', 'chemin', "Espinaux à la bedosse", (SELECT id FROM Postcodes WHERE postcode = "30100"));