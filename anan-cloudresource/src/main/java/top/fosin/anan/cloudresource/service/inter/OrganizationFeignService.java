package top.fosin.anan.cloudresource.service.inter;


import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import top.fosin.anan.cloudresource.constant.ServiceConstant;
import top.fosin.anan.cloudresource.constant.UrlPrefixConstant;
import top.fosin.anan.cloudresource.dto.res.OrganizationRespDto;
import top.fosin.anan.cloudresource.service.OrganizationFeignFallbackServiceImpl;
import top.fosin.anan.model.constant.PathConstant;
import top.fosin.anan.model.dto.IdDto;
import top.fosin.anan.model.dto.PidDto;
import top.fosin.anan.model.result.MultResult;
import top.fosin.anan.model.result.SingleResult;

import java.util.List;

/**
 * @author fosin
 * @date 2017/12/29
 *
 */
@FeignClient(value = ServiceConstant.ANAN_PLATFORMSERVER, path = UrlPrefixConstant.ORGANIZATION,
        fallback = OrganizationFeignFallbackServiceImpl.class,contextId = "organizationFeignService")
public interface OrganizationFeignService {
    @PostMapping({PathConstant.PATH_ID})
    @ApiOperation("根据主键ID查询一条数据")
    SingleResult<OrganizationRespDto> findOneById(@PathVariable(IdDto.ID_NAME) Long id);

    @PostMapping({PathConstant.PATH_IDS})
    @ApiOperation("根据id查询多条数据")
    MultResult<OrganizationRespDto> findAllByIds(@RequestBody List<Long> ids);

    @PostMapping("/listChild/{pid}")
    MultResult<OrganizationRespDto> listChild(@PathVariable(PidDto.PID_NAME) Long pid);

    @PostMapping("/listAllChild/{pid}")
    MultResult<OrganizationRespDto> listAllChild(@PathVariable(PidDto.PID_NAME) Long pid);

    @PostMapping("/tree/{topId}")
    MultResult<OrganizationRespDto> tree(@PathVariable("topId") Long topId);

}

