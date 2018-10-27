package com.github.fosin.cdp.platformapi.entity;

import java.util.Date;

import com.github.fosin.cdp.util.DateTimeUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 包含菜单、按钮两种权限(CdpSysPermission)实体类
 *
 * @author fosin
 * @date 2018-10-27 09:38:39
 * @since 1.0.0
 */
@Data
@Entity
@DynamicUpdate
@Table(name = "cdp_sys_permission")
@ApiModel(value = "包含菜单、按钮两种权限实体类", description = "表(cdp_sys_permission)的对应的实体类")
public class CdpSysPermissionEntity implements Serializable {
    private static final long serialVersionUID = -77218975959330473L;

    @Transient
    @ApiModelProperty(value = "孩子节点，虚拟字段，增删改时不需要关心", notes = "孩子节点，虚拟字段，增删改时不需要关心")
    private List<CdpSysPermissionEntity> children;

    @Transient
    @ApiModelProperty(value = "是否叶子节点，虚拟字段，增删改时不需要关心", notes = "是否叶子节点，虚拟字段，增删改时不需要关心")
    private Boolean leaf;

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "权限ID", notes = "主键，系统自动生成,权限ID")
    private Long id;
    
    @Column(name = "code")
    @Basic
    @NotBlank
    @ApiModelProperty(value = "权限编码，不能重复 不能为空", notes = "权限编码，不能重复 不能为空")
    private String code;
    
    @Column(name = "p_id")
    @Basic
    @NotNull
    @ApiModelProperty(value = "父权限ID，取值于id，表示当前数据的父类权限", notes = "父权限ID，取值于id，表示当前数据的父类权限")
    private Long pId;
    
    @Column(name = "name")
    @Basic
    @NotBlank
    @ApiModelProperty(value = "权限名称", notes = "权限名称")
    private String name;
    
    @Column(name = "url")
    @Basic
    @ApiModelProperty(value = "该字段必须和type字段共同使用，详情请看type字段", notes = "该字段必须和type字段共同使用，详情请看type字段")
    private String url;
    
    @Column(name = "type")
    @Basic
    @NotNull
    @ApiModelProperty(value = "权限类型：0=按钮、1=组件菜单，对应ur是前端组件l、2=链接菜单，对应url是http(s)链接地址、3=目录菜单，对应是目录菜单，具体取值于字典表cdp_sys_dictionary.code=13，当权限类型是1：组件菜单 3：目录菜单时表示该节点不是一个叶子节点", notes = "权限类型：0=按钮、1=组件菜单，对应ur是前端组件l、2=链接菜单，对应url是http(s)链接地址、3=目录菜单，对应是目录菜单，具体取值于字典表cdp_sys_dictionary.code=13，当权限类型是1：组件菜单 3：目录菜单时表示该节点不是一个叶子节点")
    private Integer type;
    
    @Column(name = "level")
    @Basic
    @NotNull
    @ApiModelProperty(value = "菜单层级", notes = "菜单层级")
    private Integer level;
    
    @Column(name = "sort")
    @Basic
    @ApiModelProperty(value = "排序，用于显示数据时的顺序，数值越小越靠前", notes = "排序，用于显示数据时的顺序，数值越小越靠前")
    private Integer sort;
    
    @Column(name = "status")
    @Basic
    @NotNull
    @Range(max = 1)
    @ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表cdp_sys_dictionary.code=11", notes = "使用状态：0=启用，1=禁用，具体取值于字典表cdp_sys_dictionary.code=11")
    private Integer status;
    
    @Column(name = "app_name")
    @Basic
    @NotBlank
    @ApiModelProperty(value = "所属应用名称,等同于配置文件中的spring.application.name", notes = "所属应用名称,等同于配置文件中的spring.application.name")
    private String appName;
    
    @Column(name = "path")
    @Basic
    @ApiModelProperty(value = "后台请求权限地址，权限路径ant风格表达式，用于动态验证HTTP后台请求的权限标识", notes = "后台请求权限地址，权限路径ant风格表达式，用于动态验证HTTP后台请求的权限标识")
    private String path;
    
    @Column(name = "method")
    @Basic
    @ApiModelProperty(value = "http请求方法：GET、POST、DELETE、OPTIONS、PUT、PATCH，具体取值于字典表cdp_sys_dictionary.code=12 ", notes = "http请求方法：GET、POST、DELETE、OPTIONS、PUT、PATCH，具体取值于字典表cdp_sys_dictionary.code=12")
    private String method;
    
    @Column(name = "icon")
    @Basic
    @ApiModelProperty(value = "一般用于前端菜单选项前的图标", notes = "一般用于前端菜单选项前的图标")
    private String icon;
    
    @Column(name = "create_time")
    @Basic
    @NotNull
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "创建日期，该值由后台维护，更改数据时前端不需要关心", notes = "创建日期，该值由后台维护，更改数据时前端不需要关心")
    private Date createTime;
    
    @Column(name = "create_by")
    @Basic
    @NotNull
    @ApiModelProperty(value = "该值由后台维护，更改数据时前端不需要关心，取值于cdp_sys_user.id", notes = "该值由后台维护，更改数据时前端不需要关心，取值于cdp_sys_user.id")
    private Long createBy;
    
    @Column(name = "update_time")
    @Basic
    @NotNull
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "更新日期，该值由后台维护，更改数据时前端不需要关心", notes = "更新日期，该值由后台维护，更改数据时前端不需要关心")
    private Date updateTime;
    
    @Column(name = "update_by")
    @Basic
    @NotNull
    @ApiModelProperty(value = "该值由后台维护，更改数据时前端不需要关心，取值于cdp_sys_user.id", notes = "该值由后台维护，更改数据时前端不需要关心，取值于cdp_sys_user.id")
    private Long updateBy;
    
}