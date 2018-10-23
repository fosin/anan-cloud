package com.github.fosin.cdp.platformapi.entity;

import com.github.fosin.cdp.util.DateTimeUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Description
 *
 * @author fosin
 */
@Entity
@Table(name = "cdp_sys_role_permission")
@ApiModel(value = "角色权限实体类", description = "表cdp_sys_role_permission的对应的实体类")
public class CdpSysRolePermissionEntity implements Serializable {
    private Integer id;
    private Integer roleId;
    private Integer permissionId;
    private Date createTime;
    private Integer createBy;


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "主键ID", notes = "系统自动生成")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "role_id")
    @NotNull
    @ApiModelProperty(value = "角色ID", notes = "取值于角色表cdp_sys_role.id")
    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "permission_id")
    @NotNull
    @ApiModelProperty(value = "权限ID", notes = "取值于权限表cdp_sys_permission.id")
    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    @Basic
    @Column(name = "create_time")
    @ApiModelProperty(value = "创建时间", notes = "该值由后台维护，更改数据时前端不需要关心")
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "create_by")
    @ApiModelProperty(value = "创建人", notes = "该值由后台维护，更改数据时前端不需要关心，取值于CdpSysUserEntity.id")
    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CdpSysRolePermissionEntity that = (CdpSysRolePermissionEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(roleId, that.roleId) &&
                Objects.equals(permissionId, that.permissionId) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(createBy, that.createBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleId, permissionId, createTime, createBy);
    }
}
