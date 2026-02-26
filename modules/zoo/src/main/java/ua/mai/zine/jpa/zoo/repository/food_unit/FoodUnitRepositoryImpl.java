package ua.mai.zine.jpa.zoo.repository.food_unit;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class FoodUnitRepositoryImpl implements FoodUnitRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void customLogic(Long id) {
        // любая логика
    }

}
