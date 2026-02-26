package ua.mai.zine.jpa.zoo.repository.food_order;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import ua.mai.zine.jpa.zoo.repository.animal.Animal;
import ua.mai.zine.jpa.zoo.repository.food.Food;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "ZOO_FOOD_ORDER")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FoodOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FOOD_ORDER_ID")
    private Long foodOrderId;

    @Column(name = "ORDER_DT", nullable = false)
    private LocalDate orderDt;

    @Column(name = "STATE", length = 1)
    @Convert(converter = FoodOrderStateConverter.class)
    private FoodOrderState state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ANIMAL_ID", nullable = false)
    private Animal animal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FOOD_ID", nullable = false)
    private Food food;

    @Column(name = "AMOUNT", precision = 10, scale = 2, nullable = false)
    private BigDecimal amount;


//    public Long getFoodOrderId() {
//        return foodOrderId;
//    }
//
//    public void setFoodOrderId(Long foodOrderId) {
//        this.foodOrderId = foodOrderId;
//    }
//
//    public LocalDate getOrderDt() {
//        return orderDt;
//    }
//
//    public void setOrderDt(LocalDate orderDt) {
//        this.orderDt = orderDt;
//    }
//
//    public FoodOrderState getState() {
//        return state;
//    }
//
//    public void setState(FoodOrderState state) {
//        this.state = state;
//    }
//
//    public Animal getAnimal() {
//        return animal;
//    }
//
//    public void setAnimal(Animal animal) {
//        this.animal = animal;
//    }
//
//    public Food getFood() {
//        return food;
//    }
//
//    public void setFood(Food food) {
//        this.food = food;
//    }
//
//    public BigDecimal getAmount() {
//        return amount;
//    }
//
//    public void setAmount(BigDecimal amount) {
//        this.amount = amount;
//    }

}
