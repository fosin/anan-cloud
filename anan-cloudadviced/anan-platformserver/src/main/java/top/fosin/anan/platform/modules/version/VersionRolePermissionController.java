package top.fosin.anan.platform.modules.version;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fosin.anan.cloudresource.constant.PathPrefixConstant;
import top.fosin.anan.data.controller.batch.IRetrieveBatchController;
import top.fosin.anan.data.controller.batch.IUpdateBatchController;
import top.fosin.anan.platform.modules.version.dto.VersionRolePermissionUpdateDTO;
import top.fosin.anan.platform.modules.version.service.inter.VersionRolePermissionService;
import top.fosin.anan.platform.modules.version.vo.VersionRolePermissionVO;


/**
 * 系统版本角色权限表(anan_version_role_permission)控制类
 *
 * @author fosin
 * @date 2023-05-13
 */
@RestController
@RequestMapping(value = PathPrefixConstant.VERSION_ROLE_PERMISSION, params = PathPrefixConstant.DEFAULT_VERSION_PARAM)
@Tag(name = "版本角色权限管理", description = PathPrefixConstant.VERSION_ROLE_PERMISSION)
public class VersionRolePermissionController
    implements IUpdateBatchController<VersionRolePermissionUpdateDTO, Long>,
        IRetrieveBatchController<VersionRolePermissionVO, Long> {

    private final VersionRolePermissionService versionRolePermissionService;
    public VersionRolePermissionController (VersionRolePermissionService versionRolePermissionService) {
        this.versionRolePermissionService = versionRolePermissionService;
    }
    
    @Override
    public VersionRolePermissionService getBatchService() {
        return versionRolePermissionService;
    }
}
