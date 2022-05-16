package top.fosin.anan.platform.modules.pub.dao;

import org.springframework.stereotype.Repository;
import top.fosin.anan.jpa.repository.IJpaRepository;
import top.fosin.anan.platform.modules.pub.entity.Service;

import java.util.List;

/**
 * 系统服务表数据库访问层
 *
 * @author fosin
 * @date 2020-12-04 17:48:02
 */
@Repository
public interface ServiceDao extends IJpaRepository<Service, Long> {
    /**
     * 根据状态码查找所有服务数据
     *
     * @param status 状态: 0=有效，1=无效
     * @return 所有数据
     */
    List<Service> findAllByStatus(Integer status);

    /**
     * 根据服务标识查找所有服务数据
     *
     * @param code 服务标识
     * @return 所有数据
     */
    Service findOneByCode(String code);

    /**
     * 根据服务标识查找所有服务数据
     *
     * @param codes 服务标识
     * @return 所有数据
     */
    List<Service> findByCodeIn(List<String> codes);

}
