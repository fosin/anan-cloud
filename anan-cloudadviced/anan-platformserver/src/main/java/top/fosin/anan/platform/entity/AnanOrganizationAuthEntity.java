package top.fosin.anan.platform.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;
import top.fosin.anan.jpa.entity.AbstractOrganizIdCreateJpaEntity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

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
@ApiModel(value = "系统机构授权表实体类", description = "表(anan_organization_auth)的对应的实体类")
public class AnanOrganizationAuthEntity extends AbstractOrganizIdCreateJpaEntity<Long, Long> implements Serializable {
    private static final long serialVersionUID = -99392087741484947L;

    @Basic
    @ApiModelProperty(value = "版本ID", required = true)
    @Column(name = "version_id", nullable = false)
    private Long versionId;

    @Basic
    @ApiModelProperty(value = "订单ID", required = true)
    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @Basic
    @ApiModelProperty(value = "授权码", required = true)
    @Column(name = "authorization_code", nullable = false, length = 256)
    private String authorizationCode;

    @Basic
    @ApiModelProperty(value = "有效期：一般按天计算", required = true)
    @Column(name = "validity", nullable = false)
    private Integer validity;

    @Basic
    @ApiModelProperty(value = "到期后保护期", required = true)
    @Column(name = "protect_days", nullable = false)
    private Integer protectDays;

    @Basic
    @ApiModelProperty(value = "最大机构数：0=无限制 n=限制数", required = true)
    @Column(name = "max_organizs", nullable = false)
    private Integer maxOrganizs;

    @Basic
    @ApiModelProperty(value = "最大机构数：0=无限制 n=限制数", required = true)
    @Column(name = "max_users", nullable = false)
    private Integer maxUsers;

    @Basic
    @ApiModelProperty(value = "是否试用：0=不试用 1=试用", required = true)
    @Column(name = "tryout", nullable = false)
    private Integer tryout;

    @Basic
    @ApiModelProperty(value = "试用天数", required = true)
    @Column(name = "tryout_days", nullable = false)
    private Integer tryoutDays;

}
