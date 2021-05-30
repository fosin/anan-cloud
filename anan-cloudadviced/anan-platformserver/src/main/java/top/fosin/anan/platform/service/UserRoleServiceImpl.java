package top.fosin.anan.platform.service;


import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import top.fosin.anan.cloudresource.constant.RedisConstant;
import top.fosin.anan.cloudresource.dto.res.AnanUserRespDto;
import top.fosin.anan.cloudresource.dto.res.AnanUserRoleRespDto;
import top.fosin.anan.cloudresource.service.AnanUserDetailService;
import top.fosin.anan.core.exception.AnanUserOrPassInvalidException;
import top.fosin.anan.core.util.BeanUtil;
import top.fosin.anan.platform.dto.req.AnanUserRoleCreateDto;
import top.fosin.anan.platform.entity.AnanRoleEntity;
import top.fosin.anan.platform.entity.AnanUserEntity;
import top.fosin.anan.platform.entity.AnanUserRoleEntity;
import top.fosin.anan.platform.repository.UserRepository;
import top.fosin.anan.platform.repository.UserRoleRepository;
import top.fosin.anan.platform.service.inter.UserRoleService;
import top.fosin.anan.redis.cache.AnanCacheManger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author fosin
 * @date 2017/12/29
 */
@Service
@Lazy
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AnanCacheManger ananCacheManger;
    private final AnanUserDetailService ananUserDetailService;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository,
                               UserRepository userRepository,
                               PasswordEncoder passwordEncoder, AnanCacheManger ananCacheManger,
                               AnanUserDetailService ananUserDetailService) {
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.ananCacheManger = ananCacheManger;
        this.ananUserDetailService = ananUserDetailService;
    }

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
        AnanUserEntity entity = userRepository.findByUsercode(usercode);
        if (null == entity || entity.getId() < 1) {
            throw new AnanUserOrPassInvalidException();
        }

        if (!passwordEncoder.matches(password, entity.getPassword())) {
            throw new AnanUserOrPassInvalidException();
        }

        return findByUserId(entity.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<AnanUserRoleRespDto> updateInBatch(String deleteCol, Long deleteValue, Collection<AnanUserRoleCreateDto> dtos) {
        if ("userId".equals(deleteCol)) {
            Assert.isTrue(dtos.stream().allMatch(entity -> entity.getUserId().equals(deleteValue)), "需要更新的数据集中有与用户ID不匹配的数据!");
            //如果是用户角色，则只需要删除一个用户的缓存
            clearUserCache(deleteValue);
            userRoleRepository.deleteByUserId(deleteValue);
        } else {
            Assert.isTrue(dtos.stream().allMatch(entity -> entity.getRoleId().equals(deleteValue)), "需要更新的数据集中有与角色ID不匹配的数据!");
            //如果是角色用户，则需要删除所有角色相关用户的缓存
            for (AnanUserRoleCreateDto dto : dtos) {
                clearUserCache(dto.getUserId());
            }
            userRoleRepository.deleteByRoleId(deleteValue);
        }

        return getAnanUserRoleEntities(dtos);
    }

    private void clearUserCache(Long userId) {
        AnanUserRespDto respDto = ananCacheManger.get(RedisConstant.ANAN_USER, userId + "", AnanUserRespDto.class);
        if (respDto != null) {
            ananCacheManger.evict(RedisConstant.ANAN_USER, respDto.getUsercode());
        }
        ananCacheManger.evict(RedisConstant.ANAN_USER, userId + "");
        ananCacheManger.evict(RedisConstant.ANAN_USER_ALL_PERMISSIONS, userId + "");
    }

    private List<AnanUserRoleRespDto> getAnanUserRoleEntities(Collection<AnanUserRoleCreateDto> dtos) {
        List<AnanUserRoleEntity> saveEntities = new ArrayList<>();
        if (dtos == null || dtos.size() == 0) {
            return null;
        }
        Long organizId = ananUserDetailService.getAnanOrganizId();
        for (AnanUserRoleCreateDto entity : dtos) {
            AnanUserRoleEntity ananUserRoleEntity = new AnanUserRoleEntity();
            ananUserRoleEntity.setUserId(entity.getUserId());
            AnanRoleEntity ananRoleEntity = new AnanRoleEntity();
            ananRoleEntity.setId(entity.getRoleId());
            ananUserRoleEntity.setRole(ananRoleEntity);
            if (entity.getOrganizId() == null) {
                ananUserRoleEntity.setOrganizId(organizId);
            } else {
                ananUserRoleEntity.setOrganizId(entity.getOrganizId());
            }
            saveEntities.add(ananUserRoleEntity);
        }

        return BeanUtil.copyCollectionProperties(getRepository().saveAll(saveEntities), AnanUserRoleRespDto.class);
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
    public UserRoleRepository getRepository() {
        return userRoleRepository;
    }
}
