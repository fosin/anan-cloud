package com.github.fosin.cdp.platformapi.entity;

import com.github.fosin.cdp.util.DateTimeUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Description:
 *
 * @author fosin
 * @date 2018.7.29
 */
@Entity
@DynamicUpdate
@Table(name = "cdp_sys_parameter")
@ApiModel(value = "通用参数实体类", description = "表cdp_sys_parameter的对应的实体类")
public class CdpSysParameterEntity implements Serializable {
    private Integer id;
    private String name;
    private String value;
    private Integer type;
    private String scope;
    private String defaultValue;
    private String description;
    private Date createTime;
    private Integer createBy;
    private Date updateTime;
    private Integer updateBy;
    private Date applyTime;
    private Integer applyBy;
    private Integer status;

    @Id
    @Column(name = "id")
    @ApiModelProperty(value = "主键ID", notes = "系统自动生成")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    @NotBlank
    @ApiModelProperty(value = "参数名称")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "value")
    @ApiModelProperty(value = "参数值")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Basic
    @Column(name = "type")
    @NotNull
    @ApiModelProperty(value = "参数类型", notes = "取值于cdp_sys_dictionary_detail.code = 10")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Basic
    @Column(name = "scope")
    @ApiModelProperty(value = "作用域")
    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    @Basic
    @Column(name = "default_value")
    @ApiModelProperty(value = "默认值")
    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Basic
    @Column(name = "description")
    @ApiModelProperty(value = "参数详细描述")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Basic
    @Column(name = "apply_time")
    @ApiModelProperty(value = "发布时间", notes = "该值由后台维护，更改数据时前端不需要关心")
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    @Basic
    @Column(name = "apply_by")
    @ApiModelProperty(value = "发包人", notes = "该该值由后台维护，更改数据时前端不需要关心，取值于CdpSysUserEntity.id")
    public Integer getApplyBy() {
        return applyBy;
    }

    public void setApplyBy(Integer applyBy) {
        this.applyBy = applyBy;
    }

    @Basic
    @Column(name = "status")
    @Range(max = 2)
    @NotNull
    @ApiModelProperty(value = "状态", notes = "0：正常状态 1：修改状态 2：删除状态")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CdpSysParameterEntity that = (CdpSysParameterEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(value, that.value) &&
                Objects.equals(type, that.type) &&
                Objects.equals(scope, that.scope) &&
                Objects.equals(defaultValue, that.defaultValue) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(createBy, that.createBy) &&
                Objects.equals(updateTime, that.updateTime) &&
                Objects.equals(updateBy, that.updateBy) &&
                Objects.equals(applyTime, that.applyTime) &&
                Objects.equals(applyBy, that.applyBy) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, value, type, scope, defaultValue, createTime, createBy, updateTime, updateBy, applyTime, applyBy, status);
    }
}
