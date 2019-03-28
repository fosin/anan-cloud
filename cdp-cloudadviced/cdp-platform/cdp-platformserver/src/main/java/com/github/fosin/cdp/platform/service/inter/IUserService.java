package com.github.fosin.cdp.platform.service.inter;

import com.github.fosin.cdp.jpa.service.ISimpleJpaService;
import com.github.fosin.cdp.platformapi.dto.request.CdpUserCreateDto;
import com.github.fosin.cdp.platformapi.dto.request.CdpUserRetrieveDto;
import com.github.fosin.cdp.platformapi.dto.request.CdpUserUpdateDto;
import com.github.fosin.cdp.platformapi.entity.CdpUserEntity;

import java.util.List;

/**
 * 2017/12/27.
 * Time:15:12
 *
 * @author fosin
 */
public interface IUserService extends ISimpleJpaService<CdpUserEntity, Long, CdpUserCreateDto, CdpUserRetrieveDto, CdpUserUpdateDto> {
    CdpUserEntity findByUsercode(String usercode);

    CdpUserEntity changePassword(Long id, String password, String confirmPassword1, String confirmPassword2);

    String resetPassword(Long id);

    List<CdpUserEntity> findOtherUsersByRoleId(Long roleId);

    List<CdpUserEntity> findRoleUsersByRoleId(Long roleId);

    List<CdpUserEntity> findAllByOrganizId(Long organizId);
}
