package ua.mai.zine.jpa.zoo.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnimalTypeDto {

    private Integer animalTypeId;
    private String name;
    private String descr;

}
