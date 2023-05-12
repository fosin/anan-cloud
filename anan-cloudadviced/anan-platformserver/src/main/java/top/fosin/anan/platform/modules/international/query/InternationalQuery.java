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
 * 国际化语言集(anan_international)通用查询DTO
 *
 * @author fosin
 * @date 2023-05-12
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "国际化语言集通用查询DTO", description = "国际化语言集(anan_international)通用查询DTO")
public class InternationalQuery extends LogiSortQuery<LogiQueryRule,SortRule,Integer> {
    private static final long serialVersionUID = -28236847738632954L;
    
    @ApiModelProperty(value = "标识", example = "String")
    private String code;

    @ApiModelProperty(value = "名称", example = "String")
    private String name;

    @ApiModelProperty(value = "图标", example = "String")
    private String icon;

    @ApiModelProperty(value = "状态：0=启用，1=禁用", example = "Integer")
    private Integer status;

    @ApiModelProperty(value = "默认标志", example = "Integer")
    private Integer defaultFlag;

}
