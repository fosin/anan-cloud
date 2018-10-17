package com.starlight.cdp.platformapi.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Range;

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
@DynamicUpdate
@Table(name = "cdp_sys_role")
@ApiModel(value = "角色表实体类", description = "表cdp_sys_role的对应的实体类")
public class CdpSysRoleEntity implements Serializable {
    private Integer id;
    private String name;
    private String value;
    private String tips;
    private Date createTime;
    private Date updateTime;
    private Integer status;
    private Integer createBy;
    private Integer updateBy;

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
    @Column(name = "name")
    @NotNull
    @ApiModelProperty(value = "角色名称",example = "管理员")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "value")
    @NotNull
    @ApiModelProperty(value = "角色标识",example = "admin")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Basic
    @Column(name = "tips")
    @ApiModelProperty(value = "角色说明")
    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    @Basic
    @Column(name = "create_time")
    @ApiModelProperty(value = "创建时间", notes = "该值由后台维护，更改数据时前端不需要关心")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "update_time")
    @ApiModelProperty(value = "更新时间", notes = "该值由后台维护，更改数据时前端不需要关心")
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Basic
    @Column(name = "status")
    @NotNull
    @Range(max = 1)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
    @Column(name = "update_by")
    @ApiModelProperty(value = "更新人", notes = "该值由后台维护，更改数据时前端不需要关心，取值于CdpSysUserEntity.id")
    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CdpSysRoleEntity that = (CdpSysRoleEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(value, that.value) &&
                Objects.equals(tips, that.tips) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(updateTime, that.updateTime) &&
                Objects.equals(status, that.status) &&
                Objects.equals(createBy, that.createBy) &&
                Objects.equals(updateBy, that.updateBy);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, value, tips, createTime, updateTime, status, createBy, updateBy);
    }

    @Override
    public String toString() {
        return "CdpSysRoleEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", tips='" + tips + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", status=" + status +
                ", createBy=" + createBy +
                ", updateBy=" + updateBy +
                '}';
    }
}
