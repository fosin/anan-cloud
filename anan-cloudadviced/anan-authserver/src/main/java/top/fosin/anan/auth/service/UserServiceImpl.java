package top.fosin.anan.auth.service;

import top.fosin.anan.auth.service.inter.UserService;
import top.fosin.anan.cloudresource.constant.RedisConstant;
import top.fosin.anan.platformapi.entity.AnanUserEntity;
import top.fosin.anan.platformapi.entity.AnanUserRoleEntity;
import top.fosin.anan.platformapi.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

/**
 * 2017/12/27.
 * Time:15:13
 *
 * @author fosin
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Cacheable(value = RedisConstant.ANAN_USER, key = "#usercode")
    @Transactional(readOnly = true)
    public AnanUserEntity findByUsercode(String usercode) {
        Assert.notNull(usercode, "用户工号不能为空!");
        //该代码看似无用，其实是为了解决懒加载和缓存先后问题
        AnanUserEntity userEntity = userRepository.findByUsercode(usercode);
        if (userEntity != null) {
            List<AnanUserRoleEntity> userRoles = userEntity.getUserRoles();
            log.debug(userRoles.toString());
        }
        return userEntity;
    }

}
