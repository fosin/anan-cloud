package top.fosin.anan.platform.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.fosin.anan.model.dto.QuerySortRuleDto;
import top.fosin.anan.model.module.SortRule;
import top.fosin.anan.model.module.QueryRule;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 国际化语言集(AnanInternational)查询DTO
 *
 * @author fosin
 * @date 2020-12-08 20:54:17
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "国际化语言集查询DTO", description = "表(anan_international)的对应的查询DTO")
public class AnanInternationalRetrieveDto extends QuerySortRuleDto<QueryRule, SortRule, Integer> implements Serializable {
    private static final long serialVersionUID = 380520117161696142L;

    @NotBlank(message = "标识" + "{javax.validation.constraints.NotBlank.message}")
    @ApiModelProperty(value = "标识", example = "String")
    private String code;

    @NotBlank(message = "名称" + "{javax.validation.constraints.NotBlank.message}")
    @ApiModelProperty(value = "名称", example = "String")
    private String name;

    @ApiModelProperty(value = "图标", example = "String")
    private String icon;

    @NotNull(message = "状态：0=启用，1=禁用" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "状态：0=启用，1=禁用", example = "0")
    private Integer status;

    @NotNull(message = "默认标志" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "默认标志", example = "0")
    private Integer defaultFlag;

}
