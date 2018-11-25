package com.github.fosin.cdp.platformapi.entity;

import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import com.github.fosin.cdp.util.DateTimeUtil;
/**
 * 系统版本表(CdpSysVersion)实体类
 *
 * @author fosin
 * @date 2018-11-18 17:28:24
 * @since 1.0.0
 */
@Data
@Entity
@DynamicUpdate
@Table(name = "cdp_sys_version")
@ApiModel(value = "系统版本表实体类", description = "表(cdp_sys_version)的对应的实体类")
public class CdpSysVersionEntity implements Serializable {
    private static final long serialVersionUID = 494035158572739735L;
    
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
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Column(name = "create_time")
    @ApiModelProperty(value = "创建日期")
    private Date createTime;
    
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
    @Column(name = "type")
    @ApiModelProperty(value = "版本类型：0=收费版 1=免费版 2=开发版")
    private Integer type;

    @Basic
    @NotNull
    @Column(name = "validity")
    @ApiModelProperty(value = "有效期：一般按天计算")
    private Integer validity;
    
    @Basic
    @NotNull
    @Column(name = "status")
    @ApiModelProperty(value = "启用状态：0=启用，1=禁用")
    private Integer status;
    
}