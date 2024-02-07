package top.fosin.anan.platform.modules.organization;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import top.fosin.anan.cloudresource.constant.FieldConstant;
import top.fosin.anan.cloudresource.constant.PathPrefixConstant;
import top.fosin.anan.cloudresource.entity.req.RegisterDTO;
import top.fosin.anan.cloudresource.entity.res.OrganizTreeDTO;
import top.fosin.anan.data.controller.*;
import top.fosin.anan.data.result.ResultUtils;
import top.fosin.anan.data.result.SingleResult;
import top.fosin.anan.platform.modules.organization.dto.OrganizationAuthDTO;
import top.fosin.anan.platform.modules.organization.dto.OrganizationCreateDTO;
import top.fosin.anan.platform.modules.organization.dto.OrganizationUpdateDTO;
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
@Tag(name = "机构管理", description = PathPrefixConstant.ORGANIZATION)
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

    @Operation(summary = "机构注册", description = "用户自助注册机构")
    @Parameters({
            @Parameter(name = "registerDto", required = true)
    })
    @PutMapping(value = "/register")
    public SingleResult<Boolean> register(@RequestBody RegisterDTO registerDto) {
        return ResultUtils.success(orgAuthService.register(registerDto));
    }


    @Operation(summary = "根据父机构ID获取其孩子节点数据", description = "根据父机构ID获取其孩子节点数据")
    @Parameter(name = FieldConstant.ORGANIZ_ID, required = true)
    @GetMapping("/auth/{organizId}")
    public SingleResult<OrganizationAuthDTO> getOrganizAuth(@PathVariable(FieldConstant.ORGANIZ_ID) Long organizId) {
        List<OrganizationAuthDTO> authRespDtos = orgAuthService.findAllByOrganizId(organizId);
        Assert.isTrue(!authRespDtos.isEmpty(), "该机构还未购买服务器!");
        return ResultUtils.success(authRespDtos.get(0));
    }

    @Override
    public OrgService getService() {
        return orgService;
    }
}
