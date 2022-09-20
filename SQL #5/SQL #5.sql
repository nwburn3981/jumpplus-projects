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
)

#Test Data


