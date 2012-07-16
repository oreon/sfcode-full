/*
 * (c) Copyright 2005-2012 JAXIO, www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-backend-sd:src/main/java/project/repository/support/ByPatternSpecifications.p.vm.java
 */
package com.company.demo.repository.support;

import static com.google.common.collect.Iterables.toArray;
import static com.google.common.collect.Lists.newArrayList;
import static javax.persistence.metamodel.Attribute.PersistentAttributeType.MANY_TO_ONE;
import static javax.persistence.metamodel.Attribute.PersistentAttributeType.ONE_TO_ONE;
import static org.apache.commons.lang.StringUtils.isNotEmpty;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.SingularAttribute;

import org.springframework.data.jpa.domain.Specification;

public class ByPatternSpecifications {
    /**
     * Lookup entities having at least one String attribute matching the passed pattern.
     */
    public static <T> Specification<T> byPattern(final EntityManager em, final String pattern, final Class<T> type) {
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                List<Predicate> predicates = newArrayList();
                EntityType<T> entity = em.getMetamodel().entity(type);
                for (Attribute<T, ?> attr : entity.getDeclaredSingularAttributes()) {
                    if (attr.getPersistentAttributeType() == MANY_TO_ONE
                            || attr.getPersistentAttributeType() == ONE_TO_ONE) {
                        continue;
                    }
                    if (attr.getJavaType() == String.class && isNotEmpty(pattern)) {
                        System.out.println(attr.getName() + " " + pattern);
                        predicates.add(builder.like(root.get(attribute(entity, attr)), pattern(pattern)));
                    }
                }
                return predicates.isEmpty() ? builder.conjunction() : builder.or(toArray(predicates, Predicate.class));
            }

            private SingularAttribute<T, String> attribute(EntityType<T> entity, Attribute<T, ?> attr) {
                return entity.getDeclaredSingularAttribute(attr.getName(), String.class);
            }
        };
    }

    static private String pattern(String str) {
        return "%" + str + "%";
    }

}