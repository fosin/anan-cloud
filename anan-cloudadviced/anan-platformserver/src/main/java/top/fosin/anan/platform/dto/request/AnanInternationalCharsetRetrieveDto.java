package top.fosin.anan.platform.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 国际化语言字符集(AnanInternationalCharset)查询DTO
 *
 * @author fosin
 * @date 2020-12-09 10:34:35
 * @since 1.0.0
 */
@Data
@ApiModel(value = "国际化语言字符集查询DTO", description = "表(anan_international_charset)的对应的查询DTO")
public class AnanInternationalCharsetRetrieveDto implements Serializable {
    private static final long serialVersionUID = 329752756986005664L;

    @NotNull(message = "主键" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "主键", example = "Long")
    private Long id;

    @NotNull(message = "国际化语言ID" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "国际化语言ID", example = "Integer")
    private Integer internationalId;

    @NotNull(message = "服务ID" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "服务ID", example = "Integer")
    private Integer serviceId;

    @ApiModelProperty(value = "自定义字符集", example = "String")
    private String charset;

    @NotNull(message = "状态：0=启用，1=禁用" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "状态：0=启用，1=禁用", example = "Integer")
    private Integer status;

}
