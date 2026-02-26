package ua.mai.zine.jpa.zoo.repository.food_order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ua.mai.zine.jpa.Utils;
import ua.mai.zine.jpa.zoo.repository.animal.Animal;
import ua.mai.zine.jpa.zoo.repository.animal.AnimalRepository;
import ua.mai.zine.jpa.zoo.repository.food.Food;
import ua.mai.zine.jpa.zoo.repository.food.FoodRepository;import ua.mai.zine.jpa.zoo.service.dto.FoodOrderDto;

@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FoodOrderMapper {

    private final AnimalRepository animalRepository;
    private final FoodRepository foodRepository;

    public FoodOrder toEntity(FoodOrderDto dto) {
        if (dto == null) {
            return null;
        }

        FoodOrder foodOrder = new FoodOrder();

        foodOrder.setFoodOrderId(dto.getFoodOrderId());
        foodOrder.setOrderDt(dto.getOrderDt());

        if (dto.getState() != null) {
            FoodOrderState state = FoodOrderState.fromCode(dto.getState())
                    .orElseThrow(() -> new IllegalArgumentException("FoodOrderDto: invalid state: " + dto.getState()));
            foodOrder.setState(FoodOrderState.valueOf(dto.getState()));
        } else
            foodOrder.setState(FoodOrderState.NEW);

        if (dto.getAmount() == null) {
            throw new IllegalArgumentException("FoodOrderDto: amount is required");
        } else {
            if (dto.getAmount() <= 0)
                throw new IllegalArgumentException("FoodOrderDto: amount must be positive");
            foodOrder.setAmount(Utils.toBigDecimal(dto.getAmount()));
        }

        if (dto.getAnimalId() == null) {
            throw new IllegalArgumentException("FoodOrderDto: animalId is required");
        } else {
            Animal animal = animalRepository.findById(dto.getAnimalId())
                    .orElseThrow(() -> new IllegalArgumentException("FoodOrderDto: Animal not found for animalId=" + dto.getAnimalId()));
            foodOrder.setAnimal(animal);
        }

        if (dto.getFoodId() == null) {
            throw new IllegalArgumentException("FoodOrderDto: foodId is required");
        } else {
            Food food = foodRepository.findById(dto.getFoodId())
                    .orElseThrow(() -> new IllegalArgumentException("FoodOrderDto: Food not found for foudId=" + dto.getFoodId()));
            foodOrder.setFood(food);
        }

        return foodOrder;
    }

    public FoodOrderDto toDto(FoodOrder entity) {
        if (entity == null) {
            return null;
        }
        FoodOrderDto dto = new FoodOrderDto();

        dto.setFoodOrderId(entity.getFoodOrderId());
        dto.setOrderDt(entity.getOrderDt());
        dto.setState(entity.getState().getCode());
        dto.setAnimalId(entity.getAnimal().getAnimalId());
        dto.setFoodId(entity.getFood().getFoodId());
        dto.setAmount(Utils.toDouble(entity.getAmount()));

        return dto;
    }

}
