package top.fosin.anan.platform.modules.role.service.inter;

import org.springframework.validation.annotation.Validated;
import top.fosin.anan.cloudresource.entity.req.RoleReqDTO;
import top.fosin.anan.cloudresource.entity.res.RoleRespDTO;
import top.fosin.anan.jpa.service.ISimpleJpaService;
import top.fosin.anan.platform.modules.role.po.Role;

import javax.validation.constraints.Positive;
import java.util.List;

/**
 * @author fosin
 * @date 2017/12/29
 */
@Validated
public interface RoleService extends ISimpleJpaService<RoleReqDTO, RoleRespDTO, Long, Role> {

    List<RoleRespDTO> findOtherUsersByRoleId(@Positive Long userId);

    List<RoleRespDTO> findRoleUsersByRoleId(@Positive Long userId);

    List<RoleRespDTO> findAllByOrganizId(@Positive Long organizId);
}
