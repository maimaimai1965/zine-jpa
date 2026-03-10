package ua.mai.zine.jpa.zoo.repository.animal;

import java.time.LocalDate;

public record AnimalDto (
    Long animalId,
    String nickname,
    Integer animalTypeId,
    String gender,
    LocalDate birthDt,
    LocalDate deathDt,
    String descr
){}
