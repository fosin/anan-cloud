package top.fosin.anan.cloudresource.entity.res;



import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.data.entity.IdPid;

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
@Schema(description = "用户所有权限的响应DTO")
public class UserAllPermissionsRespDTO extends IdPid<Long> {
    private static final long serialVersionUID = 836500519130655446L;

    @Schema(description = "权限编码，不能重复 不能为空")
    private String code;

    @Schema(description = "权限名称")
    private String name;

    @Schema(description = "该字段必须和type字段共同使用，详情请看type字段")
    private String url;

    @Schema(description = "权限类型：0=按钮、1=组件菜单，对应ur是前端组件l、2=链接菜单，对应url是http(s)链接地址、3=目录菜单，对应是目录菜单，具体取值于字典表anan_dictionary.code=13，当权限类型是1：组件菜单 3：目录菜单时表示该节点不是一个叶子节点")
    private Byte type;

    @Schema(description = "菜单层级")
    private Integer level;

    @Schema(description = "排序，用于显示数据时的顺序，数值越小越靠前")
    private Integer sort;

    @Schema(description = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.code=11")
    private Byte status;

    @Schema(description = "所属服务：等同于anan_service.id")
    private Long serviceId;

    @Schema(description = "后台请求权限地址，权限路径ant风格表达式，用于动态验证HTTP后台请求的权限标识")
    private String path;

    @Schema(description = "http请求方法：GET、POST、DELETE、OPTIONS、PUT、PATCH，具体取值于字典表anan_dictionary.id=12")
    private String method;

    @Schema(description = "路由地址，权限路径ant风格表达式，默认等于code")
    private String routePath;

    @Schema(description = "一般用于前端菜单选项前的图标")
    private String icon;

    @Schema(description = "用户序号")
    private Long userId;

    @Schema(description = "补充方式：0=增加权限、1=删除权限")
    private Integer addMode;

    @Schema(description = "角色序号")
    private Long roleId;

    @Schema(description = "机构序号")
    private Long organizId;

}
