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
 * 系统机构授权表(CdpSysOrganizationAuth)实体类
 *
 * @author fosin
 * @date 2018-11-18 17:28:24
 * @since 1.0.0
 */
@Data
@Entity
@DynamicUpdate
@Table(name = "cdp_sys_organization_auth")
@ApiModel(value = "系统机构授权表实体类", description = "表(cdp_sys_organization_auth)的对应的实体类")
public class CdpSysOrganizationAuthEntity implements Serializable {
    private static final long serialVersionUID = -32523459261341634L;
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "主键，系统自动生成,机构授权ID")
    private Long id;
    
    @Basic
    @NotNull
    @Column(name = "organiz_id")
    @ApiModelProperty(value = "机构ID")
    private Long organizId;
    
    @Basic
    @NotNull
    @Column(name = "version_id")
    @ApiModelProperty(value = "版本ID")
    private Long versionId;
    
    @Basic
    @NotNull
    @Column(name = "order_id")
    @ApiModelProperty(value = "订单ID")
    private Long orderId;
    
    @Basic
    @NotBlank
    @Column(name = "authorization_code")
    @ApiModelProperty(value = "授权码")
    private String authorizationCode;
    
    @Basic
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Column(name = "create_time")
    @ApiModelProperty(value = "创建日期，该值由后台维护，更改数据时前端不需要关心")
    private Date createTime;
    
    @Basic
    @NotNull
    @Column(name = "create_by")
    @ApiModelProperty(value = "该值由后台维护，更改数据时前端不需要关心，取值于cdp_sys_user.id")
    private Long createBy;
    
}