package com.github.fosin.cdp.platformapi.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
/**
 * 系统通用字典表(CdpSysDictionary)实体类
 *
 * @author fosin
 * @date 2019-01-27 18:42:43
 * @since 1.0.0
 */
@Data
@Entity
@DynamicUpdate
@Table(name = "cdp_sys_dictionary")
@ApiModel(value = "系统通用字典表实体类", description = "表(cdp_sys_dictionary)的对应的实体类")
public class CdpSysDictionaryEntity  implements Serializable {
    private static final long serialVersionUID = -48637204028516104L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "字典代码, 主键，一般系统自动生成")
    @Column(name = "code", nullable = false)
    private Long code;

    @Basic
    @ApiModelProperty(value = "字典名称", required = true)
    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @Basic
    @ApiModelProperty(value = "字典类别，区别字典的大分类，取值于表cdp_sys_dictionary.code = 1数据", required = true)
    @Column(name = "type", nullable = false)
    private Integer type;

    @Basic
    @ApiModelProperty(value = "字典作用域，以字典类别为前提，在字典类别基础上再次细化分类字典")
    @Column(name = "scope", length = 64)
    private String scope;

}