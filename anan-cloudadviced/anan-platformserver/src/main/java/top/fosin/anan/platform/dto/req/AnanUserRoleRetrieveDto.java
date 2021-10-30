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
 * 系统用户角色表(AnanUserRole)查询DTO
 *
 * @author fosin
 * @date 2019-02-19 18:17:04
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "用户角色表查询DTO", description = "用户角色的查询DTO")
public class AnanUserRoleRetrieveDto extends QuerySortRuleDto<LogicalQueryRule, SortRule> {
    private static final long serialVersionUID = 818450290607468187L;

    @ApiModelProperty(value = "机构ID")
    private Long organizId;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "角色ID")
    private Long roleId;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "创建时间，该值由后台维护，更改数据时前端不需要关心")
    private Date createTime;

    @ApiModelProperty(value = "该值由后台维护，更改数据时前端不需要关心，取值于anan_user.id")
    private Long createBy;

}
