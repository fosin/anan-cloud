package top.fosin.anan.platform.modules.role;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fosin.anan.cloudresource.constant.FieldConstant;
import top.fosin.anan.cloudresource.constant.PathPrefixConstant;
import top.fosin.anan.cloudresource.constant.PathSuffixConstant;
import top.fosin.anan.cloudresource.entity.res.UserDTO;
import top.fosin.anan.core.exception.AnanControllerException;
import top.fosin.anan.data.result.MultResult;
import top.fosin.anan.data.result.ResultUtils;
import top.fosin.anan.platform.modules.role.dto.UserRoleRespDTO;
import top.fosin.anan.data.controller.batch.IRetrieveBatchController;
import top.fosin.anan.data.controller.batch.IUpdateBatchController;
import top.fosin.anan.platform.modules.role.dto.RoleUserUpdateDTO;
import top.fosin.anan.platform.modules.role.service.inter.RoleUserService;
import top.fosin.anan.platform.modules.user.service.inter.UserService;


/**
 * 系统角色权限表(anan_role_permission)控制类
 *
 * @author fosin
 * @date 2023-05-13
 */
@RestController
@RequestMapping(value = PathPrefixConstant.ROLE_USER, params = PathPrefixConstant.DEFAULT_VERSION_PARAM)
@Tag(name = "角色用户管理", description = PathPrefixConstant.ROLE_USER)
public class RoleUserController implements
        IUpdateBatchController<RoleUserUpdateDTO, Long>,
        IRetrieveBatchController<UserRoleRespDTO, Long> {
    private final UserService userService;
    private final RoleUserService roleUserService;

    public RoleUserController(UserService userService, RoleUserService roleUserService) {
        this.userService = userService;
        this.roleUserService = roleUserService;
    }

    @Operation(summary = "根据用户序号查找用户目前不拥有的所有角色信息", description = "根据用户序号查找用户目前不拥有的所有角色信息")
    @Parameter(name = FieldConstant.ROLE_ID, description = "角色ID,取值于Role.id", in = ParameterIn.DEFAULT, required = true)
    @GetMapping(value = "/other" + PathSuffixConstant.ROLE_ID)
    public MultResult<UserDTO> listOtherUsersByRoleId(@PathVariable(FieldConstant.ROLE_ID) Long roleId) throws AnanControllerException {
        return ResultUtils.success(userService.listOtherUsersByRoleId(roleId));
    }

    @Override
    public RoleUserService getBatchService() {
        return roleUserService;
    }
}
