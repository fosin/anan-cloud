package top.fosin.anan.platform.service.inter;

import top.fosin.anan.jpa.service.ISimpleJpaService;
import top.fosin.anan.cloudresource.dto.request.AnanUserCreateDto;
import top.fosin.anan.cloudresource.dto.request.AnanUserRetrieveDto;
import top.fosin.anan.cloudresource.dto.request.AnanUserUpdateDto;
import top.fosin.anan.platformapi.entity.AnanUserEntity;

import java.util.List;

/**
 * 2017/12/27.
 * Time:15:12
 *
 * @author fosin
 */
public interface UserService extends ISimpleJpaService<AnanUserEntity, Long, AnanUserCreateDto, AnanUserRetrieveDto, AnanUserUpdateDto> {
    AnanUserEntity findByUsercode(String usercode);

    AnanUserEntity changePassword(Long id, String password, String confirmPassword1, String confirmPassword2);

    AnanUserEntity resetPassword(Long id);

    List<AnanUserEntity> findOtherUsersByRoleId(Long roleId);

    List<AnanUserEntity> findRoleUsersByRoleId(Long roleId);

    List<AnanUserEntity> findAllByOrganizId(Long organizId, Integer status);
}
