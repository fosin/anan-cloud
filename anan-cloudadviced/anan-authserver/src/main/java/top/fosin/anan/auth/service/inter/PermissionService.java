package top.fosin.anan.auth.service.inter;


import top.fosin.anan.cloudresource.dto.AnanUserAllPermissionDto;
import top.fosin.anan.cloudresource.dto.request.AnanPermissionRetrieveDto;
import top.fosin.anan.jpa.service.IRetrieveJpaService;
import top.fosin.anan.platformapi.entity.AnanPermissionEntity;
import top.fosin.anan.platformapi.entity.AnanUserAllPermissionsEntity;

import java.util.List;

/**
 * 2017/12/29.
 * Time:12:37
 *
 * @author fosin
 */
public interface PermissionService extends IRetrieveJpaService<AnanPermissionEntity, Long, AnanPermissionEntity> {
    List<AnanPermissionRetrieveDto> findByServiceCode(String serviceCode);

    List<AnanUserAllPermissionsEntity> findByUserId(Long userId);

    AnanUserAllPermissionDto findTreeByUserId(Long userId);
}
