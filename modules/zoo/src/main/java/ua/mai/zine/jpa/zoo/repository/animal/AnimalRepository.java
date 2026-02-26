package ua.mai.zine.jpa.zoo.repository.animal;

import org.springframework.stereotype.Repository;
import ua.mai.zine.jpa.repository.BaseRepository;

@Repository
public interface AnimalRepository extends BaseRepository<Animal, Long>, AnimalRepositoryCustom
{
}

