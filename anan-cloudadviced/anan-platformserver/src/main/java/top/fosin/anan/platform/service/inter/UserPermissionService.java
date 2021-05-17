package top.fosin.anan.platform.service.inter;


import top.fosin.anan.platform.dto.request.AnanUserPermissionCreateDto;
import top.fosin.anan.platform.dto.request.AnanUserPermissionRetrieveDto;
import top.fosin.anan.platform.dto.res.AnanUserPermissionRespDto;
import top.fosin.anan.jpa.service.batch.ICrudBatchJpaService;
import top.fosin.anan.platform.entity.AnanUserPermissionEntity;

import java.util.List;

/**
 * 2017/12/29.
 * Time:12:37
 *
 * @author fosin
 */
public interface UserPermissionService extends
        ICrudBatchJpaService<AnanUserPermissionEntity,
                AnanUserPermissionRespDto,
                Long, Long, AnanUserPermissionCreateDto,
                AnanUserPermissionRetrieveDto> {
    List<AnanUserPermissionRespDto> findByUserIdAndOrganizId(Long userId, Long organizId);

    List<AnanUserPermissionRespDto> findByUserId(Long userId);

    long countByPermissionId(Long permissionId);
}
