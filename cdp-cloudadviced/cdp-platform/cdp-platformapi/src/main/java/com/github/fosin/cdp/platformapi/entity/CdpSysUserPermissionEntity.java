package com.github.fosin.cdp.platformapi.entity;

import java.util.Date;

import com.github.fosin.cdp.jpa.entity.AbstractOrganizIdCreateJpaEntity;
import com.github.fosin.cdp.util.DateTimeUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 用于增减用户的单项权限，通常实在角色的基础上增减单项权限(CdpSysUserPermission)实体类
 *
 * @author fosin
 * @date 2018-10-27 09:38:39
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@DynamicUpdate
@Table(name = "cdp_sys_user_permission")
@ApiModel(value = "用于增减用户的单项权限，通常实在角色的基础上增减单项权限实体类", description = "表(cdp_sys_user_permission)的对应的实体类")
public class CdpSysUserPermissionEntity extends AbstractOrganizIdCreateJpaEntity implements Serializable {
    private static final long serialVersionUID = 200006140276012754L;

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "用户权限ID", notes = "主键，系统自动生成,用户权限ID")
    private Long id;

    @Column(name = "user_id")
    @Basic
    @NotNull
    @ApiModelProperty(value = "用户ID", notes = "用户ID")
    private Long userId;

    @Column(name = "permission_id")
    @Basic
    @NotNull
    @ApiModelProperty(value = "权限ID", notes = "权限ID")
    private Long permissionId;

    @Column(name = "add_mode")
    @Basic
    @NotNull
    @ApiModelProperty(value = "补充方式：0=增加权限、1=删除权限", notes = "补充方式：0=增加权限、1=删除权限")
    private Integer addMode;

}