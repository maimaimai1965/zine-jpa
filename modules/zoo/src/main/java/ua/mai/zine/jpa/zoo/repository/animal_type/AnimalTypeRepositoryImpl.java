package ua.mai.zine.jpa.zoo.repository.animal_type;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class AnimalTypeRepositoryImpl implements AnimalTypeRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void customLogic(Long id) {
        // любая логика
    }

}
