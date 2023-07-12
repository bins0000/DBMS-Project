/*
-- Drop tables to start fresh
DROP TABLE IF EXISTS test;
DROP TABLE IF EXISTS make;
DROP TABLE IF EXISTS fix;
DROP TABLE IF EXISTS mistake;
DROP TABLE IF EXISTS purchase;
DROP TABLE IF EXISTS complain;
DROP TABLE IF EXISTS quality_controller;
DROP TABLE IF EXISTS worker;
DROP TABLE IF EXISTS technical_staff;
DROP TABLE IF EXISTS product_1;
DROP TABLE IF EXISTS product_2;
DROP TABLE IF EXISTS product_3;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS accident;
DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS complaint;


-- Create tables

-- Quality Controller
CREATE TABLE quality_controller (
    qname varchar(30) NOT NULL,
    qaddress varchar(50) NOT NULL,
    qsalary REAL NOT NULL,
    qtype INT NOT NULL,
    PRIMARY KEY (qname)
);

-- create an index on quality_controller table with salary being the serch key. 
CREATE INDEX qsalary_idx 
ON quality_controller(qsalary ASC)

-- Worker
CREATE TABLE worker (
    wname varchar(30) NOT NULL,
    waddress varchar(50) NOT NULL,
    wsalary REAL NOT NULL,
    max_no_products INT NOT NULL,
    PRIMARY KEY (wname)
);

-- create an index on worker table with salary being the serch key. 
CREATE INDEX worker 
ON worker(wsalary ASC)

-- Technical Staff
CREATE TABLE technical_staff (
    tname varchar(30) NOT NULL,
    taddress varchar(50) NOT NULL,
    tsalary REAL NOT NULL,
    highest_degree varchar(30) NOT NULL,
    tech_position varchar(30) NOT NULL,
    PRIMARY KEY (tname)
);

-- create an index on worker table with salary being the serch key. 
CREATE INDEX technical_staff 
ON technical_staff(tsalary ASC)

-- Product 
CREATE TABLE product (
    product_ID INT NOT NULL,
    size VARCHAR(15) NOT NULL,
    ptype INT NOT NULL,
    account_number INT,
    established_date DATE,
    cost REAL, 
    PRIMARY KEY (product_ID)
);

-- Product 1
CREATE TABLE product_1 (
    product_ID INT NOT NULL,
    software varchar(30) NOT NULL,
    --account_number INT,
    --established_date DATE,
    --cost REAL, 
    PRIMARY KEY (product_ID),
    FOREIGN KEY (product_ID) REFERENCES product(product_ID)
);

-- Product 2
CREATE TABLE product_2 (
    product_ID INT NOT NULL,
    color varchar(30) NOT NULL,
    --account_number INT,
    --established_date DATE,
    --cost REAL, 
    PRIMARY KEY (product_ID),
    FOREIGN KEY (product_ID) REFERENCES product(product_ID)
);

-- Product 3
CREATE TABLE product_3 (
    product_ID INT NOT NULL,
    pweight REAL NOT NULL,
    --account_number INT,
    --established_date DATE,
    --cost REAL, 
    PRIMARY KEY (product_ID),
    FOREIGN KEY (product_ID) REFERENCES product(product_ID)
);

-- Accident
CREATE TABLE accident (
    accident_number INT NOT NULL,
    accident_date DATE NOT NULL,
    work_day_lost INT, 
    PRIMARY KEY (accident_number)
);

-- Customer
CREATE TABLE customer (
    cname varchar(30) NOT NULL,
    caddress varchar(50) NOT NULL,
    PRIMARY KEY (cname)
);

-- Complaint
CREATE TABLE complaint (
    complaint_ID INT NOT NULL,
    complaint_date DATE NOT NULL,
    complaint_description varchar(50) NOT NULL,
    treatment_expected varchar(30) NOT NULL,
    PRIMARY KEY (complaint_ID)
);

-- Test -- Inspection from quality controller
CREATE TABLE test (
    qname varchar(30) NOT NULL,
    product_ID INT NOT NULL,
    PRIMARY KEY (qname, product_ID),
    FOREIGN KEY (qname) REFERENCES quality_controller(qname),
    FOREIGN KEY (product_ID) REFERENCES product(product_ID)
);

-- Make -- Production from worker
CREATE TABLE make (
    wname varchar(30) NOT NULL,
    product_ID INT NOT NULL,
    date_produced DATE NOT NULL,
    production_time REAL NOT NULL,
    PRIMARY KEY (wname, product_ID),
    FOREIGN KEY (wname) REFERENCES worker(wname),
    FOREIGN KEY (product_ID) REFERENCES product(product_ID)
);

-- Fix -- Repair from technical staff
CREATE TABLE fix (
    tname varchar(30) NOT NULL,
    product_ID INT NOT NULL,
    date_repaired DATE NOT NULL,
    PRIMARY KEY (tname, product_ID),
    FOREIGN KEY (tname) REFERENCES technical_staff(tname),
    FOREIGN KEY (product_ID) REFERENCES product(product_ID)
);

-- Mistake -- when a worker or technical staff makes an accident
CREATE TABLE mistake (
    accident_number INT NOT NULL,
    product_ID INT NOT NULL,
    wname varchar(30),
    tname varchar(30),
    PRIMARY KEY (accident_number, product_ID),
    FOREIGN KEY (accident_number) REFERENCES accident(accident_number),
    FOREIGN KEY (wname) REFERENCES worker(wname),
    FOREIGN KEY (tname) REFERENCES technical_staff(tname),
    FOREIGN KEY (product_ID) REFERENCES product(product_ID)
);

-- Purchase -- when a customer purchases a product
CREATE TABLE purchase (
    cname varchar(30),
    product_ID INT NOT NULL,
    PRIMARY KEY (cname, product_ID),
    FOREIGN KEY (cname) REFERENCES customer(cname),
    FOREIGN KEY (product_ID) REFERENCES product(product_ID)
);

-- Complain -- when a customer complains about a product
CREATE TABLE complain (
    cname varchar(30),
    product_ID INT NOT NULL,
    complaint_ID INT NOT NULL,
    PRIMARY KEY (cname, product_ID, complaint_ID),
    FOREIGN KEY (cname) REFERENCES customer(cname),
    FOREIGN KEY (product_ID) REFERENCES product(product_ID),
    FOREIGN KEY (complaint_ID) REFERENCES complaint(complaint_ID)
);
*/


-- Queries/Procedures

-- 1. Enter a new employee 

-- 1.1 Enter a quality controller
DROP PROCEDURE IF EXISTS quality_controller_insert
GO
CREATE PROCEDURE quality_controller_insert
(@qname VARCHAR(50),
@qaddress VARCHAR(70),
@qsalary INT,
@qtype INT)
AS
BEGIN
    INSERT INTO quality_controller (qname, qaddress, qsalary, qtype) VALUES (@qname, @qaddress, @qsalary, @qtype);
END
GO

-- 1.2 Enter a worker
DROP PROCEDURE IF EXISTS worker_insert
GO
CREATE PROCEDURE worker_insert
(@wname VARCHAR(50),
@waddress VARCHAR(70),
@wsalary INT,
@max_no_products INT)
AS
BEGIN
    INSERT INTO worker (wname, waddress, wsalary, max_no_products) VALUES (@wname, @waddress, @wsalary, @max_no_products);
END
GO

-- 1.3 Enter a technical staff 
DROP PROCEDURE IF EXISTS technical_staff_insert
GO
CREATE PROCEDURE technical_staff_insert
(@tname VARCHAR(50),
@taddress VARCHAR(70),
@tsalary INT,
@highest_degree VARCHAR(15),
@tech_position VARCHAR(50))
AS
BEGIN
    INSERT INTO technical_staff (tname, taddress, tsalary, highest_degree, tech_position) 
    VALUES (@tname, @taddress, @tsalary, @highest_degree, @tech_position);
END
GO


-- 2. Enter a new product
DROP PROCEDURE IF EXISTS product_insert
GO
CREATE PROCEDURE product_insert
(@product_ID INT,
@size VARCHAR(15),
@ptype INT
--@account_number INT,
--@established_date DATE,
--@cost REAL
)
AS
BEGIN
    INSERT INTO product (product_ID, size, ptype) 
    VALUES (@product_ID, @size, @ptype);
END
GO

-- for Product 1
DROP PROCEDURE IF EXISTS product1_insert
GO
CREATE PROCEDURE product1_insert
(@product_ID INT,
@software VARCHAR(50))
AS
BEGIN
    INSERT INTO product_1 (product_ID, software) 
    VALUES (@product_ID, @software);
END
GO

-- for Product 2
DROP PROCEDURE IF EXISTS product2_insert
GO
CREATE PROCEDURE product2_insert
(@product_ID INT,
@color VARCHAR(20))
AS
BEGIN
    INSERT INTO product_2 (product_ID, color) 
    VALUES (@product_ID, @color);
END
GO

-- for Product 3
DROP PROCEDURE IF EXISTS product3_insert
GO
CREATE PROCEDURE product3_insert
(@product_ID INT,
@pweight REAL)
AS
BEGIN
    INSERT INTO product_3 (product_ID, pweight) 
    VALUES (@product_ID, @pweight);
END
GO

-- Make insert -- insert the worker-production information
DROP PROCEDURE IF EXISTS make_insert
GO
CREATE PROCEDURE make_insert
(@wname VARCHAR(50),
@product_ID INT,
@date_produced DATE,
@production_time REAL)
AS
BEGIN
    INSERT INTO make (wname, product_ID, date_produced, production_time) 
    VALUES (@wname, @product_ID, @date_produced, @production_time);
END
GO

-- Fix insert -- insert the technical-staff-repair information
DROP PROCEDURE IF EXISTS fix_insert
GO
CREATE PROCEDURE fix_insert
(@tname VARCHAR(50),
@product_ID INT,
@date_repaired DATE)
AS
BEGIN
    INSERT INTO fix (tname, product_ID, date_repaired) 
    VALUES (@tname, @product_ID, @date_repaired);
END
GO

-- Test insert -- insert the quality-controller-testing information
DROP PROCEDURE IF EXISTS test_insert
GO
CREATE PROCEDURE test_insert
(@qname VARCHAR(50),
@product_ID INT)
AS
BEGIN
    INSERT INTO test (qname, product_ID) 
    VALUES (@qname, @product_ID);
END
GO

-- 3. Enter a customer and product purchased
-- Customer insert
DROP PROCEDURE IF EXISTS customer_insert
GO
CREATE PROCEDURE customer_insert
(@cname VARCHAR(50),
@caddress VARCHAR(50))
AS
BEGIN
    INSERT INTO customer (cname, caddress) 
    VALUES (@cname, @caddress);
END
GO

-- Purchase insert -- product that customer purchased
DROP PROCEDURE IF EXISTS purchase_insert
GO
CREATE PROCEDURE purchase_insert
(@cname VARCHAR(50),
@product_ID INT)
AS
BEGIN
    INSERT INTO purchase (cname, product_ID) 
    VALUES (@cname, @product_ID);
END
GO

-- 4. Create a new account associated with a product
DROP PROCEDURE IF EXISTS account_insert
GO
CREATE PROCEDURE account_insert
(@acc_product_ID INT,
@account_number INT,
@established_date DATE,
@cost REAL)
AS
BEGIN
    UPDATE product
    SET account_number = @account_number, established_date = @established_date, cost = @cost
    WHERE product_ID = @acc_product_ID;
END
GO

-- 5. Enter a complaint associated with a customer and product
-- Insert in complaint
DROP PROCEDURE IF EXISTS complaint_insert
GO
CREATE PROCEDURE complaint_insert
(@complaint_ID INT,
@complaint_date DATE,
@complaint_description VARCHAR(100),
@treatment_expected VARCHAR(100))
AS
BEGIN
    INSERT INTO complaint (complaint_ID, complaint_date, complaint_description, treatment_expected) 
    VALUES (@complaint_ID, @complaint_date, @complaint_description, @treatment_expected);
END
GO
 
-- Insert in complain relationship
DROP PROCEDURE IF EXISTS complain_insert
GO
CREATE PROCEDURE complain_insert
(@complain_cname VARCHAR(50),
@complaint_product_ID INT,
@complaint_ID INT)
AS
BEGIN
    INSERT INTO complain (cname, product_ID ,complaint_ID) 
    VALUES (@complain_cname, @complaint_product_ID, @complaint_ID);
END
GO

-- 6. Enter an accident associated with an appropriate employee and product
-- Insert in accident
DROP PROCEDURE IF EXISTS accident_insert
GO
CREATE PROCEDURE accident_insert
(@accident_number INT,
@accident_date DATE,
@work_day_lost INT)
AS
BEGIN
    INSERT INTO accident (accident_number, accident_date, work_day_lost) 
    VALUES (@accident_number, @accident_date, @work_day_lost);
END
GO
 
-- Insert in mistake relationship with worker
DROP PROCEDURE IF EXISTS mistake_worker_insert
GO
CREATE PROCEDURE mistake_worker_insert
(@accident_number INT,
@product_ID INT,
@name VARCHAR(50))
AS
BEGIN
    INSERT INTO mistake (accident_number, product_ID, wname) 
    VALUES (@accident_number, @product_ID, @name);
END
GO

-- Insert in mistake relationship with technical staff
DROP PROCEDURE IF EXISTS mistake_technical_insert
GO
CREATE PROCEDURE mistake_technical_insert
(@accident_number INT,
@product_ID INT,
@name VARCHAR(50))
AS
BEGIN
    INSERT INTO mistake (accident_number, product_ID, tname) 
    VALUES (@accident_number, @product_ID, @name);
END
GO

-- 7. Retrieve the date produced and time spent to produce a particular product
DROP PROCEDURE IF EXISTS case7
GO
CREATE PROCEDURE case7
(@product_ID INT)
AS
BEGIN
    SELECT date_produced, production_time 
    FROM make
    WHERE product_ID = @product_ID
END
GO

-- 8. Retrieve all products made by a particular worker
DROP PROCEDURE IF EXISTS case8
GO
CREATE PROCEDURE case8
(@wname VARCHAR(50))
AS
BEGIN
    SELECT product_ID 
    FROM make
    WHERE wname = @wname
END
GO

-- 9. Retrieve the total number of errors a particular quality controller made. 
--    This is the total number of products certified by this controller and got some complaints
DROP PROCEDURE IF EXISTS case9
GO
CREATE PROCEDURE case9
(@qname VARCHAR(50))
AS
BEGIN
    SELECT COUNT(t.product_ID) 
    FROM test t, complain c
    WHERE t.qname = @qname
        AND t.product_ID IN (SELECT c.product_ID)
END
GO

-- 10. Retrieve the total costs of the products in the product3 category which were repaired at the
--     request of a particular quality controller
DROP PROCEDURE IF EXISTS case10
GO
CREATE PROCEDURE case10
(@qname VARCHAR(50))
AS
BEGIN
    SELECT SUM(p.cost) as total_cost
    FROM test t, fix f, product_3 p3, product p
    WHERE p.product_ID = p3.product_ID
        AND f.product_ID = p3.product_ID
        AND t.product_ID = f.product_ID
        AND t.qname = @qname
END
GO

-- 11. Retrieve all customers (in name order) who purchased all products of a particular color

DROP PROCEDURE IF EXISTS case11
GO
CREATE PROCEDURE case11
(@color VARCHAR(30))
AS
BEGIN
    SELECT p.cname
    FROM purchase p, product_2 p2
    WHERE p.product_ID = p2.product_ID
        AND p2.color = @color
END
GO

-- 12. Retrieve all employees whose salary is above a particular salary
DROP PROCEDURE IF EXISTS case12
GO
CREATE PROCEDURE case12
(@salary REAL)
AS
BEGIN
    SELECT t.tname
    FROM technical_staff t
    WHERE t.tsalary > @salary
    UNION
    SELECT q.qname
    FROM quality_controller q
    WHERE q.qsalary > @salary
    UNION
    SELECT w.wname
    FROM worker w
    WHERE w.wsalary > @salary
END
GO

-- 13. Retrieve the total number of work days lost due to accidents in repairing the products which got complaints 
DROP PROCEDURE IF EXISTS case13
GO
CREATE PROCEDURE case13
AS
BEGIN
    SELECT SUM(a.work_day_lost) as days_lost
    FROM accident a, mistake m, complain c, fix f
    WHERE a.accident_number = m.accident_number
        AND m.product_ID IN (SELECT c.product_ID)
        AND m.product_ID IN (SELECT f.product_ID)
END
GO

-- 14. Retrieve the average cost of all products made in a particular year
DROP PROCEDURE IF EXISTS case14
GO
CREATE PROCEDURE case14
(@year INT)
AS
BEGIN
    SELECT AVG(p.cost) as avg_cost
    FROM product p, make m
    WHERE p.product_ID = m.product_ID
        AND YEAR(m.date_produced) = @year
END
GO

-- 15. Delete all accidents whose dates are in some range
DROP PROCEDURE IF EXISTS case15
GO
CREATE PROCEDURE case15
(@date1 VARCHAR(10),
@date2 VARCHAR(10))
AS
BEGIN
    DELETE FROM mistake
    WHERE mistake.accident_number IN (SELECT accident.accident_number
                                    FROM accident
                                    WHERE [accident_date]
                                    BETWEEN @date1 AND @date2
                                    ) 
    DELETE FROM accident
    WHERE [accident_date]
    BETWEEN @date1 AND @date2
END
GO

-- 16. Import: enter new employees from a data file until the file is empty (the user must be asked to enter the input file name);
-- file location -- -- https://storageforproject.blob.core.windows.net/newcontainer/technical_staff.csv
/*
CREATE EXTERNAL DATA SOURCE MyAzureBlobStorage
WITH ( TYPE = BLOB_STORAGE,
          LOCATION = 'https://storageforproject.blob.core.windows.net/newcontainer'
);
*/
DROP PROCEDURE IF EXISTS case16_technical_staff
GO
CREATE PROCEDURE case16_technical_staff
(@file VARCHAR(100))
AS
BEGIN
    -- using dynamic SQL
    DECLARE @CSVfile varchar(100);
    SET @CSVfile = @file
    declare @q nvarchar(MAX);
    set @q=
        'BULK INSERT technical_staff
        FROM '+char(39)+@CSVfile+char(39)+'
        WITH
        (
        DATA_SOURCE = ''MyAzureBlobStorage'',
        FIELDTERMINATOR = '','',
        ROWTERMINATOR = ''\n'',
        FIRSTROW = 1  
        )'
    exec(@q)
END
GO

DROP PROCEDURE IF EXISTS case16_worker
GO
CREATE PROCEDURE case16_worker
(@file VARCHAR(100))
AS
BEGIN
    -- using dynamic SQL
    DECLARE @CSVfile varchar(100);
    SET @CSVfile = @file
    declare @q nvarchar(MAX);
    set @q=
        'BULK INSERT worker
        FROM '+char(39)+@CSVfile+char(39)+'
        WITH
        (
        DATA_SOURCE = ''MyAzureBlobStorage'',
        FIELDTERMINATOR = '','',
        ROWTERMINATOR = ''\n'',
        FIRSTROW = 1  
        )'
    exec(@q)
END
GO

DROP PROCEDURE IF EXISTS case16_quality_controller
GO
CREATE PROCEDURE case16_quality_controller
(@file VARCHAR(100))
AS
BEGIN
    -- using dynamic SQL
    DECLARE @CSVfile varchar(100);
    SET @CSVfile = @file
    declare @q nvarchar(MAX);
    set @q=
        'BULK INSERT quality_controller
        FROM '+char(39)+@CSVfile+char(39)+'
        WITH
        (
        DATA_SOURCE = ''MyAzureBlobStorage'',
        FIELDTERMINATOR = '','',
        ROWTERMINATOR = ''\n'',
        FIRSTROW = 1  
        )'
    exec(@q)
END
GO


/*
-- 17. Export: Retrieve all customers (in name order) who purchased all products of a particular color and output them to a data file instead of screen 
--     (the user must be asked to enter the output file name);
DROP PROCEDURE IF EXISTS case17
GO
CREATE PROCEDURE case17
(@color VARCHAR(30),
@filename VARCHAR(100))
AS
BEGIN
    SELECT p.cname
    FROM purchase p, product_2 p2
    WHERE p.product_ID = p2.product_ID
        AND p2.color = @color
    OUTPUT TO @filename;
END
GO
*/
