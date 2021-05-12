package top.fosin.anan.platformapi.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import top.fosin.anan.model.dto.TreeDto;
import top.fosin.anan.platformapi.entity.AnanUserEntity;
import top.fosin.anan.platformapi.service.inter.UserFeignService;

import java.util.List;

/**
 * 远程调用权限服务
 *
 * @author fosin
 * @date 2019-3-26
 */
@Slf4j
@Service
public class UserFeignFallbackServiceImpl implements UserFeignService {

    @Override
    public ResponseEntity<AnanUserEntity> findOne(@PathVariable(TreeDto.ID_NAME) Long id) {
        log.error("feign 远程获取用户信息失败:{}", id);
        return null;
    }

    @Override
    public ResponseEntity<AnanUserEntity> getByUsercode(@PathVariable("usercode") String usercode) {
        log.error("feign 远程获取用户信息失败:{}", usercode);
        return null;
    }

    @Override
    public ResponseEntity<List<AnanUserEntity>> findAllUserByOrganizId(@PathVariable("organizId") Long organizId, @PathVariable("status") Integer status) {
        log.error("feign 远程查询机构及子机构下的用户信息失败:{}", organizId);
        return null;
    }
}
