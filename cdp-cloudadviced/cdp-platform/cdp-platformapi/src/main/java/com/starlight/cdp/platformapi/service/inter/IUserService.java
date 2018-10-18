package com.starlight.cdp.platformapi.service.inter;

import com.starlight.cdp.mvc.service.ISimpleService;
import com.starlight.cdp.platformapi.entity.CdpSysUserEntity;

import java.util.List;

/**
 * 2017/12/27.
 * Time:15:12
 *
 * @author fosin
 */
public interface IUserService extends ISimpleService<CdpSysUserEntity,Integer> {
    CdpSysUserEntity findByUsercode(String usercode);

    CdpSysUserEntity changePassword(Integer id, String password, String confirmPassword1, String confirmPassword2);

    String resetPassword(Integer id);

    List<CdpSysUserEntity> findOtherUsersByRoleId(Integer roleId);

    List<CdpSysUserEntity> findRoleUsersByRoleId(Integer roleId);
}
