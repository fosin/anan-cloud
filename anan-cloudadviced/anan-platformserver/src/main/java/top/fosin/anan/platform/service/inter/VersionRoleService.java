package top.fosin.anan.platform.service.inter;

import top.fosin.anan.jpa.service.ISimpleJpaService;
import top.fosin.anan.platform.dto.req.AnanVersionRoleReqDto;
import top.fosin.anan.platform.dto.res.AnanVersionRoleRespDto;
import top.fosin.anan.platform.entity.AnanVersionRoleEntity;

/**
 * 系统版本角色表服务接口
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
public interface VersionRoleService extends ISimpleJpaService<AnanVersionRoleEntity, AnanVersionRoleRespDto,
        Long, AnanVersionRoleReqDto, AnanVersionRoleReqDto, AnanVersionRoleReqDto> {
}
