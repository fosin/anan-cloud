package top.fosin.anan.platform.modules.version.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.data.entity.Id;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
        
/**
 * 系统版本角色表(anan_version_role)更新DTO
 *
 * @author fosin
 * @date 2023-05-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@ApiModel(value = "系统版本角色表更新DTO", description = "系统版本角色表(anan_version_role)更新DTO")
public class VersionRoleUpdateDTO extends Id<Long> {
    private static final long serialVersionUID = 974616725478922939L;

    @NotNull(message = "版本序号" + "{javax.validation.constraints.NotNull.message}")
    @PositiveOrZero(message = "版本序号" + "{javax.validation.constraints.Positive.message}")
    @ApiModelProperty(value = "版本序号", required = true)
    private Long versionId;

    @NotBlank(message = "角色名称" + "{javax.validation.constraints.NotBlank.message}")
    @ApiModelProperty(value = "角色名称", required = true)
    private String name;

    @NotBlank(message = "角色标识" + "{javax.validation.constraints.NotBlank.message}")
    @ApiModelProperty(value = "角色标识", required = true)
    private String value;

    @ApiModelProperty(value = "角色说明")
    private String tips;

    @NotNull(message = "使用状态" + "{javax.validation.constraints.NotNull.message}")
    @PositiveOrZero(message = "使用状态" + "{javax.validation.constraints.Positive.message}")
    @ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.code=11", required = true)
    private Integer status;

}
