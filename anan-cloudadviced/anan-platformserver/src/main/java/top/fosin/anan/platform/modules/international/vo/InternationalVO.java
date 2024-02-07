package top.fosin.anan.platform.modules.international.vo;

                                                                                                                                                                            

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import java.util.Date;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.data.entity.Id;
/**
 * 国际化语言集(anan_international)单体VO
 *
 * @author fosin
 * @date 2023-05-12
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "国际化语言集(anan_international)单体VO")
public class InternationalVO extends Id<Long> {
    private static final long serialVersionUID = 921007472479181194L;
    @Schema(description = "标识")
    private String code;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "图标")
    private String icon;

    @Schema(description = "状态：0=启用，1=禁用")
    private Byte status;

    @Schema(description = "默认标志")
    private Integer defaultFlag;

}
