package top.fosin.anan.auth.service.inter;


import top.fosin.anan.cloudresource.entity.UserAllPermissionTreeVO;
import top.fosin.anan.cloudresource.entity.res.UserAuthDto;
import top.fosin.anan.cloudresource.entity.res.UserAllPermissionsRespDTO;

import java.util.List;

/**
 * @author fosin
 * @date 2017/12/29
 *
 */
public interface AuthService {
    List<UserAllPermissionsRespDTO> findByUserId(Long userId);

    UserAllPermissionTreeVO treeByUserId(Long userId);

    UserAuthDto findByUsercode(String usercode);
}
