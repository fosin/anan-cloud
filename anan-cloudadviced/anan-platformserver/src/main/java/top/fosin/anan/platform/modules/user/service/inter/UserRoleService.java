package top.fosin.anan.platform.modules.user.service.inter;


import top.fosin.anan.cloudresource.dto.res.UserRoleRespDto;
import top.fosin.anan.jpa.service.ICrudBatchJpaService;
import top.fosin.anan.platform.modules.user.dto.UserRoleReqDto;
import top.fosin.anan.platform.modules.user.po.UserRole;

/**
 * @author fosin
 * @date 2017/12/29
 */
public interface UserRoleService extends ICrudBatchJpaService<UserRoleReqDto, UserRoleRespDto, Long, UserRole> {
}
