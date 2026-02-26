package ua.mai.zine.jpa.zoo.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FoodUnitDto {

    private Integer foodUnitId;
    private String name;
    private String shortName;

}
