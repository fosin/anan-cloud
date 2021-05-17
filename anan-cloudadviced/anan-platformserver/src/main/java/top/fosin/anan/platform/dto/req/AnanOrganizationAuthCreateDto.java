package top.fosin.anan.platform.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.Data;

/**
 * 系统机构授权表(AnanOrganizationAuth)创建DTO
 *
 * @author fosin
 * @date 2019-02-19 18:14:31
 * @since 1.0.0
 */
@Data
@ApiModel(value = "系统机构授权表创建DTO", description = "表(anan_organization_auth)的对应的创建DTO")
public class AnanOrganizationAuthCreateDto implements Serializable {
    private static final long serialVersionUID = 456236738061734028L;

    @NotNull(message = "机构ID" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "机构ID", required = true)
    private Long organizId;

    @NotNull(message = "版本ID" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "版本ID", required = true)
    private Long versionId;

    @NotNull(message = "订单ID" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "订单ID", required = true)
    private Long orderId;

    @NotBlank(message = "授权码" + "{javax.validation.constraints.NotBlank.message}")
    @ApiModelProperty(value = "授权码", required = true)
    private String authorizationCode;

    @NotNull(message = "有效期" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "有效期：一般按天计算", required = true)
    private Integer validity;

    @NotNull(message = "到期后保护期" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "到期后保护期", required = true)
    private Integer protectDays;

    @NotNull(message = "最大机构数" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "最大机构数：0=无限制 n=限制数", required = true)
    private Integer maxOrganizs;

    @NotNull(message = "最大机构数" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "最大机构数：0=无限制 n=限制数", required = true)
    private Integer maxUsers;

    @NotNull(message = "是否试用" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "是否试用：0=不试用 1=试用", required = true)
    private Integer tryout;

    @NotNull(message = "试用天数" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "试用天数", required = true)
    private Integer tryoutDays;

}
