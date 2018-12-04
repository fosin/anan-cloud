package com.github.fosin.cdp.platformapi.entity;

import com.github.fosin.cdp.util.DateTimeUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
/**
 * 系统版本表(CdpSysVersion)实体类
 *
 * @author fosin
 * @date 2018-12-03 15:20:20
 * @since 1.0.0
 */
@Data
@Entity
@DynamicUpdate
@Table(name = "cdp_sys_version")
@ApiModel(value = "系统版本表实体类", description = "表(cdp_sys_version)的对应的实体类")
public class CdpSysVersionEntity implements Serializable {
    private static final long serialVersionUID = -47876152527742919L;
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "主键，系统自动生成,版本ID")
    private Long id;
    
    @Basic
    @NotBlank
    @Column(name = "name")
    @ApiModelProperty(value = "版本名称")
    private String name;
    
    @Basic
    @NotNull
    @Column(name = "type")
    @ApiModelProperty(value = "版本类型：0=收费版 1=免费版 2=开发版")
    private Integer type;
    
    @Basic
    @NotNull
    @Column(name = "price")
    @ApiModelProperty(value = "版本价格")
    private Double price;
    
    @Basic
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Column(name = "begin_time")
    @ApiModelProperty(value = "开始日期")
    private Date beginTime;
    
    @Basic
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Column(name = "end_time")
    @ApiModelProperty(value = "结束日期")
    private Date endTime;
    
    @Basic
    @NotNull
    @Column(name = "validity")
    @ApiModelProperty(value = "有效期：一般按天计算")
    private Integer validity;
    
    @Basic
    @NotNull
    @Column(name = "protect_days")
    @ApiModelProperty(value = "到期后保护期")
    private Integer protectDays;
    
    @Basic
    @NotNull
    @Column(name = "max_organizs")
    @ApiModelProperty(value = "最大机构数：0=无限制 n=限制数")
    private Integer maxOrganizs;
    
    @Basic
    @NotNull
    @Column(name = "max_users")
    @ApiModelProperty(value = "最大机构数：0=无限制 n=限制数")
    private Integer maxUsers;
    
    @Basic
    @NotNull
    @Column(name = "tryout")
    @ApiModelProperty(value = "是否试用：0=不试用 1=试用")
    private Integer tryout;
    
    @Basic
    @NotNull
    @Column(name = "tryout_days")
    @ApiModelProperty(value = "试用天数")
    private Integer tryoutDays;
    
    @Basic
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Column(name = "create_time")
    @ApiModelProperty(value = "创建日期")
    private Date createTime;
    
    @Basic
    @NotNull
    @Column(name = "status")
    @ApiModelProperty(value = "启用状态：0=启用，1=禁用")
    private Integer status;
    
    @Basic
    @Column(name = "description")
    @ApiModelProperty(value = "版本描述")
    private String description;
    
}