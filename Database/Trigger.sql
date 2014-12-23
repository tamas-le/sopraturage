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