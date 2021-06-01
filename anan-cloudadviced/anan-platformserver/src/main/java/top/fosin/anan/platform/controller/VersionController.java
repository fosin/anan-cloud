package top.fosin.anan.platform.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import top.fosin.anan.cloudresource.dto.res.AnanPermissionRespDto;
import top.fosin.anan.model.controller.ISimpleController;
import top.fosin.anan.model.controller.IStatusController;
import top.fosin.anan.model.dto.TreeDto;
import top.fosin.anan.platform.dto.req.*;
import top.fosin.anan.platform.dto.res.AnanOrganizationAuthRespDto;
import top.fosin.anan.platform.dto.res.AnanVersionPermissionRespDto;
import top.fosin.anan.platform.dto.res.AnanVersionRespDto;
import top.fosin.anan.platform.service.inter.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 系统版本表(table:anan_version)表控制层
 *
 * @author fosin
 * @date 2018-11-18 17:50:28
 */
@RestController
@RequestMapping("v1/version")
@Api(value = "v1/version", tags = "系统版本管理")
public class VersionController implements ISimpleController<AnanVersionRespDto,
        Long, AnanVersionCreateDto, AnanVersionRetrieveDto, AnanVersionUpdateDto>,
        IStatusController<AnanVersionRespDto, Long, Integer> {
    /**
     * 服务对象
     */
    private final VersionService versionService;
    private final VersionPermissionService versionPermissionService;
    private final OrganizationPermissionService organizationPermissionService;
    private final OrganizationAuthService organizationAuthService;
    private final PermissionService permissionService;

    public VersionController(VersionService versionService, VersionPermissionService versionPermissionService, OrganizationPermissionService organizationPermissionService, OrganizationAuthService organizationAuthService, PermissionService permissionService) {
        this.versionService = versionService;
        this.versionPermissionService = versionPermissionService;
        this.organizationPermissionService = organizationPermissionService;
        this.organizationAuthService = organizationAuthService;
        this.permissionService = permissionService;
    }

    @ApiOperation(value = "根据父权限ID获取其孩子数据列表")
    @RequestMapping(value = "/listChild/{pid}", method = {RequestMethod.POST})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "versionId", required = true, dataTypeClass = Long.class, value = "版本ID,取值于AnanVersionEntity.id", paramType = "query"),
            @ApiImplicitParam(name = TreeDto.PID_NAME, required = true, dataTypeClass = Long.class, value = "父权限ID,AnanVersionPermissionEntity.id", paramType = "path")
    })
    public ResponseEntity<List<AnanPermissionRespDto>> getListChild(@PathVariable Long pid, @RequestParam Long versionId) {
        List<AnanPermissionRespDto> list = permissionService.findByPidAndVersionId(pid, versionId);
        return ResponseEntity.ok(list);
    }

    @ApiOperation("根据版本ID获取版本权限")
    @ApiImplicitParam(name = "versionId", required = true, dataTypeClass = Long.class, value = "版本ID,取值于AnanVersionEntity.id", paramType = "path")
    @RequestMapping(value = "/permissions/{versionId}", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<List<AnanVersionPermissionRespDto>> permissions(@PathVariable Long versionId) {
        return ResponseEntity.ok(versionPermissionService.findByVersionId(versionId));
    }

    @ApiOperation(value = "根据版本ID更新版本权限", notes = "根据版本ID更新版本权限，此操作将先删除原权限，再新增新权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dtos", required = true, dataTypeClass = List.class, value = "版本权限集合(List<AnanVersionPermissionEntity>)", paramType = "body"),
            @ApiImplicitParam(name = "versionId", required = true, dataTypeClass = Long.class, value = "版本ID,取值于AnanVersionEntity.id", paramType = "path")

    })
    @PutMapping(value = "/permissions/{versionId}")
    public ResponseEntity<Boolean> permissions(@RequestBody List<AnanVersionPermissionCreateDto> dtos,
                                               @PathVariable("versionId") Long versionId) {

        //更新版本权限
        Collection<AnanVersionPermissionRespDto> respDtos = versionPermissionService.updateInBatch("versionId", versionId, dtos);

        //准备版本相关联的机构权限数据
        List<AnanOrganizationPermissionCreateDto> organizationPermissionEntities = new ArrayList<>();
        for (AnanVersionPermissionRespDto dto : respDtos) {
            AnanOrganizationPermissionCreateDto createDto = new AnanOrganizationPermissionCreateDto();
            createDto.setPermissionId(dto.getPermissionId());
            organizationPermissionEntities.add(createDto);
        }

        //查询所有通过该版本生成的机构数据
        List<AnanOrganizationAuthRespDto> organizationAuthRespDtos = organizationAuthService.findAllByVersionId(versionId);

        //更新所有机构权限数据
        for (AnanOrganizationAuthRespDto entity : organizationAuthRespDtos) {
            Long organizId = entity.getOrganizId();
            for (AnanOrganizationPermissionCreateDto entity1 : organizationPermissionEntities) {
                entity1.setOrganizId(organizId);
            }
            organizationPermissionService.updateInBatch("organizId", organizId, organizationPermissionEntities);
        }
        return ResponseEntity.ok(true);
    }

    @Override
    public VersionService getService() {
        return versionService;
    }
}
