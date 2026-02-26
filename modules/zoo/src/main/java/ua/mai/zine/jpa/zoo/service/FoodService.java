package ua.mai.zine.jpa.zoo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.mai.zine.jpa.zoo.repository.animal.Animal;
import ua.mai.zine.jpa.zoo.repository.animal.AnimalRepository;
import ua.mai.zine.jpa.zoo.repository.food.Food;
import ua.mai.zine.jpa.zoo.repository.food.FoodRepository;
import ua.mai.zine.jpa.zoo.repository.food_order.FoodOrder;
import ua.mai.zine.jpa.zoo.repository.food_order.FoodOrderRepository;
import ua.mai.zine.jpa.zoo.repository.food_order.FoodOrderState;
import ua.mai.zine.jpa.zoo.service.dto.FoodOrderDto;
import ua.mai.zine.jpa.zoo.repository.food_order.FoodOrderMapper;

@Service
//@RequiredArgsConstructor
@Transactional
public class FoodService {

    private final FoodOrderRepository foodOrderRepository;
    private final AnimalRepository animalRepository;
    private final FoodRepository foodRepository;
    private final FoodOrderMapper foodOrderMapper;

public FoodService(FoodOrderRepository foodOrderRepository, AnimalRepository animalRepository, FoodRepository foodRepository, FoodOrderMapper foodOrderMapper) {
    this.foodOrderRepository = foodOrderRepository;
    this.animalRepository = animalRepository;
    this.foodRepository = foodRepository;
    this.foodOrderMapper = foodOrderMapper;
}

   /**
     * Добавляет заказ FoodOrder, записывает его в БД со статусом NEW.
     *
     * @param foodOrderDto
     * @return
     */
    public FoodOrderDto addFoodOrder(FoodOrderDto foodOrderDto) {
        return addFoodOrder(foodOrderDto, false);
    }

    /**
     * Добавляет заказ FoodOrder, записывает его в БД со статусом NEW, и если нужна регистрация, то выполняет ее.
     * Если регистрация успешна - FoodOrder переводится в статус REGISTERED.
     *
     * @param foodOrderDto
     * @param register
     * @return
     */
    public FoodOrderDto addFoodOrder(FoodOrderDto foodOrderDto, boolean register) {

        FoodOrder foodOrder = foodOrderMapper.toEntity(foodOrderDto);
        foodOrder.setState(FoodOrderState.NEW);

        FoodOrder newFoodOrder = foodOrderRepository.save(foodOrder);
        if (register)
            registerFoodOrder(newFoodOrder);

        return foodOrderMapper.toDto(newFoodOrder);
    }

    @Transactional(readOnly = true)
    public Animal findAnimal(Long animalId) {
        return animalRepository.findById(animalId)
                .orElseThrow(() -> new IllegalArgumentException("Animal not found for id: " + animalId));
    }

    @Transactional(readOnly = true)
    public Food findFood(Integer foodId) {
        return foodRepository.findById(foodId)
                .orElseThrow(() -> new IllegalArgumentException("Food not found for id: " + foodId));
    }

    @Transactional(readOnly = true)
    public FoodOrder findFoodOrder(Long foodOrderId) {
        return foodOrderRepository.findById(foodOrderId)
                .orElseThrow(() -> new IllegalArgumentException("FoodOrder not found for id: " + foodOrderId));
    }

    private boolean registerFoodOrder(FoodOrder foodOrder) {
        if (foodOrder.getState() == FoodOrderState.NEW) {
            foodOrder.setState(FoodOrderState.IN_PROGRESS);
            if (pushFoodOrder(foodOrder)) {
                foodOrder.setState(FoodOrderState.REGISTERED);
                foodOrderRepository.save(foodOrder);
                return true;
            }
        } else
            throw new IllegalStateException("FoodOrder not in state NEW for registration (state: " + foodOrder.getState() +")");
        return false;
    }

    public boolean pushFoodOrder(FoodOrder foodOrder) {
        return true;
    }

    public boolean registerFoodOrder(Long foodOrderId) {
        FoodOrder foodOrder = findFoodOrder(foodOrderId);
        return registerFoodOrder(foodOrder);
    }

}
