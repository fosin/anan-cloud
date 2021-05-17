package top.fosin.anan.auth.service.inter;


import top.fosin.anan.auth.entity.AnanUserEntity;
import top.fosin.anan.cloudresource.dto.AnanUserAllPermissionTreeDto;
import top.fosin.anan.cloudresource.dto.res.AnanUserAllPermissionsRespDto;

import java.util.List;

/**
 * 2017/12/29.
 * Time:12:37
 *
 * @author fosin
 */
public interface AuthService {
    List<AnanUserAllPermissionsRespDto> findByUserId(Long userId);

    AnanUserAllPermissionTreeDto findTreeByUserId(Long userId);

    AnanUserEntity findByUsercode(String usercode);
}
