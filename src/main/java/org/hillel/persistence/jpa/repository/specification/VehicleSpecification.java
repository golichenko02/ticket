package org.hillel.persistence.jpa.repository.specification;

import org.hillel.persistence.entity.VehicleEntity;
import org.hillel.persistence.entity.VehicleEntity_;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.convert.QueryByExamplePredicateBuilder;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;

public enum VehicleSpecification implements ISpecification<VehicleEntity> {


    BY_ID {
        @Override
        public Specification<VehicleEntity> getSpecification(String value) {
            return (root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get(VehicleEntity_.ID), criteriaBuilder.literal(value));
        }
    },

    BY_NAME {
        @Override
        public Specification<VehicleEntity> getSpecification(String value) {
            return (root, query, criteriaBuilder)
                    -> criteriaBuilder.equal(root.get(VehicleEntity_.NAME), criteriaBuilder.literal(value));
        }
    },

    BY_NAME_AND_EXAMPLE {
        @Override
        public Specification<VehicleEntity> getSpecification(String value, VehicleEntity vehicleEntity) {
            return (root, query, criteriaBuilder) -> {
                final Predicate byName = criteriaBuilder.equal(root.get(VehicleEntity_.NAME), criteriaBuilder.literal(value));
                final Predicate byExample = QueryByExamplePredicateBuilder.getPredicate(root, criteriaBuilder, Example.of(vehicleEntity));
                return criteriaBuilder.and(byName, byExample);
            };
        }
    },

    BY_ONLY_ACTIVE {
        @Override
        public Specification<VehicleEntity> getSpecification() {
            return (root, query, criteriaBuilder)
                    -> criteriaBuilder.isTrue(root.get(VehicleEntity_.ACTIVE));
        }
    }
}
