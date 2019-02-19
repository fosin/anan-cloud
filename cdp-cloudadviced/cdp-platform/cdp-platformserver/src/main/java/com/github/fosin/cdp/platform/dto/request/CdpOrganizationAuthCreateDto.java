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
 * 系统机构授权表(CdpOrganizationAuth)创建DTO
 *
 * @author fosin
 * @date 2019-02-19 18:14:31
 * @since 1.0.0
 */
@Data
@ApiModel(value = "系统机构授权表创建DTO", description = "表(cdp_organization_auth)的对应的创建DTO")
public class CdpOrganizationAuthCreateDto implements Serializable {
    private static final long serialVersionUID = 456236738061734028L;
    
    @NotNull(message = "机构ID" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "机构ID", example = "Long", required = true)
    private Long organizId;

    @NotNull(message = "版本ID" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "版本ID", example = "Long", required = true)
    private Long versionId;

    @NotNull(message = "订单ID" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "订单ID", example = "Long", required = true)
    private Long orderId;

    @NotBlank(message = "授权码" + "{org.hibernate.validator.constraints.NotBlank.message}")
    @ApiModelProperty(value = "授权码", example = "String", required = true)
    private String authorizationCode;

    @NotNull(message = "有效期：一般按天计算" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "有效期：一般按天计算", example = "Integer", required = true)
    private Integer validity;

    @NotNull(message = "到期后保护期" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "到期后保护期", example = "Integer", required = true)
    private Integer protectDays;

    @NotNull(message = "最大机构数：0=无限制 n=限制数" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "最大机构数：0=无限制 n=限制数", example = "Integer", required = true)
    private Integer maxOrganizs;

    @NotNull(message = "最大机构数：0=无限制 n=限制数" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "最大机构数：0=无限制 n=限制数", example = "Integer", required = true)
    private Integer maxUsers;

    @NotNull(message = "是否试用：0=不试用 1=试用" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "是否试用：0=不试用 1=试用", example = "Integer", required = true)
    private Integer tryout;

    @NotNull(message = "试用天数" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "试用天数", example = "Integer", required = true)
    private Integer tryoutDays;

}