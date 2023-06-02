package top.fosin.anan.platform.modules.version.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.data.valid.group.Create;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
        
/**
 * 系统版本角色表(anan_version_role)创建DTO
 *
 * @author fosin
 * @date 2023-05-13
 */
@Data
@EqualsAndHashCode
@ToString(callSuper = true)
@ApiModel(value = "系统版本角色表创建DTO", description = "系统版本角色表(anan_version_role)创建DTO")
public class VersionRoleCreateDTO {
    private static final long serialVersionUID = 549206085302538258L;

    @NotNull(message = "版本序号" + "{javax.validation.constraints.NotNull.message}", groups = Create.class)
    @PositiveOrZero(message = "版本序号" + "{javax.validation.constraints.Positive.message}", groups = Create.class)
    @ApiModelProperty(value = "版本序号", required = true)
    private Long versionId;

    @NotBlank(message = "角色名称" + "{javax.validation.constraints.NotBlank.message}", groups = Create.class)
    @ApiModelProperty(value = "角色名称", required = true)
    private String name;

    @NotBlank(message = "角色标识" + "{javax.validation.constraints.NotBlank.message}", groups = Create.class)
    @ApiModelProperty(value = "角色标识", required = true)
    private String value;

    @ApiModelProperty(value = "角色说明")
    private String tips;

    @NotNull(message = "使用状态" + "{javax.validation.constraints.NotNull.message}", groups = Create.class)
    @PositiveOrZero(message = "使用状态" + "{javax.validation.constraints.Positive.message}", groups = Create.class)
    @ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.code=11", required = true)
    private Byte status;

}
