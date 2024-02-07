package top.fosin.anan.platform.modules.parameter.query;


import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.data.entity.req.LogiSortQuery;
import top.fosin.anan.data.module.LogiQueryRule;
import top.fosin.anan.data.module.SortRule;

import java.util.Date;

        /**
 * 用于存放各种分类分组的个性化参数(anan_parameter)通用查询DTO
 *
 * @author fosin
 * @date 2023-05-13
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "用于存放各种分类分组的个性化参数(anan_parameter)通用查询DTO")
public class ParameterQuery extends LogiSortQuery<LogiQueryRule, SortRule, Long> {
    private static final long serialVersionUID = 935557570288461194L;
    
    @Schema(description = "参数键", example = "String")
    private String name;

    @Schema(description = "参数值", example = "String")
    private String value;

    @Schema(description = "参数分类：具体取值于字典表anan_dictionary.code=10", example = "Integer")
    private Byte type;

    @Schema(description = "参数作用域", example = "String")
    private String scope;

    @Schema(description = "默认值", example = "String")
    private String defaultValue;

    @Schema(description = "参数描述", example = "String")
    private String description;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Schema(description = "发布时间", example = "Date")
    private Date applyTime;

    @Schema(description = "创建人", example = "Long")
    private Long applyBy;

    @Schema(description = "参数状态：0=正常状态、1=修改状态、2=删除状态", example = "Integer")
    private Byte status;

}
