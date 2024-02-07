package top.fosin.anan.platform.modules.organization.vo;

                                                                                                                                                                                                                            

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import java.util.Date;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.data.entity.Id;
/**
 * 系统机构授权表(anan_organization_auth)集合VO
 *
 * @author fosin
 * @date 2023-05-13
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "系统机构授权表(anan_organization_auth)集合VO")
public class OrganizationAuthListVO extends Id<Long> {
    private static final long serialVersionUID = 854341745264759566L;
    @Schema(description = "机构ID")
    private Long organizId;

    @Schema(description = "版本ID")
    private Long versionId;

    @Schema(description = "订单ID")
    private Long orderId;

    @Schema(description = "授权码")
    private String authorizationCode;

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

}
