package top.fosin.anan.cloudresource.service.inter;


import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import top.fosin.anan.cloudresource.constant.ServiceConstant;
import top.fosin.anan.cloudresource.constant.UrlPrefixConstant;
import top.fosin.anan.cloudresource.dto.res.OrgRespDto;
import top.fosin.anan.cloudresource.service.OrgFeignFallbackServiceImpl;
import top.fosin.anan.model.constant.PathConstant;
import top.fosin.anan.model.dto.IdDto;
import top.fosin.anan.model.dto.IdPidDto;
import top.fosin.anan.model.result.MultResult;
import top.fosin.anan.model.result.SingleResult;

import java.util.List;

/**
 * @author fosin
 * @date 2017/12/29
 */
@FeignClient(value = ServiceConstant.ANAN_PLATFORMSERVER, path = UrlPrefixConstant.ORGANIZATION,
        fallback = OrgFeignFallbackServiceImpl.class, contextId = "orgFeignService")
public interface OrgFeignService {
    @PostMapping({PathConstant.PATH_ID})
    @ApiOperation("根据主键ID查询一条数据")
    SingleResult<OrgRespDto> findOneById(@PathVariable(IdDto.ID_NAME) Long id);

    @PostMapping({PathConstant.PATH_IDS})
    @ApiOperation("根据id查询多条数据")
    MultResult<OrgRespDto> findAllByIds(@RequestBody List<Long> ids);

    @PostMapping("/listChild/{pid}")
    MultResult<OrgRespDto> listChild(@PathVariable(IdPidDto.PID_NAME) Long pid);

    @PostMapping("/listAllChild/{pid}")
    MultResult<OrgRespDto> listAllChild(@PathVariable(IdPidDto.PID_NAME) Long pid);

    @PostMapping("/tree/{topId}")
    MultResult<OrgRespDto> tree(@PathVariable("topId") Long topId);

}

