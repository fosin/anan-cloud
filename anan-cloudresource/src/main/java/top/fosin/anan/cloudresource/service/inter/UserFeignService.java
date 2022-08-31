package top.fosin.anan.cloudresource.service.inter;

import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import top.fosin.anan.cloudresource.constant.ServiceConstant;
import top.fosin.anan.cloudresource.constant.UrlPrefixConstant;
import top.fosin.anan.cloudresource.dto.res.UserRespDto;
import top.fosin.anan.cloudresource.service.UserFeignFallbackServiceImpl;
import top.fosin.anan.data.constant.PathConstant;
import top.fosin.anan.data.entity.res.TreeVO;
import top.fosin.anan.data.result.MultResult;
import top.fosin.anan.data.result.SingleResult;

import java.util.List;

/**
 * @author fosin
 * @date 2017/12/29
 */
@FeignClient(value = ServiceConstant.ANAN_PLATFORMSERVER, path = UrlPrefixConstant.USER,
        fallback = UserFeignFallbackServiceImpl.class, contextId = "userFeignService")
public interface UserFeignService {
    @GetMapping(value = {PathConstant.PATH_ID})
    @ApiOperation("根据主键ID查询一条数据")
    SingleResult<UserRespDto> findOneById(@PathVariable(TreeVO.ID_NAME) Long id, @RequestParam(value = UrlPrefixConstant.API_VERSION_NAME) String version);

    @PostMapping(value = {PathConstant.PATH_IDS})
    @ApiOperation("根据id查询多条数据")
    MultResult<UserRespDto> listByIds(@RequestBody List<Long> ids, @RequestParam(value = UrlPrefixConstant.API_VERSION_NAME) String version);

    @GetMapping(value = "/usercode/{usercode}")
    SingleResult<UserRespDto> findOneByUsercode(@PathVariable("usercode") String usercode, @RequestParam(value = UrlPrefixConstant.API_VERSION_NAME) String version);

    @GetMapping(value = "/list/organizId/{organizId}/{status}")
    MultResult<UserRespDto> listByOrganizId(@PathVariable("organizId") Long organizId,
                                            @PathVariable("status") Integer status,
                                            @RequestParam(value = UrlPrefixConstant.API_VERSION_NAME) String version);

    @GetMapping(value = "/list/topId/{topId}/{status}")
    MultResult<UserRespDto> listAllChildByTopId(@PathVariable("topId") Long topId,
                                                @PathVariable("status") Integer status,
                                                @RequestParam(value = UrlPrefixConstant.API_VERSION_NAME) String version);
}

