package ua.mai.zine.jpa.zoo.repository.animal;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;import ua.mai.zine.jpa.zoo.repository.animal_type.AnimalType;
import ua.mai.zine.jpa.zoo.repository.tank_animal.TankAnimal;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "ZO_ANIMAL")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ANIMAL_ID")
    private Long animalId;

    @Column(name = "NICKNAME", length = 30, nullable = false)
    private String nickname;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ANIMAL_TYPE_ID", nullable = false)
    private AnimalType animalType;

    @Column(name = "GENDER", length = 1, nullable = false)
    private String gender;

    @Column(name = "BIRTH_DT")
    private LocalDate birthDt;

    @Column(name = "DEATH_DT")
    private LocalDate deathDt;

    @Column(name = "DESCR", length = 255)
    private String descr;

    @OneToMany(mappedBy = "animal", fetch = FetchType.LAZY /*, cascade = CascadeType.ALL, orphanRemoval = true*/)
    private Set<TankAnimal> tankAnimals;


    public Long getAnimalId() {
        return animalId;
    }

    public void setAnimalId(Long animalId) {
        this.animalId = animalId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public AnimalType getAnimalType() {
        return animalType;
    }

    public void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDt() {
        return birthDt;
    }

    public void setBirthDt(LocalDate birthDt) {
        this.birthDt = birthDt;
    }

    public LocalDate getDeathDt() {
        return deathDt;
    }

    public void setDeathDt(LocalDate deathDt) {
        this.deathDt = deathDt;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public Set<TankAnimal> getTankAnimals() {
        return tankAnimals;
    }

    public void setTankAnimals(Set<TankAnimal> tankAnimals) {
        this.tankAnimals = tankAnimals;
    }
}
