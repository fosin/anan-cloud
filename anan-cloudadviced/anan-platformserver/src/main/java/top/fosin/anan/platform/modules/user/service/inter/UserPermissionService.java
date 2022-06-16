package top.fosin.anan.platform.modules.user.service.inter;


import top.fosin.anan.jpa.service.ICrudBatchJpaService;
import top.fosin.anan.platform.modules.user.dto.UserPermissionReqDto;
import top.fosin.anan.platform.modules.user.dto.UserPermissionRespDto;
import top.fosin.anan.platform.modules.user.entity.UserPermission;

import java.util.List;

/**
 * @author fosin
 * @date 2017/12/29
 */
public interface UserPermissionService extends
        ICrudBatchJpaService< UserPermissionReqDto,UserPermissionRespDto, Long,UserPermission> {
    List<UserPermissionRespDto> findByUserIdAndOrganizId(Long userId, Long organizId);

}
