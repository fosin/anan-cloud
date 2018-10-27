package com.github.fosin.cdp.platformapi.entity;

import java.util.Date;

import com.github.fosin.cdp.util.DateTimeUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 系统用户表(CdpSysUser)实体类
 *
 * @author fosin
 * @date 2018-10-27 09:38:39
 * @since 1.0.0
 */
@Data
@Entity
@DynamicUpdate
@Table(name = "cdp_sys_user")
@ApiModel(value = "系统用户表实体类", description = "表(cdp_sys_user)的对应的实体类")
public class CdpSysUserEntity implements Serializable {
    private static final long serialVersionUID = 897030139778409164L;

    /**
     * orphanRemoval=true配置表明删除无关联的数据。级联更新子结果集时此配置最关键
     */
    @OneToMany(orphanRemoval = true, cascade = {CascadeType.REFRESH, CascadeType.REMOVE})
    @JoinColumn(name = "user_id")
    private List<CdpSysUserRoleEntity> userRoles;

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "用户ID", notes = "主键，系统自动生成,用户ID")
    private Long id;
    
    @Column(name = "organiz_id")
    @Basic
    @NotNull
    @ApiModelProperty(value = "机构ID", notes = "机构ID")
    private Long organizId;
    
    @Column(name = "usercode")
    @Basic
    @NotBlank
    @ApiModelProperty(value = "用户工号", notes = "用户工号")
    private String usercode;
    
    @Column(name = "username")
    @Basic
    @NotBlank
    @ApiModelProperty(value = "用户姓名", notes = "用户姓名")
    private String username;
    
    @Column(name = "password")
    @Basic
    @NotBlank
    @ApiModelProperty(value = "传入原始密码，后台会对原始密码进行加密后再存储", notes = "传入原始密码，后台会对原始密码进行加密后再存储")
    private String password;
    
    @Column(name = "birthday")
    @Basic
    @Past
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "生日", notes = "生日")
    private Date birthday;
    
    @Column(name = "sex")
    @Basic
    @ApiModelProperty(value = "使用状态：具体取值于字典表cdp_sys_dictionary.code=15", notes = "使用状态：具体取值于字典表cdp_sys_dictionary.code=15")
    private Integer sex;
    
    @Column(name = "email")
    @Basic
    @ApiModelProperty(value = "电子邮箱", notes = "电子邮箱")
    private String email;
    
    @Column(name = "phone")
    @Basic
    @ApiModelProperty(value = "手机号码", notes = "手机号码")
    private String phone;
    
    @Column(name = "status")
    @Basic
    @NotNull
    @ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表cdp_sys_dictionary.code=11", notes = "使用状态：0=启用，1=禁用，具体取值于字典表cdp_sys_dictionary.code=11")
    private Integer status;
    
    @Column(name = "avatar")
    @Basic
    @ApiModelProperty(value = "头像", notes = "头像")
    private String avatar;
    
    @Column(name = "create_time")
    @Basic
    @NotNull
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "创建日期，该值由后台维护，更改数据时前端不需要关心", notes = "创建日期，该值由后台维护，更改数据时前端不需要关心")
    private Date createTime;
    
    @Column(name = "create_by")
    @Basic
    @NotNull
    @ApiModelProperty(value = "创建人，该值由后台维护，更改数据时前端不需要关心，取值于cdp_sys_user.id", notes = "创建人，该值由后台维护，更改数据时前端不需要关心，取值于cdp_sys_user.id")
    private Long createBy;
    
    @Column(name = "update_time")
    @Basic
    @NotNull
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "更新日期，该值由后台维护，更改数据时前端不需要关心", notes = "更新日期，该值由后台维护，更改数据时前端不需要关心")
    private Date updateTime;
    
    @Column(name = "update_by")
    @Basic
    @NotNull
    @ApiModelProperty(value = "更新人，该值由后台维护，更改数据时前端不需要关心，取值于cdp_sys_user.id", notes = "更新人，该值由后台维护，更改数据时前端不需要关心，取值于cdp_sys_user.id")
    private Long updateBy;
    
    @Column(name = "expire_time")
    @Basic
    @NotNull
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "过期时间，账户过期后用户被锁定切不能登录系统", notes = "过期时间，账户过期后用户被锁定切不能登录系统")
    private Date expireTime;
    
}