package top.fosin.anan.platform.service.inter;


import top.fosin.anan.platform.dto.res.AnanVersionPermissionRespDto;
import top.fosin.anan.jpa.service.batch.ICrudBatchJpaService;
import top.fosin.anan.platform.dto.req.AnanVersionPermissionCreateDto;
import top.fosin.anan.platform.dto.req.AnanVersionPermissionRetrieveDto;
import top.fosin.anan.platform.entity.AnanVersionPermissionEntity;

import java.util.List;

/**
 * 系统版本权限系统版本权限表服务接口
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
public interface AnanVersionPermissionService extends ICrudBatchJpaService<AnanVersionPermissionEntity,
        AnanVersionPermissionRespDto,
        Long, Long, AnanVersionPermissionCreateDto, AnanVersionPermissionRetrieveDto> {
    List<AnanVersionPermissionRespDto> findByVersionId(Long versionId);

    long countByPermissionId(Long permissionId);

}
