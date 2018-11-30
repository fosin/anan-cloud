package com.github.fosin.cdp.platformapi.entity;

import com.github.fosin.cdp.mvc.constant.RegExpConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import lombok.Data;

/**
 * 通用系统字典表(CdpSysDictionary)实体类
 *
 * @author fosin
 * @date 2018-10-27 09:38:39
 * @since 1.0.0
 */
@Data
@Entity
@DynamicUpdate
@Table(name = "cdp_sys_dictionary")
@ApiModel(value = "通用系统字典表实体类", description = "表(cdp_sys_dictionary)的对应的实体类")
public class CdpSysDictionaryEntity implements Serializable {
    private static final long serialVersionUID = -58811180751914818L;
    
    @Column(name = "code")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "字典代码", notes = "主键，系统自动生成,字典代码")
    private Long code;
    
    @Column(name = "name")
    @Basic
    @NotBlank
    @ApiModelProperty(value = "字典名称", notes = "字典名称")
    @Pattern(regexp = RegExpConstant.SPECIAL, message = "名称不能包含特殊字符")
    private String name;
    
    @Column(name = "type")
    @Basic
    @NotNull
    @ApiModelProperty(value = "字典类别，区别字典的大分类，取值于表cdp_sys_dictionary.code = 1数据", notes = "字典类别，区别字典的大分类，取值于表cdp_sys_dictionary.code = 1数据")
    private Integer type;
    
    @Column(name = "scope")
    @Basic
    @Pattern(regexp = RegExpConstant.SPECIAL, message = "作用域不能包含特殊字符")
    @ApiModelProperty(value = "字典作用域，以字典类别为前提，在字典类别基础上再次细化分类字典", notes = "字典作用域，以字典类别为前提，在字典类别基础上再次细化分类字典")
    private String scope;
    
}