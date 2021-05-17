package top.fosin.anan.platform.service.inter;

import top.fosin.anan.cloudresource.dto.req.AnanUserRetrieveDto;
import top.fosin.anan.cloudresource.dto.res.AnanUserRespDto;
import top.fosin.anan.jpa.service.ISimpleJpaService;
import top.fosin.anan.platform.dto.request.AnanUserCreateDto;
import top.fosin.anan.platform.dto.request.AnanUserUpdateDto;
import top.fosin.anan.platform.dto.res.AnanUserRespPassDto;
import top.fosin.anan.platform.entity.AnanUserEntity;

import java.util.List;

/**
 * 2017/12/27.
 * Time:15:12
 *
 * @author fosin
 */
public interface UserService extends ISimpleJpaService<AnanUserEntity,
        AnanUserRespDto,
        Long,
        AnanUserCreateDto, AnanUserRetrieveDto, AnanUserUpdateDto> {
    AnanUserRespDto findByUsercode(String usercode);

    AnanUserRespDto changePassword(Long id, String password, String confirmPassword1, String confirmPassword2);

    AnanUserRespPassDto resetPassword(Long id);

    List<AnanUserRespDto> findOtherUsersByRoleId(Long roleId);

    List<AnanUserRespDto> findRoleUsersByRoleId(Long roleId);

    List<AnanUserRespDto> findAllByOrganizId(Long organizId, Integer status);
}
