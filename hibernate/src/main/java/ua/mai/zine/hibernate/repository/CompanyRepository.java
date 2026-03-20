package ua.mai.zine.hibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.mai.zine.hibernate.entity.CompanyEntity;
import ua.mai.zine.hibernate.projection.EmployeeNameView;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Integer> {

}
