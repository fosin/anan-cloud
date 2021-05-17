package top.fosin.anan.platform.service.inter;

import top.fosin.anan.platform.dto.res.AnanVersionRoleRespDto;
import top.fosin.anan.platform.dto.req.AnanVersionRoleCreateDto;
import top.fosin.anan.platform.dto.req.AnanVersionRoleRetrieveDto;
import top.fosin.anan.platform.dto.req.AnanVersionRoleUpdateDto;
import top.fosin.anan.platform.entity.AnanVersionRoleEntity;
import top.fosin.anan.jpa.service.ISimpleJpaService;

/**
 * 系统版本角色表(anan_version_role)表服务接口
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
public interface AnanVersionRoleService extends ISimpleJpaService<AnanVersionRoleEntity, AnanVersionRoleRespDto,
        Long, AnanVersionRoleCreateDto, AnanVersionRoleRetrieveDto, AnanVersionRoleUpdateDto> {
}
