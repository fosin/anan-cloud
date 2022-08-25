package top.fosin.anan.cloudresource.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.fosin.anan.cloudresource.dto.res.UserRespDto;
import top.fosin.anan.cloudresource.service.inter.UserFeignService;
import top.fosin.anan.data.result.MultResult;
import top.fosin.anan.data.result.SingleResult;

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
    public SingleResult<UserRespDto> findOneById(Long id, String version) {
        log.error("feign 远程获取用户信息失败:{}", id);
        return null;
    }

    @Override
    public MultResult<UserRespDto> listByIds(List<Long> ids, String version) {
        log.error("feign 远程获取用户信息失败:{}", ids);
        return null;
    }

    @Override
    public SingleResult<UserRespDto> findOneByUsercode(String usercode, String version) {
        log.error("feign 远程获取用户信息失败:{}", usercode);
        return null;
    }

    @Override
    public MultResult<UserRespDto> listByOrganizId( Long organizId, Integer status, String version) {
        log.error("feign 远程查询机构及子机构下的用户信息失败:{}", organizId);
        return null;
    }

    @Override
    public MultResult<UserRespDto> listAllChildByTopId(Long topId, Integer status, String version) {
        log.error("feign 远程查询顶级机构下的用户信息失败:{}", topId);
        return null;
    }
}
