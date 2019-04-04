package com.github.fosin.cdp.platform.service;


import com.github.fosin.cdp.cache.util.CacheUtil;
import com.github.fosin.cdp.core.exception.CdpUserOrPassInvalidException;
import com.github.fosin.cdp.jpa.repository.IJpaRepository;
import com.github.fosin.cdp.platform.repository.UserRoleRepository;
import com.github.fosin.cdp.platform.service.inter.IUserRoleService;
import com.github.fosin.cdp.platform.service.inter.IUserService;
import com.github.fosin.cdp.platformapi.constant.TableNameConstant;
import com.github.fosin.cdp.platformapi.dto.request.CdpUserRoleCreateDto;
import com.github.fosin.cdp.platformapi.entity.CdpRoleEntity;
import com.github.fosin.cdp.platformapi.entity.CdpUserEntity;
import com.github.fosin.cdp.platformapi.entity.CdpUserRoleEntity;
import com.github.fosin.cdp.platformapi.util.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collection;
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
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<CdpUserRoleEntity> findByUserId(Long userId) {
        return userRoleRepository.findByUserId(userId);
    }

    @Override
    public List<CdpUserRoleEntity> findByRoleId(Long roleId) {
        return userRoleRepository.findByRoleId(roleId);
    }

    @Override
    public List<CdpUserRoleEntity> findByUsercodeAndPassword(String usercode, String password) throws CdpUserOrPassInvalidException {
        Assert.isTrue(null != usercode && !"".equals(usercode), "请输入用户名!");
        Assert.isTrue(null != password && !"".equals(password), "传入的用户ID不能为空!");
        CdpUserEntity cdpSysUserEntity = userService.findByUsercode(usercode);
        if (null == cdpSysUserEntity || cdpSysUserEntity.getId() < 1) {
            throw new CdpUserOrPassInvalidException();
        }

        if (!passwordEncoder.matches(password, cdpSysUserEntity.getPassword())) {
            throw new CdpUserOrPassInvalidException();
        }

        return findByUserId(cdpSysUserEntity.getId());
    }

    @Override
    @Transactional
    public List<CdpUserRoleEntity> updateInBatchByUserId(Long userId, Collection<CdpUserRoleCreateDto> entities) {
        Assert.notNull(userId, "传入的用户ID不能为空!");
        Assert.isTrue(entities != null && entities.size() > 0, "传入的实体集合不能为空!");

        for (CdpUserRoleCreateDto entity : entities) {
            Assert.isTrue(entity.getUserId().equals(userId), "需要更新的数据集中有与用户ID不匹配的数据!");
        }

        userRoleRepository.deleteByUserId(userId);
        //如果是用户角色，则只需要删除一个用户的缓存
        CacheUtil.evict(TableNameConstant.CDP_USER, userId + "");
        CacheUtil.evict(TableNameConstant.CDP_USER, userService.findById(userId).getUsercode());

        return getCdpUserRoleEntities(entities);
    }

    private List<CdpUserRoleEntity> getCdpUserRoleEntities(Collection<CdpUserRoleCreateDto> entities) {
        List<CdpUserRoleEntity> saveEntities = new ArrayList<>();
        CdpUserEntity loginUser = LoginUserUtil.getUser();
        for (CdpUserRoleCreateDto entity : entities) {
            CdpUserRoleEntity cdpUserRoleEntity = new CdpUserRoleEntity();
            cdpUserRoleEntity.setUserId(entity.getUserId());
            CdpRoleEntity cdpRoleEntity = new CdpRoleEntity();
            cdpRoleEntity.setId(entity.getRoleId());
            cdpUserRoleEntity.setRole(cdpRoleEntity);
            if (entity.getOrganizId() == null) {
                cdpUserRoleEntity.setOrganizId(loginUser.getOrganizId());
            } else {
                cdpUserRoleEntity.setOrganizId(entity.getOrganizId());
            }
            saveEntities.add(cdpUserRoleEntity);
        }

        return getRepository().saveAll(saveEntities);
    }

    @Override
    @Transactional
    public List<CdpUserRoleEntity> updateInBatchByRoleId(Long roleId, Collection<CdpUserRoleCreateDto> entities) {
        Assert.notNull(roleId, "传入的角色ID不能为空!");
        Assert.isTrue(entities != null && entities.size() > 0, "传入的实体集合不能为空!");

        for (CdpUserRoleCreateDto entity : entities) {
            Assert.isTrue(entity.getRoleId().equals(roleId), "需要更新的数据集中有与角色ID不匹配的数据!");
        }

        userRoleRepository.deleteByRoleId(roleId);

        //如果是角色用户，则需要删除所有角色相关用户的缓存
        for (CdpUserRoleCreateDto entity : entities) {
            Long userId = entity.getUserId();
            CacheUtil.evict(TableNameConstant.CDP_USER, userId + "");
            CacheUtil.evict(TableNameConstant.CDP_USER, userService.findById(userId).getUsercode());
        }

        return getCdpUserRoleEntities(entities);
    }

    public String getCacheKey(Integer type, Iterable<CdpUserRoleEntity> entitis) {
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

    @Override
    public IJpaRepository<CdpUserRoleEntity, Long> getRepository() {
        return userRoleRepository;
    }
}
