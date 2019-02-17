package com.github.fosin.cdp.platform.dto.request;

import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import com.github.fosin.cdp.util.DateTimeUtil;

/**
 * 系统版本角色表(CdpVersionRole)更新DTO
 *
 * @author fosin
 * @date 2019-01-28 11:45:18
 * @since 1.0.0
 */
@Data
@ApiModel(value = "系统版本角色表更新DTO", description = "表(cdp_version_role)的对应的更新DTO")
public class CdpVersionRoleUpdateDto implements Serializable {
    private static final long serialVersionUID = 440574329453677954L;

    @NotNull
    @ApiModelProperty(value = "角色ID", example = "Long", required = true)
    private Long id;

    @NotNull
    @ApiModelProperty(value = "版本ID", example = "Long", required = true)
    private Long versionId;

    @NotBlank
    @ApiModelProperty(value = "角色名称", example = "String", required = true)
    private String name;

    @NotBlank
    @ApiModelProperty(value = "角色标识", example = "String", required = true)
    private String value;

    @ApiModelProperty(value = "角色说明", example = "String")
    private String tips;

    @NotNull
    @ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表cdp_dictionary.id=11", example = "Integer", required = true)
    private Integer status;

}
