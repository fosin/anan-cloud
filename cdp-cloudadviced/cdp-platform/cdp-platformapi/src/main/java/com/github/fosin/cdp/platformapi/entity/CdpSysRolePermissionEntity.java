package com.github.fosin.cdp.platformapi.entity;

import java.util.Date;

import com.github.fosin.cdp.util.DateTimeUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 系统角色权限表(CdpSysRolePermission)实体类
 *
 * @author fosin
 * @date 2018-10-27 09:38:39
 * @since 1.0.0
 */
@Data
@Entity
@DynamicUpdate
@Table(name = "cdp_sys_role_permission")
@ApiModel(value = "系统角色权限表实体类", description = "表(cdp_sys_role_permission)的对应的实体类")
public class CdpSysRolePermissionEntity implements Serializable {
    private static final long serialVersionUID = 264920578551301090L;
    
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "角色权限ID", notes = "主键，系统自动生成,角色权限ID")
    private Long id;
    
    @Column(name = "role_id")
    @Basic
    @ApiModelProperty(value = "角色ID", notes = "角色ID")
    private Long roleId;
    
    @Column(name = "permission_id")
    @Basic
    @NotNull
    @ApiModelProperty(value = "权限ID", notes = "权限ID")
    private Long permissionId;
    
    @Column(name = "create_time")
    @Basic
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "创建时间", notes = "创建时间")
    private Date createTime;
    
    @Column(name = "create_by")
    @Basic
    @ApiModelProperty(value = "创建人，该值由后台维护，更改数据时前端不需要关心，取值于cdp_sys_user.id", notes = "创建人，该值由后台维护，更改数据时前端不需要关心，取值于cdp_sys_user.id")
    private Long createBy;
    
}