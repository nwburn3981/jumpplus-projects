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
null,
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
SELECT stuName, stipend
FROM StudentDetails
LEFT JOIN
StudentStipend
ON StudentDetails.studId = StudentStipend.stuId;

#Query 8
SELECT *
FROM StudentDetails
RIGHT JOIN
StudentStipend
ON StudentDetails.studId = StudentStipend.stuId;

#Query 9
#Write an SQL query to fetch the number of students working in project ‘P1’
SELECT COUNT(StuId), Project
FROM StudentStipend
WHERE Project ='P1'; 

#Query 10. Write an SQL query for fetching duplicate records from a table.
SELECT *
FROM StudentDetails
GROUP BY StuName
HAVING COUNT(StuName) > 1;


#Query 11. Write an SQL query for removing duplicates from a table without using a temporary table.
DELETE
FROM StudentDetails
WHERE StudId NOT IN(
SELECT * FROM (
SELECT MAX(StudId)
FROM StudentDetails
GROUP BY stuName
) as max
) 
;

#Query 12. Write an SQL query for fetching all the Students who also have enrollment No from StudentDetails table.
SELECT *
FROM StudentDetails
WHERE EnrollmentNo IS NOT NULL;

#Query 13. Write an SQL query for creating a new table with data and structure copied from another table.
CREATE TABLE student_backup 
AS
SELECT *
FROM StudentDetails;

#Query 14. Write an SQL query to fetch a joint record between two tables using intersect.
SELECT DISTINCT * FROM StudentDetails
INNER JOIN
StudentStipend ON StudentDetails.studId = StudentStipend.stuId;

#Query 15. Write an SQL query for fetching records that are present in one table but not in another table using Minus.
SELECT *
FROM StudentDetails
WHERE studId NOT IN (
SELECT stuId 
FROM StudentStipend);

#Query 16. Write an SQL query to fetch count of students project-wise sorted by project’s count in descending order.
SELECT  stuId, Project, Stipend, Count(Project) as project_members
FROM StudentStipend
GROUP BY Project
ORDER BY project_members DESC;

#Query 17. Write an SQL query for creating an empty table with the same structure as some other table.
CREATE TABLE student_backup_backup
LIKE StudentDetails;

#Query 18. Write an SQL query for finding current date-time.
SELECT current_timestamp();

#Query 19. Write an SQL query for fetching only even rows from the table.

SELECT *
FROM ( 
SELECT row_number() 
OVER (order by studId)AS line , studId, stuName, EnrollmentNo, DateOfJoining
FROM StudentDetails) as orderTable
WHERE Mod(line, 2) = 0;

#Query 20. Write an SQL query for fetching all the Students details from StudentDetails table who joined in the Year 2018.
SELECT *
FROM StudentDetails
WHERE DateofJoining > '2018-12-12';

#Query 21. Write the SQL query to find the nth highest stipend from the table.
SELECT *
FROM ( 
SELECT row_number() 
OVER (order by Stipend DESC)AS line , stuId, Project, Stipend
FROM StudentStipend) as orderedTable
WHERE line = 3;

#Query 22. Write SQL query for fetching top n records using LIMIT?
SELECT *
FROM StudentStipend
Order BY Stipend DESC
LIMIT 2;

#Query 23. Write a query for fetching only the first name(string before space) from the Name column of StudentDetails table.
SELECT substring_index( stuName, ' ', 1)
FROM StudentDetails;

#Query 24. Write an SQL query for fetching only odd rows from the table.

SELECT *
FROM ( 
SELECT row_number() 
OVER (order by studId)AS line , studId, stuName, EnrollmentNo, DateOfJoining
FROM StudentDetails) as orderTable
WHERE Mod(line, 2) = 1;

#Query 25. Write SQL query for finding the 3rd highest stipend from the table without using TOP/limit keyword.
SELECT *
FROM ( 
SELECT row_number() 
OVER (order by Stipend DESC)AS line , stuId, Project, Stipend
FROM StudentStipend) as orderedTable
WHERE line = 3;

#Query Bonus : Try to create at least 3 bonus queries which contain 3 subqueries , can use Sakila database for that




