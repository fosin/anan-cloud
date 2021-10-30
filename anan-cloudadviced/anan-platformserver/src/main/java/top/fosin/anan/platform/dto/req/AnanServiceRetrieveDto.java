package top.fosin.anan.platform.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.fosin.anan.model.dto.QuerySortRuleDto;
import top.fosin.anan.model.module.LogicalQueryRule;
import top.fosin.anan.model.module.SortRule;

/**
 * 系统服务表(AnanService)查询DTO
 *
 * @author fosin
 * @date 2020-12-04 17:48:21
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "服务表查询DTO", description = "服务的查询DTO")
public class AnanServiceRetrieveDto extends QuerySortRuleDto<LogicalQueryRule, SortRule> {
    private static final long serialVersionUID = -95213722807513308L;

    @ApiModelProperty(value = "服务标识", example = "String")
    private String code;

    @ApiModelProperty(value = "服务名称", example = "String")
    private String name;

    @ApiModelProperty(value = "状态码：0：禁用 1：启用", example = "0")
    private Integer status;

}
