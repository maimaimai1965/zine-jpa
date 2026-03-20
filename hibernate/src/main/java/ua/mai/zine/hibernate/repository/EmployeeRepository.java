package ua.mai.zine.hibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.mai.zine.hibernate.entity.EmployeeEntity;
import ua.mai.zine.hibernate.projection.EmployeeNameView;
import ua.mai.zine.hibernate.projection.EmployeeNativeView;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer>, EmployeeRepositoryCustom
//      , QuerydslPredicateExecutor<EmployeeEntity>
{
    // 1. Построение запроса по ключевым словам (см. https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html).
    Optional<EmployeeEntity> findByFirstNameContaining(String firstName);

    // 2. Запрос на  JPQL (Java Persistence Query Language)
    @Query("select e from EmployeeEntity e where e.firstName = :name and e.salary = :salary")
    List<EmployeeEntity> findAllByFirstNameAndSalary(@Param("name") String firstName, @Param("salary") Integer salary);

    // 3. Native SQL query
    @Query(value = "select e.* from employee e where e.first_name = :name and e.salary = :salary",
           nativeQuery = true)
    List<EmployeeEntity> findAllByFirstNameAndSalaryNative(@Param("name") String firstName, @Param("salary") Integer salary);

    // 4. Построение запроса по ключевым словам + Projection
    List<EmployeeNameView> findAllBySalaryGreaterThan(Integer salary);

    // 5.Native SQL query + Projection
    @Query(value = """
                   select e.id as id,
                          e.first_name || e.last_name as fullName
                   from employee e
                   where e.salary > :salary
                   """,
           nativeQuery = true)
    List<EmployeeNativeView> findAllBySalaryGreaterThanNative(@Param("salary") Integer salary);

}
