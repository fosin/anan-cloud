package com.github.fosin.anan.platformapi.repository;

import com.github.fosin.anan.jpa.repository.IJpaRepository;
import com.github.fosin.anan.platformapi.entity.AnanServiceEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 系统服务表(anan_service)表数据库访问层
 *
 * @author fosin
 * @date 2020-12-04 17:48:02
 */
@Repository
public interface AnanServiceRepository extends IJpaRepository<AnanServiceEntity, Integer>, JpaSpecificationExecutor<AnanServiceEntity> {
    /**
     * 根据状态码查找所有服务数据
     *
     * @param status 状态: 0=有效，1=无效
     * @return 所有数据
     */
    List<AnanServiceEntity> findAllByStatus(Integer status);

    /**
     * 根据服务标识查找所有服务数据
     *
     * @param code 服务标识
     * @return 所有数据
     */
    AnanServiceEntity findOneByCode(String code);
}
