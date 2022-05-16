package top.fosin.anan.platform.modules.version.service.inter;


import top.fosin.anan.jpa.service.ICrudBatchJpaService;
import top.fosin.anan.platform.modules.version.dto.VersionPermissionReqDto;
import top.fosin.anan.platform.modules.version.dto.VersionPermissionRespDto;
import top.fosin.anan.platform.modules.version.entity.VersionPermission;

import java.util.List;

/**
 * 系统版本权限系统版本权限表服务接口
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
public interface VersionPermissionService extends ICrudBatchJpaService<VersionPermission,
        VersionPermissionRespDto,
        Long, Long, VersionPermissionReqDto, VersionPermissionReqDto> {
    List<VersionPermissionRespDto> findByVersionId(Long versionId);

    long countByPermissionId(Long permissionId);

}
