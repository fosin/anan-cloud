package top.fosin.anan.cloudresource.service.inter.rpc;


import top.fosin.anan.cloudresource.entity.res.UserRespDTO;
import top.fosin.anan.cloudresource.service.inter.base.UserBaseService;

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
public interface UserRpcService extends UserBaseService {
    UserRespDTO findOneById(@NotNull Long id);

    List<UserRespDTO> listByIds(@NotEmpty List<Long> ids);
}
