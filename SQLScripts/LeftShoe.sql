DROP DATABASE IF EXISTS left_shoe;
CREATE DATABASE left_shoe;

USE left_shoe;

CREATE TABLE users (
user_id int NOT NULL AUTO_INCREMENT,
username VARCHAR(50),
password VARCHAR(30),
first_name VARCHAR (40),
last_name VARCHAR (40),
PRIMARY KEY (user_id));

CREATE TABLE shoe (
shoe_id int NOT NULL AUTO_INCREMENT,
brand VARCHAR(50),
shoe_name VARCHAR(75),
shoe_type VARCHAR (75),
price FLOAT,
stock INT,
PRIMARY KEY (shoe_id));

CREATE TABLE orders (
order_id int NOT NULL AUTO_INCREMENT,
order_date DATE,
user_id int, 
total FLOAT,
PRIMARY KEY (order_id),
FOREIGN KEY (user_id) REFERENCES users(user_id));

CREATE TABLE orders_shoes(
order_id INT,
shoe_id INT);

INSERT INTO users  
VALUES
(NULL, 
'testuser', 
'pw123', 
'Test', 
'User');

INSERT INTO users  
VALUES
(NULL, 
'testuser2', 
'password8', 
'Johnny', 
'Test');

INSERT INTO shoe  
VALUES
(NULL, 
'Noke', 
'Alligator 5', 
'Racing Boot', 
25.97,
25);

INSERT INTO shoe  
VALUES
(NULL, 
'Inverse', 
'Teds Bed', 
'Low-Rise Sneaker', 
55.25,
50);

INSERT INTO shoe  
VALUES
(NULL, 
'Mr. Martins', 
'Milkmaid', 
'Fancy?', 
30.00,
20);

INSERT INTO shoe  
VALUES
(NULL, 
'Ranago', 
'Stepinator', 
'BIG BOY BOOT', 
45.33,
35);

INSERT INTO shoe  
VALUES
(NULL, 
'Noke', 
'Crocodile 8', 
'Slip-on Track Shoe', 
33.33,
15);

INSERT INTO shoe  
VALUES
(NULL, 
'Inverse', 
'Jebs Shed', 
'Shoe', 
15.29,
27);

INSERT INTO shoe  
VALUES
(NULL, 
'Noke', 
'Lizard 3', 
'High-End Lounge Shoe', 
153.25,
10);

INSERT INTO shoe  
VALUES
(NULL, 
'Ranago', 
'Right Shoe', 
'Shoe (RIGHT SHOE EDITION)', 
200.99,
5);

INSERT INTO orders  
VALUES
(NULL, 
'2022-09-27', 
1,
(SELECT SUM(price) FROM shoe WHERE shoe_id = 7)
);

INSERT INTO orders  
VALUES
(NULL, 
'2022-09-25', 
2,
(SELECT SUM(price) FROM shoe WHERE brand = "Ranago")
);

INSERT INTO orders_shoes  
VALUES
(1, 
7);

INSERT INTO orders_shoes  
VALUES
(2, 
4);

INSERT INTO orders_shoes  
VALUES
(2, 
8);