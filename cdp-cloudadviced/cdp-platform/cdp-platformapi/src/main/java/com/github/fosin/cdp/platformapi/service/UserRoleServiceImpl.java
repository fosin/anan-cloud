package com.github.fosin.cdp.platformapi.service;


import com.github.fosin.cdp.cache.util.CacheUtil;
import com.github.fosin.cdp.core.exception.CdpServiceException;
import com.github.fosin.cdp.core.exception.CdpUserOrPassInvalidException;
import com.github.fosin.cdp.platformapi.constant.TableNameConstant;
import com.github.fosin.cdp.platformapi.entity.CdpSysUserEntity;
import com.github.fosin.cdp.platformapi.entity.CdpSysUserRoleEntity;
import com.github.fosin.cdp.platformapi.repository.UserRoleRepository;
import com.github.fosin.cdp.platformapi.service.inter.IUserRoleService;
import com.github.fosin.cdp.platformapi.service.inter.IUserService;
import com.github.fosin.cdp.platformapi.util.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 2017/12/29.
 * Time:12:31
 *
 * @author fosin
 */
@Service
@Lazy
public class UserRoleServiceImpl implements IUserRoleService {
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private IUserService userService;

    @Override
    public List<CdpSysUserRoleEntity> findByUserId(Long userId) {
        return userRoleRepository.findByUserId(userId);
    }

    @Override
    public List<CdpSysUserRoleEntity> findByRoleId(Long roleId) {
        return userRoleRepository.findByRoleId(roleId);
    }

    @Override
    public List<CdpSysUserRoleEntity> findByUsercodeAndPassword(String usercode, String password) throws CdpUserOrPassInvalidException {
        Assert.isTrue(null != usercode && !"".equals(usercode), "请输入用户名!");
        Assert.isTrue(null != password && !"".equals(password), "传入的用户ID不能为空!");
        CdpSysUserEntity cdpSysUserEntity = userService.findByUsercode(usercode);
        if (null == cdpSysUserEntity || cdpSysUserEntity.getId() < 1) {
            throw new CdpUserOrPassInvalidException();
        }

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if (!bCryptPasswordEncoder.matches(password, cdpSysUserEntity.getPassword())) {
            throw new CdpUserOrPassInvalidException();
        }

        return findByUserId(cdpSysUserEntity.getId());
    }

    @Override
    public List<CdpSysUserRoleEntity> createInBatch(Collection<CdpSysUserRoleEntity> entitis) throws CdpServiceException {
        return userRoleRepository.save(entitis);
    }

    @Override
    public void deleteInBatch(Collection<CdpSysUserRoleEntity> entitis) throws CdpServiceException {
        userRoleRepository.deleteInBatch(entitis);
    }

    @Override
    public Collection<CdpSysUserRoleEntity> updateInBatch(Collection<CdpSysUserRoleEntity> entities) throws CdpServiceException {
        throw new CdpServiceException("该方法还未实现!");
    }

    @Override
    @Transactional
    public List<CdpSysUserRoleEntity> updateInBatchByUserId(Long userId, Iterable<CdpSysUserRoleEntity> entities) {
        Assert.notNull(userId, "传入的用户ID不能为空!");
        Assert.notNull(entities, "传入的实体集合不能为空!");

        for (CdpSysUserRoleEntity entity : entities) {
            Assert.isTrue(entity.getUserId().equals(userId),"需要更新的数据集中有与用户ID不匹配的数据!");
        }

        userRoleRepository.deleteByUserId(userId);
        //如果是用户角色，则只需要删除一个用户的缓存
        CacheUtil.evict(TableNameConstant.CDP_SYS_USER, userId + "");
        CacheUtil.evict(TableNameConstant.CDP_SYS_USER, userService.findOne(userId).getUsercode());

        if (entities.iterator().hasNext()) {
            CdpSysUserEntity loginUser = LoginUserUtil.getUser();
            Date now = new Date();
            for (CdpSysUserRoleEntity entity : entities) {
                entity.setCreateBy(loginUser.getId());
                entity.setCreateTime(now);
                if (entity.getOrganizId() == null) {
                    entity.setOrganizId(loginUser.getOrganizId());
                }
            }
            return userRoleRepository.save(entities);
        }

        return null;
    }

    @Override
    @Transactional
    public List<CdpSysUserRoleEntity> updateInBatchByRoleId(Long roleId, Iterable<CdpSysUserRoleEntity> entities)
            throws CdpServiceException {
        Assert.notNull(roleId, "传入的角色ID不能为空!");
        Assert.notNull(entities, "传入的实体集合不能为空!");
        for (CdpSysUserRoleEntity entity : entities) {
            Assert.isTrue(entity.getRole().getId().equals(roleId),"需要更新的数据集中有与角色ID不匹配的数据!");
        }

        userRoleRepository.deleteByRoleId(roleId);
        //如果是角色用户，则需要删除所有角色相关用户的缓存
        for (CdpSysUserRoleEntity entity : entities) {
            Long userId = entity.getUserId();
            CacheUtil.evict(TableNameConstant.CDP_SYS_USER, userId + "");
            CacheUtil.evict(TableNameConstant.CDP_SYS_USER, userService.findOne(userId).getUsercode());
        }

        if (entities.iterator().hasNext()) {
            CdpSysUserEntity loginUser = LoginUserUtil.getUser();
            Date now = new Date();
            for (CdpSysUserRoleEntity entity : entities) {
                entity.setCreateTime(now);
                entity.setCreateBy(loginUser.getId());
                if (entity.getOrganizId() == null) {
                    entity.setOrganizId(loginUser.getOrganizId());
                }
            }
            return userRoleRepository.save(entities);
        }

        return null;
    }

    public String getCacheKey(Integer type, Iterable<CdpSysUserRoleEntity> entitis) {
        if (type == 1) {
            return getCacheKey(type, entitis.iterator().next().getRole().getId());
        } else {
            return getCacheKey(type, entitis.iterator().next().getUserId());
        }

    }

    public String getCacheKey(Integer type, Long id) {
        if (type == 1) {
            return "RoleId" + id;
        } else {
            return "UserId" + id;
        }
    }
}
