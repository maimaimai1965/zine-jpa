
601. Human Traffic of Stadium  https://leetcode.com/problems/human-traffic-of-stadium/description/

Мое решение:

https://leetcode.com/problems/human-traffic-of-stadium/description/

with w as (
   select *
   from stadium q
   where q.people >=100
)
select t1.*
from w t1
         join w t2 ON (t2.id-1 = t1.id)
         join w t3 ON (t3.id-2 = t1.id)
UNION DISTINCT
select t1.*
from w t1
         join w t2 ON (t2.id+1 = t1.id)
         join w t3 ON (t3.id+2 = t1.id)
UNION DISTINCT
select t1.*
from w t1
         join w t2 ON (t2.id-1 = t1.id)
         join w t3 ON (t3.id+1 = t1.id)
order by id
;


Window Functions (LAG / LEAD):

WITH traffic AS (
    SELECT
        id,
        visit_date,
        people,
        LAG(people,1) OVER (ORDER BY id)  AS prev1,
        LAG(people,2) OVER (ORDER BY id)  AS prev2,
        LEAD(people,1) OVER (ORDER BY id) AS next1,
        LEAD(people,2) OVER (ORDER BY id) AS next2
    FROM Stadium
)

SELECT id, visit_date, people
FROM traffic
WHERE
    (people >= 100 AND prev1 >= 100 AND prev2 >= 100)
   OR (people >= 100 AND prev1 >= 100 AND next1 >= 100)
   OR (people >= 100 AND next1 >= 100 AND next2 >= 100)
ORDER BY visit_date;


