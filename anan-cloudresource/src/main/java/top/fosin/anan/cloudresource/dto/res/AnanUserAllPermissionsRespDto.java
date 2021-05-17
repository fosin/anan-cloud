package top.fosin.anan.cloudresource.dto.res;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.core.util.RegexUtil;
import top.fosin.anan.model.dto.PidDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * (AnanUserAllPermissions)响应DTO
 *
 * @author fosin
 * @date 2021-05-15 13:54:57
 * @since 2.6.0
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "用户所有的权限响应DTO", description = "表(anan_user_all_permissions)的响应DTO")
public class AnanUserAllPermissionsRespDto extends PidDto<Long> implements Serializable {
    private static final long serialVersionUID = 836500519130655446L;

    @ApiModelProperty(value = "权限编码，不能重复 不能为空", required = true)
    private String code;

    @ApiModelProperty(value = "权限名称", required = true)
    private String name;

    @ApiModelProperty(value = "该字段必须和type字段共同使用，详情请看type字段")
    private String url;

    @ApiModelProperty(value = "权限类型：0=按钮、1=组件菜单，对应ur是前端组件l、2=链接菜单，对应url是http(s)链接地址、3=目录菜单，对应是目录菜单，具体取值于字典表anan_dictionary.code=13，当权限类型是1：组件菜单 3：目录菜单时表示该节点不是一个叶子节点", required = true)
    private Integer type;

    @ApiModelProperty(value = "菜单层级", required = true)
    private Integer level;

    @ApiModelProperty(value = "排序，用于显示数据时的顺序，数值越小越靠前")
    private Integer sort;

    @ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.code=11", required = true)
    private Integer status;

    @ApiModelProperty(value = "所属服务：等同于anan_service.id", required = true)
    private Long serviceId;

    @ApiModelProperty(value = "后台请求权限地址，权限路径ant风格表达式，用于动态验证HTTP后台请求的权限标识")
    private String path;

    @ApiModelProperty(value = "http请求方法：GET、POST、DELETE、OPTIONS、PUT、PATCH，具体取值于字典表anan_dictionary.id=12")
    private String method;

    @ApiModelProperty(value = "路由地址，权限路径ant风格表达式，默认等于code")
    private String routePath;

    @ApiModelProperty(value = "一般用于前端菜单选项前的图标")
    private String icon;

    @ApiModelProperty(value = "用户ID", required = true)
    private Long userId;

    @ApiModelProperty(value = "补充方式：0=增加权限、1=删除权限", required = true)
    private Integer addMode;

    @ApiModelProperty(value = "角色ID", required = true)
    private Long roleId;

}
