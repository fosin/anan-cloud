package com.starlight.cdp.platformapi.entity;

import com.starlight.cdp.util.DateTimeUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Range;
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
@Table(name = "cdp_sys_user_permission")
@ApiModel(value = "用户权限表实体类", description = "表cdp_sys_user_permission的对应的实体类")
public class CdpSysUserPermissionEntity implements Serializable {
    private Integer id;
    private Integer organizId;
    private Integer userId;
    private Integer permissionId;
    private Date createTime;
    private Integer createBy;
    private Integer addMode;


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
    @Column(name = "organiz_id")
    @NotNull
    @ApiModelProperty(value = "机构ID", notes = "取值于机构表CdpSysOrganizationEntity.id")
    public Integer getOrganizId() {
        return organizId;
    }

    public void setOrganizId(Integer organizId) {
        this.organizId = organizId;
    }

    @Basic
    @Column(name = "user_id")
    @NotNull
    @ApiModelProperty(value = "用户ID", notes = "取值于用户表cdp_sys_user.id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "创建时间", notes = "该值由后台维护，更改数据时前端不需要关心")
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

    @Basic
    @Column(name = "add_mode")
    @NotNull
    @Range(max = 1)
    @ApiModelProperty(value = "附加模式", notes = "1：增权限 2：减权限")
    public Integer getAddMode() {
        return addMode;
    }

    public void setAddMode(Integer addMode) {
        this.addMode = addMode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CdpSysUserPermissionEntity that = (CdpSysUserPermissionEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(organizId, that.organizId) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(permissionId, that.permissionId) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(createBy, that.createBy) &&
                Objects.equals(addMode, that.addMode);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, organizId, userId, permissionId, createTime, createBy, addMode);
    }
}
