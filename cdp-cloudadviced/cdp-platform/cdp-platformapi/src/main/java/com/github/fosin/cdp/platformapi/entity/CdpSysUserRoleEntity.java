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
@Table(name = "cdp_sys_user_role")
@ApiModel(value = "用户角色表实体类", description = "表cdp_sys_user_role的对应的实体类")
public class CdpSysUserRoleEntity implements Serializable {
    private Integer id;
    private Integer organizId;
    private Integer userId;
    private Date createTime;
    private Integer createBy;
    private CdpSysRoleEntity role;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "role_id")
    @ApiModelProperty(value = "角色ID", notes = "取值于角色表cdp_sys_role.id")
    public CdpSysRoleEntity getRole() {
        return role;
    }

    public void setRole(CdpSysRoleEntity role) {
        this.role = role;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    /**
     * 由于该表与cdp_sys_user表是级联关系，当cdp_sys_user修改修改角色信息时
     * 如果要删除该表数据，则user_id必须为可以为空，因此数据库字段user_id和当前实体类都不能设置为非空
     * 也就是不能使用@NotNull字段了
     */
    @Basic
    @Column(name = "user_id")
    @ApiModelProperty(value = "用户ID", notes = "取值于用户表cdp_sys_user.id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CdpSysUserRoleEntity that = (CdpSysUserRoleEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(organizId, that.organizId) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(createBy, that.createBy) &&
                Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, organizId, userId, createTime, createBy, role);
    }

    @Override
    public String toString() {
        return "CdpSysUserRoleEntity{" +
                "id=" + id +
                ", organizId=" + organizId +
                ", userId=" + userId +
                ", createTime=" + createTime +
                ", createBy=" + createBy +
                ", roleEntity=" + role.toString() +
                '}';
    }
}
