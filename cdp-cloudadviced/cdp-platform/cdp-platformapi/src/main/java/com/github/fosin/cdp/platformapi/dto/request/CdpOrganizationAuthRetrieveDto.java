package com.github.fosin.cdp.platformapi.dto.request;

import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import com.github.fosin.cdp.util.DateTimeUtil;

/**
 * 系统机构授权表(CdpOrganizationAuth)查询DTO
 *
 * @author fosin
 * @date 2019-02-19 18:14:31
 * @since 1.0.0
 */
@Data
@ApiModel(value = "系统机构授权表查询DTO", description = "表(cdp_organization_auth)的对应的查询DTO")
public class CdpOrganizationAuthRetrieveDto implements Serializable {
    private static final long serialVersionUID = -38645253818049878L;
    
    @ApiModelProperty(value = "机构授权ID, 主键", example = "Long")
    private Long id;

    @ApiModelProperty(value = "机构ID", example = "Long")
    private Long organizId;

    @ApiModelProperty(value = "版本ID", example = "Long")
    private Long versionId;

    @ApiModelProperty(value = "订单ID", example = "Long")
    private Long orderId;

    @ApiModelProperty(value = "授权码", example = "String")
    private String authorizationCode;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "创建日期，该值由后台维护，更改数据时前端不需要关心", example = "Date")
    private Date createTime;

    @ApiModelProperty(value = "该值由后台维护，更改数据时前端不需要关心，取值于cdp_user.id", example = "Long")
    private Long createBy;

    @ApiModelProperty(value = "有效期：一般按天计算", example = "Integer")
    private Integer validity;

    @ApiModelProperty(value = "到期后保护期", example = "Integer")
    private Integer protectDays;

    @ApiModelProperty(value = "最大机构数：0=无限制 n=限制数", example = "Integer")
    private Integer maxOrganizs;

    @ApiModelProperty(value = "最大机构数：0=无限制 n=限制数", example = "Integer")
    private Integer maxUsers;

    @ApiModelProperty(value = "是否试用：0=不试用 1=试用", example = "Integer")
    private Integer tryout;

    @ApiModelProperty(value = "试用天数", example = "Integer")
    private Integer tryoutDays;

}