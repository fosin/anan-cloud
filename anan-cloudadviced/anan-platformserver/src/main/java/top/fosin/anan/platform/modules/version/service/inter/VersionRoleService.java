package top.fosin.anan.platform.modules.version.service.inter;

import top.fosin.anan.jpa.service.ICreateJpaService;
import top.fosin.anan.jpa.service.IDeleteJpaService;
import top.fosin.anan.jpa.service.IRetrieveJpaService;
import top.fosin.anan.jpa.service.IUpdateJpaService;
import top.fosin.anan.platform.modules.version.dto.VersionRoleCreateDTO;
import top.fosin.anan.platform.modules.version.dto.VersionRoleDTO;
import top.fosin.anan.platform.modules.version.dto.VersionRoleUpdateDTO;
import top.fosin.anan.platform.modules.version.po.VersionRole;

        /**
 * 系统版本角色表(anan_version_role)服务类
 *
 * @author fosin
 * @date 2023-05-13
 */
public interface VersionRoleService extends 
        ICreateJpaService<VersionRoleCreateDTO, VersionRoleDTO, Long, VersionRole>,
        IRetrieveJpaService<VersionRoleDTO, Long, VersionRole>,
        IUpdateJpaService<VersionRoleUpdateDTO, Long, VersionRole>,
        IDeleteJpaService<Long, VersionRole> {
}