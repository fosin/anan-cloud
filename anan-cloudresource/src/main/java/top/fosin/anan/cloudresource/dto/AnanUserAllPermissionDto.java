package top.fosin.anan.cloudresource.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.fosin.anan.core.util.RegexUtil;
import top.fosin.anan.model.dto.TreeDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 包含菜单、按钮两种权限(AnanPermission)更新DTO
 *
 * @author fosin
 * @date 2019-01-27 16:58:27
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "用户所有的权限", description = "用户所有的权限，包含用户权限和角色权限")
public class AnanUserAllPermissionDto extends TreeDto<AnanUserAllPermissionDto, Long> implements Serializable {
    private static final long serialVersionUID = 900297768117165756L;

    @NotBlank(message = "权限编码" + "{javax.validation.constraints.NotBlank.message}")
    @ApiModelProperty(value = "权限编码，不能重复 不能为空", required = true)
    @Pattern(regexp = "[A-Z][a-zA-Z0-9]{1,64}", message = "权限编码只能大写字母开始，大小写字母、数字组合而成,长度不超过64位")
    private String code;

    @NotBlank(message = "权限名称" + "{javax.validation.constraints.NotBlank.message}")
    @ApiModelProperty(value = "权限名称", required = true)
    @Pattern(regexp = RegexUtil.SPECIAL, message = "名称不能包含特殊字符")
    private String name;

    @ApiModelProperty(value = "该字段必须和type字段共同使用，详情请看type字段")
    @Pattern(regexp = "[A-Za-z0-9/:.@#?=& -]*", message = "资源路径只支持大小写字母 数字 & / : . @ - ? = #")
    private String url;

    @NotNull(message = "权限类型：0=按钮、1=组件菜单，对应ur是前端组件l、2=链接菜单，对应url是http(s)链接地址、3=目录菜单，对应是目录菜单，具体取值于字典表anan_dictionary.code=13，当权限类型是1：组件菜单 3：目录菜单时表示该节点不是一个叶子节点" + "{javax.validation.constraints.NotNull.message}")
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
    @Pattern(regexp = "[A-Za-z0-9/?*. \\\\-]", message = "匹配路径只支持大小写字母 数字 / . * - ?")
    private String path;

    @ApiModelProperty(value = "http请求方法：GET、POST、DELETE、OPTIONS、PUT、PATCH，具体取值于字典表anan_dictionary.id=12")
    @Pattern(regexp = "[A-Z]{0,20}", message = "请求方法只能大写字母组合而成,长度不超过20位")
    private String method;

    @ApiModelProperty(value = "路由地址，权限路径ant风格表达式，默认等于code")
    @Pattern(regexp = "[A-Za-z0-9/?*.: -]*", message = "匹配路径只支持大小写字母 数字 : / . * - ?")
    private String routePath;

    @ApiModelProperty(value = "一般用于前端菜单选项前的图标")
    private String icon;

    @ApiModelProperty(value = "用户ID", required = true)
    private Long userId;

    @ApiModelProperty(value = "补充方式：0=增加权限、1=删除权限", required = true)
    private Integer addMode;

    @NotNull(message = "角色ID" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "角色ID", required = true)
    private Long roleId;
}
