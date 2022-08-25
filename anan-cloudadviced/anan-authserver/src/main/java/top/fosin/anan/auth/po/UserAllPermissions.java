package top.fosin.anan.auth.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;
import top.fosin.anan.jpa.po.IdCreateUpdatePidPO;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 包含菜单、按钮两种权限(AnanPermission)实体类
 *
 * @author fosin
 * @date 2019-01-27 17:03:13
 * @since 1.0.0
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@DynamicUpdate
@Table(name = "anan_user_all_permissions")
@ApiModel(value = "用户的所有权限，包含角色权限、用户增减权限", description = "试图(anan_user_all_permissions)用户的所有权限，包含角色权限、用户增减权限")
public class UserAllPermissions extends IdCreateUpdatePidPO<Long> {
    private static final long serialVersionUID = 5284105099273855621L;

    @Basic
    @ApiModelProperty(value = "权限编码，不能重复 不能为空", required = true)
    @Column(name = "code", nullable = false, length = 64)
    private String code;

    @Basic
    @ApiModelProperty(value = "权限名称", required = true)
    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @Basic
    @ApiModelProperty(value = "该字段必须和type字段共同使用，详情请看type字段")
    @Column(name = "url")
    private String url;

    @Basic
    @ApiModelProperty(value = "权限类型：0=按钮、1=组件菜单，对应ur是前端组件l、2=链接菜单，对应url是http(s)链接地址、3=目录菜单，对应是目录菜单，具体取值于字典表anan_dictionary.id=13，当权限类型是1：组件菜单 3：目录菜单时表示该节点不是一个叶子节点", required = true)
    @Column(name = "type", nullable = false)
    private Integer type;

    @Basic
    @ApiModelProperty(value = "菜单层级", required = true)
    @Column(name = "level", nullable = false)
    private Integer level;

    @Basic
    @ApiModelProperty(value = "排序，用于显示数据时的顺序，数值越小越靠前")
    @Column(name = "sort")
    private Integer sort;

    @Basic
    @ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.id=11", required = true)
    @Column(name = "status", nullable = false)
    private Integer status;

    @Basic
    @ApiModelProperty(value = "所属服务：等同于anan_service.id", required = true)
    @Column(name = "service_id", nullable = false)
    private Long serviceId;

    @Basic
    @ApiModelProperty(value = "后台请求权限地址，权限路径ant风格表达式，用于动态验证HTTP后台请求的权限标识")
    @Column(name = "path", length = 64)
    private String path;

    @Basic
    @ApiModelProperty(value = "路由地址，权限路径ant风格表达式，默认等于code")
    @Column(name = "route_path", length = 64)
    private String routePath;

    @Basic
    @ApiModelProperty(value = "http请求方法：GET、POST、DELETE、OPTIONS、PUT、PATCH，具体取值于字典表anan_dictionary.id=12")
    @Column(name = "method", length = 64)
    private String method;

    @Basic
    @ApiModelProperty(value = "一般用于前端菜单选项前的图标")
    @Column(name = "icon", length = 64)
    private String icon;

    @Basic
    @ApiModelProperty(value = "用户序号", required = true)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Basic
    @ApiModelProperty(value = "补充方式：0=增加权限、1=删除权限", required = true)
    @Column(name = "add_mode", nullable = false)
    private Integer addMode;

    @Basic
    @ApiModelProperty(value = "机构序号", required = true)
    @Column(name = "organiz_id", nullable = false)
    private Long organizId;
}
