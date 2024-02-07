package top.fosin.anan.cloudresource.service.inter.feign;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import top.fosin.anan.cloudresource.constant.PathPrefixConstant;
import top.fosin.anan.cloudresource.constant.ServiceConstant;
import top.fosin.anan.cloudresource.entity.res.OrganizRespDTO;
import top.fosin.anan.cloudresource.service.OrgFeignFallbackServiceImpl;
import top.fosin.anan.data.constant.PathConstant;
import top.fosin.anan.data.prop.IdProp;
import top.fosin.anan.data.prop.PidProp;
import top.fosin.anan.data.result.MultResult;
import top.fosin.anan.data.result.SingleResult;

import java.util.List;

/**
 * @author fosin
 * @date 2017/12/29
 */
@FeignClient(value = ServiceConstant.ANAN_PLATFORMSERVER, path = PathPrefixConstant.ORGANIZATION,
        fallback = OrgFeignFallbackServiceImpl.class, contextId = "orgFeignService")
public interface OrgFeignService {
    @GetMapping({PathConstant.PATH_ID})
    @Operation(summary = "根据主键序号远程查询一条数据", description = "根据主键序号远程查询一条数据")
    SingleResult<OrganizRespDTO> findOneById(@PathVariable(IdProp.ID_NAME) Long id, @RequestParam(PathPrefixConstant.API_VERSION_NAME) String version);

    @PostMapping({PathConstant.PATH_IDS})
    @Operation(summary = "根据用户序号集合远程查询多条数据", description = "根据用户序号集合远程查询多条数据")
    MultResult<OrganizRespDTO> listByIds(@RequestBody List<Long> ids, @RequestParam(PathPrefixConstant.API_VERSION_NAME) String version);

    @GetMapping(PathConstant.PATH_LIST_CHILD_PID)
    @Operation(summary = "根据父序号pid远程其直接子节点的数据集合", description = "根据父序号pid远程其直接子节点的数据集合")
    @Parameter(name = PidProp.PID_NAME, description = "根据父序号pid远程其直接子节点的数据集合",
            in = ParameterIn.PATH, required = true)
    MultResult<OrganizRespDTO> listChild(@PathVariable(PidProp.PID_NAME) Long pid, @RequestParam(PathPrefixConstant.API_VERSION_NAME) String version);

    @GetMapping(PathConstant.PATH_LIST_ALL_CHILD_PID)
    @Operation(summary = "根据父序号pid远程其所有孩子数据集合", description = "根据父序号pid远程其所有孩子数据集合")
    @Parameter(name = PidProp.PID_NAME, description = "根据父序号pid远程其所有孩子数据集合",
            in = ParameterIn.PATH, required = true)
    MultResult<OrganizRespDTO> listAllChild(@PathVariable(PidProp.PID_NAME) Long pid, @RequestParam(PathPrefixConstant.API_VERSION_NAME) String version);

    @GetMapping(PathConstant.PATH_TREE_ALL_CHILD_PID)
    @Operation(summary = "根据父序号pid远程其所有孩子数据，并构建树型对象", description = "根据父序号pid远程其所有孩子数据，并构建树型对象")
    @Parameter(name = PidProp.PID_NAME, description = "根据父序号pid远程其所有孩子数据，并构建树型对象",
            in = ParameterIn.PATH, required = true)
    MultResult<OrganizRespDTO> treeAllChild(@PathVariable(PidProp.PID_NAME) Long pid, @RequestParam(PathPrefixConstant.API_VERSION_NAME) String version);

}

