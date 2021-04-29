package top.fosin.anan.platform.service.inter;


import top.fosin.anan.jpa.service.ISimpleJpaService;
import top.fosin.anan.cloudresource.dto.request.AnanPermissionCreateDto;
import top.fosin.anan.cloudresource.dto.request.AnanPermissionRetrieveDto;
import top.fosin.anan.cloudresource.dto.request.AnanPermissionUpdateDto;
import top.fosin.anan.platformapi.entity.AnanPermissionEntity;

import java.util.List;

/**
 * 2017/12/29.
 * Time:12:37
 *
 * @author fosin
 */
public interface PermissionService extends ISimpleJpaService<AnanPermissionEntity, Long, AnanPermissionCreateDto, AnanPermissionRetrieveDto, AnanPermissionUpdateDto> {
    List<AnanPermissionEntity> findByPid(Long pid);

    List<AnanPermissionEntity> findByType(Integer type);

    List<AnanPermissionEntity> findByServiceCode(String serviceCode);

    List<AnanPermissionEntity> findByPid(Long pid, Long versionId);
}
