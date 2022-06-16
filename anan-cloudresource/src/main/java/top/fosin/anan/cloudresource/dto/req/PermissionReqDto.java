package top.fosin.anan.cloudresource.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.fosin.anan.core.util.RegexUtil;
import top.fosin.anan.model.dto.req.IdQuerySortPidDto;
import top.fosin.anan.model.module.LogicalQueryRule;
import top.fosin.anan.model.module.SortRule;
import top.fosin.anan.model.valid.group.Create;
import top.fosin.anan.model.valid.group.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

/**
 * 包含菜单、按钮两种权限(AnanPermission)请求DTO
 *
 * @author fosin
 * @date 2019-01-27 18:27:20
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "包含菜单、按钮两种权限请求DTO", description = "权限的请求DTO")
public class PermissionReqDto extends IdQuerySortPidDto<LogicalQueryRule, SortRule, Long> {
    private static final long serialVersionUID = -61984917164013694L;

    @NotBlank(message = "权限编码" + "{javax.validation.constraints.NotBlank.message}",
            groups = {Create.class, Update.class})
    @ApiModelProperty(value = "权限编码，不能重复 不能为空")
    @Pattern(regexp = "[A-Z][a-zA-Z0-9]{1,64}", message = "权限编码只能大写字母开始，大小写字母、数字组合而成,长度不超过64位",
            groups = {Create.class, Update.class})
    private String code;

    @NotBlank(message = "权限名称" + "{javax.validation.constraints.NotBlank.message}",
            groups = {Create.class, Update.class})
    @ApiModelProperty(value = "权限名称")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "名称不能包含特殊字符")
    private String name;

    @ApiModelProperty(value = "该字段必须和type字段共同使用，详情请看type字段")
    @Pattern(regexp = "[A-Za-z0-9/:.@#?=& -]*", message = "资源路径只支持大小写字母 数字 & / : . @ - ? = #")
    private String url;

    @ApiModelProperty(value = "权限类型：具体取值于字典表anan_dictionary.code=13，除1、3、6之外的类型都是叶子节点")
    @NotNull(message = "权限类型" + "{javax.validation.constraints.NotNull.message}")
    private Integer type;

    @ApiModelProperty(value = "菜单层级")
    @NotNull(message = "菜单层级" + "{javax.validation.constraints.NotNull.message}",
            groups = {Create.class, Update.class})
    @Positive(message = "菜单层级" + "{javax.validation.constraints.Positive.message}",
            groups = {Create.class, Update.class})
    private Integer level;

    @ApiModelProperty(value = "排序，用于显示数据时的顺序，数值越小越靠前")
    private Integer sort;

    @ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.code=11")
    @NotNull(message = "使用状态" + "{javax.validation.constraints.NotNull.message}",
            groups = {Create.class, Update.class})
    private Integer status;

    @ApiModelProperty(value = "所属服务：等同于anan_service.id")
    @NotNull(message = "所属服务" + "{javax.validation.constraints.NotNull.message}",
            groups = {Create.class, Update.class})
    private Long serviceId;

    @ApiModelProperty(value = "后台请求权限地址，权限路径ant风格表达式，用于动态验证HTTP后台请求的权限标识")
    @Pattern(regexp = "[A-Za-z0-9/?*. -]*", message = "匹配路径只支持大小写字母 数字 / . * - ?")
    private String path;

    @ApiModelProperty(value = "路由地址，权限路径ant风格表达式，默认等于code")
    @Pattern(regexp = "[A-Za-z0-9/?*.: -]*", message = "匹配路径只支持大小写字母 数字 : / . * - ?")
    private String routePath;

    @ApiModelProperty(value = "http请求方法：GET、POST、DELETE、OPTIONS、PUT、PATCH，具体取值于字典表anan_dictionary.code=12")
    @Pattern(regexp = "[A-Z,]{0,20}", message = "http请求方法：只能大写字母和,组合而成,长度不超过20位")
    private String method;

    @ApiModelProperty(value = "一般用于前端菜单选项前的图标")
    private String icon;

}
