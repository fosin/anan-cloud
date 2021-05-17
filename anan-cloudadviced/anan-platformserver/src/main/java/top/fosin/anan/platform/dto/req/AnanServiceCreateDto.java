package top.fosin.anan.platform.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 系统服务表(AnanService)创建DTO
 *
 * @author fosin
 * @date 2020-12-05 22:10:55
 * @since 1.0.0
 */
@Data
@ApiModel(value = "系统服务表创建DTO", description = "表(anan_service)的对应的创建DTO")
public class AnanServiceCreateDto implements Serializable {
    private static final long serialVersionUID = -47807235014602112L;

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
