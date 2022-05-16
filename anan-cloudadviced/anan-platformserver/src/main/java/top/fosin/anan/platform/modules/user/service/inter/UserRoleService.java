package top.fosin.anan.platform.modules.user.service.inter;


import top.fosin.anan.cloudresource.dto.res.UserRoleRespDto;
import top.fosin.anan.jpa.service.ICrudBatchJpaService;
import top.fosin.anan.platform.modules.user.entity.UserRole;
import top.fosin.anan.platform.modules.user.dto.UserRoleReqDto;

import java.util.List;

/**
 * @author fosin
 * @date 2017/12/29
 *
 */
public interface UserRoleService extends ICrudBatchJpaService<UserRole,
        UserRoleRespDto,
        Long, Long, UserRoleReqDto, UserRoleReqDto> {
    List<UserRole> findByUserId(Long userId);

    List<UserRole> findByRoleId(Long roleId);

    List<UserRole> findByUsercodeAndPassword(String usercode, String password);
}
