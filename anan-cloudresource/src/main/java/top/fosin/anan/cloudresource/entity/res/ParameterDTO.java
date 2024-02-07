package top.fosin.anan.cloudresource.entity.res;



import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.data.entity.Id;

import java.util.Date;
/**
 * 用于存放各种分类分组的个性化参数(anan_parameter)DTO
 *
 * @author fosin
 * @date 2023-05-13
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "用于存放各种分类分组的个性化参数(anan_parameter)DTO")
public class ParameterDTO extends Id<Long> {
    private static final long serialVersionUID = -56643720935660246L;
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
    @Schema(description = "创建日期", example = "Date")
    private Date createTime;

    @Schema(description = "创建人", example = "Long")
    private Long createBy;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Schema(description = "修改日期", example = "Date")
    private Date updateTime;

    @Schema(description = "修改人", example = "Long")
    private Long updateBy;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Schema(description = "发布时间", example = "Date")
    private Date applyTime;

    @Schema(description = "创建人", example = "Long")
    private Long applyBy;

    @Schema(description = "参数状态：0=正常状态、1=修改状态、2=删除状态", example = "Integer")
    private Byte status;

}
