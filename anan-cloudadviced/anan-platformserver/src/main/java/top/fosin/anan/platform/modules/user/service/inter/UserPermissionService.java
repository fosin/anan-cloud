package top.fosin.anan.platform.modules.user.service.inter;

import top.fosin.anan.jpa.service.ICrudBatchJpaService;
import top.fosin.anan.jpa.service.IRetrieveJpaService;
import top.fosin.anan.platform.modules.user.dto.UserPermissionDTO;
import top.fosin.anan.platform.modules.user.dto.UserPermissionUpdateDTO;
import top.fosin.anan.platform.modules.user.po.UserPermission;

/**
 * 用于增减用户的单项权限，通常实在角色的基础上增减单项权限(anan_user_permission)服务类
 *
 * @author fosin
 * @date 2023-05-14
 */
public interface UserPermissionService extends
        ICrudBatchJpaService<UserPermissionUpdateDTO, UserPermissionDTO, Long, UserPermission>,
        IRetrieveJpaService<UserPermissionDTO, Long, UserPermission> {
}

