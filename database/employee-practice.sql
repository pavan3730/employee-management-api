CREATE TABLE employees (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    department VARCHAR(100) NOT NULL
);

INSERT INTO employees (name, email, department)
VALUES ('Pavan', 'pavan@example.com', 'Engineering');
INSERT INTO employees (name, email, department)
VALUES ('Sarah', 'sarah@example.com', 'Human Resources');

SELECT * FROM employees;

SELECT *
FROM employees
WHERE id = 1;

UPDATE employees
SET department = 'Backend Engineering'
WHERE id = 1;

DELETE FROM employees
WHERE id = 2;