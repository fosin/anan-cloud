package com.github.fosin.anan.cloudresource.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 包含菜单、按钮两种权限(AnanPermission)查询DTO
 *
 * @author fosin
 * @date 2019-01-27 18:27:20
 * @since 1.0.0
 */
@Data
@ApiModel(value = "包含菜单、按钮两种权限查询DTO", description = "表(anan_permission)的对应的查询DTO")
public class AnanPermissionRetrieveDto implements Serializable {
    private static final long serialVersionUID = -61984917164013694L;

    @ApiModelProperty(value = "权限ID, 主键")
    private Long id;

    @ApiModelProperty(value = "权限编码，不能重复 不能为空")
    private String code;

    @ApiModelProperty(value = "父权限ID，取值于id，表示当前数据的父类权限")
    private Long pid;

    @ApiModelProperty(value = "权限名称")
    private String name;

    @ApiModelProperty(value = "该字段必须和type字段共同使用，详情请看type字段")
    private String url;

    @ApiModelProperty(value = "权限类型：0=按钮、1=组件菜单，对应ur是前端组件l、2=链接菜单，对应url是http(s)链接地址、3=目录菜单，对应是目录菜单，具体取值于字典表anan_dictionary.code=13，当权限类型是1：组件菜单 3：目录菜单时表示该节点不是一个叶子节点")
    private Integer type;

    @ApiModelProperty(value = "菜单层级")
    private Integer level;

    @ApiModelProperty(value = "排序，用于显示数据时的顺序，数值越小越靠前")
    private Integer sort;

    @ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.code=11")
    private Integer status;

    @ApiModelProperty(value = "所属应用名称,等同于配置文件中的spring.application.name")
    private String appName;

    @ApiModelProperty(value = "后台请求权限地址，权限路径ant风格表达式，用于动态验证HTTP后台请求的权限标识")
    private String path;

    @ApiModelProperty(value = "http请求方法：GET、POST、DELETE、OPTIONS、PUT、PATCH，具体取值于字典表anan_dictionary.code=12")
    private String method;

    @ApiModelProperty(value = "一般用于前端菜单选项前的图标")
    private String icon;
}
