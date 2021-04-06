package com.github.fosin.anan.platform.dto.request;

import com.github.fosin.anan.core.util.DateTimeUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统机构授权表(AnanOrganizationAuth)查询DTO
 *
 * @author fosin
 * @date 2019-02-19 18:14:31
 * @since 1.0.0
 */
@Data
@ApiModel(value = "系统机构授权表查询DTO", description = "表(anan_organization_auth)的对应的查询DTO")
public class AnanOrganizationAuthRetrieveDto implements Serializable {
    private static final long serialVersionUID = -38645253818049878L;

    @ApiModelProperty(value = "机构授权ID, 主键")
    private Long id;

    @ApiModelProperty(value = "机构ID")
    private Long organizId;

    @ApiModelProperty(value = "版本ID")
    private Long versionId;

    @ApiModelProperty(value = "订单ID")
    private Long orderId;

    @ApiModelProperty(value = "授权码")
    private String authorizationCode;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "创建日期，该值由后台维护，更改数据时前端不需要关心")
    private Date createTime;

    @ApiModelProperty(value = "该值由后台维护，更改数据时前端不需要关心，取值于anan_user.id")
    private Long createBy;

    @ApiModelProperty(value = "有效期：一般按天计算")
    private Integer validity;

    @ApiModelProperty(value = "到期后保护期")
    private Integer protectDays;

    @ApiModelProperty(value = "最大机构数：0=无限制 n=限制数")
    private Integer maxOrganizs;

    @ApiModelProperty(value = "最大机构数：0=无限制 n=限制数")
    private Integer maxUsers;

    @ApiModelProperty(value = "是否试用：0=不试用 1=试用")
    private Integer tryout;

    @ApiModelProperty(value = "试用天数")
    private Integer tryoutDays;

}
