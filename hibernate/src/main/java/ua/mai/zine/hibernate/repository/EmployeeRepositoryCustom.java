package ua.mai.zine.hibernate.repository;


import ua.mai.zine.hibernate.dto.EmployeeFilter;
import ua.mai.zine.hibernate.entity.EmployeeEntity;

import java.util.List;

public interface EmployeeRepositoryCustom {

    List<EmployeeEntity> findByFilter(EmployeeFilter filter);
}
