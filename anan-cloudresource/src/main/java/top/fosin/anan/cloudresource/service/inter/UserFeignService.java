package top.fosin.anan.cloudresource.service.inter;

import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import top.fosin.anan.cloudresource.constant.ServiceConstant;
import top.fosin.anan.cloudresource.constant.UrlPrefixConstant;
import top.fosin.anan.cloudresource.dto.res.UserRespDto;
import top.fosin.anan.cloudresource.service.UserFeignFallbackServiceImpl;
import top.fosin.anan.model.constant.PathConstant;
import top.fosin.anan.model.dto.TreeDto;
import top.fosin.anan.model.result.MultResult;
import top.fosin.anan.model.result.SingleResult;

import java.util.List;

/**
 * @author fosin
 * @date 2017/12/29
 *
 */
@FeignClient(value = ServiceConstant.ANAN_PLATFORMSERVER, path = UrlPrefixConstant.USER,
        fallback = UserFeignFallbackServiceImpl.class,contextId = "userFeignService")
public interface UserFeignService {
    @PostMapping({PathConstant.PATH_ID})
    @ApiOperation("根据主键ID查询一条数据")
    SingleResult<UserRespDto> findOneById(@PathVariable(TreeDto.ID_NAME) Long id);

    @PostMapping({PathConstant.PATH_IDS})
    @ApiOperation("根据id查询多条数据")
    MultResult<UserRespDto> listByIds(@RequestBody List<Long> ids);

    @PostMapping("/usercode/{usercode}")
    SingleResult<UserRespDto> findOneByUsercode(@PathVariable("usercode") String usercode);

    @PostMapping("/list/organizId/{organizId}/{status}")
    MultResult<UserRespDto> listByOrganizId(@PathVariable("organizId") Long organizId, @PathVariable("status") Integer status);

    @PostMapping("/list/topId/{topId}/{status}")
    MultResult<UserRespDto> listByTopId(@PathVariable("topId") Long topId,
                                                  @PathVariable("status") Integer status);
}

