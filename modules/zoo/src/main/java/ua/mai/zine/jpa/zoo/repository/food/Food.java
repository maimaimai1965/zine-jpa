package ua.mai.zine.jpa.zoo.repository.food;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import ua.mai.zine.jpa.zoo.repository.food_unit.FoodUnit;

@Entity
@Table(name = "ZO_FOOD")
//@Getter
//@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FOOD_ID")
    private Integer foodId;

    @Column(name = "NAME", length = 128, nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FOOD_UNIT_ID")
    private FoodUnit foodUnit;


    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FoodUnit getFoodUnit() {
        return foodUnit;
    }

    public void setFoodUnit(FoodUnit foodUnit) {
        this.foodUnit = foodUnit;
    }
}
