CREATE DATABASE moonlighter;

USE moonlighter;

CREATE TABLE users (
user_id INT NOT NULL AUTO_INCREMENT,
name VARCHAR(100),
username VARCHAR(50),
password VARCHAR(50),
city VARCHAR(50),
street_address VARCHAR(150),
phone_number VARCHAR(15),
PRIMARY KEY (user_id));

INSERT INTO users
VALUES (
NULL,
"Test User",
"test1",
"password123",
"Testland",
"123 Test Dr.",
"555-555-5555"
);

INSERT INTO users
VALUES (
NULL,
"User Test",
"test2",
"password123",
"Testton",
"123 Test Rd.",
"666-666-6666"
);

CREATE TABLE accounts (
account_id INT NOT NULL AUTO_INCREMENT,
balance DOUBLE,
created VARCHAR(50),
account_type VARCHAR(15),
user_id INT,
PRIMARY KEY (account_id),
FOREIGN KEY (user_id) REFERENCES users(user_id));

INSERT INTO accounts 
VALUES (
NULL,
5000,
"2022-10-04T13:32:01.6",
"CHECKING",
1
);

INSERT INTO accounts 
VALUES (
NULL,
10000,
"2022-10-04T13:32:01.671",
"SAVINGS",
1
);

INSERT INTO accounts 
VALUES (
NULL,
200,
"2022-10-04T13:32:01.671736",
"CHECKING",
2
);

INSERT INTO accounts 
VALUES (
NULL,
500,
"2022-10-04T13:32:01.135",
"SAVINGS",
2
);

CREATE TABLE transactions(
transaction_id INT NOT NULL AUTO_INCREMENT,
description VARCHAR(150),
initial_account_id INT,
end_account_id INT,
created VARCHAR(50),
PRIMARY KEY (transaction_id),
FOREIGN KEY (initial_account_id) REFERENCES accounts(account_id),
FOREIGN KEY (end_account_id) REFERENCES accounts(account_id)
);

INSERT INTO transactions
VALUES (
NULL,
"Initial deposit - $5000",
1,
1,
"2022-10-04T13:32:01.555"
);

INSERT INTO transactions
VALUES (
NULL,
"Initial deposit - $10000",
2,
2,
"2022-10-04T13:32:01.666"
);

INSERT INTO transactions
VALUES (
NULL,
"Initial deposit - $200",
3,
3,
"2022-10-04T13:32:01.678"
);

INSERT INTO transactions
VALUES (
NULL,
"Initial deposit - $500",
4,
4,
"2022-10-04T13:32:01.67155"
);



