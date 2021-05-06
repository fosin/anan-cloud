package top.fosin.anan.platform.service.inter;

import top.fosin.anan.cloudresource.dto.request.AnanRoleCreateDto;
import top.fosin.anan.cloudresource.dto.request.AnanRoleRetrieveDto;
import top.fosin.anan.cloudresource.dto.request.AnanRoleUpdateDto;
import top.fosin.anan.jpa.service.ISimpleJpaService;
import top.fosin.anan.platformapi.entity.AnanRoleEntity;

import java.util.List;

/**
 * 2017/12/29.
 * Time:12:30
 *
 * @author fosin
 */
public interface RoleService extends ISimpleJpaService<AnanRoleEntity, Long, AnanRoleCreateDto, AnanRoleRetrieveDto, AnanRoleUpdateDto> {

    List<AnanRoleEntity> findOtherUsersByRoleId(Long userId);

    List<AnanRoleEntity> findRoleUsersByRoleId(Long userId);

    List<AnanRoleEntity> findAllByOrganizId(Long organizId);
}
