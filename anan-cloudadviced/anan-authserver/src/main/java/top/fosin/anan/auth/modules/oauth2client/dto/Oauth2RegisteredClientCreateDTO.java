package top.fosin.anan.auth.modules.oauth2client.dto;


import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.data.valid.group.Create;
import top.fosin.anan.data.valid.group.Update;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

/**
 * OAUTH2认证客户端注册表(oauth2_registered_client)创建DTO
 *
 * @author fosin
 * @date 2023-05-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Schema(description = "OAUTH2认证客户端注册表(oauth2_registered_client)创建DTO")
public class Oauth2RegisteredClientCreateDTO {
    private static final long serialVersionUID = 372060057450849410L;

    @NotBlank(message = "客户端序号" + "{jakarta.validation.constraints.NotBlank.message}", groups = Create.class)
    @Schema(description = "客户端序号", example = "String")
    private String clientId;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Schema(description = "发布日期", example = "Date")
    private Date clientIdIssuedAt;

    @Schema(description = "客户端密钥", example = "String")
    private String clientSecret;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Schema(description = "过期时间", example = "Date")
    private Date clientSecretExpiresAt;

    @NotBlank(message = "客户端名称" + "{jakarta.validation.constraints.NotBlank.message}", groups = Create.class)
    @Schema(description = "客户端名称", example = "String")
    private String clientName;

    @NotEmpty(message = "认证方法" + "{jakarta.validation.constraints.NotEmpty.message}", groups = Update.class)
    @Schema(description = "认证方法", example = "String")
    private Set<ClientAuthenticationMethod> clientAuthenticationMethods;

    @NotEmpty(message = "认证类型" + "{jakarta.validation.constraints.NotEmpty.message}", groups = Update.class)
    @Schema(description = "认证类型", example = "String")
    private Set<AuthorizationGrantType> authorizationGrantTypes;

    @Schema(description = "跳转地址", example = "String")
    @NotEmpty(message = "跳转地址" + "{jakarta.validation.constraints.NotEmpty.message}", groups = Update.class)
    private Set<String> redirectUris;

    @NotEmpty(message = "作用域" + "{jakarta.validation.constraints.NotEmpty.message}", groups = Update.class)
    @Schema(description = "作用域", example = "String")
    private Set<String> scopes;

    @NotNull(message = "客户端设置" + "{jakarta.validation.constraints.NotNull.message}", groups = Update.class)
    @Schema(description = "客户端设置", example = "String")
    private ClientSettingsDTO clientSettings;

    @NotNull(message = "令牌设置" + "{jakarta.validation.constraints.NotNull.message}", groups = Update.class)
    @Schema(description = "令牌设置", example = "String")
    private TokenSettingsDTO tokenSettings;

}
