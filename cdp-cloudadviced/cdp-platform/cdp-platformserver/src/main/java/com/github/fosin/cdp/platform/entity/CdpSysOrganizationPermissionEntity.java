package com.github.fosin.cdp.platform.entity;

import com.github.fosin.cdp.jpa.entity.AbstractOrganizIdCreateJpaEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 系统机构权限表(CdpSysOrganizationPermission)实体类
 *
 * @author fosin
 * @date 2018-11-18 17:28:24
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@DynamicUpdate
@Table(name = "cdp_sys_organization_permission")
@ApiModel(value = "系统机构权限表实体类", description = "表(cdp_sys_organization_permission)的对应的实体类")
public class CdpSysOrganizationPermissionEntity extends AbstractOrganizIdCreateJpaEntity implements Serializable {
    private static final long serialVersionUID = -17486664522779760L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "主键，系统自动生成,机构权限ID")
    private Long id;

    @Basic
    @NotNull
    @Column(name = "permission_id")
    @ApiModelProperty(value = "权限ID")
    private Long permissionId;

}