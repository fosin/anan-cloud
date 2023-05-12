package top.fosin.anan.platform.modules.dictionary.vo;

                                                                                                                                                                                                                                                            
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import java.util.Date;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.data.entity.Id;
/**
 * 系统通用字典明细表(anan_dictionary_detail)集合VO
 *
 * @author fosin
 * @date 2023-05-11
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "系统通用字典明细表集合VO", description = "系统通用字典明细表(anan_dictionary_detail)集合VO")
public class DictionaryDetailPageVO extends Id<Long> {
    private static final long serialVersionUID = -29783015088220372L;
    @ApiModelProperty(value = "取值于字典表anan_dictionary.id")
    private Long dictionaryId;

    @ApiModelProperty(value = "字典明细键，不能重复，字典内明细项唯一代码")
    private Long code;

    @ApiModelProperty(value = "字典明细字面说明")
    private String name;

    @ApiModelProperty(value = "顺序，用于显示数据时的顺序，数值越小越靠前")
    private Object sort;

    @ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.code=11")
    private Integer status;

    @ApiModelProperty(value = "标准代码，该字段通常用于对接标准字典")
    private String scode;

    @ApiModelProperty(value = "作用域，用于字典明细项的作用域")
    private String scope;

    @ApiModelProperty(value = "使用标志：0=未使用，1=已使用，已使用的字典就不能再修改name属性")
    private Integer used;

    @ApiModelProperty(value = "详细说明")
    private String description;

    @ApiModelProperty(value = "创建人")
    private Long createBy;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "创建日期")
    private Date createTime;

    @ApiModelProperty(value = "修改人")
    private Long updateBy;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "修改日期")
    private Date updateTime;

}
