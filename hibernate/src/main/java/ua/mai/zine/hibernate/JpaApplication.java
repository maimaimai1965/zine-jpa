package ua.mai.zine.hibernate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.mai.zine.hibernate.entity.EmployeeEntity;
import ua.mai.zine.hibernate.repository.EmployeeRepository;

import java.util.Optional;
import java.util.TimeZone;

@SpringBootApplication
public class JpaApplication {

    public static void main(String[] args) {
//        TimeZone.setDefault(java.util.TimeZone.getTimeZone("UTC"));
        System.out.println("JVM timezone: " + TimeZone.getDefault().getID());

        ConfigurableApplicationContext context = SpringApplication.run(JpaApplication.class, args);

        checkService(context);
    }

    private static void checkService(ConfigurableApplicationContext context) {
        EmployeeRepository employeeRepository = context.getBean(EmployeeRepository.class);

        Optional<EmployeeEntity> еmployee = employeeRepository.findById(1);
        еmployee.ifPresentOrElse(
                e -> System.out.println("employeeRepository.findById(1) -> " + e.getFirstName()),
                () -> System.out.println("Not found"));
    }

}
