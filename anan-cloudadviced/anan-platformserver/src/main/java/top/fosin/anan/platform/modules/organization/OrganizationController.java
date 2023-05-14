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
import top.fosin.anan.cloudresource.entity.res.OrganizTreeDTO;
import top.fosin.anan.data.controller.*;
import top.fosin.anan.data.result.ResultUtils;
import top.fosin.anan.data.result.SingleResult;
import top.fosin.anan.platform.modules.organization.dto.*;
import top.fosin.anan.platform.modules.organization.query.OrganizationQuery;
import top.fosin.anan.platform.modules.organization.service.inter.OrgAuthService;
import top.fosin.anan.platform.modules.organization.service.inter.OrgService;
import top.fosin.anan.platform.modules.organization.vo.OrganizationListVO;
import top.fosin.anan.platform.modules.organization.vo.OrganizationVO;

import java.util.List;

/**
 * @author fosin
 */
@RestController
@RequestMapping(value = PathPrefixConstant.ORGANIZATION, params = PathPrefixConstant.DEFAULT_VERSION_PARAM)
@Api(value = PathPrefixConstant.ORGANIZATION, tags = "机构管理")
public class OrganizationController
        implements ICreateController<OrganizationCreateDTO, Long>,
        IFindOneByIdController<OrganizationVO, Long>,
        IListByEntityController<OrganizationQuery, OrganizationListVO, Long>,
        IUpdateController<OrganizationUpdateDTO, Long>,
        IDeleteController<Long>,
        IRetrieveTreeController<OrganizationQuery, OrganizTreeDTO, Long> {
    private final OrgService orgService;
    private final OrgAuthService orgAuthService;

    public OrganizationController(OrgService orgService, OrgAuthService orgAuthService) {
        this.orgService = orgService;
        this.orgAuthService = orgAuthService;
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
    public SingleResult<OrganizationAuthDTO> getOrganizAuth(@PathVariable(FieldConstant.ORGANIZ_ID) Long organizId) {
        List<OrganizationAuthDTO> authRespDtos = orgAuthService.findAllByOrganizId(organizId);
        Assert.isTrue(authRespDtos.size() > 0, "该机构还未购买服务器!");
        return ResultUtils.success(authRespDtos.get(0));
    }

    @Override
    public OrgService getService() {
        return orgService;
    }
}
