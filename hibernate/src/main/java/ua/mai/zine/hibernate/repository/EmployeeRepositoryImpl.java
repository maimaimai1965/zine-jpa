package ua.mai.zine.hibernate.repository;

import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import ua.mai.zine.hibernate.dto.EmployeeFilter;
import ua.mai.zine.hibernate.entity.EmployeeEntity;

import java.util.List;

import static ua.mai.zine.hibernate.entity.QEmployeeEntity.employeeEntity;
import static ua.mai.zine.hibernate.schema.Tables.EMPLOYEE;

@RequiredArgsConstructor
public class EmployeeRepositoryImpl implements EmployeeRepositoryCustom {

    private final DSLContext dslContext;
    private final EntityManager entityManager;

    @Override
    public List<EmployeeEntity> findByFilterWithJooq(EmployeeFilter filter) {
        List<EmployeeEntity> list = dslContext.select(EMPLOYEE.fields())
                .from(EMPLOYEE)
                .where(EMPLOYEE.FIRST_NAME.equalIgnoreCase(filter.getFirstName()))
                .fetch(record -> record.into(EmployeeEntity.class));
        return list;
    }

    @Override
    public List<EmployeeEntity> findByFilterWithJpaQuery(EmployeeFilter filter) {
        return new JPAQuery<EmployeeEntity>(entityManager)
                .select(employeeEntity)
                .from(employeeEntity)
                .where(employeeEntity.firstName.containsIgnoreCase(filter.getFirstName()))
                .fetch();
    }

}
