package top.fosin.anan.platform.modules.version;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import top.fosin.anan.cloudresource.dto.res.PermissionRespDto;
import top.fosin.anan.model.controller.ISimpleController;
import top.fosin.anan.model.dto.TreeDto;
import top.fosin.anan.model.result.MultResult;
import top.fosin.anan.model.result.ResultUtils;
import top.fosin.anan.model.result.SingleResult;
import top.fosin.anan.platform.modules.pub.service.inter.PermissionService;
import top.fosin.anan.platform.modules.version.dto.VersionPermissionReqDto;
import top.fosin.anan.platform.modules.version.dto.VersionPermissionRespDto;
import top.fosin.anan.platform.modules.version.dto.VersionReqDto;
import top.fosin.anan.platform.modules.version.dto.VersionRespDto;
import top.fosin.anan.platform.modules.version.service.inter.VersionPermissionService;
import top.fosin.anan.platform.modules.version.service.inter.VersionService;

import java.util.List;

/**
 * 系统版本表(table:anan_version)表控制层
 *
 * @author fosin
 * @date 2018-11-18 17:50:28
 */
@RestController
@RequestMapping("v1/version")
@Api(value = "v1/version", tags = "版本管理")
public class VersionController implements ISimpleController<VersionRespDto,
        Long, VersionReqDto, VersionReqDto, VersionReqDto> {
    /**
     * 服务对象
     */
    private final VersionService versionService;
    private final VersionPermissionService versionPermissionService;
    private final PermissionService permissionService;

    public VersionController(VersionService versionService, VersionPermissionService versionPermissionService, PermissionService permissionService) {
        this.versionService = versionService;
        this.versionPermissionService = versionPermissionService;
        this.permissionService = permissionService;
    }

    @ApiOperation(value = "根据父权限ID获取其孩子数据列表")
    @RequestMapping(value = "/listChild/{pid}", method = {RequestMethod.POST})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "versionId", required = true, dataTypeClass = Long.class, value = "版本ID,取值于AnanVersionEntity.id", paramType = "query"),
            @ApiImplicitParam(name = TreeDto.PID_NAME, required = true, dataTypeClass = Long.class, value = "父权限ID,VersionPermission.id", paramType = "path")
    })
    public MultResult<PermissionRespDto> getListChild(@PathVariable Long pid, @RequestParam Long versionId) {
        List<PermissionRespDto> list = permissionService.findByPidAndVersionId(pid, versionId);
        return ResultUtils.success(list);
    }

    @ApiOperation("根据版本ID获取版本权限")
    @ApiImplicitParam(name = "versionId", required = true, dataTypeClass = Long.class, value = "版本ID,取值于AnanVersionEntity.id", paramType = "path")
    @RequestMapping(value = "/permissions/{versionId}", method = {RequestMethod.GET, RequestMethod.POST})
    public MultResult<VersionPermissionRespDto> permissions(@PathVariable Long versionId) {
        return ResultUtils.success(versionPermissionService.findByVersionId(versionId));
    }

    @ApiOperation(value = "根据版本ID更新版本权限", notes = "根据版本ID更新版本权限，此操作将先删除原权限，再新增新权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dtos", required = true, dataTypeClass = List.class, value = "版本权限集合(List<VersionPermission>)", paramType = "body"),
            @ApiImplicitParam(name = "versionId", required = true, dataTypeClass = Long.class, value = "版本ID,取值于AnanVersionEntity.id", paramType = "path")

    })
    @PutMapping(value = "/permissions/{versionId}")
    public SingleResult<Boolean> permissions(@RequestBody List<VersionPermissionReqDto> dtos,
                                                             @PathVariable("versionId") Long versionId) {
        //更新版本权限
        versionPermissionService.updateInBatch("versionId", versionId, dtos);
        return ResultUtils.success(true);
    }

    @Override
    public VersionService getService() {
        return versionService;
    }
}
