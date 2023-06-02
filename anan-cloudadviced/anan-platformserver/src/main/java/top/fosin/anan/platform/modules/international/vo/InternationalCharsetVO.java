package top.fosin.anan.platform.modules.international.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.data.entity.Id;
/**
 * 国际化语言字符集(anan_international_charset)单体VO
 *
 * @author fosin
 * @date 2023-05-12
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "国际化语言字符集单体VO", description = "国际化语言字符集(anan_international_charset)单体VO")
public class InternationalCharsetVO extends Id<Long> {
    private static final long serialVersionUID = 799797940020227019L;
    @ApiModelProperty(value = "国际化语言ID")
    private Long internationalId;

    @ApiModelProperty(value = "服务ID")
    private Long serviceId;

    @ApiModelProperty(value = "自定义字符集")
    private String charset;

    @ApiModelProperty(value = "状态：0=启用，1=禁用")
    private Byte status;

}
