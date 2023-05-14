package top.fosin.anan.platform.modules.user;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fosin.anan.cloudresource.constant.PathPrefixConstant;
import top.fosin.anan.data.controller.IListByEntityController;
import top.fosin.anan.data.controller.batch.IUpdateBatchController;
import top.fosin.anan.platform.modules.user.dto.UserPermissionUpdateDTO;
import top.fosin.anan.platform.modules.user.query.UserPermissionQuery;
import top.fosin.anan.platform.modules.user.service.inter.UserPermissionService;
import top.fosin.anan.platform.modules.user.vo.UserPermissionVO;


/**
 * 用于增减用户的单项权限，通常实在角色的基础上增减单项权限(anan_user_permission)控制类
 *
 * @author fosin
 * @date 2023-05-14
 */
@RestController
@RequestMapping(value = PathPrefixConstant.USER_PERMISSION, params = PathPrefixConstant.DEFAULT_VERSION_PARAM)
@Api(value = PathPrefixConstant.USER_PERMISSION, tags = "用户权限管理")
public class UserPermissionController
        implements IUpdateBatchController<UserPermissionUpdateDTO, Long>,
        IListByEntityController<UserPermissionQuery, UserPermissionVO, Long> {
    private final UserPermissionService userPermissionService;

    public UserPermissionController(UserPermissionService userPermissionService) {
        this.userPermissionService = userPermissionService;
    }

    @Override
    public UserPermissionService getBatchService() {
        return userPermissionService;
    }

    @Override
    public UserPermissionService getService() {
        return userPermissionService;
    }
}
