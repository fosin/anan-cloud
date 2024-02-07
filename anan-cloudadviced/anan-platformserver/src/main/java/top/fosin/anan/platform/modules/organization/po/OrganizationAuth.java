package top.fosin.anan.platform.modules.organization.po;


import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;
import top.fosin.anan.jpa.po.IdCreateOrganizPO;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * 系统机构授权表(AnanOrganizationAuth)实体类
 *
 * @author fosin
 * @date 2019-01-28 12:50:37
 * @since 1.0.0
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@DynamicUpdate
@Table(name = "anan_organization_auth")
@Schema(description = "机构授权的实体类")
public class OrganizationAuth extends IdCreateOrganizPO<Long> {
    private static final long serialVersionUID = -99392087741484947L;

    @Basic
    @Schema(description = "版本序号")
    @Column(name = "version_id", nullable = false)
    private Long versionId;

    @Basic
    @Schema(description = "订单序号")
    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @Basic
    @Schema(description = "授权码")
    @Column(name = "authorization_code", nullable = false, length = 256)
    private String authorizationCode;

    @Basic
    @Schema(description = "有效期：一般按天计算")
    @Column(name = "validity", nullable = false)
    private Integer validity;

    @Basic
    @Schema(description = "到期后保护期")
    @Column(name = "protect_days", nullable = false)
    private Integer protectDays;

    @Basic
    @Schema(description = "最大机构数：0=无限制 n=限制数")
    @Column(name = "max_organizs", nullable = false)
    private Integer maxOrganizs;

    @Basic
    @Schema(description = "最大机构数：0=无限制 n=限制数")
    @Column(name = "max_users", nullable = false)
    private Integer maxUsers;

    @Basic
    @Schema(description = "是否试用：0=不试用 1=试用")
    @Column(name = "tryout", nullable = false)
    private Integer tryout;

    @Basic
    @Schema(description = "试用天数")
    @Column(name = "tryout_days", nullable = false)
    private Integer tryoutDays;

}
