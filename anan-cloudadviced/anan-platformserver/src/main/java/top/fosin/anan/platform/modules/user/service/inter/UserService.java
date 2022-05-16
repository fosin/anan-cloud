package top.fosin.anan.platform.modules.user.service.inter;

import top.fosin.anan.cloudresource.dto.req.UserReqDto;
import top.fosin.anan.cloudresource.dto.res.UserRespDto;
import top.fosin.anan.jpa.service.ISimpleJpaService;
import top.fosin.anan.platform.modules.user.dto.UserPassRespDto;
import top.fosin.anan.platform.modules.user.entity.User;

import java.util.List;

/**
 * @author fosin
 * @date 2017/12/27
 *
 */
public interface UserService extends ISimpleJpaService<User,
        UserRespDto,
        Long,
        UserReqDto, UserReqDto, UserReqDto> {
    UserRespDto findByUsercode(String usercode);

    UserRespDto changePassword(Long id, String password, String confirmPassword1, String confirmPassword2);

    UserPassRespDto resetPassword(Long id);

    List<UserRespDto> findOtherUsersByRoleId(Long roleId);

    List<UserRespDto> findRoleUsersByRoleId(Long roleId);

    List<UserRespDto> listByOrganizId(Long organizId, Integer status);

    List<UserRespDto> listAllChildByTopId(Long topId, Integer status);
}
