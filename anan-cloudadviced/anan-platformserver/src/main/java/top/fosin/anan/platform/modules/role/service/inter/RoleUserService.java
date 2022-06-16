package top.fosin.anan.platform.modules.role.service.inter;


import top.fosin.anan.cloudresource.dto.res.UserRoleRespDto;
import top.fosin.anan.jpa.service.ICrudBatchJpaService;
import top.fosin.anan.platform.modules.role.dto.RoleUserReqDto;
import top.fosin.anan.platform.modules.user.entity.UserRole;

/**
 * @author fosin
 * @date 2017/12/29
 */
public interface RoleUserService extends ICrudBatchJpaService< RoleUserReqDto, UserRoleRespDto, Long,UserRole> {
}
