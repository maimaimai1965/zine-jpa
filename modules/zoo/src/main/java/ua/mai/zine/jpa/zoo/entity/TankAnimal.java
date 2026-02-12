package ua.mai.zine.jpa.zoo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "ZO_TANK_ANIMAL")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TankAnimal {

    @EmbeddedId
    private TankAnimalId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("tankId")
    @JoinColumn(name = "TANK_ID")
    private Tank tank;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("animalId")
    @JoinColumn(name = "ANIMAL_ID")
    private Animal animal;

    @Column(name = "FROM_DT", nullable = false)
    private LocalDateTime fromDt;

    @Column(name = "TO_DT")
    private LocalDateTime toDt;
}
