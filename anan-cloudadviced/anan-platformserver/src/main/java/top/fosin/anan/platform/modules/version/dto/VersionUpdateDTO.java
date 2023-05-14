package top.fosin.anan.platform.modules.version.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.data.entity.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.Date;
        
/**
 * 系统版本表(anan_version)更新DTO
 *
 * @author fosin
 * @date 2023-05-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@ApiModel(value = "系统版本表更新DTO", description = "系统版本表(anan_version)更新DTO")
public class VersionUpdateDTO extends Id<Long> {
    private static final long serialVersionUID = -80177336105245954L;

    @NotBlank(message = "版本名称" + "{javax.validation.constraints.NotBlank.message}")
    @ApiModelProperty(value = "版本名称", required = true)
    private String name;

    @NotNull(message = "版本类型" + "{javax.validation.constraints.NotNull.message}")
    @PositiveOrZero(message = "版本类型" + "{javax.validation.constraints.Positive.message}")
    @ApiModelProperty(value = "版本类型：0=收费版 1=免费版 2=开发版", required = true)
    private Integer type;

    @NotNull(message = "版本价格" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "版本价格", required = true)
    private Double price;

    @ApiModelProperty(value = "活动开始时间", required = true)
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    private Date beginTime;

    @ApiModelProperty(value = "活动结束时间", required = true)
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    private Date endTime;

    @NotNull(message = "有效期" + "{javax.validation.constraints.NotNull.message}")
    @Positive(message = "有效期" + "{javax.validation.constraints.Positive.message}")
    @ApiModelProperty(value = "有效期：一般按天计算", required = true)
    private Integer validity;

    @NotNull(message = "到期后保护期" + "{javax.validation.constraints.NotNull.message}")
    @PositiveOrZero(message = "到期后保护期" + "{javax.validation.constraints.Positive.message}")
    @ApiModelProperty(value = "到期后保护期", required = true)
    private Integer protectDays;

    @NotNull(message = "最大机构数" + "{javax.validation.constraints.NotNull.message}")
    @PositiveOrZero(message = "最大机构数" + "{javax.validation.constraints.Positive.message}")
    @ApiModelProperty(value = "最大机构数：0=无限制 n=限制数", required = true)
    private Integer maxOrganizs;

    @NotNull(message = "最大用户数" + "{javax.validation.constraints.NotNull.message}")
    @PositiveOrZero(message = "最大用户数" + "{javax.validation.constraints.Positive.message}")
    @ApiModelProperty(value = "最大用户数：0=无限制 n=限制数", required = true)
    private Integer maxUsers;

    @NotNull(message = "是否试用" + "{javax.validation.constraints.NotNull.message}")
    @PositiveOrZero(message = "是否试用" + "{javax.validation.constraints.Positive.message}")
    @ApiModelProperty(value = "是否试用：0=不试用 1=试用", required = true)
    private Integer tryout;

    @NotNull(message = "试用天数" + "{javax.validation.constraints.NotNull.message}")
    @Positive(message = "试用天数" + "{javax.validation.constraints.Positive.message}")
    @ApiModelProperty(value = "试用天数", required = true)
    private Integer tryoutDays;

    @NotNull(message = "启用状态" + "{javax.validation.constraints.NotNull.message}")
    @PositiveOrZero(message = "启用状态" + "{javax.validation.constraints.Positive.message}")
    @ApiModelProperty(value = "启用状态：0=启用，1=禁用", required = true)
    private Integer status;

    @ApiModelProperty(value = "版本描述")
    private String description;
}
