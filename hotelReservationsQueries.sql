# 5 Alter Table Statments

ALTER TABLE billing RENAME TO billings; 

ALTER TABLE frontDeskAgents ADD Gender varchar(10);

ALTER TABLE checkInTransactions MODIFY checkIn_DateTime datetime NOT NULL;

ALTER TABLE guests DROP age;

ALTER TABLE bookingReservationMethods AUTO_INCREMENT = 100; 

ALTER TABLE bookingReservationMethods DROP Confirmation_Number; 

ALTER TABLE bookingReservationMethods DROP Past_Confirmation_Number; 

ALTER TABLE cancelledReservations MODIFY Cancellation_Date DATETIME AFTER Confirmation_Number;


# 10 statements for insertion

INSERT INTO guests (First_Name, Last_Name, Email, Phone) 
VALUES ("Carine", "Schmitt", "Carine@gmail.com", "(407) 642-7555");

INSERT INTO paymentDetails (Card_Holder_Name, CC_Numbers, Expiration_Date, CVV, Guest_ID) 
VALUES ("Carine Schmitt", "4716-3120-9994-2670", "04/2028", 973, 1);

INSERT INTO billings (Account_ID, Amount, Payment_ID, Guest_ID) 
VALUES ("ACC#100", 79.23, 1, 1);

INSERT INTO hotel (Hotel_ID, Name, Address, City, State, ZipCode, Phone, Fax)
VALUES ("TMWS", "Tampa Marriott Water Street", "505 Water St", "Tampa", "Florida", 33602, "(813) 221-4900", "(813) 221-4901");

INSERT INTO roomTypes (Room_Type_ID, Room_Type_Name)
VALUES  ("SK", "Standard King"),
		("SQQ", "Standard Queen Queen"),
        ("KB", "King Balcony"),
        ("QQB", "Queen Queen Balcony"),
        ("KBW", "King Balcony Water View"),
        ("QQBW", "Queen Queen Balcony Water View"),
        ("PRS", "Presidental Suite"),
        ("GS", "Governor Suite"),
        ("KS", "King Suite"),
        ("CKS", "Corner King Suite");
        
INSERT INTO bookingReservationMethods (Booking_Method_Channel)
VALUES  ("Hotel Website"), 
		("Center Reservation"), 
        ("Expedia"), 
        ("Booking.com"), 
        ("Priceline"), 
        ("Hotels.com");       

INSERT INTO reservations (Confirmation_Number, First_Name, Last_Name, Arrival_Date, 
							Departure_Date, Email, Room_Type_ID, Booking_Method_ID)
VALUES ("TMWS-7894561", "Peter", "Ferguson", '2023-11-08', '2023-11-12', "PeterF@gmail.com", "KB", 102);

INSERT INTO cancelledReservations 
VALUES ("CNCL-1567892", "TMWS-2456105", '2023-06-02 23:15:02', 4);

INSERT INTO frontDeskAgents (First_Name, Last_Name)
VALUES ("Mary" , "Young"),
	   ("Peter", "Lee"),
       ("Scott", "Favey"),
       ("Linda", "Murphy");

INSERT INTO checkOutTransactions (checkOut_DateTime, confirmation_Number, 
									Payment_id, account_id, frontdeskAgent_ID)
VALUES ('2023-01-16 10:31:15', "TMWS-1587923", 3, "ACC#101", 1);


# 10 statements for updating

UPDATE reservations 
SET Guest_ID = 2, Hotel_ID = "TMWS" 
WHERE First_Name = "Peter";

UPDATE roomTypes 
SET Room_Type_ID = "KBL" 
WHERE Room_Type_Name = "King Balcony";

UPDATE frontDeskAgents 
SET Gender = "Female" 
WHERE FrontDeskAgent_ID = 1;

UPDATE paymentDetails 
SET CVV = 512 
WHERE Card_Holder_Name = "Peter Ferguson" AND Guest_ID = 2;

UPDATE pastReservations 
SET Room_Type_ID = "KBW", Booking_Method_ID = 104 
WHERE Past_Confirmation_Number = "HS-10001";

UPDATE checkInTransactions
SET CheckIn_DateTime = '2023-01-15 16:15:05' 
WHERE CheckIn_Trans_ID = 1;

UPDATE billings 
SET Amount = 316.17 
WHERE Account_ID = "ACC#101";

UPDATE hotel 
SET ZipCode = 33607 
WHERE Name = "Tampa Marriott Water Street";

UPDATE roomTypes 
SET Room_Type_Name = "Standard 2 Queen(s)" 
WHERE Room_Type_ID = "SQQ";

UPDATE paymentDetails 
SET Card_Holder_Name = "Kwai X Lee" 
WHERE Payment_ID = 4;


# 10 statements for deletions

DELETE FROM guests 
WHERE First_Name = "Dorothy";

DELETE FROM frontDeskAgents 
WHERE FrontDeskAgent_ID = 4;

DELETE FROM billings 
WHERE Amount <= 2.00;
        
DELETE FROM pastReservations 
WHERE City IN ("Tampa", "Orlando", "Wesley Chapel");

DELETE FROM bookingReservationMethods
WHERE Booking_Method_ID NOT BETWEEN 100 AND 105;       

DELETE FROM checkOutTransactions 
WHERE FrontDeskAgent_ID = (
					SELECT frontDeskAgent_ID 
                    FROM FrontDeskAgents 
                    WHERE Working_Shift = "Night Shift");


DELETE FROM roomTypes 
WHERE Room_Type_ID 
LIKE "__K%";

DELETE FROM cancelledReservations 
WHERE Cancellation_Date = '2023-07-15 07:15:00';

DELETE FROM checkInTransactions 
WHERE CheckIn_Trans_ID = 2;

DELETE FROM reservations 
WHERE Confirmation_Number = "TMWS-7842135" AND Last_Name = "Thompson";


# 1 big statement to join all tables in the database

SELECT guests.Guest_ID, guests.First_Name, guests.Last_Name, reservations.Confirmation_Number,
		cancelledReservations.Cancelation_Number, cancelledReservations.Hotel_ID, roomTypes.Room_Type_ID,
		reservations.Booking_Method_ID, bookingReservationMethods.Booking_Method_Channel,
		paymentDetails.Card_Holder_Name, billings.Amount, checkInTransactions.CheckIn_DateTime,
		checkOutTransactions.CheckOut_DateTime,
		checkInTransactions.FrontDeskAgent_ID
FROM guests
LEFT JOIN reservations ON guests.Guest_ID = reservations.Guest_ID
LEFT JOIN pastReservations ON pastReservations.Guest_ID = guests.Guest_ID
LEFT JOIN cancelledReservations ON cancelledReservations.Confirmation_Number = reservations.Confirmation_Number
LEFT JOIN hotel ON hotel.Hotel_ID = cancelledReservations.Hotel_ID
LEFT JOIN roomTypes ON roomTypes.Room_Type_ID = reservations.Room_Type_ID
LEFT JOIN bookingReservationMethods ON bookingReservationMethods.Booking_Method_ID = reservations.Booking_Method_ID
LEFT JOIN paymentDetails ON paymentDetails.Confirmation_Number = reservations.Confirmation_Number
LEFT JOIN billings ON billings.Payment_ID = paymentDetails.Payment_ID
LEFT JOIN checkInTransactions ON checkInTransactions.Confirmation_Number = reservations.Confirmation_Number
LEFT JOIN checkOutTransactions ON checkOutTransactions.Confirmation_Number = reservations.Confirmation_Number
LEFT JOIN frontDeskAgents ON frontDeskAgents.FrontDeskAgent_ID = checkInTransactions.FrontDeskAgent_ID;


# 5 statements with left, right, inner, outer joins

SELECT guests.Guest_ID, guests.First_Name, guests.Last_Name, billings.Amount, billings.Account_ID
FROM guests
INNER JOIN billings 
ON guests.Guest_ID = billings.Guest_ID;

SELECT* FROM reservations 
LEFT JOIN bookingReservationMethods 
ON reservations.Booking_Method_ID = bookingReservationMethods.Booking_Method_ID;

SELECT billings.Account_ID, paymentDetails.Payment_ID, paymentDetails.Card_Holder_Name, billings.Amount 
FROM paymentDetails 
RIGHT JOIN billings 
ON paymentDetails.Payment_ID = billings.Payment_ID;

SELECT * FROM checkInTransactions 
INNER JOIN reservations 
ON checkInTransactions.Confirmation_Number = reservations.Confirmation_Number;

SELECT guests.Guest_ID, First_Name, Last_Name, Phone, Account_ID, Amount, Payment_ID
FROM guests 
INNER JOIN billings 
ON guests.Guest_ID = billings.Guest_ID
WHERE First_Name LIkE "C%"; 


# 7 statements with aggregate functions and group by and without having

SELECT AVG(Amount) AS Average_Amount, Guest_ID
FROM billings
GROUP BY Guest_ID;

SELECT MIN(Hourly_Pay) AS Min_HourlyPay, FrontDeskAgent_ID, First_Name
FROM frontDeskAgents
GROUP BY FrontDeskAgent_ID
ORDER BY Min_HourlyPay;

SELECT First_Name, COUNT(*) AS Total_Reservations_Per_Guest
FROM reservations
GROUP BY First_Name
ORDER BY Total_Reservations_Per_Guest DESC;

SELECT Confirmation_Number, First_Name, MAX(Arrival_Date) AS Furthest_Reservation
FROM reservations
GROUP BY Confirmation_Number
ORDER BY Furthest_Reservation DESC;

SELECT billings.Guest_ID, guests.First_Name, SUM(Amount) AS Total_Amount_Spend_Each_Guest 
FROM billings 
LEFT JOIN guests ON billings.Guest_ID = guests.Guest_ID
GROUP BY billings.Guest_ID;

SELECT COUNT(paymentDetails.Confirmation_Number) AS numOfMethodPaymentOnReservation, 
	Payment_ID, Card_Holder_Name, Confirmation_Number
FROM paymentDetails
GROUP BY Payment_ID;

SELECT AVG(Amount) AS Average_Amount_By_Transaction_Date, Transaction_Date
FROM billings
GROUP BY Transaction_Date
ORDER BY Average_Amount_By_Transaction_Date;


# 7 statements with aggregate functions and group by and with having.

SELECT AVG(Amount) AS Average_Amount, Guest_ID
FROM billings
GROUP BY Guest_ID
HAVING Average_Amount > 175.00;

SELECT MIN(Hourly_Pay) AS Min_HourlyPay, FrontDeskAgent_ID, First_Name
FROM frontDeskAgents
GROUP BY FrontDeskAgent_ID
HAVING FrontDeskAgent_ID NOT BETWEEN 3 AND 5;

SELECT First_Name, COUNT(*) AS Total_Reservations_Per_Guest
FROM reservations
GROUP BY First_Name
HAVING Total_Reservations_Per_Guest >=2;

SELECT Confirmation_Number, First_Name, MAX(Arrival_Date) AS Furthest_Reservation
FROM reservations
GROUP BY Confirmation_Number
HAVING Furthest_Reservation > '2023-06-10'
ORDER BY Furthest_Reservation DESC;

SELECT billings.Guest_ID, guests.First_Name, SUM(Amount) AS Total_Amount_Spend_Each_Guest 
FROM billings 
LEFT JOIN guests ON billings.Guest_ID = guests.Guest_ID
GROUP BY billings.Guest_ID
HAVING Total_Amount_Spend_Each_Guest BETWEEN 500.00 AND 1500.00;

SELECT COUNT(paymentDetails.Confirmation_Number) AS numOfMethodPaymentOnReservation, 
	Payment_ID, Card_Holder_Name, Confirmation_Number
FROM paymentDetails
GROUP BY Payment_ID 
HAVING numOfMethodPaymentOnReservation >=1;

SELECT AVG(Amount) AS Average_Amount_By_Transaction_Date, Transaction_Date
FROM billings
GROUP BY Transaction_Date
HAVING Transaction_Date IN ('2023-01-15', '2023-05-01');
























