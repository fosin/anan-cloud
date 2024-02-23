package top.fosin.anan.platform.modules.version.vo;


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
 * 系统版本表(anan_version)集合VO
 *
 * @author fosin
 * @date 2023-05-13
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "系统版本表(anan_version)集合VO")
public class VersionListVO extends Id<Long> {
    private static final long serialVersionUID = 758004944032911574L;
    @Schema(description = "版本名称")
    private String name;

    @Schema(description = "版本类型：0=收费版 1=免费版 2=开发版")
    private Byte type;

    @Schema(description = "版本价格")
    private BigDecimal price;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Schema(description = "活动开始日期")
    private Date beginTime;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Schema(description = "活动结束日期")
    private Date endTime;

    @Schema(description = "有效期：一般按天计算")
    private Integer validity;

    @Schema(description = "到期后保护期")
    private Integer protectDays;

    @Schema(description = "最大机构数：0=无限制 n=限制数")
    private Integer maxOrganizs;

    @Schema(description = "最大机构数：0=无限制 n=限制数")
    private Integer maxUsers;

    @Schema(description = "是否试用：0=不试用 1=试用")
    private Integer tryout;

    @Schema(description = "试用天数")
    private Integer tryoutDays;

    @Schema(description = "启用状态：0=启用，1=禁用")
    private Byte status;

    @Schema(description = "版本描述")
    private String description;

}
