package top.fosin.anan.platform.service.inter;


import top.fosin.anan.jpa.service.ICrudBatchJpaService;
import top.fosin.anan.platform.dto.req.AnanUserPermissionCreateDto;
import top.fosin.anan.platform.dto.req.AnanUserPermissionRetrieveDto;
import top.fosin.anan.platform.dto.res.AnanUserPermissionRespDto;
import top.fosin.anan.platform.entity.AnanUserPermissionEntity;

import java.util.List;

/**
 * @author fosin
 * @date 2017/12/29
 *
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
