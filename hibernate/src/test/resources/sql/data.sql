
-- for CompanyRepositoryTest.testGetById()
insert into company (id, name)
values
       (1, 'Apple'),
       (2, 'Google');

SELECT setval('company_id_seq', (SELECT MAX(id) FROM company));


-- for EmployeeRepositoryTest
insert into employee (id, first_name, last_name, birth_day, salary, company_id)
values
    (1,'Ivan','Ivanov','2006-03-03',1000, NULL),
    (2,'Petr','Petrov','2008-04-04',700, NULL);

SELECT setval('employee_id_seq', (SELECT MAX(id) FROM company));
