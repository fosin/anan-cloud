package top.fosin.anan.platform.modules.organization.query;


import io.swagger.v3.oas.annotations.media.Schema;

import org.hibernate.annotations.DynamicUpdate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
 * 系统机构授权表(anan_organization_auth)通用查询DTO
 *
 * @author fosin
 * @date 2023-05-13
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "系统机构授权表(anan_organization_auth)通用查询DTO")
public class OrganizationAuthQuery extends LogiSortQuery<LogiQueryRule,SortRule,Long> {
    private static final long serialVersionUID = -74357556683159483L;
    
    @Schema(description = "机构ID", example = "Long")
    private Long organizId;

    @Schema(description = "版本ID", example = "Long")
    private Long versionId;

    @Schema(description = "订单ID", example = "Long")
    private Long orderId;

    @Schema(description = "授权码", example = "String")
    private String authorizationCode;

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
