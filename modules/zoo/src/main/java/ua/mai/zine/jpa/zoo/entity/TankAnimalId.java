package ua.mai.zine.jpa.zoo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TankAnimalId implements Serializable {

    @Column(name = "TANK_ID")
    private Integer tankId;

    @Column(name = "ANIMAL_ID")
    private Long animalId;
}
