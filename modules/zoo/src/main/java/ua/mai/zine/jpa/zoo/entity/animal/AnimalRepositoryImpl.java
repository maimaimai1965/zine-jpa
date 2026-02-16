package ua.mai.zine.jpa.zoo.entity.animal;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import ua.mai.zine.jpa.zoo.entity.animal_type.AnimalTypeRepositoryCustom;

public class AnimalRepositoryImpl implements AnimalRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void customLogic(Long id) {
        // любая логика
    }

}
