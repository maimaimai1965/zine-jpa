package ua.mai.zine.jpa.zoo.repository.food_order;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class FoodOrderRepositoryImpl implements FoodOrderRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void customLogic(Long id) {
        // любая логика
    }

}
