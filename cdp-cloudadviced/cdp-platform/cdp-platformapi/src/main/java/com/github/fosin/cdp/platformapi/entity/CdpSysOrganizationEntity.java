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
@Table(name = "cdp_sys_organization")
@ApiModel(value = "机构实体类", description = "表cdp_sys_organization的对应的实体类")
public class CdpSysOrganizationEntity implements Serializable {
    private Integer id;
    private Integer pId;
    private String code;
    private String name;
    private Integer level;
    private String fullname;
    private String address;
    private String telphone;
    private Integer status;
    private Date createTime;
    private Integer createBy;
    private Date updateTime;
    private Integer updateBy;


    private List<CdpSysOrganizationEntity> children;

    @Transient
    @ApiIgnore
    public List<CdpSysOrganizationEntity> getChildren() {
        return children;
    }

    public void setChildren(List<CdpSysOrganizationEntity> children) {
        this.children = children;
    }


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "ID主键", notes = "系统自动生成")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "p_id")
    @NotNull
    @ApiModelProperty(value = "父机构ID", notes = "取值于id，表示当前数据所属的父类机构")
    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    @Basic
    @Column(name = "code")
    @NotBlank
    @ApiModelProperty(value = "机构编码", notes = "自定义机构编码，下级机构必须以上级机构编码为前缀")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "name")
    @NotNull
    @ApiModelProperty(value = "机构名称", notes = "机构名称")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "level")
    @NotNull
    @ApiModelProperty(value = "机构层级", notes = "机构层级")
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Basic
    @Column(name = "fullname")
    @NotNull
    @ApiModelProperty(value = "机构全面", notes = "机构全名")
    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Basic
    @Column(name = "address")
    @ApiModelProperty(value = "机构层级", notes = "机构层级")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "telphone")
    @ApiModelProperty(value = "机构层级", notes = "机构层级")
    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    @Basic
    @Column(name = "status")
    @NotNull
    @Range(max = 1)
    @ApiModelProperty(value = "状态", notes = "0：有效 1：无效")
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
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "更新时间",notes = "该值由后台维护，更改数据时前端不需要关心")
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
        CdpSysOrganizationEntity that = (CdpSysOrganizationEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(pId, that.pId) &&
                Objects.equals(code, that.code) &&
                Objects.equals(name, that.name) &&
                Objects.equals(level, that.level) &&
                Objects.equals(fullname, that.fullname) &&
                Objects.equals(address, that.address) &&
                Objects.equals(telphone, that.telphone) &&
                Objects.equals(status, that.status) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(createBy, that.createBy) &&
                Objects.equals(updateTime, that.updateTime) &&
                Objects.equals(updateBy, that.updateBy);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, pId, code, name, level, fullname, address, telphone, status, createTime, createBy, updateTime, updateBy);
    }
}
