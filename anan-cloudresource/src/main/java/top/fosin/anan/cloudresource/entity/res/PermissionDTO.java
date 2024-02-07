package top.fosin.anan.cloudresource.entity.res;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.data.entity.IdPid;

import java.util.Date;

/**
 * 功能权限表(AnanPermission)响应DTO
 *
 * @author fosin
 * @date 2021-05-16 14:32:15
 * @since 2.6.0
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@Schema(description = "权限的响应DTO")
public class PermissionDTO extends IdPid<Long> {
    private static final long serialVersionUID = -59729421245439638L;

    @Schema(description = "权限编码，不能重复 不能为空", example = "String")
    private String code;

    @Schema(description = "权限名称", example = "String")
    private String name;

    @Schema(description = "该字段必须和type字段共同使用，详情请看type字段", example = "String")
    private String url;

    @Schema(description = "路由地址，权限路径ant风格表达式，默认等于code", example = "String")
    private String routePath;

    @Schema(description = "权限类型：0=按钮、1=组件菜单，对应ur是前端组件、2=链接菜单，对应url是http(s)链接地址、3=目录菜单、4=系统模块，具体取值于字典表anan_dictionary.code=13，当权限类型是1、3、4：目录菜单时表示该节点不是一个叶子节点", example = "Integer")
    private Byte type;

    @Schema(description = "菜单层级", example = "Integer")
    private Integer level;

    @Schema(description = "排序，用于显示数据时的顺序，数值越小越靠前", example = "Integer")
    private Integer sort;

    @Schema(description = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.code=11", example = "Integer")
    private Byte status;

    @Schema(description = "所属服务,等同于表anan_service.id", example = "Integer")
    private Long serviceId;

    @Schema(description = "所属服务,等同于表anan_service.code", example = "anan-platformserver")
    private String serviceCode;

    @Schema(description = "后台请求权限地址，权限路径ant风格表达式，用于动态验证HTTP后台请求的权限标识", example = "String")
    private String path;

    @Schema(description = "http请求方法：GET、POST、DELETE、OPTIONS、PUT、PATCH，具体取值于字典表anan_dictionary.code=12", example = "String")
    private String method;

    @Schema(description = "一般用于前端菜单选项前的图标", example = "String")
    private String icon;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Schema(description = "创建日期", example = "Date")
    private Date createTime;

    @Schema(description = "创建人", example = "Long")
    private Long createBy;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Schema(description = "修改日期", example = "Date")
    private Date updateTime;

    @Schema(description = "修改人", example = "Long")
    private Long updateBy;


}
