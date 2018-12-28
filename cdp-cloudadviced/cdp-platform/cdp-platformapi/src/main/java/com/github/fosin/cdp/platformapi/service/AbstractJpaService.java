package com.github.fosin.cdp.platformapi.service;

import com.github.fosin.cdp.platformapi.util.JpaUtil;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;
import java.util.List;

/**
 * Description:
 *
 * @author fosin
 * @date 2018.12.12
 */
public abstract class AbstractJpaService<E> {
    public List<E> findAllByEntityDynamic(Serializable entity) {
        return getRepository().findAll(JpaUtil.getSpecification(entity));
    }

    public List<E> findAllByEntityDynamic(Serializable entity, boolean allowEmpty) {
        return getRepository().findAll(JpaUtil.getSpecification(entity, null, allowEmpty));
    }

    public List<E> findAllByEntityDynamic(Serializable entity, String dateColName) {
        return getRepository().findAll(JpaUtil.getSpecification(entity, dateColName, false));
    }

    public List<E> findAllByEntityDynamic(Serializable entity, String dateColName, boolean allowEmpty) {
        return getRepository().findAll(JpaUtil.getSpecification(entity, dateColName, allowEmpty));
    }

    public E findOneByEntityDynamic(Serializable entity) {
        return getRepository().findOne(JpaUtil.getSpecification(entity, false));
    }

    public long countByEntityDynamic(Serializable entity) {
        return getRepository().count(JpaUtil.getSpecification(entity, false));
    }

    public long countByEntityDynamic(Serializable entity, boolean allowEmpty) {
        return getRepository().count(JpaUtil.getSpecification(entity, allowEmpty));
    }

    protected abstract JpaSpecificationExecutor<E> getRepository();
}
