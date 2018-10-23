package com.github.fosin.cdp.platformapi.entity;

import com.github.fosin.cdp.util.DateTimeUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;
import springfox.documentation.annotations.ApiIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
@Table(name = "cdp_sys_permission")
@ApiModel(value = "权限实体类", description = "表cdp_sys_permission的对应的实体类")
public class CdpSysPermissionEntity implements Serializable {
    private Integer id;
    private String code;
    private Integer pId;
    private String name;
    private String url;
    private String appName;
    private String path;
    private String method;
    private String icon;
    private Integer type;
    private Integer level;
    private Integer sort;
    private Integer status;
    private Date createTime;
    private Integer createBy;
    private Date updateTime;
    private Integer updateBy;

    private List<CdpSysPermissionEntity> children;
    private Boolean leaf;

    @Transient
    @ApiIgnore
    public List<CdpSysPermissionEntity> getChildren() {
        return children;
    }

    public void setChildren(List<CdpSysPermissionEntity> children) {
        this.children = children;
    }

    @Transient
    @ApiIgnore
    public Boolean getLeaf() {
        return leaf;
    }

    public void setLeaf(Boolean leaf) {
        this.leaf = leaf;
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
    @Column(name = "code")
    @NotBlank
    @Pattern(regexp = "[A-Za-z0-9_]{1,64}")
    @ApiModelProperty(value = "权限编码")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "p_id")
    @NotNull
    @ApiModelProperty(value = "父权限ID", notes = "取值于id，表示当前数据的父类权限")
    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    @Basic
    @Column(name = "name")
    @NotBlank
    @ApiModelProperty(value = "权限名称")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "url")
    @ApiModelProperty(value = "资源路径")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "app_name")
    @NotBlank
    @ApiModelProperty(value = "所属应用")
    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    @Basic
    @Column(name = "path")
    @ApiModelProperty(value = "权限请求路径")
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Basic
    @Column(name = "method")
    @ApiModelProperty(value = "权限请求方法")
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Basic
    @Column(name = "type")
    @NotNull
    @ApiModelProperty(value = "权限类别",notes = "取值于字典明细表CdpSysDictionaryDetailEntity.code = 13")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
        //当权限类型是1：组件菜单 3：目录菜单时表示该节点不是一个叶子节点
        this.leaf = this.type != 1 && this.type != 3;
    }

    @Basic
    @Column(name = "level")
    @NotNull
    @ApiModelProperty(value = "权限层级")
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Basic
    @Column(name = "sort")
    @ApiModelProperty(value = "权限排序",notes = "用于显示数据时的顺序，数值越小越靠前")
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Basic
    @Column(name = "icon")
    @ApiModelProperty(value = "权限图标",notes = "用于显示数据时的顺序，数值越小越靠前")
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Basic
    @Column(name = "status")
    @NotNull
    @Range(max = 1)
    @ApiModelProperty(value = "状态", notes = "取值于字典明细表CdpSysDictionaryDetailEntity.code = 11")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    @Basic
    @Column(name = "update_time")
    @ApiModelProperty(value = "更新时间",notes = "该值由后台维护，更改数据时前端不需要关心")
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Basic
    @Column(name = "update_by")
    @ApiModelProperty(value = "更新人",notes = "该值由后台维护，更改数据时前端不需要关心，取值于CdpSysUserEntity.id")
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
        CdpSysPermissionEntity that = (CdpSysPermissionEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(code, that.code) &&
                Objects.equals(pId, that.pId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(url, that.url) &&
                Objects.equals(appName, that.appName) &&
                Objects.equals(path, that.path) &&
                Objects.equals(method, that.method) &&
                Objects.equals(type, that.type) &&
                Objects.equals(level, that.level) &&
                Objects.equals(sort, that.sort) &&
                Objects.equals(icon, that.icon) &&
                Objects.equals(status, that.status) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(createBy, that.createBy) &&
                Objects.equals(updateTime, that.updateTime) &&
                Objects.equals(updateBy, that.updateBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, pId, name, url, appName, path, method, type, level, sort, icon, status, createTime, createBy, updateTime, updateBy);
    }
}
