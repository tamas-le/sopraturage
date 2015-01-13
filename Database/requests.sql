INSERT INTO Postcodes (postcode, city)
	 VALUES ('31400', 'Toulouse'),
	 		('81000', 'Albi'),
	 		('31770', 'Colomiers'),
			('30100', 'Ales');

INSERT INTO Addresses (num, way_type, way_name, id_postcode, latitude, longitude)
	 VALUES ('1', 'avenue', "Andre marie Ampere", (SELECT id FROM Postcodes WHERE postcode = "31770"), '43.6122296','1.3075650'),
	 		('2', 'avenue', "Leon Foucault", (SELECT id FROM Postcodes WHERE postcode = "31770"), '43.6121522','1.3103126999999404'),
	 		('5', 'rue', "Jean Borotra", (SELECT id FROM Postcodes WHERE postcode = "81000"), '43.9185965','2.1709938000000193'),
	 		('37', 'chemin', "Ramassiers", (SELECT id FROM Postcodes WHERE postcode = "31770"), '43.5993517','1.3551164'),
	 		('17', 'avenue', "Colonel Roche", (SELECT id FROM Postcodes WHERE postcode = "31400"), '37.4224411','-122.0842864'),
	 		('378', 'chemin', "Espinaux ae la bedosse", (SELECT id FROM Postcodes WHERE postcode = "30100"), '37.4227411','-122.0842864');




INSERT INTO Homes
	 VALUES ((SELECT id FROM Addresses WHERE num = "17" and 
	 										 way_type="avenue" and 
	 										 way_name="Colonel Roche" and 
	 										 id_postcode = (SELECT id FROM Postcodes WHERE postcode = "31400"))),
	 		((SELECT id FROM Addresses WHERE num = "378" and 
	 										 way_type="chemin" and 
	 										 way_name="Espinaux ae la bedosse" and 
	 										 id_postcode = (SELECT id FROM Postcodes WHERE postcode = "30100")));



INSERT INTO Workplaces (id, name)
	 VALUES ((SELECT id FROM Addresses WHERE num = "37" and 
	 										 way_type="chemin" and 
	 										 way_name="Ramassiers" and 
	 										 id_postcode = (SELECT id FROM Postcodes WHERE postcode = "31770")), "Sopra Ramassiers"),
	 		((SELECT id FROM Addresses WHERE num = "1" and 
	 										 way_type="avenue" and 
	 										 way_name="Andre marie Ampere" and 
	 										 id_postcode = (SELECT id FROM Postcodes WHERE postcode = "31770")), "Sopra Colo 1"),
	 		((SELECT id FROM Addresses WHERE num = "2" and 
	 										 way_type="avenue" and 
	 										 way_name="Leon Foucault" and 
	 										 id_postcode = (SELECT id FROM Postcodes WHERE postcode = "31770")), "Sopra Colo 2"),
	 		((SELECT id FROM Addresses WHERE num = "5" and 
	 										 way_type="rue" and 
	 										 way_name="Jean Borotra" and 
	 										 id_postcode = (SELECT id FROM Postcodes WHERE postcode = "81000")), "Sopra Albi");



INSERT INTO Users (first_name, last_name, email, password, phone_number, workplace, home)
	 VALUES ('Julien', 'Baladier', "julien.baladier@gmail.com", "beaugosse", "0642971715", 2, 5),
	 		('Aurelien', 'Tamas-Leloup', "aurelien.tamasle@gmail.com", "pd", "0654567654", 3, 6),
	 		('Loic', 'Boyeldieu', "loic.boyeldieu@gmail.com", "pd", "0654567654", 4, 6);



INSERT INTO Administrators
	 VALUES (2);

INSERT INTO Sessions (id, time_stamp_connection, time_stamp_deconnection)
	 VALUES (1, "2000-01-01 00:00:01", "2000-01-01 00:00:01");




-- Récuperer toutes les workplaces

SELECT num, way_type, way_name, postcode, city
FROM Addresses
INNER JOIN Postcodes
ON Addresses.id_postcode = Postcodes.id
INNER JOIN Workplaces
ON Workplaces.id = Addresses.id;