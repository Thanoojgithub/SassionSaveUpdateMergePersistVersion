create table mydb.employee (EID BIGINT PRIMARY KEY DEFAULT NEXTVAL('employeeIdGen'), ENAME VARCHAR(30) NOT NULL, VERSION INT);
drop table mydb.employee;

select * from mydb.employee;

CREATE SEQUENCE employeeIdGen INCREMENT BY 1 START WITH 1;


------------------composite key-----------------------------------
CREATE TABLE mydb.customer (
CID BIGINT NOT NULL,
CNAME VARCHAR(30) NOT NULL,
LOCATION VARCHAR(30) NOT NULL,
VERSION INT,
DOB DATE,
CONSTRAINT "CUSTOMER_CK_CID_CNAME" PRIMARY KEY (cid, cname)
);

ALTER TABLE mydb.customer ADD CONSTRAINT "CUSTOMER_CK_CID_CNAME" PRIMARY KEY (CID, CNAME);
ALTER TABLE mydb.customer DROP CONSTRAINT "CUSTOMER_CK_CID_CNAME";
DROP TABLE mydb.customer;
DELETE FROM mydb.customer;
---------------------------------------------------------------------------------