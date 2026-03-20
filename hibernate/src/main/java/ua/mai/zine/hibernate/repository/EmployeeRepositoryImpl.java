package ua.mai.zine.hibernate.repository;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import ua.mai.zine.hibernate.dto.EmployeeFilter;
import ua.mai.zine.hibernate.entity.EmployeeEntity;

import java.util.List;

import static ua.mai.zine.hibernate.schema.Tables.EMPLOYEE;

@RequiredArgsConstructor
public class EmployeeRepositoryImpl implements EmployeeRepositoryCustom {

    private final DSLContext dslContext;

    @Override
    public List<EmployeeEntity> findByFilter(EmployeeFilter filter) {
        List<EmployeeEntity> list = dslContext.select(EMPLOYEE.fields())
                .from(EMPLOYEE)
                .where(EMPLOYEE.FIRST_NAME.equalIgnoreCase(filter.getFirstName()))
                .fetch(record -> record.into(EmployeeEntity.class));
        return list;
    }

}
