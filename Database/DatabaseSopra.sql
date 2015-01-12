DROP DATABASE Sopraturage;

CREATE DATABASE Sopraturage
	CHARACTER SET 'utf8'
	COLLATE 'utf8_bin';
	
CREATE USER 'java'@'localhost' IDENTIFIED BY '123';
GRANT ALL ON sopraturage.* TO 'java'@'localhost' IDENTIFIED BY '123';

USE Sopraturage;

CREATE TABLE Postcodes (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	postcode CHAR(5) NOT NULL,
	city VARCHAR(255) NOT NULL,
	CONSTRAINT postcode_unique UNIQUE (postcode)
);


CREATE TABLE Addresses (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	num INT UNSIGNED NOT NULL,
	way_type enum("rue", "avenue", "chemin", "allée", "boulevard", "route", "ruelle") NOT NULL,
	way_name VARCHAR(255) NOT NULL,
	id_postcode INT UNSIGNED NOT NULL,
	longitude FLOAT(10, 7) NOT NULL,
	latitude FLOAT(10, 7) NOT NULL,
	FOREIGN KEY(id_postcode) REFERENCES Postcodes(id),
	CONSTRAINT address_unique UNIQUE (num, way_type, way_name, id_postcode),
	CONSTRAINT coodinates_unique UNIQUE (longitude, latitude)
);


CREATE TABLE Homes (
	id INT UNSIGNED NOT NULL PRIMARY KEY,
	FOREIGN KEY(id) REFERENCES Addresses(id)
);


CREATE TABLE Workplaces (
	id INT UNSIGNED NOT NULL PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	FOREIGN KEY(id) REFERENCES Addresses(id)
);



CREATE TABLE Users (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	first_name VARCHAR(255) NOT NULL,
	last_name VARCHAR(255) NOT NULL,
	email VARCHAR(255) NOT NULL,
	password CHAR(32) NOT NULL,
	phone_number CHAR(10) NOT NULL,
	profile_image VARCHAR(255) DEFAULT "/images/inconnu.jpg",
	workplace INT UNSIGNED NOT NULL,
	home INT UNSIGNED NOT NULL,
	is_a_driver BOOLEAN NOT NULL DEFAULT FALSE,
	accept_notifications BOOLEAN NOT NULL DEFAULT TRUE,
	working_on_monday BOOLEAN NOT NULL DEFAULT FALSE,
	working_on_tuesday BOOLEAN NOT NULL DEFAULT FALSE,
	working_on_wednesday BOOLEAN NOT NULL DEFAULT FALSE,
	working_on_thursday BOOLEAN NOT NULL DEFAULT FALSE,
	working_on_friday BOOLEAN NOT NULL DEFAULT FALSE,
	working_start_time TIME NOT NULL DEFAULT "08:00",
	working_stop_time TIME NOT NULL DEFAULT "18:00",
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


DELIMITER $$
CREATE TRIGGER validate_email_and_number_before_insert BEFORE INSERT ON Sopraturage.Users
FOR EACH ROW 
BEGIN 
IF (NEW.email REGEXP "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9.-]+$" ) = 0 THEN 
  SIGNAL SQLSTATE '12345'
     SET MESSAGE_TEXT = 'Wroooong email address!!!';

ELSEIF (NEW.phone_number REGEXP "^0[1-68]([0-9]{2}){4}$") = 0 THEN 
	SIGNAL SQLSTATE '12345'
     SET MESSAGE_TEXT = 'Wroooong phone number !!!';
END IF;

END$$
DELIMITER ;




DELIMITER $$
CREATE TRIGGER validate_postcode_before_insert BEFORE INSERT ON Sopraturage.Postcodes
FOR EACH ROW 
BEGIN 
IF (NEW.postcode REGEXP "^[0-9]{5}$" ) = 0 THEN 
  SIGNAL SQLSTATE '12345'
     SET MESSAGE_TEXT = 'Wroooong postcode!!!';
END IF;

END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER validate_coordinates BEFORE INSERT ON Sopraturage.Addresses
FOR EACH ROW 
BEGIN 
IF ((NEW.longitude < -180) OR (NEW.longitude > 180) OR (NEW.latitude < -180) OR (NEW.latitude > 180)) THEN 
  SIGNAL SQLSTATE '12345'
     SET MESSAGE_TEXT = 'Wroooong coordinates';
END IF;

END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER validate_time_stamps BEFORE INSERT ON Sopraturage.Sessions
FOR EACH ROW 
BEGIN 
IF (NEW.time_stamp_connection > NEW.time_stamp_deconnection) THEN 
  SIGNAL SQLSTATE '12345'
     SET MESSAGE_TEXT = 'Wroooong connection/deconnection times';
END IF;

END$$
DELIMITER ;