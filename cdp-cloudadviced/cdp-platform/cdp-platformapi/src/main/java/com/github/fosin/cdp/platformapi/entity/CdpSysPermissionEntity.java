package com.github.fosin.cdp.platformapi.entity;

import com.github.fosin.cdp.util.DateTimeUtil;
import com.github.fosin.cdp.util.RegexUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
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

/**
 * 包含菜单、按钮两种权限(CdpSysPermission)实体类
 *
 * @author fosin
 * @date 2018-10-27 09:38:39
 * @since 1.0.0
 */
@Entity
@DynamicUpdate
@Table(name = "cdp_sys_permission")
@ApiModel(value = "包含菜单、按钮两种权限实体类", description = "表(cdp_sys_permission)的对应的实体类")
@EqualsAndHashCode
public class CdpSysPermissionEntity implements Serializable {
    private static final long serialVersionUID = -77218975959330473L;

    @Getter
    @Setter
    @Transient
    @ApiModelProperty(value = "孩子节点，虚拟字段，增删改时不需要关心", notes = "孩子节点，虚拟字段，增删改时不需要关心")
    private List<CdpSysPermissionEntity> children;

    @Transient
    @ApiModelProperty(value = "是否叶子节点，虚拟字段，增删改时不需要关心", notes = "是否叶子节点，虚拟字段，增删改时不需要关心")
    private Boolean leaf;

    /**
     * 当权限类型是1：组件菜单 3：目录菜单 表示该节点不是一个叶子节点
     */
    @ApiIgnore
    public Boolean getLeaf() {
        this.leaf = this.type != 1 && this.type != 3;
        return leaf;
    }

    public void setLeaf(Boolean leaf) {
        this.leaf = leaf;
    }

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "权限ID", notes = "主键，系统自动生成,权限ID")
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @Column(name = "code")
    @Basic
    @NotBlank
    @Pattern(regexp = "[a-zA-Z0-9_]{1,64}", message = "权限编码只能大小写字母、数字、下杠(_)组合而成,长度不超过64位")
    @ApiModelProperty(value = "权限编码，不能重复 不能为空", notes = "权限编码，不能重复 不能为空")
    private String code;

    @Getter
    @Setter
    @Column(name = "p_id")
    @Basic
    @NotNull
    @ApiModelProperty(value = "父权限ID，取值于id，表示当前数据的父类权限", notes = "父权限ID，取值于id，表示当前数据的父类权限")
    private Long pId;

    @Getter
    @Setter
    @Column(name = "name")
    @Basic
    @NotBlank
    @ApiModelProperty(value = "权限名称", notes = "权限名称")
    @Pattern(regexp =RegexUtil.SPECIAL, message = "名称不能包含特殊字符")
    private String name;

    @Getter
    @Setter
    @Column(name = "url")
    @Basic
    @Pattern(regexp = "[A-Za-z0-9/:.@?=& -]*", message = "资源路径只支持大小写字母 数字 & / : . @ - ? =")
    @ApiModelProperty(value = "该字段必须和type字段共同使用，详情请看type字段", notes = "该字段必须和type字段共同使用，详情请看type字段")
    private String url;

    @Getter
    @Setter
    @Column(name = "type")
    @Basic
    @NotNull
    @ApiModelProperty(value = "权限类型：0=按钮、1=组件菜单，对应ur是前端组件l、2=链接菜单，对应url是http(s)链接地址、3=目录菜单，对应是目录菜单，具体取值于字典表cdp_sys_dictionary.code=13，当权限类型是1：组件菜单 3：目录菜单时表示该节点不是一个叶子节点", notes = "权限类型：0=按钮、1=组件菜单，对应ur是前端组件l、2=链接菜单，对应url是http(s)链接地址、3=目录菜单，对应是目录菜单，具体取值于字典表cdp_sys_dictionary.code=13，当权限类型是1：组件菜单 3：目录菜单时表示该节点不是一个叶子节点")
    private Integer type;

    @Getter
    @Setter
    @Column(name = "level")
    @Basic
    @NotNull
    @ApiModelProperty(value = "菜单层级", notes = "菜单层级")
    private Integer level;

    @Getter
    @Setter
    @Column(name = "sort")
    @Basic
    @ApiModelProperty(value = "排序，用于显示数据时的顺序，数值越小越靠前", notes = "排序，用于显示数据时的顺序，数值越小越靠前")
    private Integer sort;

    @Getter
    @Setter
    @Column(name = "status")
    @Basic
    @NotNull
    @Range(max = 1)
    @ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表cdp_sys_dictionary.code=11", notes = "使用状态：0=启用，1=禁用，具体取值于字典表cdp_sys_dictionary.code=11")
    private Integer status;

    @Getter
    @Setter
    @Column(name = "app_name")
    @Basic
    @NotBlank
    @Pattern(regexp = "[a-zA-Z][a-zA-Z0-9_-]{1,64}", message = "应用名称只能大小写字母开始、数字、下杠(_)组合而成,长度不超过64位")
    @ApiModelProperty(value = "所属应用名称,等同于配置文件中的spring.application.name", notes = "所属应用名称,等同于配置文件中的spring.application.name")
    private String appName;

    @Getter
    @Setter
    @Column(name = "path")
    @Basic
    @Pattern(regexp = "[A-Za-z0-9/?*. -]+", message = "权限路径只支持大小写字母 数字 / . * - ?")
    @ApiModelProperty(value = "后台请求权限地址，权限路径ant风格表达式，用于动态验证HTTP后台请求的权限标识", notes = "后台请求权限地址，权限路径ant风格表达式，用于动态验证HTTP后台请求的权限标识")
    private String path;

    @Getter
    @Setter
    @Column(name = "method")
    @Basic
    @Pattern(regexp = "[A-Z]{0,20}", message = "请求方法只能大写字母组合而成,长度不超过20位")
    @ApiModelProperty(value = "http请求方法：GET、POST、DELETE、OPTIONS、PUT、PATCH，具体取值于字典表cdp_sys_dictionary.code=12 ", notes = "http请求方法：GET、POST、DELETE、OPTIONS、PUT、PATCH，具体取值于字典表cdp_sys_dictionary.code=12")
    private String method;

    @Getter
    @Setter
    @Column(name = "icon")
    @Basic
    @ApiModelProperty(value = "一般用于前端菜单选项前的图标", notes = "一般用于前端菜单选项前的图标")
    private String icon;

    @Getter
    @Setter
    @Column(name = "create_time")
    @Basic
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "创建日期，该值由后台维护，更改数据时前端不需要关心", notes = "创建日期，该值由后台维护，更改数据时前端不需要关心")
    private Date createTime;

    @Getter
    @Setter
    @Column(name = "create_by")
    @Basic
    @ApiModelProperty(value = "该值由后台维护，更改数据时前端不需要关心，取值于cdp_sys_user.id", notes = "该值由后台维护，更改数据时前端不需要关心，取值于cdp_sys_user.id")
    private Long createBy;

    @Getter
    @Setter
    @Column(name = "update_time")
    @Basic
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "更新日期，该值由后台维护，更改数据时前端不需要关心", notes = "更新日期，该值由后台维护，更改数据时前端不需要关心")
    private Date updateTime;

    @Getter
    @Setter
    @Column(name = "update_by")
    @Basic
    @ApiModelProperty(value = "该值由后台维护，更改数据时前端不需要关心，取值于cdp_sys_user.id", notes = "该值由后台维护，更改数据时前端不需要关心，取值于cdp_sys_user.id")
    private Long updateBy;

}