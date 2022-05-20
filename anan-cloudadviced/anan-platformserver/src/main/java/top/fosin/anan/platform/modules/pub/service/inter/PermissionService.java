package top.fosin.anan.platform.modules.pub.service.inter;


import top.fosin.anan.cloudresource.dto.req.PermissionReqDto;
import top.fosin.anan.cloudresource.dto.res.PermissionRespDto;
import top.fosin.anan.cloudresource.dto.res.PermissionRespTreeDto;
import top.fosin.anan.jpa.service.IRetrieveTreeJpaService;
import top.fosin.anan.jpa.service.ISimpleJpaService;
import top.fosin.anan.platform.modules.pub.entity.Permission;

import java.util.List;

/**
 * @author fosin
 * @date 2017/12/29
 *
 */
public interface PermissionService extends ISimpleJpaService<Permission,
        PermissionRespDto,
        Long, PermissionReqDto,
        PermissionReqDto,
        PermissionReqDto>,
        IRetrieveTreeJpaService<Permission,
                PermissionRespTreeDto, Long, PermissionReqDto> {
    List<PermissionRespDto> findByPid(Long pid);

    List<PermissionRespDto> findByServiceCode(String serviceCode);

    List<PermissionRespDto> findByPidAndVersionId(Long pid, Long versionId);

    List<PermissionRespDto> findByServiceCodes(List<String> serviceCodes);
}
