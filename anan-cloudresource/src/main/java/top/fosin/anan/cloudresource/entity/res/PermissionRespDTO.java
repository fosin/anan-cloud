package top.fosin.anan.cloudresource.entity.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.data.entity.IdPid;

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
@ApiModel(value = "功能权限表响应DTO", description = "权限的响应DTO")
public class PermissionRespDTO extends IdPid<Long> {
    private static final long serialVersionUID = -59729421245439638L;
    @ApiModelProperty(value = "权限编码，不能重复 不能为空", example = "String")
    private String code;

    @ApiModelProperty(value = "权限名称", example = "String")
    private String name;

    @ApiModelProperty(value = "该字段必须和type字段共同使用，详情请看type字段", example = "String")
    private String url;

    @ApiModelProperty(value = "路由地址，权限路径ant风格表达式，默认等于code", example = "String")
    private String routePath;

    @ApiModelProperty(value = "权限类型：具体取值于字典表anan_dictionary.code=13，除1、3、6之外的类型都是叶子节点", example = "1")
    private Integer type;

    @ApiModelProperty(value = "菜单层级", example = "1")
    private Integer level;

    @ApiModelProperty(value = "排序，用于显示数据时的顺序，数值越小越靠前", example = "1")
    private Integer sort;

    @ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.code=11", example = "0")
    private Integer status;

    @ApiModelProperty(value = "所属服务,等同于表anan_service.id", example = "1")
    private Long serviceId;

    @ApiModelProperty(value = "所属服务,等同于表anan_service.code", example = "anan-platformserver")
    private String serviceCode;

    @ApiModelProperty(value = "后台请求权限地址，权限路径ant风格表达式，用于动态验证HTTP后台请求的权限标识", example = "String")
    private String path;

    @ApiModelProperty(value = "http请求方法：GET、POST、DELETE、OPTIONS、PUT、PATCH，具体取值于字典表anan_dictionary.code=12", example = "String")
    private String method;

    @ApiModelProperty(value = "一般用于前端菜单选项前的图标", example = "String")
    private String icon;

}
