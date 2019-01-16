package com.github.fosin.cdp.platform.entity;

import com.github.fosin.cdp.jpa.entity.AbstractOrganizIdCreateJpaEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 系统机构授权表(CdpSysOrganizationAuth)实体类
 *
 * @author fosin
 * @date 2018-11-18 17:28:24
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@DynamicUpdate
@Table(name = "cdp_sys_organization_auth")
@ApiModel(value = "系统机构授权表实体类", description = "表(cdp_sys_organization_auth)的对应的实体类")
public class CdpSysOrganizationAuthEntity extends AbstractOrganizIdCreateJpaEntity implements Serializable {
    private static final long serialVersionUID = -32523459261341634L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "主键，系统自动生成,机构授权ID")
    private Long id;

    @Basic
    @NotNull
    @Column(name = "version_id")
    @ApiModelProperty(value = "版本ID")
    private Long versionId;

    @Basic
    @NotNull
    @Column(name = "order_id")
    @ApiModelProperty(value = "订单ID")
    private Long orderId;

    @Basic
    @NotBlank
    @Column(name = "authorization_code")
    @ApiModelProperty(value = "授权码")
    private String authorizationCode;

    @Basic
    @NotNull
    @Column(name = "validity")
    @ApiModelProperty(value = "有效期：一般按天计算")
    private Integer validity;

    @Basic
    @NotNull
    @Column(name = "protect_days")
    @ApiModelProperty(value = "到期后保护期")
    private Integer protectDays;

    @Basic
    @NotNull
    @Column(name = "max_organizs")
    @ApiModelProperty(value = "最大机构数：0=无限制 n=限制数")
    private Integer maxOrganizs;

    @Basic
    @NotNull
    @Column(name = "max_users")
    @ApiModelProperty(value = "最大机构数：0=无限制 n=限制数")
    private Integer maxUsers;

    @Basic
    @NotNull
    @Column(name = "tryout")
    @ApiModelProperty(value = "是否试用：0=不试用 1=试用")
    private Integer tryout;

    @Basic
    @NotNull
    @Column(name = "tryout_days")
    @ApiModelProperty(value = "试用天数")
    private Integer tryoutDays;


}