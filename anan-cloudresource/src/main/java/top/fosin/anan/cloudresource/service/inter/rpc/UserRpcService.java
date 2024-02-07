package top.fosin.anan.cloudresource.service.inter.rpc;


import top.fosin.anan.cloudresource.entity.res.UserDTO;
import top.fosin.anan.cloudresource.service.inter.base.UserBaseService;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

/**
 * 用户GRPC调用入口服务
 *
 * @author fosin
 * @date 2023/4/8
 * @since 3.0.0
 */
public interface UserRpcService extends UserBaseService {
    UserDTO findOneById(@NotNull Long id);

    List<UserDTO> listByIds(@NotEmpty List<Long> ids);
}
