package top.fosin.anan.platform.modules.version.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.data.entity.Id;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 系统版本表(anan_version)DTO
 *
 * @author fosin
 * @date 2023-05-13
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "系统版本表(anan_version)DTO")
public class VersionDTO extends Id<Long> {
    private static final long serialVersionUID = -83185128249683941L;
    @Schema(description = "版本名称", example = "String")
    private String name;

    @Schema(description = "版本类型：0=收费版 1=免费版 2=开发版", example = "Integer")
    private Byte type;

    @Schema(description = "版本价格", example = "Double")
    private BigDecimal price;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Schema(description = "活动开始时间", example = DateTimeUtil.DATETIME_PATTERN)
    private Date beginTime;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Schema(description = "活动结束时间", example = DateTimeUtil.DATETIME_PATTERN)
    private Date endTime;

    @Schema(description = "有效期：一般按天计算", example = "Integer")
    private Integer validity;

    @Schema(description = "到期后保护期", example = "Integer")
    private Integer protectDays;

    @Schema(description = "最大机构数：0=无限制 n=限制数", example = "Integer")
    private Integer maxOrganizs;

    @Schema(description = "最大机构数：0=无限制 n=限制数", example = "Integer")
    private Integer maxUsers;

    @Schema(description = "是否试用：0=不试用 1=试用", example = "Integer")
    private Integer tryout;

    @Schema(description = "试用天数", example = "Integer")
    private Integer tryoutDays;

    @Schema(description = "启用状态：0=启用，1=禁用", example = "Integer")
    private Byte status;

    @Schema(description = "版本描述", example = "String")
    private String description;

    @Schema(description = "创建人", example = "Long")
    private Long createBy;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Schema(description = "创建日期", example = "Date")
    private Date createTime;

    @Schema(description = "修改人", example = "Long")
    private Long updateBy;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Schema(description = "修改日期", example = "Date")
    private Date updateTime;

}
