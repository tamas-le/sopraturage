INSERT INTO Postcodes (postcode, city)
	 VALUES ('31400', 'Toulouse'),
	 		('31770', 'Colomiers'),
			('30100', 'Alès');


INSERT INTO Addresses (num, way_type, way_name, id_postcode)
	 VALUES ('17', 'avenue', "Colonel Roche", (SELECT id FROM Postcodes WHERE postcode = "31400")),
	 		('37', 'chemin', "Ramassiers", (SELECT id FROM Postcodes WHERE postcode = "31770")),
	 		('378', 'chemin', "Espinaux à la bedosse", (SELECT id FROM Postcodes WHERE postcode = "30100"));




INSERT INTO Homes
	 VALUES ((SELECT id FROM Addresses WHERE num = "17" and 
	 										 way_type="avenue" and 
	 										 way_name="Colonel Roche" and 
	 										 id_postcode = (SELECT id FROM Postcodes WHERE postcode = "31400"))),
	 		((SELECT id FROM Addresses WHERE num = "378" and 
	 										 way_type="chemin" and 
	 										 way_name="Espinaux à la bedosse" and 
	 										 id_postcode = (SELECT id FROM Postcodes WHERE postcode = "30100")));



INSERT INTO Workplaces
	 VALUES ((SELECT id FROM Addresses WHERE num = "37" and 
	 										 way_type="chemin" and 
	 										 way_name="Ramassiers" and 
	 										 id_postcode = (SELECT id FROM Postcodes WHERE postcode = "31770")));

INSERT INTO Users (surname, name, email, password, phone_number, workplace, home)
	 VALUES ('Julien', 'Baladier', "julien.baladier@gmail.com", "beaugosse", "0642971715", 2, 1),
	 		('Aurélien', 'Tamas-Leloup', "aurelien.tamasle@gmail.com", "pd", "0654567654", 2, 3),
	 		('Loïc', 'Boyeldieu', "loic.boyeldieu@gmail.com", "pd", "0654567654", 2, 3);



INSERT INTO Administrators
	 VALUES (1);


-- Récuperer toutes les workplaces

SELECT num, way_type, way_name, postcode, city
FROM Addresses
INNER JOIN Postcodes
ON Addresses.id_postcode = Postcodes.id
INNER JOIN Workplaces
ON Workplaces.id = Addresses.id;