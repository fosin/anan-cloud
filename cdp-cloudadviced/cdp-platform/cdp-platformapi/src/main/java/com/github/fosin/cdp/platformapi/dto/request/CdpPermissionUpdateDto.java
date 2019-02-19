package com.github.fosin.cdp.platformapi.dto.request;

import com.github.fosin.cdp.util.RegexUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 包含菜单、按钮两种权限(CdpPermission)更新DTO
 *
 * @author fosin
 * @date 2019-01-27 16:58:27
 * @since 1.0.0
 */
@Data
@ApiModel(value = "包含菜单、按钮两种权限更新DTO", description = "表(cdp_permission)的对应的更新DTO")
public class CdpPermissionUpdateDto implements Serializable {
    private static final long serialVersionUID = 900297768117165756L;

    @NotNull(message = "权限ID" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "权限ID, 主键", example = "Long", required = true)
    private Long id;

    @NotBlank(message = "权限编码，不能重复 不能为空" + "{org.hibernate.validator.constraints.NotBlank.message}")
    @ApiModelProperty(value = "权限编码，不能重复 不能为空", example = "String", required = true)
    @Pattern(regexp = "[A-Z][a-zA-Z0-9]{1,64}", message = "权限编码只能大写字母开始，大小写字母、数字组合而成,长度不超过64位")
    private String code;

    @NotNull(message = "父权限ID，取值于id，表示当前数据的父类权限" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "父权限ID，取值于id，表示当前数据的父类权限", example = "Long", required = true)
    private Long pId;

    @NotBlank(message = "权限名称" + "{org.hibernate.validator.constraints.NotBlank.message}")
    @ApiModelProperty(value = "权限名称", example = "String", required = true)
    @Pattern(regexp = RegexUtil.SPECIAL, message = "名称不能包含特殊字符")
    private String name;

    @ApiModelProperty(value = "该字段必须和type字段共同使用，详情请看type字段", example = "String")
    @Pattern(regexp = "[A-Za-z0-9/:.@?=& -]*", message = "资源路径只支持大小写字母 数字 & / : . @ - ? =")
    private String url;

    @NotNull(message = "权限类型：0=按钮、1=组件菜单，对应ur是前端组件l、2=链接菜单，对应url是http(s)链接地址、3=目录菜单，对应是目录菜单，具体取值于字典表cdp_dictionary.code=13，当权限类型是1：组件菜单 3：目录菜单时表示该节点不是一个叶子节点" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "权限类型：0=按钮、1=组件菜单，对应ur是前端组件l、2=链接菜单，对应url是http(s)链接地址、3=目录菜单，对应是目录菜单，具体取值于字典表cdp_dictionary.code=13，当权限类型是1：组件菜单 3：目录菜单时表示该节点不是一个叶子节点", example = "Integer", required = true)
    private Integer type;

    @NotNull(message = "菜单层级" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "菜单层级", example = "Integer", required = true)
    private Integer level;

    @ApiModelProperty(value = "排序，用于显示数据时的顺序，数值越小越靠前", example = "Integer")
    private Integer sort;

    @NotNull(message = "使用状态：0=启用，1=禁用，具体取值于字典表cdp_dictionary.code=11" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表cdp_dictionary.code=11", example = "Integer", required = true)
    private Integer status;

    @NotBlank(message = "所属应用名称,等同于配置文件中的spring.application.name" + "{org.hibernate.validator.constraints.NotBlank.message}")
    @ApiModelProperty(value = "所属应用名称,等同于配置文件中的spring.application.name", example = "String", required = true)
    @Pattern(regexp = "[a-zA-Z][a-zA-Z0-9_-]{1,64}", message = "应用名称只能大小写字母开始、数字、下杠(_)组合而成,长度不超过64位")
    private String appName;

    @ApiModelProperty(value = "后台请求权限地址，权限路径ant风格表达式，用于动态验证HTTP后台请求的权限标识", example = "String")
    private String path;

    @ApiModelProperty(value = "http请求方法：GET、POST、DELETE、OPTIONS、PUT、PATCH，具体取值于字典表cdp_dictionary.id=12", example = "String")
    @Pattern(regexp = "[A-Z]{0,20}", message = "请求方法只能大写字母组合而成,长度不超过20位")
    private String method;

    @ApiModelProperty(value = "一般用于前端菜单选项前的图标", example = "String")
    private String icon;

}
