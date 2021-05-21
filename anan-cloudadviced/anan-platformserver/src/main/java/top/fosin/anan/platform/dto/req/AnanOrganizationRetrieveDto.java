package top.fosin.anan.platform.dto.req;

import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.core.util.RegexUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import top.fosin.anan.model.dto.QuerySortRuleDto;
import top.fosin.anan.model.module.SortRule;
import top.fosin.anan.model.module.LogicalQueryRule;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * 系统机构表(AnanOrganization)查询DTO
 *
 * @author fosin
 * @date 2019-01-27 18:30:48
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "系统机构表查询DTO", description = "系统机构的查询DTO")
public class AnanOrganizationRetrieveDto extends QuerySortRuleDto<LogicalQueryRule,SortRule> implements Serializable {
    private static final long serialVersionUID = 755956109753090820L;

    @ApiModelProperty(value = "父机构编号，取值于id，表示当前数据所属的父类机构")
    private Long pid;

    @ApiModelProperty(value = "顶级机构编号：一般指用户注册的机构，通常是一个集团组的最高级别机构，取值于id")
    private Long topId;

    @ApiModelProperty(value = "机构编码，自定义机构编码，下级机构必须以上级机构编码为前缀")
    @Pattern(regexp = "[\\w]{1,64}", message = "机构编码只能大小写字母、数字、下杠(_)组合而成,长度不超过64位")
    private String code;

    @ApiModelProperty(value = "机构名称")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "名称不能包含特殊字符")
    private String name;

    @ApiModelProperty(value = "深度")
    private Integer level;

    @ApiModelProperty(value = "机构全名")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "名称不能包含特殊字符")
    private String fullname;

    @ApiModelProperty(value = "机构地址")
    private String address;

    @ApiModelProperty(value = "机构电话")
    private String telphone;

    @ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.code=11")
    private Integer status;

    @ApiModelProperty(value = "创建人", example = "1")
    private Long createBy;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "创建日期", example = "2021-05-08 13:25:11")
    private Date createTime;

    @ApiModelProperty(value = "修改人", example = "1")
    private Long updateBy;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "修改日期", example = "2021-05-08 13:25:11")
    private Date updateTime;
}
