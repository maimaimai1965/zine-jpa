package ua.mai.zine.jpa.zoo.entity.animal_type;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "ZO_ANIMAL_TYPE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnimalType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ANIMAL_TYPE_ID")
    private Integer animalTypeId;

    @Column(name = "NAME", length = 100, nullable = false)
    private String name;

    @Column(name = "DESCR", length = 255)
    private String descr;
}
