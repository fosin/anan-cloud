package top.fosin.anan.platform.service.inter;


import top.fosin.anan.cloudresource.dto.request.AnanPermissionCreateDto;
import top.fosin.anan.cloudresource.dto.request.AnanPermissionRetrieveDto;
import top.fosin.anan.cloudresource.dto.request.AnanPermissionUpdateDto;
import top.fosin.anan.cloudresource.dto.res.AnanPermissionRespDto;
import top.fosin.anan.jpa.service.IRetrieveTreeJpaService;
import top.fosin.anan.jpa.service.ISimpleJpaService;
import top.fosin.anan.platformapi.entity.AnanPermissionEntity;

import java.util.Collection;
import java.util.List;

/**
 * 2017/12/29.
 * Time:12:37
 *
 * @author fosin
 */
public interface PermissionService extends ISimpleJpaService<AnanPermissionEntity,
        Long, AnanPermissionCreateDto,
        AnanPermissionRetrieveDto,
        AnanPermissionUpdateDto>,
        IRetrieveTreeJpaService<AnanPermissionEntity,
                AnanPermissionRespDto, Long, AnanPermissionRetrieveDto> {
    default Collection<AnanPermissionEntity> findByPid(Long pid) {
        AnanPermissionRetrieveDto dto = new AnanPermissionRetrieveDto();
        dto.setPid(pid);
        return findAllByEntity(dto);
    }

    List<AnanPermissionEntity> findByServiceCode(String serviceCode);

    List<AnanPermissionEntity> findByPidAndVersionId(Long pid, Long versionId);
}
