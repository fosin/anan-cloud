package top.fosin.anan.platformapi.service.inter;


import top.fosin.anan.cloudresource.constant.ServiceConstant;
import top.fosin.anan.cloudresource.constant.SystemConstant;
import top.fosin.anan.cloudresource.constant.UrlPrefixConstant;
import top.fosin.anan.platformapi.entity.AnanUserEntity;
import top.fosin.anan.platformapi.service.UserFeignFallbackServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * 2017/12/29.
 * Time:12:37
 *
 * @author fosin
 */
@FeignClient(value = ServiceConstant.ANAN_PLATFORMSERVER, path = UrlPrefixConstant.USER, fallback = UserFeignFallbackServiceImpl.class)
public interface UserFeignService {
    @PostMapping({"/{id}"})
    @ApiOperation("根据主键ID查询一条数据")
    ResponseEntity<AnanUserEntity> findOne(@PathVariable(SystemConstant.ID_NAME) Long id);

    @PostMapping("/usercode/{usercode}")
    ResponseEntity<AnanUserEntity> getByUsercode(@PathVariable("usercode") String usercode);

    @PostMapping("/childList/organizId/{organizId}/{status}")
    ResponseEntity<List<AnanUserEntity>> findAllUserByOrganizId(@PathVariable("organizId") Long organizId, @PathVariable("status") Integer status);

}

