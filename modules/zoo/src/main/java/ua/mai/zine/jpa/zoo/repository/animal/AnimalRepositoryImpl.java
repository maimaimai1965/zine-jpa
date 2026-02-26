package ua.mai.zine.jpa.zoo.repository.animal;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class AnimalRepositoryImpl implements AnimalRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void customLogic(Long id) {
        // любая логика
    }

}
