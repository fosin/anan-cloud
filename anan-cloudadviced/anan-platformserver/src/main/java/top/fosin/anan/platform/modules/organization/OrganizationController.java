package top.fosin.anan.platform.modules.organization;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import top.fosin.anan.cloudresource.constant.UrlPrefixConstant;
import top.fosin.anan.cloudresource.dto.req.RegisterDto;
import top.fosin.anan.cloudresource.dto.res.OrganizationRespDto;
import top.fosin.anan.cloudresource.dto.res.OrganizationTreeDto;
import top.fosin.anan.model.controller.BaseController;
import top.fosin.anan.model.controller.IRetrieveTreeController;
import top.fosin.anan.model.controller.ISimpleController;
import top.fosin.anan.model.result.MultResult;
import top.fosin.anan.model.result.ResultUtils;
import top.fosin.anan.model.result.SingleResult;
import top.fosin.anan.platform.modules.organization.dto.OrganizationAuthRespDto;
import top.fosin.anan.platform.modules.organization.dto.OrganizationPermissionReqDto;
import top.fosin.anan.platform.modules.organization.dto.OrganizationPermissionRespDto;
import top.fosin.anan.platform.modules.organization.dto.OrganizationReqDto;
import top.fosin.anan.platform.modules.organization.service.inter.OrganizationAuthService;
import top.fosin.anan.platform.modules.organization.service.inter.OrganizationPermissionService;
import top.fosin.anan.platform.modules.organization.service.inter.OrganizationService;

import java.util.List;

/**
 * @author fosin
 */
@RestController
@RequestMapping(UrlPrefixConstant.ORGANIZATION)
@Api(value = UrlPrefixConstant.ORGANIZATION, tags = "机构管理")
public class OrganizationController extends BaseController
        implements ISimpleController<OrganizationRespDto, Long,
        OrganizationReqDto, OrganizationReqDto,
        OrganizationReqDto>,
        IRetrieveTreeController<OrganizationTreeDto, Long, OrganizationReqDto> {
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
    public MultResult<OrganizationPermissionRespDto> permissions(@PathVariable Long organizId) {
        return ResultUtils.success(organizationPermissionService.findByOrganizId(organizId));
    }

    @ApiOperation(value = "根据版本ID更新版本权限", notes = "根据版本ID更新版本权限，此操作将先删除原权限，再新增新权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dtos", value = "版本权限集合",
                    dataTypeClass = List.class, paramType = "body"),
            @ApiImplicitParam(name = "organizId", value = "机构ID,取值于AnanOrganizationEntity.id",
                    required = true, dataTypeClass = Long.class, paramType = "path")

    })
    @PutMapping(value = "/permissions/{organizId}")
    public MultResult<OrganizationPermissionRespDto> permissions(@RequestBody List<OrganizationPermissionReqDto> dtos,
                                                                                 @PathVariable("organizId") Long organizId) {
        return ResultUtils.success(organizationPermissionService.updateInBatch("organizId", organizId, dtos));
    }

    @ApiOperation(value = "机构注册", notes = "用户自助注册机构")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "registerDto", required = true, dataTypeClass = RegisterDto.class, value = "注册新机构、新用户", paramType = "body")
    })
    @PutMapping(value = "/register")
    public SingleResult<Boolean> register(@RequestBody RegisterDto registerDto) {
        return ResultUtils.success(organizationAuthService.register(registerDto));
    }


    @ApiOperation("根据父机构ID获取其孩子节点数据")
    @ApiImplicitParam(name = "organizId", required = true, dataTypeClass = Long.class, value = "机构ID,取值于AnanOrganizationEntity.id", paramType = "path")
    @PostMapping("/auth/{organizId}")
    public SingleResult<OrganizationAuthRespDto> getOrganizAuth(@PathVariable("organizId") Long organizId) {
        List<OrganizationAuthRespDto> authRespDtos = organizationAuthService.findAllByOrganizId(organizId);
        Assert.isTrue(authRespDtos.size() > 0, "该机构还未购买服务器!");
        return ResultUtils.success(authRespDtos.get(0));
    }

    @Override
    public OrganizationService getService() {
        return organizationService;
    }
}
