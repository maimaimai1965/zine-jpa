package ua.mai.zine.jpa.zoo.repository.tank;

import org.springframework.stereotype.Repository;
import ua.mai.zine.jpa.repository.BaseRepository;

@Repository
public interface TankRepository extends BaseRepository<Tank, Integer>, TankRepositoryCustom
{
}

