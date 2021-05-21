package top.fosin.anan.platform.dto.req;

import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.model.dto.QuerySortRuleDto;
import top.fosin.anan.model.module.SortRule;
import top.fosin.anan.model.module.LogicalQueryRule;

/**
 * 系统版本表(AnanVersion)查询DTO
 *
 * @author fosin
 * @date 2019-02-19 18:14:31
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "系统版本表查询DTO", description = "表(anan_version)的对应的查询DTO")
public class AnanVersionRetrieveDto extends QuerySortRuleDto<LogicalQueryRule,SortRule> implements Serializable {
    private static final long serialVersionUID = -91727267823167686L;

    @ApiModelProperty(value = "版本名称")
    private String name;

    @ApiModelProperty(value = "版本类型：0=收费版 1=免费版 2=开发版")
    private Integer type;

    @ApiModelProperty(value = "版本价格")
    private Double price;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "活动开始日期")
    private Date beginTime;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "活动结束日期")
    private Date endTime;

    @ApiModelProperty(value = "有效期：一般按天计算")
    private Integer validity;

    @ApiModelProperty(value = "到期后保护期")
    private Integer protectDays;

    @ApiModelProperty(value = "最大机构数：0=无限制 n=限制数")
    private Integer maxOrganizs;

    @ApiModelProperty(value = "最大机构数：0=无限制 n=限制数")
    private Integer maxUsers;

    @ApiModelProperty(value = "是否试用：0=不试用 1=试用")
    private Integer tryout;

    @ApiModelProperty(value = "试用天数")
    private Integer tryoutDays;

    @ApiModelProperty(value = "启用状态：0=启用，1=禁用")
    private Integer status;

    @ApiModelProperty(value = "版本描述")
    private String description;

    @ApiModelProperty(value = "创建人，创建人：该值由后台自动维护，取值于系统用户表的编号")
    private Long createBy;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "创建日期")
    private Date createTime;

    @ApiModelProperty(value = "更新人，创建人：该值由后台自动维护，取值于系统用户表的编号")
    private Long updateBy;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "更新日期，该值由后台自动维护")
    private Date updateTime;

}
