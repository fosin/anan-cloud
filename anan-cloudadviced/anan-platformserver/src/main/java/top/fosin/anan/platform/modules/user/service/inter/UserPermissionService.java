package top.fosin.anan.platform.modules.user.service.inter;


import top.fosin.anan.jpa.service.ICrudBatchJpaService;
import top.fosin.anan.platform.modules.user.entity.UserPermission;
import top.fosin.anan.platform.modules.user.dto.UserPermissionReqDto;
import top.fosin.anan.platform.modules.user.dto.UserPermissionRespDto;

import java.util.List;

/**
 * @author fosin
 * @date 2017/12/29
 *
 */
public interface UserPermissionService extends
        ICrudBatchJpaService<UserPermission,
                UserPermissionRespDto,
                Long, Long, UserPermissionReqDto,
                UserPermissionReqDto> {
    List<UserPermissionRespDto> findByUserIdAndOrganizId(Long userId, Long organizId);

    List<UserPermissionRespDto> findByUserId(Long userId);

    long countByPermissionId(Long permissionId);
}
