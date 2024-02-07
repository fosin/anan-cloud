package top.fosin.anan.platform.modules.organization.dto;


import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.data.entity.Id;
import top.fosin.anan.data.valid.group.Update;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

/**
 * 系统机构授权表(anan_organization_auth)更新DTO
 *
 * @author fosin
 * @date 2023-05-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Schema(description = "系统机构授权表(anan_organization_auth)更新DTO")
public class OrganizationAuthUpdateDTO extends Id<Long> {
    private static final long serialVersionUID = 107240940921084439L;

    @NotNull(message = "机构序号" + "{jakarta.validation.constraints.NotNull.message}", groups = Update.class)
    @Positive(message = "机构序号" + "{jakarta.validation.constraints.Positive.message}", groups = Update.class)
    @Schema(description = "机构序号")
    private Long organizId;

    @NotNull(message = "版本序号" + "{jakarta.validation.constraints.NotNull.message}", groups = Update.class)
    @Positive(message = "版本序号" + "{jakarta.validation.constraints.Positive.message}", groups = Update.class)
    @Schema(description = "版本序号")
    private Long versionId;

    @NotNull(message = "订单序号" + "{jakarta.validation.constraints.NotNull.message}", groups = Update.class)
    @Positive(message = "订单序号" + "{jakarta.validation.constraints.Positive.message}", groups = Update.class)
    @Schema(description = "订单序号")
    private Long orderId;

    @NotBlank(message = "授权码" + "{jakarta.validation.constraints.NotBlank.message}", groups = Update.class)
    @Schema(description = "授权码")
    private String authorizationCode;

    @NotNull(message = "有效期" + "{jakarta.validation.constraints.NotNull.message}", groups = Update.class)
    @Positive(message = "有效期" + "{jakarta.validation.constraints.Positive.message}", groups = Update.class)
    @Schema(description = "有效期：一般按天计算")
    private Integer validity;

    @NotNull(message = "到期后保护期" + "{jakarta.validation.constraints.NotNull.message}", groups = Update.class)
    @Positive(message = "到期后保护期" + "{jakarta.validation.constraints.Positive.message}", groups = Update.class)
    @Schema(description = "到期后保护期")
    private Integer protectDays;

    @NotNull(message = "最大机构数" + "{jakarta.validation.constraints.NotNull.message}", groups = Update.class)
    @Positive(message = "最大机构数" + "{jakarta.validation.constraints.Positive.message}", groups = Update.class)
    @Schema(description = "最大机构数：0=无限制 n=限制数")
    private Integer maxOrganizs;

    @NotNull(message = "最大机构数" + "{jakarta.validation.constraints.NotNull.message}", groups = Update.class)
    @Positive(message = "最大机构数" + "{jakarta.validation.constraints.Positive.message}", groups = Update.class)
    @Schema(description = "最大机构数：0=无限制 n=限制数")
    private Integer maxUsers;

    @NotNull(message = "是否试用" + "{jakarta.validation.constraints.NotNull.message}", groups = Update.class)
    @PositiveOrZero(message = "是否试用" + "{jakarta.validation.constraints.Positive.message}", groups = Update.class)
    @Schema(description = "是否试用：0=不试用 1=试用")
    private Integer tryout;

    @NotNull(message = "试用天数" + "{jakarta.validation.constraints.NotNull.message}", groups = Update.class)
    @Positive(message = "试用天数" + "{jakarta.validation.constraints.Positive.message}", groups = Update.class)
    @Schema(description = "试用天数")
    private Integer tryoutDays;

}
