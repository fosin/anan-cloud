package com.github.fosin.cdp.platform.dto.request;

import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import com.github.fosin.cdp.util.DateTimeUtil;

/**
 * 系统机构授权表(CdpSysOrganizationAuth)查询DTO
 *
 * @author fosin
 * @date 2019-01-28 11:45:17
 * @since 1.0.0
 */
@Data
@ApiModel(value = "系统机构授权表查询DTO", description = "表(cdp_sys_organization_auth)的对应的查询DTO")
public class CdpSysOrganizationAuthRetrieveDto implements Serializable {
    private static final long serialVersionUID = -90780975945559727L;
    
    @NotNull
    @ApiModelProperty(value = "机构授权ID", example = "Long")
    private Long id;

    @NotNull
    @ApiModelProperty(value = "机构ID", example = "Long")
    private Long organizId;

    @NotNull
    @ApiModelProperty(value = "版本ID", example = "Long")
    private Long versionId;

    @NotNull
    @ApiModelProperty(value = "订单ID", example = "Long")
    private Long orderId;

    @NotBlank
    @ApiModelProperty(value = "授权码", example = "String")
    private String authorizationCode;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "创建日期，该值由后台维护，更改数据时前端不需要关心", example = "Date")
    private Date createTime;

    @NotNull
    @ApiModelProperty(value = "该值由后台维护，更改数据时前端不需要关心，取值于cdp_sys_user.id", example = "Long")
    private Long createBy;

    @NotNull
    @ApiModelProperty(value = "有效期：一般按天计算", example = "Integer")
    private Integer validity;

    @NotNull
    @ApiModelProperty(value = "到期后保护期", example = "Integer")
    private Integer protectDays;

    @NotNull
    @ApiModelProperty(value = "最大机构数：0=无限制 n=限制数", example = "Integer")
    private Integer maxOrganizs;

    @NotNull
    @ApiModelProperty(value = "最大机构数：0=无限制 n=限制数", example = "Integer")
    private Integer maxUsers;

    @NotNull
    @ApiModelProperty(value = "是否试用：0=不试用 1=试用", example = "Integer")
    private Integer tryout;

    @NotNull
    @ApiModelProperty(value = "试用天数", example = "Integer")
    private Integer tryoutDays;

}