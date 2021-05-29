package org.hillel.service;

import org.hillel.persistence.entity.VehicleEntity;
import org.hillel.persistence.repository.CommonRepository;
import org.hillel.persistence.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Collection;

@Service(value = "transactionalVehicleService")
public class TransactionalVehicleService extends CommonService<VehicleEntity, Long> {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    PlatformTransactionManager platformTransactionManager;

//    @PersistenceContext
//    EntityManagerFactory entityManagerFactory;

    public TransactionalVehicleService(CommonRepository<VehicleEntity, Long> repository) {
        super(repository);
    }


    @Transactional
    @Override
    public VehicleEntity createOrUpdate(VehicleEntity entity) {
        return vehicleRepository.createOrUpdate(entity);
//        // 1st way to manage transaction
//        return transactionTemplate.execute(transactionStatus -> vehicleRepository.createOrUpdate(entity));

        // 2nd way to manage transaction
//        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
//        final TransactionStatus transaction = platformTransactionManager.getTransaction(transactionDefinition);
//        vehicleRepository.createOrUpdate(entity);
//        platformTransactionManager.commit(transaction);
//        // if exception then
//        platformTransactionManager.rollback(transaction);
//        return entity;

        // one more way to manage transaction
//        one more way to manage transaction
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        final EntityTransaction transaction = entityManager.getTransaction();
//        transaction.begin();
//        entityManager.persist(entity);
//        transaction.commit();
//        // if exception then
//        transaction.rollback();
//        return entity;
    }

    @Override
    public Collection<VehicleEntity> findAllByName(String name) {
        return vehicleRepository.findAllByName(name);
    }


    @Transactional(readOnly = true)
    @Override
    public Collection<VehicleEntity> findWithMaxFreeSeats(){
        return vehicleRepository.findVehicleWithMaxFreeSeats();
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<VehicleEntity> findWithMinFreeSeats(){
        return vehicleRepository.findVehicleWithMinFreeSeats();
    }
}
