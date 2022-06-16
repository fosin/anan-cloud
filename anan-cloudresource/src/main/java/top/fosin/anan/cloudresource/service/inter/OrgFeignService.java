package top.fosin.anan.cloudresource.service.inter;


import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import top.fosin.anan.cloudresource.constant.ServiceConstant;
import top.fosin.anan.cloudresource.constant.UrlPrefixConstant;
import top.fosin.anan.cloudresource.dto.res.OrgRespDto;
import top.fosin.anan.cloudresource.service.OrgFeignFallbackServiceImpl;
import top.fosin.anan.model.constant.PathConstant;
import top.fosin.anan.model.dto.IdDto;
import top.fosin.anan.model.dto.IdPidDto;
import top.fosin.anan.model.prop.PidProp;
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
    @ApiOperation("根据主键序号远程查询一条数据")
    SingleResult<OrgRespDto> findOneById(@PathVariable(IdDto.ID_NAME) Long id);

    @PostMapping({PathConstant.PATH_IDS})
    @ApiOperation("根据用户序号集合远程查询多条数据")
    MultResult<OrgRespDto> findAllByIds(@RequestBody List<Long> ids);

    @PostMapping(PathConstant.PATH_LIST_CHILD_PID)
    @ApiOperation(value = "根据父序号pid远程其直接子节点的数据集合")
    @ApiImplicitParam(name = PidProp.PID_NAME, value = "根据父序号pid远程其直接子节点的数据集合",
            paramType = "path", required = true, dataTypeClass = Long.class)
    MultResult<OrgRespDto> listChild(@PathVariable(IdPidDto.PID_NAME) Long pid);

    @PostMapping(PathConstant.PATH_LIST_ALL_CHILD_PID)
    @ApiOperation(value = "根据父序号pid远程其所有孩子数据集合")
    @ApiImplicitParam(name = PidProp.PID_NAME, value = "根据父序号pid远程其所有孩子数据集合",
            paramType = "path", required = true, dataTypeClass = Long.class)
    MultResult<OrgRespDto> listAllChild(@PathVariable(IdPidDto.PID_NAME) Long pid);

    @GetMapping(PathConstant.PATH_TREE_ALL_CHILD_PID)
    @ApiOperation(value = "根据父序号pid远程其所有孩子数据，并构建树型对象")
    @ApiImplicitParam(name = PidProp.PID_NAME, value = "根据父序号pid远程其所有孩子数据，并构建树型对象",
            paramType = "path", required = true, dataTypeClass = Long.class)
    MultResult<OrgRespDto> treeAllChild(@PathVariable(IdPidDto.PID_NAME) Long pid);

}

