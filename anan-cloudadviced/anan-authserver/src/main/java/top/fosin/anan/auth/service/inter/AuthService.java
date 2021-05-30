package top.fosin.anan.auth.service.inter;


import top.fosin.anan.cloudresource.dto.AnanUserAllPermissionTreeDto;
import top.fosin.anan.cloudresource.dto.AnanUserAuthDto;
import top.fosin.anan.cloudresource.dto.res.AnanUserAllPermissionsRespDto;

import java.util.List;

/**
 * @author fosin
 * @date 2017/12/29
 *
 */
public interface AuthService {
    List<AnanUserAllPermissionsRespDto> findByUserId(Long userId);

    AnanUserAllPermissionTreeDto findTreeByUserId(Long userId);

    AnanUserAuthDto findByUsercode(String usercode);
}
