package top.fosin.anan.platform.modules.version.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.data.valid.group.Create;

import java.math.BigDecimal;
import java.util.Date;
        
/**
 * 系统版本表(anan_version)创建DTO
 *
 * @author fosin
 * @date 2023-05-13
 */
@Data
@EqualsAndHashCode
@ToString(callSuper = true)
@Schema(description = "系统版本表(anan_version)创建DTO")
public class VersionCreateDTO {
    private static final long serialVersionUID = 921938718796477873L;

    @NotBlank(message = "版本名称" + "{jakarta.validation.constraints.NotBlank.message}", groups = Create.class)
    @Schema(description = "版本名称")
    private String name;

    @NotNull(message = "版本类型" + "{jakarta.validation.constraints.NotNull.message}", groups = Create.class)
    @PositiveOrZero(message = "版本类型" + "{jakarta.validation.constraints.Positive.message}", groups = Create.class)
    @Schema(description = "版本类型：0=收费版 1=免费版 2=开发版")
    private Byte type;

    @NotNull(message = "版本价格" + "{jakarta.validation.constraints.NotNull.message}", groups = Create.class)
    @Schema(description = "版本价格")
    private BigDecimal price;

    @Schema(description = "活动开始时间")
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    private Date beginTime;

    @Schema(description = "活动结束时间")
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    private Date endTime;

    @NotNull(message = "有效期" + "{jakarta.validation.constraints.NotNull.message}", groups = Create.class)
    @Positive(message = "有效期" + "{jakarta.validation.constraints.Positive.message}", groups = Create.class)
    @Schema(description = "有效期：一般按天计算")
    private Integer validity;

    @NotNull(message = "到期后保护期" + "{jakarta.validation.constraints.NotNull.message}", groups = Create.class)
    @PositiveOrZero(message = "到期后保护期" + "{jakarta.validation.constraints.Positive.message}", groups = Create.class)
    @Schema(description = "到期后保护期")
    private Integer protectDays;

    @NotNull(message = "最大机构数" + "{jakarta.validation.constraints.NotNull.message}", groups = Create.class)
    @PositiveOrZero(message = "最大机构数" + "{jakarta.validation.constraints.Positive.message}", groups = Create.class)
    @Schema(description = "最大机构数：0=无限制 n=限制数")
    private Integer maxOrganizs;

    @NotNull(message = "最大用户数" + "{jakarta.validation.constraints.NotNull.message}", groups = Create.class)
    @PositiveOrZero(message = "最大用户数" + "{jakarta.validation.constraints.Positive.message}", groups = Create.class)
    @Schema(description = "最大用户数：0=无限制 n=限制数")
    private Integer maxUsers;

    @NotNull(message = "是否试用" + "{jakarta.validation.constraints.NotNull.message}", groups = Create.class)
    @PositiveOrZero(message = "是否试用" + "{jakarta.validation.constraints.Positive.message}", groups = Create.class)
    @Schema(description = "是否试用：0=不试用 1=试用")
    private Integer tryout;

    @NotNull(message = "试用天数" + "{jakarta.validation.constraints.NotNull.message}", groups = Create.class)
    @Positive(message = "试用天数" + "{jakarta.validation.constraints.Positive.message}", groups = Create.class)
    @Schema(description = "试用天数")
    private Integer tryoutDays;

    @NotNull(message = "启用状态" + "{jakarta.validation.constraints.NotNull.message}", groups = Create.class)
    @PositiveOrZero(message = "启用状态" + "{jakarta.validation.constraints.Positive.message}", groups = Create.class)
    @Schema(description = "启用状态：0=启用，1=禁用")
    private Byte status;

    @Schema(description = "版本描述")
    private String description;

}
