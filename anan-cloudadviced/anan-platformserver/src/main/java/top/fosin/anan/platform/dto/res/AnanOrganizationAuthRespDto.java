package top.fosin.anan.platform.dto.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.model.dto.OrganizIdDto;

/**
 * 系统机构授权表(AnanOrganizationAuth)响应DTO
 *
 * @author fosin
 * @date 2021-05-16 14:32:14
 * @since 2.6.0
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "机构授权表响应DTO", description = "机构授权的响应DTO")
public class AnanOrganizationAuthRespDto extends OrganizIdDto<Long> {
    private static final long serialVersionUID = -14631872198139889L;

    @ApiModelProperty(value = "版本序号", example = "Long")
    private Long versionId;

    @ApiModelProperty(value = "订单序号", example = "Long")
    private Long orderId;

    @ApiModelProperty(value = "授权码", example = "String")
    private String authorizationCode;

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
