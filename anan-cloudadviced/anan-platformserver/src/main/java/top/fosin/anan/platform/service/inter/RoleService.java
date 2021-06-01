package top.fosin.anan.platform.service.inter;

import top.fosin.anan.cloudresource.dto.res.AnanRoleRespDto;
import top.fosin.anan.jpa.service.ISimpleJpaService;
import top.fosin.anan.jpa.service.IStatusJpaService;

import top.fosin.anan.platform.dto.req.AnanRoleCreateDto;
import top.fosin.anan.platform.dto.req.AnanRoleRetrieveDto;
import top.fosin.anan.platform.dto.req.AnanRoleUpdateDto;
import top.fosin.anan.platform.entity.AnanRoleEntity;

import java.util.List;

/**
 * @author fosin
 * @date 2017/12/29
 *
 */
public interface RoleService extends ISimpleJpaService<AnanRoleEntity,
        AnanRoleRespDto,
        Long, AnanRoleCreateDto,
        AnanRoleRetrieveDto, AnanRoleUpdateDto>,
        IStatusJpaService<AnanRoleEntity,AnanRoleRespDto, Long, Integer> {

    List<AnanRoleRespDto> findOtherUsersByRoleId(Long userId);

    List<AnanRoleRespDto> findRoleUsersByRoleId(Long userId);

    List<AnanRoleRespDto> findAllByOrganizId(Long organizId);
}
