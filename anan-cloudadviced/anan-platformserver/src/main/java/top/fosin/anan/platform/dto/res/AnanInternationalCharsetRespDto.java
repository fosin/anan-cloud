package top.fosin.anan.platform.dto.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.model.dto.IdCreateUpdateDto;

import java.io.Serializable;

/**
 * 国际化语言字符集(AnanInternationalCharset)响应DTO
 *
 * @author fosin
 * @date 2021-05-16 14:32:14
 * @since 2.6.0
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "国际化语言字符集响应DTO", description = "表(anan_international_charset)的响应DTO")
public class AnanInternationalCharsetRespDto extends IdCreateUpdateDto<Long> implements Serializable {
    private static final long serialVersionUID = 931996735216252232L;
    @ApiModelProperty(value = "国际化语言ID", example = "Integer")
    private Long internationalId;

    @ApiModelProperty(value = "服务ID", example = "Integer")
    private Long serviceId;

    @ApiModelProperty(value = "自定义字符集", example = "String")
    private String charset;

    @ApiModelProperty(value = "状态：0=启用，1=禁用", example = "Integer")
    private Integer status;

}
