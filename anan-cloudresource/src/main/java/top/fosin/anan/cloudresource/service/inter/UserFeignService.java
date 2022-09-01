package top.fosin.anan.cloudresource.service.inter;

import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import top.fosin.anan.cloudresource.constant.FieldConstant;
import top.fosin.anan.cloudresource.constant.PathPrefixConstant;
import top.fosin.anan.cloudresource.constant.PathSuffixConstant;
import top.fosin.anan.cloudresource.constant.ServiceConstant;
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
@FeignClient(value = ServiceConstant.ANAN_PLATFORMSERVER, path = PathPrefixConstant.USER,
        fallback = UserFeignFallbackServiceImpl.class, contextId = "userFeignService")
public interface UserFeignService {
    @GetMapping({PathConstant.PATH_ID})
    @ApiOperation("根据主键ID查询一条数据")
    SingleResult<UserRespDto> findOneById(@PathVariable(TreeVO.ID_NAME) Long id, @RequestParam(PathPrefixConstant.API_VERSION_NAME) String version);

    @PostMapping({PathConstant.PATH_IDS})
    @ApiOperation("根据id查询多条数据")
    MultResult<UserRespDto> listByIds(@RequestBody List<Long> ids, @RequestParam(PathPrefixConstant.API_VERSION_NAME) String version);

    @GetMapping(PathSuffixConstant.USER_CODE)
    SingleResult<UserRespDto> findOneByUsercode(@PathVariable(FieldConstant.USER_CODE) String usercode, @RequestParam(PathPrefixConstant.API_VERSION_NAME) String version);

    @GetMapping("/list/organizId/{organizId}/{status}")
    MultResult<UserRespDto> listByOrganizId(@PathVariable("organizId") Long organizId,
                                            @PathVariable("status") Integer status,
                                            @RequestParam(PathPrefixConstant.API_VERSION_NAME) String version);

    @GetMapping("/list/topId/{topId}/{status}")
    MultResult<UserRespDto> listAllChildByTopId(@PathVariable("topId") Long topId,
                                                @PathVariable("status") Integer status,
                                                @RequestParam(PathPrefixConstant.API_VERSION_NAME) String version);
}

