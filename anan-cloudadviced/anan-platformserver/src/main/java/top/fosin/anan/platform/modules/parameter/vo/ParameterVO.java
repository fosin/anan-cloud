package top.fosin.anan.platform.modules.parameter.vo;



import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.data.entity.Id;

import java.util.Date;

        /**
 * 用于存放各种分类分组的个性化参数(anan_parameter)单体VO
 *
 * @author fosin
 * @date 2023-05-13
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "用于存放各种分类分组的个性化参数(anan_parameter)单体VO")
public class ParameterVO extends Id<Long> {
    private static final long serialVersionUID = -53397983808305148L;
    @Schema(description = "参数键")
    private String name;

    @Schema(description = "参数值")
    private String value;

    @Schema(description = "参数分类：具体取值于字典表anan_dictionary.code=10")
    private Byte type;

    @Schema(description = "参数作用域")
    private String scope;

    @Schema(description = "默认值")
    private String defaultValue;

    @Schema(description = "参数描述")
    private String description;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Schema(description = "发布时间")
    private Date applyTime;

    @Schema(description = "发布人")
    private Long applyBy;

    @Schema(description = "参数状态：0=正常状态、1=修改状态、2=删除状态")
    private Byte status;

}
