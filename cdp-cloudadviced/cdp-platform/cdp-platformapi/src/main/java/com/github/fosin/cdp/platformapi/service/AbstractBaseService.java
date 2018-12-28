package com.github.fosin.cdp.platformapi.service;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.util.Assert;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Date;
import java.util.List;

/**
 * Description:
 *
 * @author fosin
 * @date 2018.12.12
 */
public abstract class AbstractBaseService<E> {
    public Specification<E> getSpecification(Serializable entity) {
        Assert.notNull(entity, "传入的实体参数不能为空");
        return (root, query, cb) -> {
            Predicate predicate = null;
            boolean hasValue = false;
            Field[] declaredFields = entity.getClass().getDeclaredFields();
            for (Field f : declaredFields) {
                String fieldName = f.getName();
                f.setAccessible(true);
                Object value = null;

                if (Modifier.isStatic(f.getModifiers())) {
                    continue;
                }
                try {
                    value = f.get(entity);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                if (value != null) {
                    Path<Object> path = root.get(fieldName);
                    if (predicate == null) {
                        predicate = cb.equal(path, value);
                    } else {
                        predicate = cb.and(predicate, cb.equal(path, value));
                    }
                    hasValue = true;
                }
            }
            Assert.isTrue(hasValue, "至少要传入一个查询条件!");
            return predicate;
        };
    }

    public Specification<E> getSpecification(Serializable entity, String dateColName) {
        Assert.notNull(entity, "传入的实体参数不能为空");
        return (root, query, cb) -> {
            Predicate predicate = null;
            boolean hasValue = false;
            Field[] declaredFields = entity.getClass().getDeclaredFields();
            for (Field f : declaredFields) {
                String fieldName = f.getName();
                f.setAccessible(true);
                Object value = null;

                if (Modifier.isStatic(f.getModifiers())) {
                    continue;
                }

                try {
                    value = f.get(entity);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                if (value != null) {
                    Predicate predicate2;
                    if (("beginDate".equals(fieldName)) || ("endDate".equals(fieldName)) || ("beginTime".equals(fieldName)) || ("endTime".equals(fieldName))) {
                        Path<Date> datePath = root.get(dateColName);
                        if ("beginDate".equals(fieldName) || ("beginTime".equals(fieldName))) {
                            predicate2 = cb.greaterThanOrEqualTo(datePath, (Date) value);
                        } else {
                            predicate2 = cb.lessThanOrEqualTo(datePath, (Date) value);
                        }
                    } else {
                        Path<Object> path = root.get(fieldName);
                        predicate2 = cb.equal(path, value);
                    }

                    if (predicate == null) {
                        predicate = predicate2;
                    } else {
                        predicate = cb.and(predicate, predicate2);
                    }
                    hasValue = true;
                }

            }
            Assert.isTrue(hasValue, "至少要传入一个查询条件!");
            return predicate;
        };
    }

    public List<E> findAllByEntityDynamic(Serializable entity) {
        return getRepository().findAll(getSpecification(entity));
    }

    public List<E> findAllByEntityDynamic(Serializable entity, String dateColName) {
        return getRepository().findAll(getSpecification(entity, dateColName));
    }

    protected abstract JpaSpecificationExecutor<E> getRepository();
}
