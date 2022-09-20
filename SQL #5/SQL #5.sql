Use sakila;

CREATE TABLE StudentDetails
(
studId  int NOT NULL PRIMARY KEY,
stuName VARCHAR(50) NOT NULL,
EnrollmentNo int unique,
DateOfJoining date
);

CREATE TABLE StudentStipend
(
stuId int,
Project VARCHAR(4) NOT NULL,
Stipend int,
foreign key (stuId) references StudentDetails(studId)
);

#Test Data
Insert into StudentDetails
Values(
11,
'Nick Panchal',
1234567,
'2019-02-01');

Insert into StudentDetails
Values(
21,
'Yash Panchal',
2468101,
'2017-03-15');

Insert into StudentDetails
Values(
31,
'Gyan Rathod',
3689245,
'2018-05-27');

Insert into StudentStipend
Values(
11,
'P1',
80000);

Insert into StudentStipend
Values(
21,
'P2',
10000);

Insert into StudentStipend
Values(
31,
'P1',
120000);

#Query 1

Insert into StudentDetails
Values(
41,
'Kit Beget',
1115487,
'2020-06-17');

#Query 2

SELECT * 
FROM StudentDetails
WHERE StudId = 11;

#Query 3

UPDATE StudentStipend
SET Project = 'P3'
WHERE StuId = 31;

#Query 4
DROP TABLE StudentStipend;

#Query 5
DELETE FROM StudentDetails
WHERE StudId = 41;

#Query 6
SELECT stuName
FROM StudentDetails
INNER JOIN
StudentStipend
ON StudentDetails.studId = StudentStipend.stuId
WHERE Stipend >= 50000 && Stipend <= 100000;

#Query 7




