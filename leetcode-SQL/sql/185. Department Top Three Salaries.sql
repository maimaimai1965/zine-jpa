
-- 185. Department Top Three Salaries  https://leetcode.com/problems/department-top-three-salaries/description/


 with s AS (
  SELECT DISTINCT departmentId, salary
  FROM employee
),
q AS (
  SELECT s.*,
         ROW_NUMBER() OVER(PARTITION BY departmentId ORDER BY salary DESC) num
  FROM s
)
SELECT d.name AS Department, e.name AS Employee, e.salary AS Salary
 FROM q
 JOIN employee   e ON (e.departmentId = q.departmentId AND e.salary = q.salary)
 JOIN department d ON (d.id = q.departmentId)
 WHERE q.num <=3
 ;

