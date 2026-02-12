package ua.mai.zine.jpa.zoo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "ZO_ANIMAL")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ANIMAL_ID")
    private Long animalId;

    @Column(name = "NICKNAME", length = 30, nullable = false)
    private String nickname;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ANIMAL_TYPE_ID", nullable = false)
    private AnimalType animalType;

    @Column(name = "GENDER", length = 1, nullable = false)
    private String gender;

    @Column(name = "BIRTH_DT")
    private LocalDate birthDt;

    @Column(name = "DEATH_DT")
    private LocalDate deathDt;

    @Column(name = "DESCR", length = 255)
    private String descr;
}
