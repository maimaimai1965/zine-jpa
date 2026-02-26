package ua.mai.zine.jpa.zoo.repository.food_unit;

import org.springframework.stereotype.Repository;
import ua.mai.zine.jpa.repository.BaseRepository;

@Repository
public interface FoodUnitRepository extends BaseRepository<FoodUnit, Integer>, FoodUnitRepositoryCustom
{
}
