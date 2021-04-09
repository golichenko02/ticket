package org.hillel.persistence.repository;

import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.persistence.entity.StopEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class StopRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Long create(final StopEntity stopEntity){
        //check
        entityManager.persist(stopEntity);
        return stopEntity.getId();
    }
}
