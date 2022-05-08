package top.fosin.anan.platform.service.inter;

import top.fosin.anan.jpa.service.ICrudBatchJpaService;
import top.fosin.anan.platform.dto.req.AnanVersionRolePermissionReqDto;
import top.fosin.anan.platform.dto.res.AnanVersionRolePermissionRespDto;
import top.fosin.anan.platform.entity.AnanVersionRolePermissionEntity;

import java.util.List;

/**
 * 系统版本角色权限系统版本角色权限表服务接口
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
public interface VersionRolePermissionService extends ICrudBatchJpaService<AnanVersionRolePermissionEntity,
        AnanVersionRolePermissionRespDto,
        Long, Long, AnanVersionRolePermissionReqDto, AnanVersionRolePermissionReqDto> {
    List<AnanVersionRolePermissionRespDto> findByRoleId(Long roleId);
}
