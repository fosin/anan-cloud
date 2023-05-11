package top.fosin.anan.platform.modules.organization;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import top.fosin.anan.cloudresource.constant.FieldConstant;
import top.fosin.anan.cloudresource.constant.PathPrefixConstant;
import top.fosin.anan.cloudresource.entity.req.RegisterDTO;
import top.fosin.anan.cloudresource.entity.res.OrganizRespDTO;
import top.fosin.anan.cloudresource.entity.res.OrganizTreeDTO;
import top.fosin.anan.data.controller.BaseController;
import top.fosin.anan.data.controller.ICrudController;
import top.fosin.anan.data.controller.IRetrieveTreeController;
import top.fosin.anan.data.result.MultResult;
import top.fosin.anan.data.result.ResultUtils;
import top.fosin.anan.data.result.SingleResult;
import top.fosin.anan.platform.modules.organization.dto.OrgAuthRespDto;
import top.fosin.anan.platform.modules.organization.dto.OrgPermissionReqDto;
import top.fosin.anan.platform.modules.organization.dto.OrgPermissionRespDto;
import top.fosin.anan.platform.modules.organization.dto.OrgReqDto;
import top.fosin.anan.platform.modules.organization.service.inter.OrgAuthService;
import top.fosin.anan.platform.modules.organization.service.inter.OrgPermissionService;
import top.fosin.anan.platform.modules.organization.service.inter.OrgService;

import java.util.List;

/**
 * @author fosin
 */
@RestController
@RequestMapping(value = PathPrefixConstant.ORGANIZATION, params = PathPrefixConstant.DEFAULT_VERSION_PARAM)
@Api(value = PathPrefixConstant.ORGANIZATION, tags = "机构管理")
public class OrgController extends BaseController
        implements ICrudController<OrgReqDto, OrgReqDto, OrganizRespDTO, OrganizRespDTO, OrganizRespDTO, Long>,
        IRetrieveTreeController<OrgReqDto, OrganizTreeDTO, Long> {
    private final OrgService orgService;
    private final OrgAuthService orgAuthService;
    private final OrgPermissionService orgPermissionService;

    public OrgController(OrgService orgService, OrgAuthService orgAuthService, OrgPermissionService orgPermissionService) {
        this.orgService = orgService;
        this.orgAuthService = orgAuthService;
        this.orgPermissionService = orgPermissionService;
    }

    @ApiOperation("根据机构ID获取机构权限")
    @ApiImplicitParam(name = FieldConstant.ORGANIZ_ID, value = "机构ID,取值于Organization.id",
            required = true, dataTypeClass = Long.class, paramType = "path")
    @GetMapping(value = "/permissions/{organizId}")
    public MultResult<OrgPermissionRespDto> permissions(@PathVariable Long organizId) {
        return ResultUtils.success(orgPermissionService.listByForeingKey(organizId));
    }

    @ApiOperation(value = "根据版本ID更新版本权限", notes = "根据版本ID更新版本权限，此操作将先删除原权限，再新增新权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dtos", value = "版本权限集合",
                    dataTypeClass = List.class, paramType = "body"),
            @ApiImplicitParam(name = FieldConstant.ORGANIZ_ID, value = "机构ID,取值于Organization.id",
                    required = true, dataTypeClass = Long.class, paramType = "path")

    })
    @PutMapping(value = "/permissions/{organizId}")
    public MultResult<OrgPermissionRespDto> permissions(
            @RequestBody List<OrgPermissionReqDto> dtos,
            @PathVariable(FieldConstant.ORGANIZ_ID) Long organizId) {
        return ResultUtils.success(orgPermissionService.processInBatch(organizId, dtos, false));
    }

    @ApiOperation(value = "机构注册", notes = "用户自助注册机构")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "registerDto", required = true, dataTypeClass = RegisterDTO.class, value = "注册新机构、新用户", paramType = "body")
    })
    @PutMapping(value = "/register")
    public SingleResult<Boolean> register(@RequestBody RegisterDTO registerDto) {
        return ResultUtils.success(orgAuthService.register(registerDto));
    }


    @ApiOperation("根据父机构ID获取其孩子节点数据")
    @ApiImplicitParam(name = FieldConstant.ORGANIZ_ID, required = true, dataTypeClass = Long.class, value = "机构ID,取值于Organization.id", paramType = "path")
    @GetMapping("/auth/{organizId}")
    public SingleResult<OrgAuthRespDto> getOrganizAuth(@PathVariable(FieldConstant.ORGANIZ_ID) Long organizId) {
        List<OrgAuthRespDto> authRespDtos = orgAuthService.findAllByOrganizId(organizId);
        Assert.isTrue(authRespDtos.size() > 0, "该机构还未购买服务器!");
        return ResultUtils.success(authRespDtos.get(0));
    }

    @Override
    public OrgService getService() {
        return orgService;
    }
}
