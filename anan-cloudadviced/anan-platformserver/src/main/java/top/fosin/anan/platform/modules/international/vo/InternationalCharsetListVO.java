package top.fosin.anan.platform.modules.international.vo;

                                                                                                                                                            

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.data.entity.Id;
/**
 * 国际化语言字符集(anan_international_charset)集合VO
 *
 * @author fosin
 * @date 2023-05-12
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "国际化语言字符集(anan_international_charset)集合VO")
public class InternationalCharsetListVO extends Id<Long> {
    private static final long serialVersionUID = 462714746365581958L;
    @Schema(description = "国际化语言ID")
    private Long internationalId;

    @Schema(description = "服务ID")
    private Long serviceId;

    @Schema(description = "自定义字符集")
    private String charset;

    @Schema(description = "状态：0=启用，1=禁用")
    private Byte status;

}
