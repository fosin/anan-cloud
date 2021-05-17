package top.fosin.anan.platform.service.inter;


import top.fosin.anan.platform.dto.req.AnanPermissionCreateDto;
import top.fosin.anan.cloudresource.dto.req.AnanPermissionRetrieveDto;
import top.fosin.anan.platform.dto.req.AnanPermissionUpdateDto;
import top.fosin.anan.cloudresource.dto.res.AnanPermissionRespDto;
import top.fosin.anan.cloudresource.dto.res.AnanPermissionRespTreeDto;
import top.fosin.anan.jpa.service.IRetrieveTreeJpaService;
import top.fosin.anan.jpa.service.ISimpleJpaService;
import top.fosin.anan.platform.entity.AnanPermissionEntity;

import java.util.Collection;
import java.util.List;

/**
 * 2017/12/29.
 * Time:12:37
 *
 * @author fosin
 */
public interface PermissionService extends ISimpleJpaService<AnanPermissionEntity,
        AnanPermissionRespDto,
        Long, AnanPermissionCreateDto,
        AnanPermissionRetrieveDto,
        AnanPermissionUpdateDto>,
        IRetrieveTreeJpaService<AnanPermissionEntity,
                AnanPermissionRespTreeDto, Long, AnanPermissionRetrieveDto> {
    Collection<AnanPermissionRespDto> findByPid(Long pid);

    List<AnanPermissionRespDto> findByServiceCode(String serviceCode);

    List<AnanPermissionRespDto> findByPidAndVersionId(Long pid, Long versionId);
}
