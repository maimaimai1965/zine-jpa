package ua.mai.zine.jpa.zoo.service.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FoodOrderDto {

    private Long foodOrderId;
    private LocalDate orderDt;
    private String state;
    private Long animalId;
    private Integer foodId;
    private Double amount;


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
//    public String getState() {
//        return state;
//    }
//
//    public void setState(String state) {
//        this.state = state;
//    }
//
//    public Long getAnimalId() {
//        return animalId;
//    }
//
//    public void setAnimalId(Long animalId) {
//        this.animalId = animalId;
//    }
//
//    public Integer getFoodId() {
//        return foodId;
//    }
//
//    public void setFoodId(Integer foodId) {
//        this.foodId = foodId;
//    }
//
//    public Double getAmount() {
//        return amount;
//    }
//
//    public void setAmount(Double amount) {
//        this.amount = amount;
//    }
}
