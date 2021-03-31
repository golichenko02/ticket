package org.hillel.persistence.repository;

import org.hillel.persistence.entity.JourneyEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class JourneyRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Long create(final JourneyEntity journeyEntity){
        //check
        entityManager.persist(journeyEntity);
        return journeyEntity.getId();
    }
}
