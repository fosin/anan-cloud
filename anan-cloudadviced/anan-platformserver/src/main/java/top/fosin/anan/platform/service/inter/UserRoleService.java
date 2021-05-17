package top.fosin.anan.platform.service.inter;


import top.fosin.anan.platform.dto.request.AnanUserRoleCreateDto;
import top.fosin.anan.platform.dto.request.AnanUserRoleRetrieveDto;
import top.fosin.anan.cloudresource.dto.res.AnanUserRoleRespDto;
import top.fosin.anan.core.exception.AnanUserOrPassInvalidException;
import top.fosin.anan.jpa.service.batch.ICrudBatchJpaService;
import top.fosin.anan.platform.entity.AnanUserRoleEntity;

import java.util.List;

/**
 * 2017/12/29.
 * Time:12:30
 *
 * @author fosin
 */
public interface UserRoleService extends ICrudBatchJpaService<AnanUserRoleEntity,
        AnanUserRoleRespDto,
        Long, Long, AnanUserRoleCreateDto, AnanUserRoleRetrieveDto> {
    List<AnanUserRoleEntity> findByUserId(Long userId);

    List<AnanUserRoleEntity> findByRoleId(Long roleId);

    List<AnanUserRoleEntity> findByUsercodeAndPassword(String usercode, String password) throws AnanUserOrPassInvalidException;
}
