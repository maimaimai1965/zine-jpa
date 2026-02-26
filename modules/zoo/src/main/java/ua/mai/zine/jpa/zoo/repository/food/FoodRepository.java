package ua.mai.zine.jpa.zoo.repository.food;

import org.springframework.stereotype.Repository;
import ua.mai.zine.jpa.repository.BaseRepository;

@Repository
public interface FoodRepository extends BaseRepository<Food, Integer>, FoodRepositoryCustom {
}
