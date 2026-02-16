package ua.mai.zine.jpa.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ua.mai.zine.jpa.zoo.entity.animal.Animal;
import ua.mai.zine.jpa.zoo.entity.animal.AnimalRepository;
import ua.mai.zine.jpa.zoo.entity.animal_type.AnimalType;
import ua.mai.zine.jpa.zoo.entity.animal_type.AnimalTypeRepository;
import ua.mai.zine.jpa.zoo.entity.tank.Tank;
import ua.mai.zine.jpa.zoo.entity.tank.TankRepository;
import ua.mai.zine.jpa.zoo.entity.tank_animal.TankAnimal;
import ua.mai.zine.jpa.zoo.entity.tank_animal.TankAnimalRepository;

@Configuration
@EntityScan(basePackageClasses = {
        AnimalType.class,
        Animal.class,
        Tank.class,
        TankAnimal.class,
})
@EnableJpaRepositories(
        basePackageClasses = {
                AnimalTypeRepository.class,
                AnimalRepository.class,
                TankRepository.class,
                TankAnimalRepository.class,
        }
)
public class JpaZooConfiguration {
}
