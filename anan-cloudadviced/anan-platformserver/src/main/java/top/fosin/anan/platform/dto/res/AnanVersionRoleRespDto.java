package top.fosin.anan.platform.dto.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.model.dto.IdCreateUpdateDto;

/**
 * 系统版本角色表(AnanVersionRole)响应DTO
 *
 * @author fosin
 * @date 2021-05-16 14:32:15
 * @since 2.6.0
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "版本角色表响应DTO", description = "版本角色的响应DTO")
public class AnanVersionRoleRespDto extends IdCreateUpdateDto<Long> {
    private static final long serialVersionUID = -39744614020133302L;
    @ApiModelProperty(value = "版本序号", example = "Long")
    private Long versionId;

    @ApiModelProperty(value = "角色名称", example = "String")
    private String name;

    @ApiModelProperty(value = "角色标识", example = "String")
    private String value;

    @ApiModelProperty(value = "角色说明", example = "String")
    private String tips;

    @ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.code=11", example = "Integer")
    private Integer status;

}
