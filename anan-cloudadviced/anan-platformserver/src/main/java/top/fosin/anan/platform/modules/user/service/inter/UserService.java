package top.fosin.anan.platform.modules.user.service.inter;

import org.springframework.validation.annotation.Validated;
import top.fosin.anan.cloudresource.entity.req.UserCreateDTO;
import top.fosin.anan.cloudresource.entity.req.UserUpdateDTO;
import top.fosin.anan.cloudresource.entity.res.UserDTO;
import top.fosin.anan.cloudresource.service.inter.base.UserBaseService;
import top.fosin.anan.jpa.service.ICreateJpaService;
import top.fosin.anan.jpa.service.IDeleteJpaService;
import top.fosin.anan.jpa.service.IRetrieveJpaService;
import top.fosin.anan.jpa.service.IUpdateJpaService;
import top.fosin.anan.platform.modules.user.po.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import java.util.List;

/**
 * @author fosin
 * @date 2017/12/27
 */
@Validated
public interface UserService extends
        ICreateJpaService<UserCreateDTO, UserDTO, Long, User>,
        IRetrieveJpaService<UserDTO, Long, User>,
        IUpdateJpaService<UserUpdateDTO, Long, User>,
        IDeleteJpaService<Long, User>,
        UserBaseService {

    void changePassword(@Positive Long id, @NotBlank String password, @NotBlank String confirmPassword1, @NotBlank String confirmPassword2);

    String resetPassword(@Positive Long id);

    List<UserDTO> listOtherUsersByRoleId(@Positive Long roleId);

}
