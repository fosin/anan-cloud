package top.fosin.anan.platform.modules.version.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.DynamicUpdate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.data.entity.req.LogiSortQuery;
import top.fosin.anan.data.module.SortRule;
import top.fosin.anan.data.module.LogiQueryRule;
import java.util.Date;

        /**
 * 系统版本表(anan_version)通用查询DTO
 *
 * @author fosin
 * @date 2023-05-13
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "系统版本表通用查询DTO", description = "系统版本表(anan_version)通用查询DTO")
public class VersionQuery extends LogiSortQuery<LogiQueryRule,SortRule,Long> {
    private static final long serialVersionUID = 343101047418823027L;
    
    @ApiModelProperty(value = "版本名称", example = "String")
    private String name;

    @ApiModelProperty(value = "版本类型：0=收费版 1=免费版 2=开发版", example = "Integer")
    private Byte type;

    @ApiModelProperty(value = "版本价格", example = "Double")
    private Double price;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "活动开始日期", example = "Date")
    private Date beginTime;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "活动结束日期", example = "Date")
    private Date endTime;

    @ApiModelProperty(value = "有效期：一般按天计算", example = "Integer")
    private Integer validity;

    @ApiModelProperty(value = "到期后保护期", example = "Integer")
    private Integer protectDays;

    @ApiModelProperty(value = "最大机构数：0=无限制 n=限制数", example = "Integer")
    private Integer maxOrganizs;

    @ApiModelProperty(value = "最大机构数：0=无限制 n=限制数", example = "Integer")
    private Integer maxUsers;

    @ApiModelProperty(value = "是否试用：0=不试用 1=试用", example = "Integer")
    private Integer tryout;

    @ApiModelProperty(value = "试用天数", example = "Integer")
    private Integer tryoutDays;

    @ApiModelProperty(value = "启用状态：0=启用，1=禁用", example = "Integer")
    private Byte status;

    @ApiModelProperty(value = "版本描述", example = "String")
    private String description;

}
