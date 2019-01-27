package com.github.fosin.cdp.platformapi.dto.request;

import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import com.github.fosin.cdp.util.DateTimeUtil;

/**
 * 包含菜单、按钮两种权限(CdpSysPermission)查询DTO
 *
 * @author fosin
 * @date 2019-01-27 18:27:20
 * @since 1.0.0
 */
@Data
@ApiModel(value = "包含菜单、按钮两种权限查询DTO", description = "表(cdp_sys_permission)的对应的查询DTO")
public class CdpSysPermissionRetrieveDto implements Serializable {
    private static final long serialVersionUID = -70091075711562695L;
    
    @NotNull
    @ApiModelProperty(value = "权限ID", example = "Long")
    private Long id;

    @NotBlank
    @ApiModelProperty(value = "权限编码，不能重复 不能为空", example = "String")
    private String code;

    @NotNull
    @ApiModelProperty(value = "父权限ID，取值于id，表示当前数据的父类权限", example = "Long")
    private Long pId;

    @NotBlank
    @ApiModelProperty(value = "权限名称", example = "String")
    private String name;

    @ApiModelProperty(value = "该字段必须和type字段共同使用，详情请看type字段", example = "String")
    private String url;

    @NotNull
    @ApiModelProperty(value = "权限类型：0=按钮、1=组件菜单，对应ur是前端组件l、2=链接菜单，对应url是http(s)链接地址、3=目录菜单，对应是目录菜单，具体取值于字典表cdp_sys_dictionary.code=13，当权限类型是1：组件菜单 3：目录菜单时表示该节点不是一个叶子节点", example = "Integer")
    private Integer type;

    @NotNull
    @ApiModelProperty(value = "菜单层级", example = "Integer")
    private Integer level;

    @ApiModelProperty(value = "排序，用于显示数据时的顺序，数值越小越靠前", example = "Integer")
    private Integer sort;

    @NotNull
    @ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表cdp_sys_dictionary.code=11", example = "Integer")
    private Integer status;

    @NotBlank
    @ApiModelProperty(value = "所属应用名称,等同于配置文件中的spring.application.name", example = "String")
    private String appName;

    @ApiModelProperty(value = "后台请求权限地址，权限路径ant风格表达式，用于动态验证HTTP后台请求的权限标识", example = "String")
    private String path;

    @ApiModelProperty(value = "http请求方法：GET、POST、DELETE、OPTIONS、PUT、PATCH，具体取值于字典表cdp_sys_dictionary.code=12", example = "String")
    private String method;

    @ApiModelProperty(value = "一般用于前端菜单选项前的图标", example = "String")
    private String icon;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "创建日期，该值由后台维护，更改数据时前端不需要关心", example = "Date")
    private Date createTime;

    @NotNull
    @ApiModelProperty(value = "该值由后台维护，更改数据时前端不需要关心，取值于cdp_sys_user.id", example = "Long")
    private Long createBy;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "更新日期，该值由后台维护，更改数据时前端不需要关心", example = "Date")
    private Date updateTime;

    @NotNull
    @ApiModelProperty(value = "该值由后台维护，更改数据时前端不需要关心，取值于cdp_sys_user.id", example = "Long")
    private Long updateBy;

}