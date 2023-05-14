package top.fosin.anan.platform.modules.role.service.inter;


import top.fosin.anan.jpa.service.ICrudBatchJpaService;
import top.fosin.anan.platform.modules.role.dto.RoleUserUpdateDTO;
import top.fosin.anan.platform.modules.role.dto.UserRoleRespDTO;
import top.fosin.anan.platform.modules.user.po.UserRole;

/**
 * @author fosin
 * @date 2017/12/29
 */
public interface RoleUserService extends ICrudBatchJpaService<RoleUserUpdateDTO, UserRoleRespDTO, Long, UserRole> {
}
