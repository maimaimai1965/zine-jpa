package ua.mai.zine.hibernate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @Size(max = 128)
    @Column(name = "username", nullable = false, length = 128)
    private String username;

    @Size(max = 128)
    @Column(name = "firstname", length = 128)
    private String firstname;

    @Size(max = 128)
    @Column(name = "lastname", length = 128)
    private String lastname;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "age")
    private Integer age;


}