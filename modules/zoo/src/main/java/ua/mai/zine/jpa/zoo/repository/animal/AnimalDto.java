package ua.mai.zine.jpa.zoo.repository.animal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnimalDto {

    private Long animalId;
    private String nickname;
    private Integer animalTypeId;
    private String gender;
    private LocalDate birthDt;
    private LocalDate deathDt;
    private String descr;

}
