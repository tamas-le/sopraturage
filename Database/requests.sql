INSERT INTO Postcodes (postcode, city)
	 VALUES ('31400', 'Toulouse'),
	 		('31770', 'Colomiers'),
			('30100', 'Ales');


INSERT INTO Addresses (num, way_type, way_name, id_postcode, longitude, latitude)
	 VALUES ('17', 'avenue', "Colonel Roche", (SELECT id FROM Postcodes WHERE postcode = "31400"), '37.4224411','-122.0842864'),
	 		('37', 'chemin', "Ramassiers", (SELECT id FROM Postcodes WHERE postcode = "31770"), '37.4234411','-122.0842864'),
	 		('378', 'chemin', "Espinaux à la bedosse", (SELECT id FROM Postcodes WHERE postcode = "30100"), '37.4227411','-122.0842864');




INSERT INTO Homes
	 VALUES ((SELECT id FROM Addresses WHERE num = "17" and 
	 										 way_type="avenue" and 
	 										 way_name="Colonel Roche" and 
	 										 id_postcode = (SELECT id FROM Postcodes WHERE postcode = "31400"))),
	 		((SELECT id FROM Addresses WHERE num = "378" and 
	 										 way_type="chemin" and 
	 										 way_name="Espinaux à la bedosse" and 
	 										 id_postcode = (SELECT id FROM Postcodes WHERE postcode = "30100")));



INSERT INTO Workplaces (id, name)
	 VALUES ((SELECT id FROM Addresses WHERE num = "37" and 
	 										 way_type="chemin" and 
	 										 way_name="Ramassiers" and 
	 										 id_postcode = (SELECT id FROM Postcodes WHERE postcode = "31770")), "Sopra Ramassiers");

INSERT INTO Users (first_name, last_name, email, password, phone_number, workplace, home)
	 VALUES ('Julien', 'Baladier', "julien.baladier@gmail.com", "beaugosse", "0642971715", 2, 1),
	 		('Aurélien', 'Tamas-Leloup', "aurelien.tamasle@gmail.com", "pd", "0654567654", 2, 3),
	 		('Loïc', 'Boyeldieu', "loic.boyeldieu@gmail.com", "pd", "0654567654", 2, 3);



INSERT INTO Administrators
	 VALUES (1);

INSERT INTO Sessions (id, time_stamp_connection, time_stamp_deconnection)
	 VALUES (1, "2000-01-01 00:00:01", "2000-01-01 00:00:01");




-- Récuperer toutes les workplaces

SELECT num, way_type, way_name, postcode, city
FROM Addresses
INNER JOIN Postcodes
ON Addresses.id_postcode = Postcodes.id
INNER JOIN Workplaces
ON Workplaces.id = Addresses.id;