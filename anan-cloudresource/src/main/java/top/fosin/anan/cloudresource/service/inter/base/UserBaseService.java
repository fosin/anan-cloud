package top.fosin.anan.cloudresource.service.inter.base;


import top.fosin.anan.cloudresource.dto.res.UserRespDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 用户GRPC调用入口服务
 *
 * @author fosin
 * @date 2023/4/8
 * @since 3.0.0
 */
public interface UserBaseService {
    UserRespDto findOneByUsercode(@NotBlank String usercode);

    List<UserRespDto> listByOrganizId(@NotNull Long organizId, @NotNull Integer status);

    List<UserRespDto> listAllChildByTopId(@NotNull Long topId, @NotNull Integer status);
}
