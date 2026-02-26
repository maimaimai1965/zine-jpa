package ua.mai.zine.jpa.zoo.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TankDto {

    private Integer tankId;
    private String tankType;
    private String numberCd;
    private String descr;

}
