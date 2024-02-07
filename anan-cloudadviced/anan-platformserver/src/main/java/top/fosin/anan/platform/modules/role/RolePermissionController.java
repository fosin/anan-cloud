package top.fosin.anan.platform.modules.role;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fosin.anan.cloudresource.constant.PathPrefixConstant;
import top.fosin.anan.data.controller.batch.IRetrieveBatchController;
import top.fosin.anan.data.controller.batch.IUpdateBatchController;
import top.fosin.anan.platform.modules.role.dto.RolePermissionUpdateDTO;
import top.fosin.anan.platform.modules.role.service.inter.RolePermissionService;
import top.fosin.anan.platform.modules.role.vo.RolePermissionVO;


/**
 * 系统角色权限表(anan_role_permission)控制类
 *
 * @author fosin
 * @date 2023-05-13
 */
@RestController
@RequestMapping(value = PathPrefixConstant.ROLE_PERMISSION, params = PathPrefixConstant.DEFAULT_VERSION_PARAM)
@Tag(name = "角色权限管理", description = PathPrefixConstant.ROLE_PERMISSION)
public class RolePermissionController implements
        IUpdateBatchController<RolePermissionUpdateDTO, Long>,
        IRetrieveBatchController<RolePermissionVO, Long> {

    private final RolePermissionService rolePermissionService;

    public RolePermissionController(RolePermissionService rolePermissionService) {
        this.rolePermissionService = rolePermissionService;
    }

    @Override
    public RolePermissionService getBatchService() {
        return rolePermissionService;
    }
}
