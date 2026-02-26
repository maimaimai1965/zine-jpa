package ua.mai.zine.jpa.zoo.repository.food;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class FoodRepositoryImpl implements FoodRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void customLogic(Long id) {
        // любая логика
    }

}
