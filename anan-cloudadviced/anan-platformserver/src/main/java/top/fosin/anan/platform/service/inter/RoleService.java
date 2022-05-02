package top.fosin.anan.platform.service.inter;

import top.fosin.anan.cloudresource.dto.req.AnanRoleReqDto;
import top.fosin.anan.cloudresource.dto.res.AnanRoleRespDto;
import top.fosin.anan.jpa.service.ISimpleJpaService;
import top.fosin.anan.platform.entity.AnanRoleEntity;

import java.util.List;

/**
 * @author fosin
 * @date 2017/12/29
 *
 */
public interface RoleService extends ISimpleJpaService<AnanRoleEntity,
        AnanRoleRespDto,
        Long, AnanRoleReqDto,
        AnanRoleReqDto, AnanRoleReqDto> {

    List<AnanRoleRespDto> findOtherUsersByRoleId(Long userId);

    List<AnanRoleRespDto> findRoleUsersByRoleId(Long userId);

    List<AnanRoleRespDto> findAllByOrganizId(Long organizId);
}
