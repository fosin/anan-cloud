package top.fosin.anan.cloudresource.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import top.fosin.anan.cloudresource.dto.res.UserRespDto;
import top.fosin.anan.cloudresource.service.inter.UserFeignService;
import top.fosin.anan.model.dto.TreeDto;

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
    public ResponseEntity<UserRespDto> findOneById(@PathVariable(TreeDto.ID_NAME) Long id) {
        log.error("feign 远程获取用户信息失败:{}", id);
        return null;
    }

    @Override
    public ResponseEntity<List<UserRespDto>> listByIds(List<Long> ids) {
        log.error("feign 远程获取用户信息失败:{}", ids);
        return null;
    }

    @Override
    public ResponseEntity<UserRespDto> findOneByUsercode(@PathVariable("usercode") String usercode) {
        log.error("feign 远程获取用户信息失败:{}", usercode);
        return null;
    }

    @Override
    public ResponseEntity<List<UserRespDto>> listByOrganizId(@PathVariable("organizId") Long organizId, @PathVariable("status") Integer status) {
        log.error("feign 远程查询机构及子机构下的用户信息失败:{}", organizId);
        return null;
    }

    @Override
    public ResponseEntity<List<UserRespDto>> listByTopId(Long topId, Integer status) {
        log.error("feign 远程查询顶级机构下的用户信息失败:{}", topId);
        return null;
    }
}
