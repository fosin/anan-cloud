package top.fosin.anan.platform.modules.user.service.inter;

import org.springframework.validation.annotation.Validated;
import top.fosin.anan.cloudresource.entity.req.UserCreateDTO;
import top.fosin.anan.cloudresource.entity.req.UserUpdateDTO;
import top.fosin.anan.cloudresource.entity.res.UserRespDTO;
import top.fosin.anan.cloudresource.service.inter.base.UserBaseService;
import top.fosin.anan.jpa.service.*;
import top.fosin.anan.platform.modules.user.po.User;
import top.fosin.anan.platform.modules.user.query.UserQuery;
import top.fosin.anan.platform.modules.user.vo.UserPageVO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.List;

/**
 * @author fosin
 * @date 2017/12/27
 */
@Validated
public interface UserService extends IRetrieveJpaService<UserRespDTO, Long, User>,
        ICreateJpaService<UserCreateDTO, UserRespDTO, Long, User>,
        IUpdateJpaService<UserUpdateDTO, Long, User>,
        IDeleteJpaService<Long, User>,
        IPageJpaService<UserQuery, UserPageVO, Long, User>,
        UserBaseService {

    void changePassword(@Positive Long id, @NotBlank String password, @NotBlank String confirmPassword1, @NotBlank String confirmPassword2);

    String resetPassword(@Positive Long id);

    List<UserRespDTO> findOtherUsersByRoleId(@Positive Long roleId);

    List<UserRespDTO> findRoleUsersByRoleId(@Positive Long roleId);
}
