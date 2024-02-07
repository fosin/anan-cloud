package top.fosin.anan.platform.modules.user;

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
import top.fosin.anan.core.util.BeanUtil;
import top.fosin.anan.data.controller.batch.IRetrieveBatchController;
import top.fosin.anan.data.controller.batch.IUpdateBatchController;
import top.fosin.anan.data.result.MultResult;
import top.fosin.anan.data.result.ResultUtils;
import top.fosin.anan.platform.modules.role.dto.RoleDTO;
import top.fosin.anan.platform.modules.role.service.inter.RoleService;
import top.fosin.anan.platform.modules.role.vo.RoleListVO;
import top.fosin.anan.platform.modules.user.dto.UserRoleUpdateDTO;
import top.fosin.anan.platform.modules.user.service.inter.UserRoleService;

import java.util.List;


/**
 * 系统用户角色表(anan_user_role)控制类
 *
 * @author fosin
 * @date 2023-05-14
 */
@RestController
@RequestMapping(value = PathPrefixConstant.USER_ROLE, params = PathPrefixConstant.DEFAULT_VERSION_PARAM)
@Tag(name = "用户角色管理", description = PathPrefixConstant.USER_ROLE)
public class UserRoleController
        implements IUpdateBatchController<UserRoleUpdateDTO, Long>,
        IRetrieveBatchController<RoleListVO, Long> {
    private final RoleService roleService;
    private final UserRoleService userRoleService;

    public UserRoleController(RoleService roleService, UserRoleService userRoleService) {
        this.roleService = roleService;
        this.userRoleService = userRoleService;
    }

    @Operation(summary = "根据用户序号查找用户所有角色信息", description = "根据用户序号查找用户所有角色信息")
    @Parameter(name = FieldConstant.USER_ID, description = "用户ID,对应Role.id", in = ParameterIn.DEFAULT, required = true)
    @GetMapping(value = "/other" + PathSuffixConstant.USER_ID)
    public MultResult<RoleListVO> getOtherRoles(@PathVariable(FieldConstant.USER_ID) Long userId) {
        List<RoleDTO> roleDTOS = roleService.listOtherUsersByRoleId(userId);
        return ResultUtils.success(BeanUtil.copyProperties(roleDTOS, RoleListVO.class));
    }

    @Override
    public UserRoleService getBatchService() {
        return userRoleService;
    }
}
