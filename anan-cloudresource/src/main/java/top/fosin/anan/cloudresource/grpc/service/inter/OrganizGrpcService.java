package top.fosin.anan.cloudresource.grpc.service.inter;


import top.fosin.anan.cloudresource.grpc.organiz.OrganizResp;

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
public interface OrganizGrpcService {
    OrganizResp findOneById(@NotNull Long id);

    List<OrganizResp> listByIds(@NotEmpty List<Long> ids);

    List<OrganizResp> listChild(@NotNull Long pid);

    List<OrganizResp> listAllChild(@NotNull Long pid);

}
