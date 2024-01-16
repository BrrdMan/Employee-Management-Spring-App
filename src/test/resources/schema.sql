CREATE TABLE employee(
    emp_id UUID PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    gender VARCHAR(100),
    email VARCHAR(100),
    dob DATE,
    address VARCHAR(255)
);