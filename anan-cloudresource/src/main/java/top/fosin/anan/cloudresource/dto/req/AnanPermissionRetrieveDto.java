package top.fosin.anan.cloudresource.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.model.dto.QuerySortRuleDto;
import top.fosin.anan.model.module.LogicalQueryRule;
import top.fosin.anan.model.module.SortRule;

import java.io.Serializable;
import java.util.Date;

/**
 * 包含菜单、按钮两种权限(AnanPermission)查询DTO
 *
 * @author fosin
 * @date 2019-01-27 18:27:20
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "包含菜单、按钮两种权限查询DTO", description = "权限的查询DTO")
public class AnanPermissionRetrieveDto extends QuerySortRuleDto<LogicalQueryRule, SortRule> implements Serializable {
    private static final long serialVersionUID = -61984917164013694L;

    @ApiModelProperty(value = "权限编码，不能重复 不能为空")
    private String code;

    @ApiModelProperty(value = "父权限ID，取值于id，表示当前数据的父类权限")
    private Long pid;

    @ApiModelProperty(value = "权限名称")
    private String name;

    @ApiModelProperty(value = "该字段必须和type字段共同使用，详情请看type字段")
    private String url;

    @ApiModelProperty(value = "权限类型：0=按钮、1=组件菜单，对应ur是前端组件l、2=链接菜单，对应url是http(s)链接地址、3=目录菜单，对应是目录菜单，具体取值于字典表anan_dictionary.code=13，当权限类型是1：组件菜单 3：目录菜单时表示该节点不是一个叶子节点")
    private Integer type;

    @ApiModelProperty(value = "菜单层级")
    private Integer level;

    @ApiModelProperty(value = "排序，用于显示数据时的顺序，数值越小越靠前")
    private Integer sort;

    @ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.code=11")
    private Integer status;

    @ApiModelProperty(value = "所属服务：等同于anan_service.id", required = true)
    private Long serviceId;

    @ApiModelProperty(value = "后台请求权限地址，权限路径ant风格表达式，用于动态验证HTTP后台请求的权限标识")
    private String path;

    @ApiModelProperty(value = "路由地址，权限路径ant风格表达式，默认等于code")
    private String routePath;

    @ApiModelProperty(value = "http请求方法：GET、POST、DELETE、OPTIONS、PUT、PATCH，具体取值于字典表anan_dictionary.code=12")
    private String method;

    @ApiModelProperty(value = "一般用于前端菜单选项前的图标")
    private String icon;

    @ApiModelProperty(value = "创建人", example = "1")
    private Long createBy;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "创建时间", example = "2021-05-08 13:25:11")
    private Date createTime;

    @ApiModelProperty(value = "修改人", example = "1")
    private Long updateBy;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "修改时间", example = "2021-05-08 13:25:11")
    private Date updateTime;
}
