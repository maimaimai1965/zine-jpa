package ua.mai.zine.jpa.zoo.repository.tank_animal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TankAnimalDto {

    private Long tankAnimalId;
    private Integer tankId;
    private Long animalId;
    private LocalDateTime toDt;

}
