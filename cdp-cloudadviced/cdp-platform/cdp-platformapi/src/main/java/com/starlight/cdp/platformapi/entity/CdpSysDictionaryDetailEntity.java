package com.starlight.cdp.platformapi.entity;

import com.starlight.cdp.util.DateTimeUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.DynamicUpdate;
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
 * @date 2018.7.30
 */
@Entity
@DynamicUpdate
@Table(name = "cdp_sys_dictionary_detail")
@ApiModel(value = "字典明细实体类", description = "表cdp_sys_dictionary_detail的对应的实体类")
public class CdpSysDictionaryDetailEntity implements Serializable {
    private Integer id;
    private Integer name;
    private String value;
    private Integer code;
    private Integer sort;
    private Integer status;
    private String scode;
    private String scope;
    private Integer createBy;
    private Date createTime;
    private Integer updateBy;
    private Date updateTime;

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
    @Column(name = "name")
    @ApiModelProperty(value = "字典明细键", notes = "不能重复，字典内明细项唯一代码")
    public Integer getName() {
        return name;
    }

    public void setName(Integer name) {
        this.name = name;
    }

    @Basic
    @Column(name = "value")
    @ApiModelProperty(value = "字典明细值", notes = "字典明细值表示字面意义", example = "例如性别的男、女、不明确")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Basic
    @Column(name = "code")
    @NotNull
    @ApiModelProperty(value = "字典代码", notes = "取值于字典明细表CdpSysDictionaryDetailEntity.code")
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Basic
    @Column(name = "sort")
    @NotNull
    @ApiModelProperty(value = "排列顺序", notes = "用于显示数据时的顺序，数值越小越靠前")
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Basic
    @Column(name = "status")
    @NotNull
    @ApiModelProperty(value = "状态", notes = "取值于字典明细表CdpSysDictionaryDetailEntity.code = 11")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "scode")
    @ApiModelProperty(value = "标准代码", notes = "该字段通常用于对接标准字典")
    public String getScode() {
        return scode;
    }

    public void setScode(String scode) {
        this.scode = scode;
    }

    @Basic
    @Column(name = "scope")
    @ApiModelProperty(value = "作用域", notes = "用于字典明细项的作用域")
    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
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
    @Column(name = "update_by")
    @ApiModelProperty(value = "更新人",notes = "该值由后台维护，更改数据时前端不需要关心，取值于CdpSysUserEntity.id")
    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CdpSysDictionaryDetailEntity that = (CdpSysDictionaryDetailEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(value, that.value) &&
                Objects.equals(code, that.code) &&
                Objects.equals(sort, that.sort) &&
                Objects.equals(status, that.status) &&
                Objects.equals(scode, that.scode) &&
                Objects.equals(createBy, that.createBy) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(updateBy, that.updateBy) &&
                Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, value, code, sort, status, scode, createBy, createTime, updateBy, updateTime);
    }
}
