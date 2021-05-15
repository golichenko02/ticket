package org.hillel.service;

import org.hillel.persistence.entity.AbstractModifyEntity;
import org.hillel.persistence.repository.CommonRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

public abstract class CommonService<E extends AbstractModifyEntity<ID>, ID extends Serializable> implements GenericService<E, ID> {
    private final CommonRepository<E, ID> repository;

    public CommonService(CommonRepository<E, ID> repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<E> findAll(QueryType queryType) {
        switch (queryType) {
            case HQL:
                return findAll();
            case NATIVE:
                return findAllAsNative();
            case NAMED:
                return findAllAsNamed();
            case CRITERIA:
                return findAllAsCriteria();
            case STORED_PROCEDURE:
                return findAllAsStoredProcedure();
            default:
                throw new IllegalArgumentException("unknown queryType");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<E> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<E> findAllAsNative() {
        return repository.findAllAsNative();
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<E> findAllAsNamed() {
        return repository.findAllAsNamed();
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<E> findAllAsCriteria() {
        return repository.findAllAsCriteria();
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<E> findAllAsStoredProcedure() {
        return repository.findAllAsStoredProcedure();
    }

    @Transactional
    @Override
    public E createOrUpdate(E entity) {
        return repository.createOrUpdate(entity);
    }

    @Transactional
    @Override
    public void remove(E entity) {
        repository.remove(entity);
    }

    @Transactional
    @Override
    public void removeById(ID id) {
        repository.removeById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<E> findByIds(ID... ids) {
        return repository.findByIds(ids);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<E> findById(ID id) {
        return repository.findById(id);
    }
}
