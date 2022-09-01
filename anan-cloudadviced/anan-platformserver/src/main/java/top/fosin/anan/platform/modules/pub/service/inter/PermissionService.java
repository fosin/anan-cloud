package top.fosin.anan.platform.modules.pub.service.inter;


import org.springframework.validation.annotation.Validated;
import top.fosin.anan.cloudresource.dto.req.PermissionReqDto;
import top.fosin.anan.cloudresource.dto.res.PermissionRespDto;
import top.fosin.anan.cloudresource.dto.res.PermissionRespTreeDto;
import top.fosin.anan.jpa.service.IRetrieveTreeJpaService;
import top.fosin.anan.jpa.service.ISimpleJpaService;
import top.fosin.anan.platform.modules.pub.po.Permission;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author fosin
 * @date 2017/12/29
 */
@Validated
public interface PermissionService extends ISimpleJpaService<PermissionReqDto, PermissionRespDto, Long,Permission>,
        IRetrieveTreeJpaService<PermissionReqDto, PermissionRespTreeDto, Long, Permission> {
    List<PermissionRespDto> findByServiceCode(@NotBlank String serviceCode);

    List<PermissionRespTreeDto> findByPidAndVersionId(Long pid, Long versionId);

    List<PermissionRespDto> findByServiceCodes(@NotEmpty List<String> serviceCodes);
}
