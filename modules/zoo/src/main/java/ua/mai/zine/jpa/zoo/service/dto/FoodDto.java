package ua.mai.zine.jpa.zoo.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FoodDto {

    private Integer foodId;
    private String name;
    private Integer foodUnitId;

}
