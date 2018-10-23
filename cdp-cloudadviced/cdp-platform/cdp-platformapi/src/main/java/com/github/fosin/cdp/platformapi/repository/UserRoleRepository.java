package com.github.fosin.cdp.platformapi.repository;

import com.github.fosin.cdp.platformapi.entity.CdpSysUserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 2017/12/27.
 * Time:16:09
 *
 * @author fosin
 */
@Repository
public interface UserRoleRepository extends JpaRepository<CdpSysUserRoleEntity, Integer> {
    List<CdpSysUserRoleEntity> findByUserId(Integer userId);
    List<CdpSysUserRoleEntity> findByRoleId(Integer roleId);
    void deleteByUserId(Integer userId);
    void deleteByRoleId(Integer roleId);
}