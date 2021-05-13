package top.fosin.anan.platform.service.inter;


import top.fosin.anan.cloudresource.dto.request.AnanUserRoleCreateDto;
import top.fosin.anan.cloudresource.dto.request.AnanUserRoleRetrieveDto;
import top.fosin.anan.core.exception.AnanUserOrPassInvalidException;
import top.fosin.anan.jpa.service.batch.ICrudBatchJpaService;
import top.fosin.anan.platformapi.entity.AnanUserRoleEntity;

import java.util.Collection;
import java.util.List;

/**
 * 2017/12/29.
 * Time:12:30
 *
 * @author fosin
 */
public interface UserRoleService extends ICrudBatchJpaService<AnanUserRoleEntity, Long, Long, AnanUserRoleCreateDto, AnanUserRoleRetrieveDto, AnanUserRoleCreateDto> {
    List<AnanUserRoleEntity> findByUserId(Long userId);

    List<AnanUserRoleEntity> findByRoleId(Long roleId);

    List<AnanUserRoleEntity> findByUsercodeAndPassword(String usercode, String password) throws AnanUserOrPassInvalidException;

    List<AnanUserRoleEntity> updateInBatchByUserId(Long userId, Collection<AnanUserRoleCreateDto> entitis);

    List<AnanUserRoleEntity> updateInBatchByRoleId(Long roleId, Collection<AnanUserRoleCreateDto> entitis);
}
