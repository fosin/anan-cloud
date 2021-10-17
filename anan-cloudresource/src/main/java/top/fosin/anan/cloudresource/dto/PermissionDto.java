package top.fosin.anan.cloudresource.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.fosin.anan.model.dto.IdDto;

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
@ApiModel(value = "权限DTO", description = "权限DTO")
public class PermissionDto<ID extends Serializable> extends IdDto<ID> implements Serializable {
    private static final long serialVersionUID = -16743805718001139L;

    @ApiModelProperty(value = "权限ID", example = "Long")
    private Long permissionId;

}
