package com.github.fosin.anan.platform.repository;

import com.github.fosin.anan.jpa.repository.IJpaRepository;
import com.github.fosin.anan.platform.entity.AnanVersionRoleEntity;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
/**
 * 系统版本角色表(anan_version_role)表数据库访问层
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Repository
@Lazy
public interface AnanVersionRoleRepository extends IJpaRepository<AnanVersionRoleEntity, Long> {
}
