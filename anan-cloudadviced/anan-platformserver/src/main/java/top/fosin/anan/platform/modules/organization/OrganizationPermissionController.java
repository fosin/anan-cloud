package top.fosin.anan.platform.modules.organization;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fosin.anan.cloudresource.constant.PathPrefixConstant;
import top.fosin.anan.data.controller.batch.IRetrieveBatchController;
import top.fosin.anan.data.controller.batch.IUpdateBatchController;
import top.fosin.anan.platform.modules.organization.dto.OrganizationPermissionUpdateDTO;
import top.fosin.anan.platform.modules.organization.service.inter.OrgPermissionService;
import top.fosin.anan.platform.modules.organization.vo.OrganizationPermissionVO;


/**
 * 系统机构权限表(anan_organization_permission)控制类
 *
 * @author fosin
 * @date 2023-05-13
 */
@RestController
@RequestMapping(value = PathPrefixConstant.ORGANIZATION_PERMISSION, params = PathPrefixConstant.DEFAULT_VERSION_PARAM)
@Tag(name = "机构权限管理", description = PathPrefixConstant.ORGANIZATION_PERMISSION)
public class OrganizationPermissionController implements
        IUpdateBatchController<OrganizationPermissionUpdateDTO, Long>,
        IRetrieveBatchController<OrganizationPermissionVO, Long> {

    private final OrgPermissionService orgPermissionService;

    public OrganizationPermissionController(OrgPermissionService orgPermissionService) {
        this.orgPermissionService = orgPermissionService;
    }

    @Override
    public OrgPermissionService getBatchService() {
        return orgPermissionService;
    }
}
