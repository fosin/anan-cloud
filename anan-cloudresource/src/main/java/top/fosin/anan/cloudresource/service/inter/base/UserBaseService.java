package top.fosin.anan.cloudresource.service.inter.base;


import org.springframework.validation.annotation.Validated;
import top.fosin.anan.cloudresource.entity.res.UserDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

/**
 * 用户GRPC调用入口服务
 *
 * @author fosin
 * @date 2023/4/8
 * @since 3.0.0
 */
@Validated
public interface UserBaseService {
    UserDTO findOneByUsercode(@NotBlank String usercode);

    List<UserDTO> listByOrganizId(@NotNull Long organizId, @NotNull Byte status);

    List<UserDTO> listAllChildByTopId(@NotNull Long topId, @NotNull Byte status);
}
