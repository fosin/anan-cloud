package top.fosin.anan.platform.modules.international.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import top.fosin.anan.data.entity.req.LogiSortQuery;
import top.fosin.anan.data.module.SortRule;
import top.fosin.anan.data.module.LogiQueryRule;


        /**
 * 国际化语言字符集(anan_international_charset)通用查询DTO
 *
 * @author fosin
 * @date 2023-05-12
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "国际化语言字符集通用查询DTO", description = "国际化语言字符集(anan_international_charset)通用查询DTO")
public class InternationalCharsetQuery extends LogiSortQuery<LogiQueryRule,SortRule,Long> {
    private static final long serialVersionUID = 113876643715826403L;
    
    @ApiModelProperty(value = "国际化语言ID", example = "Integer")
    private Long internationalId;

    @ApiModelProperty(value = "服务ID", example = "Integer")
    private Long serviceId;

    @ApiModelProperty(value = "自定义字符集", example = "String")
    private String charset;

    @ApiModelProperty(value = "状态：0=启用，1=禁用", example = "Integer")
    private Byte status;

}
