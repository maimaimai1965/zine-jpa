package ua.mai.zine.jpa.zoo.entity.tank_animal;

import org.springframework.stereotype.Repository;
import ua.mai.zine.jpa.repository.BaseRepository;
import ua.mai.zine.jpa.zoo.entity.tank.Tank;
import ua.mai.zine.jpa.zoo.entity.tank.TankRepositoryCustom;

@Repository
public interface TankAnimalRepository extends BaseRepository<TankAnimal, Long>, TankAnimalRepositoryCustom
{
}

