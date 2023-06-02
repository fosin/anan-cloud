package top.fosin.anan.platform.modules.organization.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.data.valid.group.Create;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

/**
 * 系统机构授权表(anan_organization_auth)创建DTO
 *
 * @author fosin
 * @date 2023-05-13
 */
@Data
@EqualsAndHashCode
@ToString(callSuper = true)
@ApiModel(value = "系统机构授权表创建DTO", description = "系统机构授权表(anan_organization_auth)创建DTO")
public class OrganizationAuthCreateDTO {
    private static final long serialVersionUID = -70243310973062012L;

    @NotNull(message = "机构序号" + "{javax.validation.constraints.NotNull.message}", groups = Create.class)
    @Positive(message = "机构序号" + "{javax.validation.constraints.Positive.message}", groups = Create.class)
    @ApiModelProperty(value = "机构序号", required = true)
    private Long organizId;

    @NotNull(message = "版本序号" + "{javax.validation.constraints.NotNull.message}", groups = Create.class)
    @Positive(message = "版本序号" + "{javax.validation.constraints.Positive.message}", groups = Create.class)
    @ApiModelProperty(value = "版本序号", required = true)
    private Long versionId;

    @NotNull(message = "订单序号" + "{javax.validation.constraints.NotNull.message}", groups = Create.class)
    @Positive(message = "订单序号" + "{javax.validation.constraints.Positive.message}", groups = Create.class)
    @ApiModelProperty(value = "订单序号", required = true)
    private Long orderId;

    @NotBlank(message = "授权码" + "{javax.validation.constraints.NotBlank.message}", groups = Create.class)
    @ApiModelProperty(value = "授权码", required = true)
    private String authorizationCode;

    @NotNull(message = "有效期" + "{javax.validation.constraints.NotNull.message}", groups = Create.class)
    @Positive(message = "有效期" + "{javax.validation.constraints.Positive.message}", groups = Create.class)
    @ApiModelProperty(value = "有效期：一般按天计算", required = true)
    private Integer validity;

    @NotNull(message = "到期后保护期" + "{javax.validation.constraints.NotNull.message}", groups = Create.class)
    @Positive(message = "到期后保护期" + "{javax.validation.constraints.Positive.message}", groups = Create.class)
    @ApiModelProperty(value = "到期后保护期", required = true)
    private Integer protectDays;

    @NotNull(message = "最大机构数" + "{javax.validation.constraints.NotNull.message}", groups = Create.class)
    @Positive(message = "最大机构数" + "{javax.validation.constraints.Positive.message}", groups = Create.class)
    @ApiModelProperty(value = "最大机构数：0=无限制 n=限制数", required = true)
    private Integer maxOrganizs;

    @NotNull(message = "最大机构数" + "{javax.validation.constraints.NotNull.message}", groups = Create.class)
    @Positive(message = "最大机构数" + "{javax.validation.constraints.Positive.message}", groups = Create.class)
    @ApiModelProperty(value = "最大机构数：0=无限制 n=限制数", required = true)
    private Integer maxUsers;

    @NotNull(message = "是否试用" + "{javax.validation.constraints.NotNull.message}", groups = Create.class)
    @PositiveOrZero(message = "是否试用" + "{javax.validation.constraints.Positive.message}", groups = Create.class)
    @ApiModelProperty(value = "是否试用：0=不试用 1=试用", required = true)
    private Integer tryout;

    @NotNull(message = "试用天数" + "{javax.validation.constraints.NotNull.message}", groups = Create.class)
    @Positive(message = "试用天数" + "{javax.validation.constraints.Positive.message}", groups = Create.class)
    @ApiModelProperty(value = "试用天数", required = true)
    private Integer tryoutDays;

}
