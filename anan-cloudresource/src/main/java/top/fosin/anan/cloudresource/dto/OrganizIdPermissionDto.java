package top.fosin.anan.cloudresource.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 机构序号DTO
 *
 * @author fosin
 * @date 2019-02-19 18:14:31
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "机构序号DTO", description = "机构序号DTO")
public class OrganizIdPermissionDto<ID extends Serializable> extends PermissionId<ID> {
    private static final long serialVersionUID = -16743805718001139L;

    @ApiModelProperty(value = "机构序号")
    private ID organizId;

}
