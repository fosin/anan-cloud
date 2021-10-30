package top.fosin.anan.platform.dto.req;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.model.dto.QuerySortRuleDto;
import top.fosin.anan.model.module.LogicalQueryRule;
import top.fosin.anan.model.module.SortRule;

/**
 * 系统角色表(AnanRole)查询DTO
 *
 * @author fosin
 * @date 2019-01-27 18:27:18
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "角色表查询DTO", description = "角色的查询DTO")
public class AnanRoleRetrieveDto extends QuerySortRuleDto<LogicalQueryRule, SortRule> {
    private static final long serialVersionUID = 431913654589649616L;

    @ApiModelProperty(value = "机构ID")
    private Long organizId;

    @ApiModelProperty(value = "角色名称")
    private String name;

    @ApiModelProperty(value = "角色标识")
    private String value;

    @ApiModelProperty(value = "角色说明")
    private String tips;

    @ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.code=11")
    private Integer status;

    @ApiModelProperty(value = "内置标志：是否是系统内置角色，内置角色不能被用户删除和修改，0=不是 1=是")
    private Integer builtIn;

    @ApiModelProperty(value = "创建人", example = "1")
    private Long createBy;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "创建时间", example = "2021-05-08 13:25:11")
    private Date createTime;

    @ApiModelProperty(value = "修改人", example = "1")
    private Long updateBy;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "修改时间", example = "2021-05-08 13:25:11")
    private Date updateTime;
}
