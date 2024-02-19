CREATE DATABASE db_public_transport;
USE db_public_transport;

CREATE TABLE card_type
(
    cType         varchar(20)      NOT NULL,
    Expiry_Period tinyint UNSIGNED NOT NULL,
    CONSTRAINT pk_a PRIMARY KEY (cType)
);

CREATE TABLE travel_card
(
    Card_Id      char(5)    NOT NULL,
    Balance      double            DEFAULT '0',
    Status       varchar(20)       DEFAULT 'inactive',
    Issue_Date   date        NOT NULL,
    Last_Usage   datetime          DEFAULT NULL,
    Travel_Count smallint UNSIGNED DEFAULT '0',
    cType        varchar(20) NOT NULL,
    CONSTRAINT pk_b PRIMARY KEY (Card_Id),
    KEY cType (cType),
    CONSTRAINT travel_card_ibfk_1 FOREIGN KEY (cType) REFERENCES card_type (cType) ON DELETE CASCADE
);

CREATE TABLE passenger
(
    Passenger_Id char(5)    NOT NULL,
    Full_Name    varchar(20) NOT NULL,
    Birth_Date   date        NOT NULL,
    Card_Id      char(5)    NOT NULL,
    CONSTRAINT pk_c PRIMARY KEY (Passenger_Id),
    KEY Card_Id (Card_Id),
    CONSTRAINT passenger_ibfk_1 FOREIGN KEY (Card_Id) REFERENCES travel_card (Card_Id) ON DELETE CASCADE
);

CREATE TABLE passenger_address
(
    Address_Id   char(3)    NOT NULL,
    Town         varchar(20) NOT NULL,
    Neighborhood varchar(20) NOT NULL,
    Street       varchar(20) NOT NULL,
    Number       varchar(20) NOT NULL,
    X_Coordinate double DEFAULT NULL,
    Y_Coordinate double DEFAULT NULL,
    Passenger_Id char(5)    NOT NULL,
    CONSTRAINT pk_d PRIMARY KEY (Address_Id, Passenger_Id),
    KEY Passenger_Id (Passenger_Id),
    CONSTRAINT passenger_address_ibfk_1 FOREIGN KEY (Passenger_Id) REFERENCES passenger (Passenger_Id) ON DELETE CASCADE
);

CREATE TABLE passenger_phone
(
    Phone        char(10)    NOT NULL,
    Phone_Type   varchar(20) NOT NULL,
    Passenger_Id char(5)    NOT NULL,
    CONSTRAINT pk_e PRIMARY KEY (Phone, Passenger_Id),
    KEY Passenger_Id (Passenger_Id),
    CONSTRAINT passenger_phone_ibfk_1 FOREIGN KEY (Passenger_Id) REFERENCES passenger (Passenger_Id) ON DELETE CASCADE
);

CREATE TABLE user_account
(
    Email        varchar(50) NOT NULL,
    Password     varchar(20) NOT NULL,
    Last_Login   date DEFAULT NULL,
    Passenger_Id char(5)    NOT NULL,
    CONSTRAINT pk_f PRIMARY KEY (Email),
    KEY Passenger_Id (Passenger_Id),
    CONSTRAINT user_account_ibfk_1 FOREIGN KEY (Passenger_Id) REFERENCES passenger (Passenger_Id) ON DELETE CASCADE
);

CREATE TABLE travel_review
(
    Review_Id      char(5)    NOT NULL,
    Message        text,
    Travel_Rating char(1)     NOT NULL,
    Review_Rating char(1) DEFAULT '0',
    Email          varchar(60) NOT NULL,
    CONSTRAINT pk_g PRIMARY KEY (Review_Id),
    KEY Email (Email),
    CONSTRAINT review_ibfk_1 FOREIGN KEY (Email) REFERENCES user_account (Email) ON DELETE CASCADE
);

CREATE TABLE stop
(
    Stop_Id      char(2)    NOT NULL,
    Stop_Name    varchar(20) NOT NULL,
    Town         varchar(20) NOT NULL,
    Neighborhood varchar(20) NOT NULL,
    Street       varchar(20) NOT NULL,
    X_Coordinate double         NOT NULL,
    Y_Coordinate double        NOT NULL,
    CONSTRAINT pk_h PRIMARY KEY (Stop_Id) 
);

CREATE TABLE route
(
    Route_Id  char(4) NOT NULL,
    Direction bit(1)  NOT NULL,
    CONSTRAINT pk_i PRIMARY KEY (Route_Id, Direction)
);

CREATE TABLE route_departure_time
(
    Departure_Time time    NOT NULL,
    Route_Id       char(4) NOT NULL,
    Direction      bit(1)  NOT NULL,
    CONSTRAINT pk_j PRIMARY KEY (Departure_Time, Route_Id, Direction),
    KEY Route_Id (Route_Id, Direction),
    CONSTRAINT route_departure_time_ibfk_1 FOREIGN KEY (Route_Id, Direction) REFERENCES route (Route_Id, Direction)  ON DELETE CASCADE
);

CREATE TABLE route_includes
(
    Visit_Order tinyint UNSIGNED NOT NULL,
    Route_Id    char(4)          NOT NULL,
    Stop_Id     char(2)         NOT NULL,
    CONSTRAINT pk_k PRIMARY KEY (Route_Id, Stop_Id),
    KEY Stop_Id (Stop_Id),
    CONSTRAINT route_includes_ibfk_1 FOREIGN KEY (Route_Id) REFERENCES route (Route_Id) ON DELETE CASCADE,
    CONSTRAINT route_includes_ibfk_2 FOREIGN KEY (Stop_Id) REFERENCES stop (Stop_Id) ON DELETE CASCADE
);

CREATE TABLE journey
(
    Duration  smallint UNSIGNED NOT NULL,
    Stop_Id_1 char(2)          NOT NULL,
    Stop_Id_2 char(2)          NOT NULL,
    CONSTRAINT pk_l PRIMARY KEY (Stop_Id_1, Stop_Id_2),
    KEY Stop_Id_2 (Stop_Id_2),
    CONSTRAINT journey_ibfk_1 FOREIGN KEY (Stop_Id_1) REFERENCES stop (Stop_Id) ON DELETE CASCADE,
    CONSTRAINT journey_ibfk_2 FOREIGN KEY (Stop_Id_2) REFERENCES stop (Stop_Id) ON DELETE CASCADE
);

CREATE TABLE vehicle
(
    Vehicle_Id   char(4)          NOT NULL,
    Vehicle_Type varchar(20)       NOT NULL,
    Capacity     smallint UNSIGNED NOT NULL,
    Status       varchar(20) DEFAULT NULL,
    CONSTRAINT pk_m PRIMARY KEY (Vehicle_Id)
);

CREATE TABLE driver
(
    Driver_Id      char(5)    NOT NULL,
    Driver_Name    varchar(20) NOT NULL,
    License_Type   varchar(20) NOT NULL,
    License_Expiry date        NOT NULL,
    CONSTRAINT pk_n PRIMARY KEY (Driver_Id)
);

CREATE TABLE driver_drives
(
    Driver_Id  char(5) NOT NULL,
    Vehicle_Id char(4) NOT NULL,
    CONSTRAINT pk_o PRIMARY KEY (Driver_Id, Vehicle_Id),
    KEY Vehicle_Id (Vehicle_Id),
    CONSTRAINT driver_drives_ibfk_1 FOREIGN KEY (Driver_Id) REFERENCES driver (Driver_Id) ON DELETE CASCADE,
    CONSTRAINT driver_drives_ibfk_2 FOREIGN KEY (Vehicle_Id) REFERENCES vehicle (Vehicle_Id) ON DELETE CASCADE
);

CREATE TABLE on_the_move
(
    Status     varchar(20) DEFAULT NULL,
    Route_Id   char(4)  NOT NULL,
    Direction  bit(1)   NOT NULL,
    Last_Stop  char(2) NOT NULL,
    Vehicle_Id char(4) NOT NULL,
    CONSTRAINT pk_q PRIMARY KEY (Route_Id, Direction, Vehicle_Id),
    KEY Route_Id (Route_Id, Direction),
    KEY Last_Stop (Last_Stop),
    KEY Vehicle_Id (Vehicle_Id),
    CONSTRAINT on_the_move_ibfk_1 FOREIGN KEY (Last_Stop) REFERENCES stop (Stop_Id) ON DELETE CASCADE,
    CONSTRAINT on_the_move_ibfk_2 FOREIGN KEY (Route_Id, Direction) REFERENCES route (Route_Id, Direction) ON DELETE CASCADE,
    CONSTRAINT on_the_move_ibfk_3 FOREIGN KEY (Vehicle_Id) REFERENCES vehicle (Vehicle_Id) ON DELETE CASCADE
);

CREATE TABLE transport_price
(
    Price    double      NOT NULL,
    cType    varchar(20) NOT NULL,
    Route_Id char(4)     NOT NULL,
    CONSTRAINT pk_r PRIMARY KEY (cType, Route_Id),
    KEY Route_Id (Route_Id),
    CONSTRAINT transport_price_ibfk_1 FOREIGN KEY (cType) REFERENCES card_type (cType) ON DELETE CASCADE,
    CONSTRAINT transport_price_ibfk_2 FOREIGN KEY (Route_Id) REFERENCES route (Route_Id) ON DELETE CASCADE
);