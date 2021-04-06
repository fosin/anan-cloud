package com.github.fosin.anan.cloudresource.dto.request;

import com.github.fosin.anan.core.util.RegexUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 包含菜单、按钮两种权限(AnanPermission)创建DTO
 *
 * @author fosin
 * @date 2019-01-27 16:58:27
 * @since 1.0.0
 */
@Data
@ApiModel(value = "包含菜单、按钮两种权限创建DTO", description = "表(anan_permission)的对应的创建DTO")
public class AnanPermissionCreateDto implements Serializable {
    private static final long serialVersionUID = -64600322422519137L;

    @NotBlank(message = "权限编码" + "{javax.validation.constraints.NotBlank.message}")
    @ApiModelProperty(value = "权限编码，不能重复 不能为空", required = true)
    @Pattern(regexp = "[A-Z][a-zA-Z0-9]{1,64}", message = "权限编码只能大写字母开始，大小写字母、数字组合而成,长度不超过64位")
    private String code;

    @NotNull(message = "父权限ID" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "父权限ID，取值于id，表示当前数据的父类权限", required = true)
    private Long pid;

    @NotBlank(message = "权限名称" + "{javax.validation.constraints.NotBlank.message}")
    @ApiModelProperty(value = "权限名称", required = true)
    @Pattern(regexp = RegexUtil.SPECIAL, message = "名称不能包含特殊字符")
    private String name;

    @ApiModelProperty(value = "资源路径必须和type字段共同使用，详情请看type字段")
    @Pattern(regexp = "[A-Za-z0-9/:.@#?=& -]*", message = "资源路径只支持大小写字母 数字 & / : . @ - ? = #")
    private String url;

    @NotNull(message = "权限类型" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "权限类型：0=按钮、1=组件菜单，对应ur是前端组件l、2=链接菜单，对应url是http(s)链接地址、3=目录菜单，对应是目录菜单，具体取值于字典表anan_dictionary.code=13，当权限类型是1：组件菜单 3：目录菜单时表示该节点不是一个叶子节点", required = true)
    private Integer type;

    @NotNull(message = "菜单层级" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "菜单层级", required = true)
    private Integer level;

    @ApiModelProperty(value = "排序，用于显示数据时的顺序，数值越小越靠前")
    private Integer sort;

    @NotNull(message = "使用状态" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.code=11", required = true)
    private Integer status;

    @NotNull(message = "所属服务" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "所属服务：等同于anan_service.id", required = true)
    private Integer serviceId;

    @ApiModelProperty(value = "后台请求权限地址，权限路径ant风格表达式，用于动态验证HTTP后台请求的权限标识")
    @Pattern(regexp = "[A-Za-z0-9/?*. -]*", message = "匹配路径只支持大小写字母 数字 / . * - ?")
    private String path;

    @ApiModelProperty(value = "路由地址，权限路径ant风格表达式，默认等于code")
    @Pattern(regexp = "[A-Za-z0-9/?*.: -]*", message = "匹配路径只支持大小写字母 数字 : / . * - ?")
    private String routePath;

    @ApiModelProperty(value = "http请求方法：GET、POST、DELETE、OPTIONS、PUT、PATCH，具体取值于字典表anan_dictionary.id=12")
    @Pattern(regexp = "[A-Z,]{0,20}", message = "http请求方法：只能大写字母和,组合而成,长度不超过20位")
    private String method;

    @ApiModelProperty(value = "一般用于前端菜单选项前的图标")
    private String icon;

}
