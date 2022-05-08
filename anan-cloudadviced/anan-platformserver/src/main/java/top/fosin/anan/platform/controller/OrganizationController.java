package top.fosin.anan.platform.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import top.fosin.anan.cloudresource.constant.UrlPrefixConstant;
import top.fosin.anan.cloudresource.dto.req.RegisterDto;
import top.fosin.anan.cloudresource.dto.res.AnanOrganizationRespDto;
import top.fosin.anan.cloudresource.dto.res.AnanOrganizationTreeDto;
import top.fosin.anan.model.controller.BaseController;
import top.fosin.anan.model.controller.IRetrieveTreeController;
import top.fosin.anan.model.controller.ISimpleController;
import top.fosin.anan.platform.dto.req.AnanOrganizationPermissionReqDto;
import top.fosin.anan.platform.dto.req.AnanOrganizationReqDto;
import top.fosin.anan.platform.dto.res.AnanOrganizationAuthRespDto;
import top.fosin.anan.platform.dto.res.AnanOrganizationPermissionRespDto;
import top.fosin.anan.platform.service.inter.OrganizationAuthService;
import top.fosin.anan.platform.service.inter.OrganizationPermissionService;
import top.fosin.anan.platform.service.inter.OrganizationService;

import java.util.Collection;
import java.util.List;

/**
 * @author fosin
 */
@RestController
@RequestMapping(UrlPrefixConstant.ORGANIZATION)
@Api(value = UrlPrefixConstant.ORGANIZATION, tags = "机构管理")
public class OrganizationController extends BaseController
        implements ISimpleController<AnanOrganizationRespDto, Long,
                AnanOrganizationReqDto, AnanOrganizationReqDto,
                AnanOrganizationReqDto>,
        IRetrieveTreeController<AnanOrganizationTreeDto, Long, AnanOrganizationReqDto> {
    private final OrganizationService organizationService;
    private final OrganizationAuthService organizationAuthService;
    private final OrganizationPermissionService organizationPermissionService;

    public OrganizationController(OrganizationService organizationService, OrganizationAuthService organizationAuthService, OrganizationPermissionService organizationPermissionService) {
        this.organizationService = organizationService;
        this.organizationAuthService = organizationAuthService;
        this.organizationPermissionService = organizationPermissionService;
    }

    @ApiOperation("根据机构ID获取机构权限")
    @ApiImplicitParam(name = "organizId", value = "机构ID,取值于AnanOrganizationEntity.id",
            required = true, dataTypeClass = Long.class, paramType = "path")
    @RequestMapping(value = "/permissions/{organizId}", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<List<AnanOrganizationPermissionRespDto>> permissions(@PathVariable Long organizId) {
        return ResponseEntity.ok(organizationPermissionService.findByOrganizId(organizId));
    }

    @ApiOperation(value = "根据版本ID更新版本权限", notes = "根据版本ID更新版本权限，此操作将先删除原权限，再新增新权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dtos", value = "版本权限集合(List<AnanOrganizationPermissionEntity>)",
                    dataTypeClass = List.class, paramType = "body"),
            @ApiImplicitParam(name = "organizId", value = "机构ID,取值于AnanOrganizationEntity.id",
                    required = true, dataTypeClass = Long.class, paramType = "path")

    })
    @PutMapping(value = "/permissions/{organizId}")
    public ResponseEntity<Collection<AnanOrganizationPermissionRespDto>> permissions(@RequestBody List<AnanOrganizationPermissionReqDto> dtos,
                                                                                     @PathVariable("organizId") Long organizId) {
        return ResponseEntity.ok(organizationPermissionService.updateInBatch("organizId", organizId, dtos));
    }

    @ApiOperation(value = "机构注册", notes = "用户自助注册机构")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "registerDto", required = true, dataTypeClass = RegisterDto.class, value = "注册新机构、新用户", paramType = "body")
    })
    @PutMapping(value = "/register")
    public ResponseEntity<Boolean> register(@RequestBody RegisterDto registerDto) {
        return ResponseEntity.ok(organizationAuthService.register(registerDto));
    }


    @ApiOperation("根据父机构ID获取其孩子节点数据")
    @ApiImplicitParam(name = "organizId", required = true, dataTypeClass = Long.class, value = "机构ID,取值于AnanOrganizationEntity.id", paramType = "path")
    @PostMapping("/auth/{organizId}")
    public ResponseEntity<AnanOrganizationAuthRespDto> getOrganizAuth(@PathVariable("organizId") Long organizId) {
        List<AnanOrganizationAuthRespDto> authRespDtos = organizationAuthService.findAllByOrganizId(organizId);
        Assert.isTrue(authRespDtos.size() > 0, "该机构还未购买服务器!");
        return ResponseEntity.ok(authRespDtos.get(0));
    }

    @Override
    public OrganizationService getService() {
        return organizationService;
    }
}
