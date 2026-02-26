package ua.mai.zine.jpa.zoo.repository.tank;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;import ua.mai.zine.jpa.zoo.repository.tank_animal.TankAnimal;import java.util.Set;

@Entity
@Table(name = "ZO_TANK")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TANK_ID")
    private Integer tankId;

    @OneToMany(mappedBy = "tank", fetch = FetchType.LAZY /*, cascade = CascadeType.ALL, orphanRemoval = true*/)
    private Set<TankAnimal> tankAnimals;

    @Column(name = "TANK_TYPE", length = 1, nullable = false)
    private String tankType;

    @Column(name = "NUMBER_CD", length = 20, nullable = false)
    private String numberCd;

    @Column(name = "DESCR", length = 255)
    private String descr;
}
