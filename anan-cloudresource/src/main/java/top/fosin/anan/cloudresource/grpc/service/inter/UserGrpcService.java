package top.fosin.anan.cloudresource.grpc.service.inter;


import top.fosin.anan.cloudresource.grpc.user.UserResp;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 用户GRPC调用入口服务
 *
 * @author fosin
 * @date 2023/4/8
 * @since 3.0.0
 */
public interface UserGrpcService {
    UserResp findOneById(@NotNull Long id);

    UserResp findOneByUsercode(@NotBlank String usercode);

    List<UserResp> listByIds(@NotEmpty List<Long> ids);

    List<UserResp> listByOrganizId(@NotNull Long organizId, @NotNull Integer status);

    List<UserResp> listAllChildByTopId(@NotNull Long topId, @NotNull Integer status);
}
