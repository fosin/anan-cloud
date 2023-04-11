package top.fosin.anan.platform.modules.user.service.inter;

import org.springframework.validation.annotation.Validated;
import top.fosin.anan.cloudresource.dto.req.UserReqDto;
import top.fosin.anan.cloudresource.dto.res.UserRespDto;
import top.fosin.anan.cloudresource.service.inter.base.UserBaseService;
import top.fosin.anan.jpa.service.ISimpleJpaService;
import top.fosin.anan.platform.modules.user.po.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.List;

/**
 * @author fosin
 * @date 2017/12/27
 *
 */
@Validated
public interface UserService extends ISimpleJpaService<UserReqDto,UserRespDto, Long, User>, UserBaseService {

    void changePassword(@Positive Long id, @NotBlank String password, @NotBlank String confirmPassword1, @NotBlank String confirmPassword2);

    String resetPassword(@Positive Long id);

    List<UserRespDto> findOtherUsersByRoleId(@Positive Long roleId);

    List<UserRespDto> findRoleUsersByRoleId(@Positive Long roleId);
}
