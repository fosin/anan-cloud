package top.fosin.anan.platform.modules.user.service.inter;

import top.fosin.anan.jpa.service.ICrudBatchJpaService;
import top.fosin.anan.platform.modules.user.dto.UserRoleDTO;
import top.fosin.anan.platform.modules.user.dto.UserRoleUpdateDTO;
import top.fosin.anan.platform.modules.user.po.UserRole;

/**
 * 系统用户角色表(anan_user_role)服务类
 *
 * @author fosin
 * @date 2023-05-14
 */
public interface UserRoleService extends
        ICrudBatchJpaService<UserRoleUpdateDTO, UserRoleDTO, Long, UserRole> {
}

