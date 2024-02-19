USE db_public_transport;

--
-- Inserting data into table card_type
--
INSERT INTO card_type(cType, Expiry_Period) VALUES
('Disabled', 20),
('Elder', 25),
("Martyr's Relative", 10),
('National Athlete', 1),
('Standard', 1),
('Student', 1),
('Veteran', 20);

--
-- Inserting data into table travel_card
--
INSERT INTO travel_card
(Card_Id, Balance, Status, Issue_Date, Last_Usage, Travel_Count, cType) 
VALUES
('12340', 108, 'active', '2021-05-07', '2022-06-22 09:50:17', 8, 'Standard'),
('12341', 119, 'active', '2021-06-23', '2022-07-21 06:50:17', 48, 'Standard'),
('12342', 125, 'active', '2021-03-15', '2022-12-22 09:50:17', 187, 'Student'),
('12343', 10, 'active', '2022-04-07', '2022-06-22 09:50:07', 89, 'Student'),
('12344', 112, 'active', '2021-05-07', '2022-06-18 09:40:17', 7, 'Student'),
('12345', 64, 'active', '2021-04-08', '2022-08-12 09:50:19', 3, 'Disabled'),
('12346', 80, 'active', '2021-01-07', '2023-01-22 08:50:17', 67, "Martyr's Relative"),
('12347', 99, 'active', '2021-10-04', '2023-08-06 09:50:07', 135, 'National Athlete'),
('12348', 45, 'active', '2021-05-05', '2023-07-02 09:50:17', 39, 'National Athlete'),
('12349', 145, 'active', '2021-12-07', '2023-11-22 09:50:17', 78, 'Elder'),
('12350', 167, 'active', '2021-09-28', '2023-10-01 08:50:19', 56, 'Elder'),
('12351', 200, 'active', '2022-04-25', '2023-09-09 09:50:17', 78, 'Veteran'),
('12352', 178, 'active', '2021-05-07', '2023-08-07 09:50:17', 12, 'Veteran');

--
-- Inserting data into table passenger
--

INSERT INTO 
passenger(Passenger_Id, Full_Name, Birth_Date, Card_Id) 
VALUES
('11111', 'Akshatha S', '2003-05-30', '12342'),
('22222', 'Venkatesh Rayudu', '2002-10-23', '12340'),
('33333', 'Sindhu N', '2003-07-18', '12341'),
('44444', 'Supriya Singh', '2003-05-30', '12349'),
('55555', 'Rachana B', '2003-08-31', '12350'),
('66666', 'Tanushree A', '2003-10-27', '12343'),
('77777', 'Nikhitha J', '2003-08-06', '12344'),
('88888', 'Rahul Jangra', '2003-08-27', '12347'),
('99999', 'Rizwan Khan', '2003-11-29', '12348'),
('12222', 'Karthik Ramesh', '2003-11-12', '12346'),
('13333', 'Nitya M', '2003-06-09', '12351'),
('14444', 'Dhruvan', '2001-05-22', '12345'),
('15555', 'Sathvik', '2002-08-30', '12352');

--
-- Inserting data into table passenger_address
--
INSERT INTO passenger_address
(Address_Id, Passenger_Id, Town, Neighborhood, Street, Number, X_Coordinate, Y_Coordinate) 
VALUES
('001', '11111', 'Bangalore Urban', 'Bettahalasur', '2nd main road', '287', 13.162827424370091, 77.60997900000001),
('002', '22222', 'Bangalore Urban', 'SMVIT college hostel', 'SMVIT college road', '101', 13.151176304096245, 77.6099743800764),
('003', '33333', 'Bangalore Urban', 'Prasanahalli Village', '4th street', '57', 13.233402127290692, 77.69605751519559),
('004', '44444', 'Bangalore Urban', 'Bagalur', '1st street', '25', 13.11637492125574, 77.63550806658202),
('005', '55555', 'Bangalore Urban', 'Sugatta', 'SMVIT Girls hostel', '16', 13.148777536562472, 77.60385152513977),
('006', '66666', 'Bangalore Urban', 'Yelahanka,NES', '3rd street', '32', 13.097896929764275, 77.59151795124006),
('007', '77777', 'Bangalore Urban', 'MS Palya', '5th street', '56', 13.081580360375625, 77.54807059970796),
('008', '88888', 'Bangalore Urban', 'SMVIT college hostel', 'SMVIT college road', '101', 13.151176304096245, 77.6099743800764),
('009', '99999', 'Bangalore Urban', 'MS Palya', '5th street', '56', 13.081580360375625, 77.54807059970796),
('010', '12222', 'Bangalore Urban', 'Yelahanka 5th Phase', '3rd street', '17', 13.10593637668281, 77.57286856260666),
('011', '13333', 'Bangalore Urban', 'Kodigehalli', '5th main road', '287', 13.071018613047253, 77.58294255243575),
('012', '14444', 'Bangalore Urban', 'Bettahalasur', '2nd main road', '287', 13.162827424370091, 77.60997900000001),
('013', '15555', 'Bangalore Urban', 'SMVIT college hostel', 'SMVIT college road', '101', 13.151176304096245, 77.6099743800764);

--
-- Inserting data into table passenger_phone
--
INSERT INTO passenger_phone(Phone, Passenger_Id, Phone_Type) VALUES
('8197098016', '11111', 'Mobile'),
('1234567890', '11111', 'Work'),
('1020304050', '22222', 'Mobile'),
('1122334455', '33333', 'Mobile'),
('1000023456', '33333', 'Pager'),
('1234560000', '44444', 'Mobile'),
('1405648859', '55555', 'Mobile'),
('9855323219', '66666', 'Mobile(Personal)'),
('8195645466', '66666', 'Fax'),
('1432655321', '77777', 'Mobile'),
('7754323122', '88888', 'Mobile'),
('9989877654', '99999', 'Mobile'),
('1405648859', '12222', 'Fax'),
('9454545343', '12222', 'Mobile'),
('8989787859', '13333', 'Mobile'),
('7675647669', '14444', 'Mobile'),
('7867323122', '14444', 'Mobile');

--
-- Inserting data into table user_account
--
INSERT INTO user_account(Email, Password, Last_Login, Passenger_Id) VALUES
('ak@gmail.com', 'ak12345', '2021-09-15', '11111'),
('venky@gmail.com', 've12345', '2021-01-09', '22222'),
('sin@gmail.com', 'si12345', '2022-07-23', '33333'),
('sup@gmail.com', 'sup12345', '2022-07-18', '44444'),
('rachs@gmail.com', 'rac12345', '2021-07-11', '55555'),
('don@gmail.com', 'ta12345', '2018-04-20', '66666'),
('niks@gmail.com', 'nik12345', '2022-12-07', '77777'),
('rj@gmail.com', 'rj12345', NULL, '88888'),
('riz@gmail.com', 'riz12345', '2021-03-04', '99999'),
('kar@gmail.com', 'kar12345', '2022-01-27', '12222'),
('nitya@gmail.com', 'nit12345', '2021-11-16', '13333'),
('dhruvan@gmail.com', 'dh12345', '2022-11-23', '14444'),
('svik@gmail.com', 'sv12345', '2021-11-03', '15555');

--
-- Inserting data into table travel_review
--
INSERT INTO travel_review
(Review_Id, Message, Travel_Rating, Review_Rating, Email) 
VALUES
('11111',"The overall experience was good. There was no rash driving unlike private buses. I feel safe while traveling on BMTC buses. The drivers seem well-trained, and I've noticed adherence to safety regulations. The buses are equipped with necessary safety features. It also covers most areas I need to travel to. However, there's room for improvement in expanding routes to reach underserved neighborhoods.", '3', '2', 'ak@gmail.com'),
('22222',"The buses are usually clean and well-maintained. It's evident that the maintenance of the fleet is a priority, contributing to a comfortable travel environment. It also has a feedback system in place, and I've used it to report issues or provide suggestions. While they acknowledge feedback, it would be great to see more tangible improvements based on passenger input.",'4','3','venky@gmail.com');

--
-- Inserting data into table stop
--
INSERT INTO stop
(Stop_Id, Stop_Name, Town, Neighborhood, Street, X_Coordinate, Y_Coordinate)
VALUES 
('01','Yelahanka Old Town','Bangalore Urban','Santhe circle','Main road',13.099466678301592, 77.59713491075871),
('02','Bagalur Cross','Bangalore Urban','BSF Campus,Yelahanka','Main road',13.1222841017757, 77.61049989356967),
('03','Hunsmaranahalli','Bangalore Urban','Yelahanka','NH7 service road',13.144939770979047, 77.61745100865494),
('04','SMVIT Cross','Bangalore Urban','SMVIT college','NH7 service road',13.151788962722382, 77.6198039107596),
('05','Sadahalli gate','Bangalore Urban','Sadahallir','NH44 highway',13.191593998710603, 77.64643253774798),
('06','Devanahalli','Bangalore Urban','Devanahalli','Main road',13.248061569415654, 77.71323613563455);

--
-- Inserting data into table route
--
INSERT INTO route
(Route_Id, Direction)
VALUES 
('401A', 0),
('401A', 1),
('298M', 0),
('298M', 1),
('283A', 0),
('283A', 1),
('291K', 0),
('291K', 1),
('289A', 0),
('289A', 1);

--
-- Inserting data into table route_departure_time 
--
INSERT INTO route_departure_time
(Departure_Time, Route_Id, Direction)
VALUES 
('06:00:00', '401A', 0),
('07:00:00', '401A', 1),
('08:00:00', '298M', 0),
('09:00:00', '298M', 1),
('08:00:00', '283A', 0),
('08:30:00', '283A', 1),
('06:00:00', '291K', 0),
('06:30:00', '291K', 1),
('06:00:00', '289A', 0),
('06:20:00', '289A', 1),

('08:00:00', '401A', 0),
('09:00:00', '401A', 1),
('10:00:00', '298M', 0),
('11:00:00', '298M', 1),
('09:30:00', '283A', 0),
('10:00:00', '283A', 1),
('07:30:00', '291K', 0),
('08:00:00', '291K', 1),
('07:00:00', '289A', 0),
('07:20:00', '289A', 1),


('12:00:00', '401A', 0),
('13:15:00', '401A', 1),
('14:00:00', '298M', 0),
('15:00:00', '298M', 1),
('14:00:00', '283A', 0),
('14:45:00', '283A', 1),
('12:00:00', '291K', 0),
('12:45:00', '291K', 1),
('12:00:00', '289A', 0),
('12:30:00', '289A', 1),

('17:00:00', '401A', 0),
('18:15:00', '401A', 1),
('18:00:00', '298M', 0),
('19:15:00', '298M', 1),
('18:00:00', '283A', 0),
('18:45:00', '283A', 1),
('18:00:00', '291K', 0),
('18:45:00', '291K', 1),
('19:00:00', '289A', 0),
('19:40:00', '289A', 1),

('19:00:00', '401A', 0),
('20:30:00', '401A', 1),
('20:00:00', '298M', 0),
('21:30:00', '298M', 1),
('20:00:00', '283A', 0),
('20:45:00', '283A', 1),
('20:30:00', '291K', 0),
('21:15:00', '291K', 1),
('20:00:00', '289A', 0),
('20:40:00', '289A', 1);
       
--
-- Inserting data into table route_includes
--

INSERT INTO route_includes
(Visit_Order, Route_Id, Stop_Id)
VALUES 
(1, '401A', '01'),
(2, '401A', '02'),
(3, '401A', '03'),
(4, '401A', '04'),
(5, '401A', '05'),
(6, '401A', '06'),
(1, '298M', '01'),
(2, '298M', '02'),
(3, '298M', '03'),
(4, '298M', '04'),
(5, '298M', '05'),
(6, '298M', '06'),
(1, '283A', '01'),
(2, '283A', '02'),
(3, '283A', '03'),
(4, '283A', '04'),
(1, '291K', '01'),
(2, '291K', '02'),
(3, '291K', '03'),
(4, '291K', '04'),
(1, '289A', '01'),
(2, '289A', '02');

--
-- Inserting data into table journey 
--
INSERT INTO journey
(Duration, Stop_Id_1, Stop_Id_2)
VALUES 
(10, '01', '02'),
(10, '02', '03'),
(5, '03', '04'),
(30, '04', '05'),
(40, '05', '06');

--
-- Inserting data into table vehicle 
--
INSERT INTO vehicle
(Vehicle_Id, Vehicle_Type, Capacity, Status)
VALUES 
('0000','Bus','44','In Service'),
('1000', 'Bus', 42, 'In Service'),
('2000', 'Bus', 52, 'In Service'),
('3000', 'Bus', 38, 'In Service'),
('4000', 'Bus', 54, 'In Service'),
('5000', 'Bus', 60, 'In Service'),
('6000', 'Bus', 45, 'In Service'),
('7000', 'Bus', 66, 'In Service'),
('8000','Bus','34','In Service'),
('9000','Bus','24','In Service'),
('1100', 'Metro', 325, 'Coming soon'),
('1200', 'Metro', 335, 'Coming soon');

--
-- Inserting data into table driver
--
INSERT INTO driver
(Driver_Id, Driver_Name, License_Type, License_Expiry)
VALUES 
('10011', 'Nithin', 'Bus', '2037-09-10'),
('11122', 'Shantha Kumari', 'Bus', '2032-06-10'),
('12233', 'Suraj Kumar', 'Bus', '2046-06-10'),
('13344', 'Sunil Shastry', 'Bus', '2043-06-20'),
('14455', 'Ramesh Kumar', 'Bus', '2041-07-30'),
('15566', 'RatnaKumari', 'Bus', '2034-04-02'),
('16677', 'Shoaib Khan', 'Bus', '2032-01-01'),
('17788', 'Sanjay Kumar', 'Bus', '2032-01-01'),
('18899', 'Dhanush Shekar', 'Bus', '2064-09-02'),
('19900', 'Chandrakala', 'Bus', '2025-10-23'),
('00001', 'Akash Sharma', 'Metro', '2028-05-23'),
('00002', 'Smitha R', 'Metro', '2032-09-29');

--
-- Inserting data into table driver_drives
--
INSERT INTO driver_drives
(Driver_Id, Vehicle_Id)
VALUES 
('10011','8000'),
('11122','0000'),
('12233','9000'),
('13344', '6000'),
('14455', '4000'),
('15566', '3000'),
('16677', '7000'),
('17788', '2000'),
('18899', '1000'),
('19900', '5000'),
('00001', '1100'),
('00002', '1200');

--
-- Inserting data into table on_the_move 
--
INSERT INTO on_the_move
(Status, Route_Id, Direction, Last_Stop, Vehicle_Id)
VALUES 
('On Road', '401A', 0, '06', '1000'),
('On Road', '401A', 1, '01', '2000'),
('On Road', '298M', 0, '06', '3000'),
('At Stop', '298M', 1, '01', '4000'),
('On Road', '283A', 0, '04', '5000'),
('On Road', '283A', 1, '01', '6000'),
('On Road', '291K', 0, '04', '7000'),
('At Stop', '291K', 1, '01', '8000'),
('On Road', '289A', 0, '02', '9000'),
('At Stop', '289A', 1, '01', '0000');

--
-- Inserting data into table transport_price 
--
INSERT INTO transport_price
(Price, cType, Route_Id)
VALUES (23.00, 'Student', '401A'),
       (23.00, 'National Athlete', '401A'),
       (23.00, 'Standard', '401A'),
       (0, 'Martyr\'s Relative', '401A'),
       (0, 'Disabled', '401A'),
       (0, 'Veteran', '401A'),
       (0, 'Elder', '401A'),
       (23.00, 'Student', '298M'),
       (23.00, 'National Athlete', '298M'),
       (23.00, 'Standard', '298M'),
       (0, 'Martyr\'s Relative', '298M'),
       (0, 'Disabled', '298M'),
       (0, 'Veteran', '298M'),
       (0, 'Elder', '298M'),
       (15.00, 'Student', '283A'),
       (15.00, 'National Athlete', '283A'),
       (15.00, 'Standard', '283A'),
       (0, 'Martyr\'s Relative', '283A'),
       (0, 'Disabled', '283A'),
       (0, 'Veteran', '283A'),
       (0, 'Elder', '283A'),
       (15.00, 'Student', '291K'),
       (15.00, 'National Athlete', '291K'),
       (15.00, 'Standard', '291K'),
       (0, 'Martyr\'s Relative', '291K'),
       (0, 'Disabled', '291K'),
       (0, 'Veteran', '291K'),
       (0, 'Elder', '291K'),
	   (5.00, 'Student', '289A'),
       (5.00, 'National Athlete', '289A'),
       (5.00, 'Standard', '289A'),
       (0, 'Martyr\'s Relative', '289A'),
       (0, 'Disabled', '289A'),
       (0, 'Veteran', '289A'),
       (0, 'Elder', '289A');