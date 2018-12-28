package com.github.fosin.cdp.platformapi.util;

import com.github.fosin.cdp.core.exception.CdpServiceException;
import com.github.fosin.cdp.util.StringUtil;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.Assert;

import javax.persistence.criteria.*;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * Description:
 *
 * @author fosin
 * @date 2018.12.28
 */
public class JpaUtil {
    public static <E> Specification<E> getSpecification(Serializable entity, boolean allowEmpty) {
        return getSpecification(entity, null, allowEmpty);
    }

    public static <E> Specification<E> getSpecification(Serializable entity) {
        return getSpecification(entity, null, false);
    }

    public static <E> Specification<E> getSpecification(Serializable entity, String dateColName) {
        return getSpecification(entity, dateColName, false);
    }

    public static <E> Specification<E> getSpecification(Serializable entity, String dateColName, boolean allowEmpty) {
        Assert.notNull(entity, "传入的实体参数不能为空");
//        Specification<E> eSpecification = new Specification<>() {
//            @Override
//            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
//                return null;
//            }
//        };
        return (root, query, cb) -> {
            return getPredicate(entity, dateColName, allowEmpty, root, cb);
        };
    }

    private static <E> Predicate getPredicate(Object entity, String dateColName, boolean allowEmpty, Root<E> root, CriteriaBuilder cb) {
        Predicate predicate = null;
        boolean hasValue = false;
        Field[] declaredFields = entity.getClass().getDeclaredFields();
        for (Field f : declaredFields) {
            String fieldName = f.getName();
            f.setAccessible(true);
            Object value;

            if (Modifier.isStatic(f.getModifiers())) {
                continue;
            }

            try {
                value = f.get(entity);
            } catch (IllegalAccessException e) {
                throw new CdpServiceException(e.getMessage());
            }

            if (value != null) {
                Predicate predicate2 = null;
                switch (f.getType().getCanonicalName()) {
//                    case "java.util.List":
//                    case "java.util.Set":
//                    case "java.util.Map":
//                    case "java.util.HashMap":
//                    case "java.util.LinkedHashMap":
//                    case "java.util.Collection":
//                    case "java.lang.Object":
//                    case "java.io.Serializable":
//
                    case "java.util.Date":
                    case "short":
                    case "java.lang.Short":
                    case "integer":
                    case "java.lang.Integer":
                    case "long":
                    case "java.lang.Long":
                    case "double":
                    case "java.lang.Double":
                    case "float":
                    case "java.lang.Float":
                    case "java.lang.String":
                    case "charactor":
                    case "java.lang.Character":
                    case "boolean":
                    case "java.lang.Boolean":
                        if (StringUtil.hasText(dateColName) && ("beginDate".equals(fieldName)) || ("endDate".equals(fieldName)) || ("beginTime".equals(fieldName)) || ("endTime".equals(fieldName))) {
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
                        break;
                    default:
                        throw new CdpServiceException("对象中存在不支持的数据类型！");
                }
                if (predicate == null) {
                    predicate = predicate2;
                } else {
                    predicate = cb.and(predicate, predicate2);
                }
                hasValue = true;
            }

        }
        if (!allowEmpty) {
            Assert.isTrue(hasValue, "至少要传入一个查询条件!");
        }
        return predicate;
    }
}
