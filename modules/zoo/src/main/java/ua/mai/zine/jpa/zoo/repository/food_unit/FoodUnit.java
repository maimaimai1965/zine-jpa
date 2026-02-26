package ua.mai.zine.jpa.zoo.repository.food_unit;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "ZO_FOOD_UNIT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FoodUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FOOD_UNIT_ID")
    private Integer foodUnitId;

    @Column(name = "NAME", length = 32, nullable = false)
    private String name;

    @Column(name = "SHORT_NAME", length = 10)
    private String shortName;

}
