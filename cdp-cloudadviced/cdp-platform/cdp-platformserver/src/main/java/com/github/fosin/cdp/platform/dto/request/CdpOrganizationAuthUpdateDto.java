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
 * 系统机构授权表(CdpOrganizationAuth)更新DTO
 *
 * @author fosin
 * @date 2019-01-28 11:45:17
 * @since 1.0.0
 */
@Data
@ApiModel(value = "系统机构授权表更新DTO", description = "表(cdp_organization_auth)的对应的更新DTO")
public class CdpOrganizationAuthUpdateDto implements Serializable {
    private static final long serialVersionUID = 903404882478744327L;

    @NotNull
    @ApiModelProperty(value = "机构授权ID", example = "Long", required = true)
    private Long id;

    @NotNull
    @ApiModelProperty(value = "机构ID", example = "Long", required = true)
    private Long organizId;

    @NotNull
    @ApiModelProperty(value = "版本ID", example = "Long", required = true)
    private Long versionId;

    @NotNull
    @ApiModelProperty(value = "订单ID", example = "Long", required = true)
    private Long orderId;

    @NotBlank
    @ApiModelProperty(value = "授权码", example = "String", required = true)
    private String authorizationCode;

    @NotNull
    @ApiModelProperty(value = "有效期：一般按天计算", example = "Integer", required = true)
    private Integer validity;

    @NotNull
    @ApiModelProperty(value = "到期后保护期", example = "Integer", required = true)
    private Integer protectDays;

    @NotNull
    @ApiModelProperty(value = "最大机构数：0=无限制 n=限制数", example = "Integer", required = true)
    private Integer maxOrganizs;

    @NotNull
    @ApiModelProperty(value = "最大机构数：0=无限制 n=限制数", example = "Integer", required = true)
    private Integer maxUsers;

    @NotNull
    @ApiModelProperty(value = "是否试用：0=不试用 1=试用", example = "Integer", required = true)
    private Integer tryout;

    @NotNull
    @ApiModelProperty(value = "试用天数", example = "Integer", required = true)
    private Integer tryoutDays;

}
