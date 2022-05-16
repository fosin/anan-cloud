package top.fosin.anan.platform.modules.role.service.inter;

import top.fosin.anan.cloudresource.dto.req.RoleReqDto;
import top.fosin.anan.cloudresource.dto.res.RoleRespDto;
import top.fosin.anan.jpa.service.ISimpleJpaService;
import top.fosin.anan.platform.modules.role.entity.Role;

import java.util.List;

/**
 * @author fosin
 * @date 2017/12/29
 *
 */
public interface RoleService extends ISimpleJpaService<Role,
        RoleRespDto,
        Long, RoleReqDto,
        RoleReqDto, RoleReqDto> {

    List<RoleRespDto> findOtherUsersByRoleId(Long userId);

    List<RoleRespDto> findRoleUsersByRoleId(Long userId);

    List<RoleRespDto> findAllByOrganizId(Long organizId);
}
