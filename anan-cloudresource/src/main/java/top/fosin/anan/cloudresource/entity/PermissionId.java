package top.fosin.anan.cloudresource.entity;


import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import top.fosin.anan.data.entity.Id;

import java.io.Serializable;

/**
 * 系统版本权限表(AnanVersionPermission)创建DTO
 *
 * @author fosin
 * @date 2019-02-19 18:14:31
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "权限DTO")
public class PermissionId<ID extends Serializable> extends Id<ID> {
    private static final long serialVersionUID = -16743805718001139L;

    @Schema(description = "权限序号", example = "Long")
    private Long permissionId;

}
