package top.fosin.anan.platform.modules.pub.service.inter;


import org.springframework.validation.annotation.Validated;
import top.fosin.anan.cloudresource.entity.req.PermissionReqDTO;
import top.fosin.anan.cloudresource.entity.res.PermissionRespDTO;
import top.fosin.anan.cloudresource.entity.res.PermissionRespTreeDTO;
import top.fosin.anan.cloudresource.service.inter.base.PermissionBaseService;
import top.fosin.anan.jpa.service.IRetrieveTreeJpaService;
import top.fosin.anan.jpa.service.ISimpleJpaService;
import top.fosin.anan.platform.modules.pub.po.Permission;

import java.util.List;

/**
 * @author fosin
 * @date 2017/12/29
 */
@Validated
public interface PermissionService extends ISimpleJpaService<PermissionReqDTO, PermissionRespDTO, Long,Permission>,
        IRetrieveTreeJpaService<PermissionReqDTO, PermissionRespTreeDTO, Long, Permission>, PermissionBaseService {
    List<PermissionRespTreeDTO> findByPidAndVersionId(Long pid, Long versionId);
}
