package top.fosin.anan.platform.modules.permission.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.core.util.RegexUtil;
import top.fosin.anan.data.valid.group.Create;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
/**
 * 功能权限表(anan_permission)创建DTO
 *
 * @author fosin
 * @date 2023-05-12
 */
@Data
@EqualsAndHashCode
@ToString(callSuper = true)
@ApiModel(value = "功能权限表创建DTO", description = "功能权限表(anan_permission)创建DTO")
public class PermissionCreateDTO {
    private static final long serialVersionUID = 384185172675417647L;

    @NotNull(message = "权限编码" + "{javax.validation.constraints.NotNull.message}", groups = Create.class)
    @ApiModelProperty(value = "权限编码", required = true)
    private Long pid;

    @NotBlank(message = "权限编码" + "{javax.validation.constraints.NotBlank.message}", groups = Create.class)
    @ApiModelProperty(value = "权限编码")
    @Pattern(regexp = "[A-Z][a-zA-Z0-9]{1,64}", message = "权限编码只能大写字母开始，大小写字母、数字组合而成,长度不超过64位", groups = Create.class)
    private String code;

    @NotBlank(message = "权限名称" + "{javax.validation.constraints.NotBlank.message}", groups = Create.class)
    @ApiModelProperty(value = "权限名称")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "名称不能包含特殊字符", groups = Create.class)
    private String name;

    @ApiModelProperty(value = "该字段必须和type字段共同使用，详情请看type字段")
    @Pattern(regexp = "[A-Za-z0-9/:.@#?=& -]*", message = "资源路径只支持大小写字母 数字 & / : . @ - ? = #", groups = Create.class)
    private String url;

    @ApiModelProperty(value = "权限类型：具体取值于字典表anan_dictionary.code=13，除1、3、6之外的类型都是叶子节点")
    @NotNull(message = "权限类型" + "{javax.validation.constraints.NotNull.message}", groups = Create.class)
    private Byte type;

    @ApiModelProperty(value = "菜单层级")
    @NotNull(message = "菜单层级" + "{javax.validation.constraints.NotNull.message}", groups = Create.class)
    @Positive(message = "菜单层级" + "{javax.validation.constraints.Positive.message}", groups = Create.class)
    private Integer level;

    @ApiModelProperty(value = "排序，用于显示数据时的顺序，数值越小越靠前")
    private Integer sort;

    @ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.code=11")
    @NotNull(message = "使用状态" + "{javax.validation.constraints.NotNull.message}", groups = Create.class)
    private Byte status;

    @ApiModelProperty(value = "所属服务：等同于anan_service.id")
    @NotNull(message = "所属服务" + "{javax.validation.constraints.NotNull.message}", groups = Create.class)
    private Long serviceId;

    @ApiModelProperty(value = "后台请求权限地址，权限路径ant风格表达式，用于动态验证HTTP后台请求的权限标识")
    @Pattern(regexp = "[A-Za-z0-9/?*. -]*", message = "匹配路径只支持大小写字母 数字 / . * - ?", groups = Create.class)
    private String path;

    @ApiModelProperty(value = "路由地址，权限路径ant风格表达式，默认等于code")
    @Pattern(regexp = "[A-Za-z0-9/?*.: -]*", message = "匹配路径只支持大小写字母 数字 : / . * - ?", groups = Create.class)
    private String routePath;

    @ApiModelProperty(value = "http请求方法：GET、POST、DELETE、OPTIONS、PUT、PATCH，具体取值于字典表anan_dictionary.code=12")
    @Pattern(regexp = "[A-Z,]{0,20}", message = "http请求方法：只能大写字母和,组合而成,长度不超过20位", groups = Create.class)
    private String method;

    @ApiModelProperty(value = "一般用于前端菜单选项前的图标")
    private String icon;

}
