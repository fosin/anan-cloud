package top.fosin.anan.platform.modules.permission.service.inter;


import org.springframework.validation.annotation.Validated;
import top.fosin.anan.cloudresource.entity.res.PermissionDTO;
import top.fosin.anan.cloudresource.entity.res.PermissionTreeDTO;
import top.fosin.anan.cloudresource.service.inter.base.PermissionBaseService;
import top.fosin.anan.jpa.service.*;
import top.fosin.anan.platform.modules.permission.dto.PermissionCreateDTO;
import top.fosin.anan.platform.modules.permission.dto.PermissionUpdateDTO;
import top.fosin.anan.platform.modules.permission.po.Permission;
import top.fosin.anan.platform.modules.permission.query.PermissionQuery;

import java.util.List;

/**
 * @author fosin
 * @date 2017/12/29
 */
@Validated
public interface PermissionService extends ICreateJpaService<PermissionCreateDTO, PermissionDTO, Long, Permission>,
        IRetrieveJpaService<PermissionDTO, Long, Permission>,
        IUpdateJpaService<PermissionUpdateDTO, Long, Permission>,
        IDeleteJpaService<Long, Permission>,
        IRetrieveTreeJpaService<PermissionQuery, PermissionTreeDTO, Long, Permission>, PermissionBaseService {
    List<PermissionTreeDTO> findByPidAndVersionId(Long pid, Long versionId);
}
