package top.fosin.anan.platform.modules.role.service.inter;


import top.fosin.anan.cloudresource.dto.res.RolePermissionRespDto;
import top.fosin.anan.jpa.service.ICrudBatchJpaService;
import top.fosin.anan.platform.modules.role.dto.RolePermissionReqDto;
import top.fosin.anan.platform.modules.role.entity.RolePermission;

/**
 * @author fosin
 * @date 2017/12/29
 */
public interface RolePermissionService extends
        ICrudBatchJpaService<RolePermissionReqDto, RolePermissionRespDto, Long, RolePermission> {
}
