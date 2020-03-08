package com.github.fosin.anan.platform.service;


import com.github.fosin.anan.cache.util.CacheUtil;
import com.github.fosin.anan.core.exception.AnanUserOrPassInvalidException;
import com.github.fosin.anan.jpa.repository.IJpaRepository;
import com.github.fosin.anan.platform.repository.UserRoleRepository;
import com.github.fosin.anan.platform.service.inter.UserRoleService;
import com.github.fosin.anan.platform.service.inter.UserService;
import com.github.fosin.anan.platformapi.constant.TableNameConstant;
import com.github.fosin.anan.platformapi.entity.AnanRoleEntity;
import com.github.fosin.anan.platformapi.entity.AnanUserEntity;
import com.github.fosin.anan.platformapi.entity.AnanUserRoleEntity;
import com.github.fosin.anan.platformapi.util.LoginUserUtil;
import com.github.fosin.anan.pojo.dto.AnanUserDto;
import com.github.fosin.anan.pojo.dto.request.AnanUserRoleCreateDto;
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
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<AnanUserRoleEntity> findByUserId(Long userId) {
        return userRoleRepository.findByUserId(userId);
    }

    @Override
    public List<AnanUserRoleEntity> findByRoleId(Long roleId) {
        return userRoleRepository.findByRoleId(roleId);
    }

    @Override
    public List<AnanUserRoleEntity> findByUsercodeAndPassword(String usercode, String password) throws AnanUserOrPassInvalidException {
        Assert.isTrue(null != usercode && !"".equals(usercode), "请输入用户名!");
        Assert.isTrue(null != password && !"".equals(password), "传入的用户ID不能为空!");
        AnanUserEntity ananSysUserEntity = userService.findByUsercode(usercode);
        if (null == ananSysUserEntity || ananSysUserEntity.getId() < 1) {
            throw new AnanUserOrPassInvalidException();
        }

        if (!passwordEncoder.matches(password, ananSysUserEntity.getPassword())) {
            throw new AnanUserOrPassInvalidException();
        }

        return findByUserId(ananSysUserEntity.getId());
    }

    @Override
    @Transactional
    public List<AnanUserRoleEntity> updateInBatchByUserId(Long userId, Collection<AnanUserRoleCreateDto> entities) {
        Assert.notNull(userId, "传入的用户ID不能为空!");
        Assert.isTrue(entities != null && entities.size() > 0, "传入的实体集合不能为空!");

        for (AnanUserRoleCreateDto entity : entities) {
            Assert.isTrue(entity.getUserId().equals(userId), "需要更新的数据集中有与用户ID不匹配的数据!");
        }

        userRoleRepository.deleteByUserId(userId);
        //如果是用户角色，则只需要删除一个用户的缓存
        CacheUtil.evict(TableNameConstant.ANAN_USER, userId + "");
        CacheUtil.evict(TableNameConstant.ANAN_USER, userService.findById(userId).getUsercode());

        return getAnanUserRoleEntities(entities);
    }

    private List<AnanUserRoleEntity> getAnanUserRoleEntities(Collection<AnanUserRoleCreateDto> entities) {
        List<AnanUserRoleEntity> saveEntities = new ArrayList<>();
        AnanUserDto loginUser = LoginUserUtil.getUser();
        for (AnanUserRoleCreateDto entity : entities) {
            AnanUserRoleEntity ananUserRoleEntity = new AnanUserRoleEntity();
            ananUserRoleEntity.setUserId(entity.getUserId());
            AnanRoleEntity ananRoleEntity = new AnanRoleEntity();
            ananRoleEntity.setId(entity.getRoleId());
            ananUserRoleEntity.setRole(ananRoleEntity);
            if (entity.getOrganizId() == null) {
                ananUserRoleEntity.setOrganizId(loginUser.getOrganizId());
            } else {
                ananUserRoleEntity.setOrganizId(entity.getOrganizId());
            }
            saveEntities.add(ananUserRoleEntity);
        }

        return getRepository().saveAll(saveEntities);
    }

    @Override
    @Transactional
    public List<AnanUserRoleEntity> updateInBatchByRoleId(Long roleId, Collection<AnanUserRoleCreateDto> entities) {
        Assert.notNull(roleId, "传入的角色ID不能为空!");
        Assert.isTrue(entities != null && entities.size() > 0, "传入的实体集合不能为空!");

        for (AnanUserRoleCreateDto entity : entities) {
            Assert.isTrue(entity.getRoleId().equals(roleId), "需要更新的数据集中有与角色ID不匹配的数据!");
        }

        userRoleRepository.deleteByRoleId(roleId);

        //如果是角色用户，则需要删除所有角色相关用户的缓存
        for (AnanUserRoleCreateDto entity : entities) {
            Long userId = entity.getUserId();
            CacheUtil.evict(TableNameConstant.ANAN_USER, userId + "");
            CacheUtil.evict(TableNameConstant.ANAN_USER, userService.findById(userId).getUsercode());
        }

        return getAnanUserRoleEntities(entities);
    }

    public String getCacheKey(Integer type, Iterable<AnanUserRoleEntity> entitis) {
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
    public IJpaRepository<AnanUserRoleEntity, Long> getRepository() {
        return userRoleRepository;
    }
}
