package top.fosin.anan.platform.modules.role.dto;


import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.data.entity.IdOrganizId;

/**
 * 系统用户角色表(AnanUserRole)响应DTO
 *
 * @author fosin
 * @date 2021-05-16 14:32:15
 * @since 2.6.0
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@Schema(description = "用户角色的响应DTO")
public class UserRoleRespDTO extends IdOrganizId<Long> {
    private static final long serialVersionUID = -30073122110919311L;

    @Schema(description = "用户序号", example = "Long")
    private Long userId;

    @Schema(description = "角色序号", example = "Long")
    private Long roleId;

}
