package top.fosin.anan.platform.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.fosin.anan.model.dto.QuerySortRuleDto;
import top.fosin.anan.model.module.LogicalQueryRule;
import top.fosin.anan.model.module.SortRule;

import java.io.Serializable;

/**
 * 国际化语言字符集(AnanInternationalCharset)查询DTO
 *
 * @author fosin
 * @date 2020-12-09 10:34:35
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "国际化语言字符集查询DTO", description = "表(anan_international_charset)的对应的查询DTO")
public class AnanInternationalCharsetRetrieveDto extends QuerySortRuleDto<LogicalQueryRule, SortRule> implements Serializable {
    private static final long serialVersionUID = 329752756986005664L;

    @ApiModelProperty(value = "国际化语言ID", example = "0")
    private Long internationalId;

    @ApiModelProperty(value = "服务ID", example = "0")
    private Long serviceId;

    @ApiModelProperty(value = "自定义字符集", example = "String")
    private String charset;

    @ApiModelProperty(value = "状态：0=启用，1=禁用", example = "0")
    private Integer status;

}
