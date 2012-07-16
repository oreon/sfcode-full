/*
 * (c) Copyright 2005-2012 JAXIO, www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-backend-sd:src/main/java/project/repository/support/ByExampleSpecifications.p.vm.java
 */
package com.company.demo.repository.support;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Iterables.toArray;
import static com.google.common.collect.Lists.newArrayList;
import static javax.persistence.metamodel.Attribute.PersistentAttributeType.MANY_TO_ONE;
import static javax.persistence.metamodel.Attribute.PersistentAttributeType.ONE_TO_ONE;
import static org.apache.commons.lang.StringUtils.isNotEmpty;

import java.lang.reflect.Method;
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
import org.springframework.util.ReflectionUtils;

import com.google.common.base.Throwables;

/**
 * Helper to create find by example query.
 */
public class ByExampleSpecifications {
    public static <T> Specification<T> byExample(final EntityManager em, final T example) {
        checkNotNull(em, "em must not be null");
        checkNotNull(example, "example must not be null");
        @SuppressWarnings("unchecked")
        final Class<T> type = (Class<T>) example.getClass();

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

                    Object attrValue = getValue(example, attr);
                    if (attrValue != null) {
                        if (attr.getJavaType() == String.class) {
                            if (isNotEmpty((String) attrValue)) {
                                predicates.add(builder.like(root.get(attribute(entity, attr.getName(), String.class)),
                                        pattern((String) attrValue)));
                            }
                        } else {
                            predicates.add(builder.equal(root.get(attribute(entity, attr.getName(), attrValue
                                    .getClass())), attrValue));
                        }
                    }
                }

                return predicates.isEmpty() ? builder.conjunction() : builder.and(toArray(predicates, Predicate.class));
            }

            private Object getValue(T example, Attribute<T, ?> attr) {
                try {
                    return ReflectionUtils.invokeMethod((Method) attr.getJavaMember(), example);
                } catch (Exception e) {
                    throw Throwables.propagate(e);
                }
            }

            private <E> SingularAttribute<T, E> attribute(EntityType<T> entity, String fieldName, Class<E> fieldClass) {
                return entity.getDeclaredSingularAttribute(fieldName, fieldClass);
            }
        };
    }

    /**
     * Lookup entities having at least one String attribute matching the passed pattern.
     */
    public static <T> Specification<T> byPatternOnStringAttributes(final EntityManager em, final String pattern,
            final Class<T> type) {
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