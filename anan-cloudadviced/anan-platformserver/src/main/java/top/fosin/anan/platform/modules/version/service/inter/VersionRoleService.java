package top.fosin.anan.platform.modules.version.service.inter;

import top.fosin.anan.jpa.service.ISimpleJpaService;
import top.fosin.anan.platform.modules.version.dto.VersionRoleReqDto;
import top.fosin.anan.platform.modules.version.dto.VersionRoleRespDto;
import top.fosin.anan.platform.modules.version.entity.VersionRole;

/**
 * 系统版本角色表服务接口
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
public interface VersionRoleService extends ISimpleJpaService<VersionRoleReqDto, VersionRoleRespDto, Long,VersionRole> {
}
