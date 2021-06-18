package top.fosin.anan.platform.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.model.dto.IdDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 系统服务表(AnanService)更新DTO
 *
 * @author fosin
 * @date 2020-12-04 17:48:20
 * @since 1.0.0
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "服务表更新DTO", description = "服务的更新DTO")
public class AnanServiceUpdateDto extends IdDto<Long> implements Serializable {
    private static final long serialVersionUID = -36886476405187311L;

    @NotBlank(message = "服务标识" + "{javax.validation.constraints.NotBlank.message}")
    @ApiModelProperty(value = "服务标识", example = "String")
    private String code;

    @NotBlank(message = "服务名称" + "{javax.validation.constraints.NotBlank.message}")
    @ApiModelProperty(value = "服务名称", example = "String")
    private String name;

    @NotNull(message = "状态码：0：禁用 1：启用" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "状态码：0：禁用 1：启用", example = "0")
    private Integer status;

}
