package top.fosin.anan.cloudresource.service.inter.rpc;


import top.fosin.anan.cloudresource.entity.res.OrganizRespDTO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 机构GRPC调用入口服务
 *
 * @author fosin
 * @date 2023/4/8
 * @since 3.0.0
 */
public interface OrganizRpcService {
    OrganizRespDTO findOneById(@NotNull Long id);

    List<OrganizRespDTO> listByIds(@NotEmpty List<Long> ids);

    List<OrganizRespDTO> listChild(@NotNull Long pid);

    List<OrganizRespDTO> listAllChild(@NotNull Long pid);

}
