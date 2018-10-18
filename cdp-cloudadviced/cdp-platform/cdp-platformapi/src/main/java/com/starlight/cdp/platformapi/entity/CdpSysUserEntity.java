package com.starlight.cdp.platformapi.entity;

import com.starlight.cdp.util.DateTimeUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Description
 *
 * @author fosin
 */
@Entity
@DynamicUpdate
@Table(name = "cdp_sys_user")
@ApiModel(value = "用户表实体类", description = "表cdp_sys_user的对应的实体类")
public class CdpSysUserEntity implements Serializable {
    private Integer id;
    private String usercode;
    private String username;
    private String password;
    private Date birthday;
    private Integer sex;
    private String email;
    private String phone;
    private Integer status;
    private Date createTime;
    private Integer createBy;
    private Date updateTime;
    private Integer updateBy;
    private Integer organizId;
    private String avatar;
    private Date expireTime;

    private List<CdpSysUserRoleEntity> userRoles;

    /**
     * orphanRemoval=true配置表明删除无关联的数据。级联更新子结果集时此配置最关键
     */
    @OneToMany(orphanRemoval = true, cascade = {CascadeType.REFRESH, CascadeType.REMOVE})
    @JoinColumn(name = "user_id")
    public List<CdpSysUserRoleEntity> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<CdpSysUserRoleEntity> userRoles) {
        this.userRoles = userRoles;
    }

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
    @Column(name = "usercode")
    @NotNull
    @Size(min = 1, max = 45)
    @Pattern(regexp = "[A-Za-z0-9_]{1,45}")
    @ApiModelProperty(value = "用户帐号", notes = "登录系统时使用的帐号",example = "admin")
    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    @Basic
    @Column(name = "username")
    @Size(min = 1, max = 45)
    @ApiModelProperty(value = "用户名称",example = "管理员")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    @ApiModelProperty(value = "用户密码", notes = "传入原始密码，后台会对原始密码进行加密后再存储")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "birthday")
    @Past
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "出生年月", notes = "格式：" + DateTimeUtil.DATETIME_PATTERN)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "sex")
    @ApiModelProperty(value = "性别", notes = "取值于字典明细表CdpSysDictionaryDetailEntity.code = 15")
    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "email")
    @Email
    @ApiModelProperty(value = "电子邮箱地址")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "phone")
    @ApiModelProperty(value = "手机号码")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "status")
    @NotNull
    @Range(max = 10)
    @ApiModelProperty(value = "状态", notes = "0：正常 1：禁用 2：锁定")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
    @Column(name = "update_time")
    @ApiModelProperty(value = "更新时间", notes = "该值由后台维护，更改数据时前端不需要关心")
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
    @Column(name = "avatar")
    @ApiModelProperty(value = "头像地址")
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Basic
    @Column(name = "expire_time")
    @NotNull
    @ApiModelProperty(value = "帐号过期时间")
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date exprireTime) {
        this.expireTime = exprireTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CdpSysUserEntity that = (CdpSysUserEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(createBy, that.createBy) &&
                Objects.equals(updateBy, that.updateBy) &&
                Objects.equals(usercode, that.usercode) &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                Objects.equals(birthday, that.birthday) &&
                Objects.equals(sex, that.sex) &&
                Objects.equals(email, that.email) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(status, that.status) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(updateTime, that.updateTime) &&
                Objects.equals(expireTime, that.expireTime) &&
                Objects.equals(avatar, that.avatar) &&
                Objects.equals(organizId, that.organizId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createBy, updateBy, usercode, username, password, birthday, sex, email, phone, status, avatar, createTime, expireTime, updateTime, organizId);
    }
}
