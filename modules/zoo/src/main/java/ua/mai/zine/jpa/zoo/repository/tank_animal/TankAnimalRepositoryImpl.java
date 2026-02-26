package ua.mai.zine.jpa.zoo.repository.tank_animal;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class TankAnimalRepositoryImpl implements TankAnimalRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void customLogic(Long id) {
        // любая логика
    }

}
