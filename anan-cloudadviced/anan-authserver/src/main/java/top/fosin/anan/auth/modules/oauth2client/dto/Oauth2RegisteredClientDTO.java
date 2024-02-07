package top.fosin.anan.auth.modules.oauth2client.dto;



import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.data.entity.Id;

import java.util.Date;
import java.util.Set;

/**
 * OAUTH2认证客户端注册表(oauth2_registered_client)DTO
 *
 * @author fosin
 * @date 2023-05-13
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "OAUTH2认证客户端注册表(oauth2_registered_client)DTO")
public class Oauth2RegisteredClientDTO extends Id<String> {
    private static final long serialVersionUID = 879671592005817991L;
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

    @Schema(description = "客户端名称", example = "String")
    private String clientName;

    @Schema(description = "认证方法", example = "String")
    private Set<ClientAuthenticationMethod> clientAuthenticationMethods;

    @Schema(description = "认证类型", example = "String")
    private Set<AuthorizationGrantType> authorizationGrantTypes;

    @Schema(description = "跳转地址", example = "String")
    private Set<String> redirectUris;

    @Schema(description = "作用域", example = "String")
    private Set<String> scopes;

    @Schema(description = "客户端设置")
    private ClientSettings clientSettings;

    @Schema(description = "令牌设置")
    private TokenSettings tokenSettings;

}
