package top.fosin.anan.platform.modules.role.service.inter;

import org.springframework.validation.annotation.Validated;
import top.fosin.anan.cloudresource.dto.req.RoleReqDto;
import top.fosin.anan.cloudresource.dto.res.RoleRespDto;
import top.fosin.anan.jpa.service.ISimpleJpaService;
import top.fosin.anan.platform.modules.role.po.Role;

import javax.validation.constraints.Positive;
import java.util.List;

/**
 * @author fosin
 * @date 2017/12/29
 */
@Validated
public interface RoleService extends ISimpleJpaService<RoleReqDto, RoleRespDto, Long, Role> {

    List<RoleRespDto> findOtherUsersByRoleId(@Positive Long userId);

    List<RoleRespDto> findRoleUsersByRoleId(@Positive Long userId);

    List<RoleRespDto> findAllByOrganizId(@Positive Long organizId);
}
