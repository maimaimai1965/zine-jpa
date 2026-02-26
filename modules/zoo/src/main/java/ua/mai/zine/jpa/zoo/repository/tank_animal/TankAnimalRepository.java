package ua.mai.zine.jpa.zoo.repository.tank_animal;

import org.springframework.stereotype.Repository;
import ua.mai.zine.jpa.repository.BaseRepository;

@Repository
public interface TankAnimalRepository extends BaseRepository<TankAnimal, Long>, TankAnimalRepositoryCustom
{
}

