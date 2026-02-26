package ua.mai.zine.jpa.zoo.repository.food_order;

import org.springframework.stereotype.Repository;
import ua.mai.zine.jpa.repository.BaseRepository;

@Repository
public interface FoodOrderRepository extends BaseRepository<FoodOrder, Long>, FoodOrderRepositoryCustom {
}
