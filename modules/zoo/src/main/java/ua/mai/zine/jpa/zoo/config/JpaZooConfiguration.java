package ua.mai.zine.jpa.zoo.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ua.mai.zine.jpa.zoo.repository.animal.Animal;
import ua.mai.zine.jpa.zoo.repository.animal.AnimalRepository;
import ua.mai.zine.jpa.zoo.repository.animal_type.AnimalType;
import ua.mai.zine.jpa.zoo.repository.animal_type.AnimalTypeRepository;
import ua.mai.zine.jpa.zoo.repository.food.Food;
import ua.mai.zine.jpa.zoo.repository.food.FoodRepository;
import ua.mai.zine.jpa.zoo.repository.food_order.FoodOrder;
import ua.mai.zine.jpa.zoo.repository.food_order.FoodOrderMapper;
import ua.mai.zine.jpa.zoo.repository.food_order.FoodOrderRepository;
import ua.mai.zine.jpa.zoo.repository.food_unit.FoodUnit;
import ua.mai.zine.jpa.zoo.repository.food_unit.FoodUnitRepository;
import ua.mai.zine.jpa.zoo.repository.tank.Tank;
import ua.mai.zine.jpa.zoo.repository.tank.TankRepository;
import ua.mai.zine.jpa.zoo.repository.tank_animal.TankAnimal;
import ua.mai.zine.jpa.zoo.repository.tank_animal.TankAnimalRepository;
import ua.mai.zine.jpa.zoo.service.FoodService;

@Configuration
@EntityScan(basePackageClasses = {
        AnimalType.class,
        Animal.class,
        Tank.class,
        TankAnimal.class,
        FoodUnit.class,
        Food.class,
        FoodOrder.class,
})
@EnableJpaRepositories(
        basePackageClasses = {
                AnimalTypeRepository.class,
                AnimalRepository.class,
                TankRepository.class,
                TankAnimalRepository.class,
                FoodUnitRepository.class,
                FoodRepository.class,
                FoodOrderRepository.class,
        }
)
@ComponentScan(basePackages = "ua.mai.zine.jpa.zoo"
)
//@ComponentScan(basePackageClasses = {
//        FoodService.class,
//        FoodOrderMapper.class,
//        // Дублирование бинов-репозиториев, чтобы Idea находила их в сервисах.
//        AnimalTypeRepository.class,
//        AnimalRepository.class,
//        TankRepository.class,
//        TankAnimalRepository.class,
//        FoodUnitRepository.class,
//        FoodRepository.class,
//        FoodOrderRepository.class,
//
//})
public class JpaZooConfiguration {
}
