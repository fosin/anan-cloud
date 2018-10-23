package com.github.fosin.cdp.platformapi.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * Description:
 *
 * @author fosin
 * @date 2018.7.30
 */
@Entity
@DynamicUpdate
@Table(name = "cdp_sys_dictionary")
@ApiModel(value = "字典实体类", description = "表cdp_sys_dictionary的对应的实体类")
public class CdpSysDictionaryEntity implements Serializable {
    private Integer code;
    private String name;
    private Integer type;
    private String scope;

    @Id
    @Column(name = "code")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "字典代码", notes = "主键，系统字典生成")
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Basic
    @Column(name = "name")
    @NotBlank
    @ApiModelProperty(value = "字典名称", notes = "字典名称")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "type")
    @NotNull
    @ApiModelProperty(value = "字典类别", notes = "区别字典的大分类，取值于表cdp_sys_dictionary.code = 1数据")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Basic
    @Column(name = "scope")
    @ApiModelProperty(value = "作用域", notes = "以字典类别为前提，在字典类别基础上再次细化分类字典")
    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CdpSysDictionaryEntity that = (CdpSysDictionaryEntity) o;
        return Objects.equals(code, that.code) &&
                Objects.equals(name, that.name) &&
                Objects.equals(type, that.type) &&
                Objects.equals(scope, that.scope);
    }

    @Override
    public int hashCode() {

        return Objects.hash(code, name, type, scope);
    }
}
