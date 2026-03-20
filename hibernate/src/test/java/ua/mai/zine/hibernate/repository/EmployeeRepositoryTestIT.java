package ua.mai.zine.hibernate.repository;

//import com.querydsl.core.types.Predicate;
//import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ua.mai.zine.hibernate.IntegrationTestBase;
import ua.mai.zine.hibernate.dto.EmployeeFilter;
import ua.mai.zine.hibernate.entity.EmployeeEntity;
import ua.mai.zine.hibernate.initializer.Postgres;
import ua.mai.zine.hibernate.projection.EmployeeNameView;
import ua.mai.zine.hibernate.projection.EmployeeNativeView;

import java.util.List;
import java.util.Optional;

//import static com.dmdev.springboot.lesson.entity.QEmployeeEntity.employeeEntity;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmployeeRepositoryTestIT extends IntegrationTestBase {

    private static final Integer IVAN_ID = 1;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void testFindById() {
        Optional<EmployeeEntity> employee = employeeRepository.findById(IVAN_ID);
        assertTrue(employee.isPresent());
    }

    @Test
    void testFindByFirstName() {
        Optional<EmployeeEntity> employee = employeeRepository.findByFirstNameContaining("va");
        assertTrue(employee.isPresent());
    }

    @Test
    void testFindByFirstNameAndSalary() {
        List<EmployeeEntity> employees = employeeRepository.findAllByFirstNameAndSalary("Ivan", 1000);
        assertThat(employees, hasSize(1));
    }

    @Test
    void testFindByFirstNameAndSalaryNative() {
        List<EmployeeEntity> employees = employeeRepository.findAllByFirstNameAndSalaryNative("Ivan", 1000);
        assertThat(employees, hasSize(1));
    }

    @Test
    void testFindBySalary() {
        List<EmployeeNameView> employees = employeeRepository.findAllBySalaryGreaterThan(500);
        assertThat(employees, hasSize(2));
    }

    @Test
    void testFindBySalaryNative() {
        List<EmployeeNativeView> employees = employeeRepository.findAllBySalaryGreaterThanNative(500);
        assertThat(employees, hasSize(2));
    }

    @Test
    void testFindByFilterCustomQuery() {
        System.out.println("Testcontainers JDBC URL (ваш контейнер): " +
                ua.mai.zine.hibernate.initializer.Postgres.container.getJdbcUrl());
//        // Если используете Spring DataSource
//        System.out.println("Spring DataSource URL: " + dataSource.getConnection().getMetaData().getURL());

        EmployeeFilter filter = EmployeeFilter.builder()
                .firstName("Ivan")
                .build();
        List<EmployeeEntity> customQuery = employeeRepository.findByFilter(filter);
        assertThat(customQuery, hasSize(1));
    }

//    @Test
//    void testQuerydslPredicates() {
//        BooleanExpression predicate = employeeEntity.firstName.containsIgnoreCase("ivaN")
//                .and(employeeEntity.salary.goe(1000));
//        Page<EmployeeEntity> allValues = employeeRepository.findAll(predicate, Pageable.unpaged());
//        assertThat(allValues.getContent(), hasSize(1));
//    }
//
//    @Test
//    void testQPredicates() {
//        EmployeeFilter filter = EmployeeFilter.builder()
//                .firstName("ivaN")
//                .salary(1000)
//                .build();
//        Predicate predicate = QPredicates.builder()
//                .add(filter.getFirstName(), employeeEntity.firstName::containsIgnoreCase)
//                .add(filter.getLastName(), employeeEntity.lastName::containsIgnoreCase)
//                .add(filter.getSalary(), employeeEntity.salary::goe)
//                .buildAnd();
//        Iterable<EmployeeEntity> result = employeeRepository.findAll(predicate);
//        assertTrue(result.iterator().hasNext());
//        System.out.println();
//    }

}
