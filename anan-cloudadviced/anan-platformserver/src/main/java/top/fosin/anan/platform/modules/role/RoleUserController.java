package top.fosin.anan.platform.modules.role;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
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
@Api(value = PathPrefixConstant.ROLE_USER, tags = "角色用户管理")
public class RoleUserController implements
        IUpdateBatchController<RoleUserUpdateDTO, Long>,
        IRetrieveBatchController<UserRoleRespDTO, Long> {
    private final UserService userService;
    private final RoleUserService roleUserService;

    public RoleUserController(UserService userService, RoleUserService roleUserService) {
        this.userService = userService;
        this.roleUserService = roleUserService;
    }

    @ApiOperation("根据用户序号查找用户目前不拥有的所有角色信息")
    @ApiImplicitParam(name = FieldConstant.ROLE_ID, value = "角色ID,取值于Role.id",
            required = true, dataTypeClass = Long.class, paramType = "path")
    @GetMapping(value = "/other" + PathSuffixConstant.ROLE_ID)
    public MultResult<UserDTO> listOtherUsersByRoleId(@PathVariable(FieldConstant.ROLE_ID) Long roleId) throws AnanControllerException {
        return ResultUtils.success(userService.listOtherUsersByRoleId(roleId));
    }

    @Override
    public RoleUserService getBatchService() {
        return roleUserService;
    }
}
