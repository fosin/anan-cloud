package top.fosin.anan.platform.modules.permission.vo;



import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.data.entity.IdPid;
/**
 * 功能权限表(anan_permission)集合VO
 *
 * @author fosin
 * @date 2023-05-12
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "功能权限表(anan_permission)集合VO")
public class PermissionListVO extends IdPid<Long> {
    private static final long serialVersionUID = 315621249830108017L;
    @Schema(description = "权限编码，不能重复 不能为空")
    private String code;

    @Schema(description = "权限名称")
    private String name;

    @Schema(description = "该字段必须和type字段共同使用，详情请看type字段")
    private String url;

    @Schema(description = "路由地址，权限路径ant风格表达式，默认等于code")
    private String routePath;

    @Schema(description = "权限类型：0=按钮、1=组件菜单，对应ur是前端组件、2=链接菜单，对应url是http(s)链接地址、3=目录菜单、4=系统模块，具体取值于字典表anan_dictionary.code=13，当权限类型是1、3、4：目录菜单时表示该节点不是一个叶子节点")
    private Byte type;

    @Schema(description = "菜单层级")
    private Integer level;

    @Schema(description = "排序，用于显示数据时的顺序，数值越小越靠前")
    private Integer sort;

    @Schema(description = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.code=11")
    private Byte status;

    @Schema(description = "所属服务,等同于表anan_service.id")
    private Long serviceId;

    @Schema(description = "后台请求权限地址，权限路径ant风格表达式，用于动态验证HTTP后台请求的权限标识")
    private String path;

    @Schema(description = "http请求方法：GET、POST、DELETE、OPTIONS、PUT、PATCH，具体取值于字典表anan_dictionary.code=12")
    private String method;

    @Schema(description = "一般用于前端菜单选项前的图标")
    private String icon;

}
