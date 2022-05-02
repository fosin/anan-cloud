package top.fosin.anan.platform.service.inter;


import top.fosin.anan.cloudresource.dto.req.AnanPermissionReqDto;
import top.fosin.anan.cloudresource.dto.res.AnanPermissionRespDto;
import top.fosin.anan.cloudresource.dto.res.AnanPermissionRespTreeDto;
import top.fosin.anan.jpa.service.IRetrieveTreeJpaService;
import top.fosin.anan.jpa.service.ISimpleJpaService;
import top.fosin.anan.platform.entity.AnanPermissionEntity;

import java.util.Collection;
import java.util.List;

/**
 * @author fosin
 * @date 2017/12/29
 *
 */
public interface PermissionService extends ISimpleJpaService<AnanPermissionEntity,
        AnanPermissionRespDto,
        Long, AnanPermissionReqDto,
        AnanPermissionReqDto,
        AnanPermissionReqDto>,
        IRetrieveTreeJpaService<AnanPermissionEntity,
                AnanPermissionRespTreeDto, Long, AnanPermissionReqDto> {
    Collection<AnanPermissionRespDto> findByPid(Long pid);

    List<AnanPermissionRespDto> findByServiceCode(String serviceCode);

    List<AnanPermissionRespDto> findByPidAndVersionId(Long pid, Long versionId);

    List<AnanPermissionRespDto> findByServiceCodes(List<String> serviceCodes);
}
