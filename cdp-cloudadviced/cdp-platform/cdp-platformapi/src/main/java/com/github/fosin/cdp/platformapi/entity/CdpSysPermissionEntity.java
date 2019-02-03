package com.github.fosin.cdp.platformapi.entity;

import com.github.fosin.cdp.jpa.entity.AbstractCreateUpdateJpaEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;
import springfox.documentation.annotations.ApiIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 包含菜单、按钮两种权限(CdpSysPermission)实体类
 *
 * @author fosin
 * @date 2019-01-27 17:03:13
 * @since 1.0.0
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@DynamicUpdate
@Table(name = "cdp_sys_permission")
@ApiModel(value = "包含菜单、按钮两种权限实体类", description = "表(cdp_sys_permission)的对应的实体类")
public class CdpSysPermissionEntity extends AbstractCreateUpdateJpaEntity<Long, Long> implements Serializable {
    private static final long serialVersionUID = -77218975959330473L;

    @Transient
    @ApiModelProperty(value = "孩子节点，虚拟字段，增删改时不需要关心", notes = "孩子节点，虚拟字段，增删改时不需要关心")
    private List<CdpSysPermissionEntity> children;


    @Transient
    @ApiModelProperty(value = "是否叶子节点，虚拟字段，增删改时不需要关心", notes = "是否叶子节点，虚拟字段，增删改时不需要关心")
    private Boolean leaf;

    /**
     * 当权限类型是1：组件菜单 3：目录菜单 表示该节点不是一个叶子节点
     */
    @ApiIgnore
    public Boolean getLeaf() {
        this.leaf = this.type != 1 && this.type != 3;
        return leaf;
    }

    public void setLeaf(Boolean leaf) {
        this.leaf = leaf;
    }

    @Basic
    @ApiModelProperty(value = "权限编码，不能重复 不能为空", required = true)
    @Column(name = "code", nullable = false, length = 64)
    private String code;

    @Basic
    @ApiModelProperty(value = "父权限ID，取值于id，表示当前数据的父类权限", required = true)
    @Column(name = "p_id", nullable = false)
    private Long pId;

    @Basic
    @ApiModelProperty(value = "权限名称", required = true)
    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @Basic
    @ApiModelProperty(value = "该字段必须和type字段共同使用，详情请看type字段")
    @Column(name = "url", length = 255)
    private String url;

    @Basic
    @ApiModelProperty(value = "权限类型：0=按钮、1=组件菜单，对应ur是前端组件l、2=链接菜单，对应url是http(s)链接地址、3=目录菜单，对应是目录菜单，具体取值于字典表cdp_sys_dictionary.id=13，当权限类型是1：组件菜单 3：目录菜单时表示该节点不是一个叶子节点", required = true)
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
    @ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表cdp_sys_dictionary.id=11", required = true)
    @Column(name = "status", nullable = false)
    private Integer status;

    @Basic
    @ApiModelProperty(value = "所属应用名称,等同于配置文件中的spring.application.name", required = true)
    @Column(name = "app_name", nullable = false, length = 64)
    private String appName;

    @Basic
    @ApiModelProperty(value = "后台请求权限地址，权限路径ant风格表达式，用于动态验证HTTP后台请求的权限标识")
    @Column(name = "path", length = 64)
    private String path;

    @Basic
    @ApiModelProperty(value = "http请求方法：GET、POST、DELETE、OPTIONS、PUT、PATCH，具体取值于字典表cdp_sys_dictionary.id=12")
    @Column(name = "method", length = 64)
    private String method;

    @Basic
    @ApiModelProperty(value = "一般用于前端菜单选项前的图标")
    @Column(name = "icon", length = 64)
    private String icon;

}