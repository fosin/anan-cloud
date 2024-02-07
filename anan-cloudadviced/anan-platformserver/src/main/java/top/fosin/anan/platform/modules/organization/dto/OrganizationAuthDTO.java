package top.fosin.anan.platform.modules.organization.dto;



import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.data.entity.Id;

import java.util.Date;
/**
 * 系统机构授权表(anan_organization_auth)DTO
 *
 * @author fosin
 * @date 2023-05-13
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "系统机构授权表(anan_organization_auth)DTO")
public class OrganizationAuthDTO extends Id<Long> {
    private static final long serialVersionUID = 439900534313942300L;

    @Schema(description = "机构ID", example = "Long")
    private Long organizId;

    @Schema(description = "版本ID", example = "Long")
    private Long versionId;

    @Schema(description = "订单ID", example = "Long")
    private Long orderId;

    @Schema(description = "授权码", example = "String")
    private String authorizationCode;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Schema(description = "创建日期", example = "Date")
    private Date createTime;

    @Schema(description = "创建人", example = "Long")
    private Long createBy;

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

}
