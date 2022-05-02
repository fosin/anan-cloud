package top.fosin.anan.platform.service.inter;

import top.fosin.anan.cloudresource.dto.req.AnanUserReqDto;
import top.fosin.anan.cloudresource.dto.res.AnanUserRespDto;
import top.fosin.anan.jpa.service.ISimpleJpaService;
import top.fosin.anan.platform.dto.res.AnanUserRespPassDto;
import top.fosin.anan.platform.entity.AnanUserEntity;

import java.util.List;

/**
 * @author fosin
 * @date 2017/12/27
 *
 */
public interface UserService extends ISimpleJpaService<AnanUserEntity,
        AnanUserRespDto,
        Long,
        AnanUserReqDto, AnanUserReqDto, AnanUserReqDto> {
    AnanUserRespDto findByUsercode(String usercode);

    AnanUserRespDto changePassword(Long id, String password, String confirmPassword1, String confirmPassword2);

    AnanUserRespPassDto resetPassword(Long id);

    List<AnanUserRespDto> findOtherUsersByRoleId(Long roleId);

    List<AnanUserRespDto> findRoleUsersByRoleId(Long roleId);

    List<AnanUserRespDto> listByOrganizId(Long organizId, Integer status);

    List<AnanUserRespDto> listAllChildByTopId(Long topId, Integer status);
}
