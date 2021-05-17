package top.fosin.anan.platform.service.inter;

import top.fosin.anan.platform.dto.request.AnanRoleCreateDto;
import top.fosin.anan.platform.dto.request.AnanRoleRetrieveDto;
import top.fosin.anan.platform.dto.request.AnanRoleUpdateDto;
import top.fosin.anan.cloudresource.dto.res.AnanRoleRespDto;
import top.fosin.anan.jpa.service.ISimpleJpaService;
import top.fosin.anan.platform.entity.AnanRoleEntity;

import java.util.List;

/**
 * 2017/12/29.
 * Time:12:30
 *
 * @author fosin
 */
public interface RoleService extends ISimpleJpaService<AnanRoleEntity,
        AnanRoleRespDto,
        Long, AnanRoleCreateDto,
        AnanRoleRetrieveDto, AnanRoleUpdateDto> {

    List<AnanRoleRespDto> findOtherUsersByRoleId(Long userId);

    List<AnanRoleRespDto> findRoleUsersByRoleId(Long userId);

    List<AnanRoleRespDto> findAllByOrganizId(Long organizId);
}
