package top.fosin.anan.auth.service.inter;


import top.fosin.anan.cloudresource.dto.UserAllPermissionTreeDto;
import top.fosin.anan.cloudresource.dto.UserAuthDto;
import top.fosin.anan.cloudresource.dto.res.UserAllPermissionsRespDto;

import java.util.List;

/**
 * @author fosin
 * @date 2017/12/29
 *
 */
public interface AuthService {
    List<UserAllPermissionsRespDto> findByUserId(Long userId);

    UserAllPermissionTreeDto treeByUserId(Long userId);

    UserAuthDto findByUsercode(String usercode);
}
