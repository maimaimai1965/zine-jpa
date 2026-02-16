package ua.mai.zine.jpa.zoo.entity.tank;

import org.springframework.stereotype.Repository;
import ua.mai.zine.jpa.repository.BaseRepository;
import ua.mai.zine.jpa.zoo.entity.animal.AnimalRepositoryCustom;

@Repository
public interface TankRepository extends BaseRepository<Tank, Integer>, TankRepositoryCustom
{
}

