create table mydb.employee (EID BIGINT PRIMARY KEY DEFAULT NEXTVAL('employeeIdGen'), ENAME VARCHAR(30) NOT NULL, VERSION INT);
drop table mydb.employee;

select * from mydb.employee;

CREATE SEQUENCE employeeIdGen INCREMENT BY 1 START WITH 1;