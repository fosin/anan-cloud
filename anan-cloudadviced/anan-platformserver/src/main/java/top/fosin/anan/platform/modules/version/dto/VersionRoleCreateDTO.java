package top.fosin.anan.platform.modules.version.dto;


import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.data.valid.group.Create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
        
/**
 * 系统版本角色表(anan_version_role)创建DTO
 *
 * @author fosin
 * @date 2023-05-13
 */
@Data
@EqualsAndHashCode
@ToString(callSuper = true)
@Schema(description = "系统版本角色表(anan_version_role)创建DTO")
public class VersionRoleCreateDTO {
    private static final long serialVersionUID = 549206085302538258L;

    @NotNull(message = "版本序号" + "{jakarta.validation.constraints.NotNull.message}", groups = Create.class)
    @PositiveOrZero(message = "版本序号" + "{jakarta.validation.constraints.Positive.message}", groups = Create.class)
    @Schema(description = "版本序号")
    private Long versionId;

    @NotBlank(message = "角色名称" + "{jakarta.validation.constraints.NotBlank.message}", groups = Create.class)
    @Schema(description = "角色名称")
    private String name;

    @NotBlank(message = "角色标识" + "{jakarta.validation.constraints.NotBlank.message}", groups = Create.class)
    @Schema(description = "角色标识")
    private String value;

    @Schema(description = "角色说明")
    private String tips;

    @NotNull(message = "使用状态" + "{jakarta.validation.constraints.NotNull.message}", groups = Create.class)
    @PositiveOrZero(message = "使用状态" + "{jakarta.validation.constraints.Positive.message}", groups = Create.class)
    @Schema(description = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.code=11")
    private Byte status;

}
