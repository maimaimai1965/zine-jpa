package ua.mai.zine.jpa.zoo.repository.tank;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class TankRepositoryImpl implements TankRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void customLogic(Long id) {
        // любая логика
    }

}
