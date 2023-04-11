package top.fosin.anan.cloudresource.service.inter.rpc;


import top.fosin.anan.cloudresource.dto.res.OrgRespDto;

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
    OrgRespDto findOneById(@NotNull Long id);

    List<OrgRespDto> listByIds(@NotEmpty List<Long> ids);

    List<OrgRespDto> listChild(@NotNull Long pid);

    List<OrgRespDto> listAllChild(@NotNull Long pid);

}
