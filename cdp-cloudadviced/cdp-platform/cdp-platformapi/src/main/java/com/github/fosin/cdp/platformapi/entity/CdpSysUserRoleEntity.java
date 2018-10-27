package com.github.fosin.cdp.platformapi.entity;

import com.github.fosin.cdp.util.DateTimeUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 系统用户角色表(CdpSysUserRole)实体类
 *
 * @author fosin
 * @date 2018-10-27 09:38:39
 * @since 1.0.0
 */
@Data
@Entity
@DynamicUpdate
@Table(name = "cdp_sys_user_role")
@ApiModel(value = "系统用户角色表实体类", description = "表(cdp_sys_user_role)的对应的实体类")
public class CdpSysUserRoleEntity implements Serializable {
    private static final long serialVersionUID = 907700875932623662L;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "role_id")
    @ApiModelProperty(value = "角色ID", notes = "取值于角色表cdp_sys_role.id")
    private CdpSysRoleEntity role;

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "用户角色ID", notes = "主键，系统自动生成,用户角色ID")
    private Long id;
    
    @Column(name = "organiz_id")
    @Basic
    @NotNull
    @ApiModelProperty(value = "机构ID", notes = "机构ID")
    private Long organizId;
    
    @Column(name = "user_id")
    @Basic
    @ApiModelProperty(value = "用户ID", notes = "用户ID")
    private Long userId;

    @Column(name = "create_time")
    @Basic
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "创建日期，该值由后台维护，更改数据时前端不需要关心", notes = "创建日期，该值由后台维护，更改数据时前端不需要关心")
    private Date createTime;
    
    @Column(name = "create_by")
    @Basic
    @ApiModelProperty(value = "该值由后台维护，更改数据时前端不需要关心，取值于cdp_sys_user.id", notes = "该值由后台维护，更改数据时前端不需要关心，取值于cdp_sys_user.id")
    private Long createBy;
    
}