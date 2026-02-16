package ua.mai.zine.jpa.zoo.entity.tank_animal;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;import ua.mai.zine.jpa.zoo.entity.animal.Animal;import ua.mai.zine.jpa.zoo.entity.tank.Tank;
import java.time.LocalDateTime;

@Entity
@Table(name = "ZO_TANK_ANIMAL")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TankAnimal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TANK_ANIMAL_ID")
    private Long tankAnimald;

    @ManyToOne(/*fetch = FetchType.LAZY*/)
    @JoinColumn(name = "TANK_ID", nullable = false)
    private Tank tank;

    @ManyToOne(/*fetch = FetchType.LAZY*/)
    @JoinColumn(name = "ANIMAL_ID", nullable = false)
    private Animal animal;


    @Column(name = "TO_DT")
    private LocalDateTime toDt;
}
