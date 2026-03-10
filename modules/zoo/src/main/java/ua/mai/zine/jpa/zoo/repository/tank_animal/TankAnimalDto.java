package ua.mai.zine.jpa.zoo.repository.tank_animal;

import java.time.LocalDateTime;

public record TankAnimalDto (
    Long tankAnimalId,
    Integer tankId,
    Long animalId,
    LocalDateTime toDt
){}
