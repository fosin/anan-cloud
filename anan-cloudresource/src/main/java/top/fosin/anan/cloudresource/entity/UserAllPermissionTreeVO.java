package top.fosin.anan.cloudresource.entity;


import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import top.fosin.anan.core.util.RegexUtil;
import top.fosin.anan.data.entity.res.TreeVO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

/**
 * 包含菜单、按钮两种权限(AnanPermission)更新DTO
 *
 * @author fosin
 * @date 2019-01-27 16:58:27
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "用户所有的权限，包含用户权限和角色权限")
public class UserAllPermissionTreeVO extends TreeVO<UserAllPermissionTreeVO, Long> {
    private static final long serialVersionUID = 900297768117165756L;

    @NotBlank(message = "权限编码" + "{jakarta.validation.constraints.NotBlank.message}")
    @Schema(description = "权限编码，不能重复 不能为空")
    @Pattern(regexp = "[A-Z][a-zA-Z0-9]{1,64}", message = "权限编码只能大写字母开始，大小写字母、数字组合而成,长度不超过64位")
    private String code;

    @NotBlank(message = "权限名称" + "{jakarta.validation.constraints.NotBlank.message}")
    @Schema(description = "权限名称")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "名称不能包含特殊字符")
    private String name;

    @Schema(description = "该字段必须和type字段共同使用，详情请看type字段")
    @Pattern(regexp = "[A-Za-z0-9/:.@#?=& -]*", message = "资源路径只支持大小写字母 数字 & / : . @ - ? = #")
    private String url;

    @NotNull(message = "权限类型：0=按钮、1=组件菜单，对应ur是前端组件l、2=链接菜单，对应url是http(s)链接地址、3=目录菜单，对应是目录菜单，具体取值于字典表anan_dictionary.code=13，当权限类型是1：组件菜单 3：目录菜单时表示该节点不是一个叶子节点" + "{jakarta.validation.constraints.NotNull.message}")
    @Schema(description = "权限类型：0=按钮、1=组件菜单，对应ur是前端组件l、2=链接菜单，对应url是http(s)链接地址、3=目录菜单，对应是目录菜单，具体取值于字典表anan_dictionary.code=13，当权限类型是1：组件菜单 3：目录菜单时表示该节点不是一个叶子节点")
    private Byte type;

    @NotNull(message = "菜单层级" + "{jakarta.validation.constraints.NotNull.message}")
    @Schema(description = "菜单层级")
    private Integer level;

    @Schema(description = "排序，用于显示数据时的顺序，数值越小越靠前")
    private Integer sort;

    @NotNull(message = "使用状态" + "{jakarta.validation.constraints.NotNull.message}")
    @Schema(description = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.code=11")
    private Byte status;

    @NotNull(message = "所属服务" + "{jakarta.validation.constraints.NotNull.message}")
    @Schema(description = "所属服务：等同于anan_service.id")
    private Long serviceId;

    @Schema(description = "后台请求权限地址，权限路径ant风格表达式，用于动态验证HTTP后台请求的权限标识")
    @Pattern(regexp = "[A-Za-z0-9/?*. \\\\-]", message = "匹配路径只支持大小写字母 数字 / . * - ?")
    private String path;

    @Schema(description = "http请求方法：GET、POST、DELETE、OPTIONS、PUT、PATCH，具体取值于字典表anan_dictionary.id=12")
    @Pattern(regexp = "[A-Z]{0,20}", message = "请求方法只能大写字母组合而成,长度不超过20位")
    private String method;

    @Schema(description = "路由地址，权限路径ant风格表达式，默认等于code")
    @Pattern(regexp = "[A-Za-z0-9/?*.: -]*", message = "匹配路径只支持大小写字母 数字 : / . * - ?")
    private String routePath;

    @Schema(description = "一般用于前端菜单选项前的图标")
    private String icon;

    @Schema(description = "用户序号")
    private Long userId;

    @Schema(description = "补充方式：0=增加权限、1=删除权限")
    private Integer addMode;

    @NotNull(message = "角色序号" + "{jakarta.validation.constraints.NotNull.message}")
    @Schema(description = "角色序号")
    private Long roleId;

    @NotNull(message = "机构序号" + "{jakarta.validation.constraints.NotNull.message}")
    @Schema(description = "机构序号")
    private Long organizId;
}
