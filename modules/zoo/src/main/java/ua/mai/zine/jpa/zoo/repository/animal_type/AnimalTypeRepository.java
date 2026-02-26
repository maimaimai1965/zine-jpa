package ua.mai.zine.jpa.zoo.repository.animal_type;

import org.springframework.stereotype.Repository;
import ua.mai.zine.jpa.repository.BaseRepository;

@Repository
public interface AnimalTypeRepository extends BaseRepository<AnimalType, Integer>, AnimalTypeRepositoryCustom
{
}

