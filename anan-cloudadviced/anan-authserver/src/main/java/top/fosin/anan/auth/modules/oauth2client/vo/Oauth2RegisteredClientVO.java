package top.fosin.anan.auth.modules.oauth2client.vo;



import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import top.fosin.anan.auth.modules.oauth2client.dto.ClientSettingsDTO;
import top.fosin.anan.auth.modules.oauth2client.dto.TokenSettingsDTO;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.data.entity.Id;

import java.util.Date;
import java.util.Set;

/**
 * OAUTH2认证客户端注册表(oauth2_registered_client)单体VO
 *
 * @author fosin
 * @date 2023-05-13
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "OAUTH2认证客户端注册表(oauth2_registered_client)单体VO")
public class Oauth2RegisteredClientVO extends Id<String> {
    private static final long serialVersionUID = -74495448187040209L;
    @Schema(description = "客户端序号")
    private String clientId;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Schema(description = "发布日期")
    private Date clientIdIssuedAt;

    @Schema(description = "客户端密钥")
    private String clientSecret;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Schema(description = "过期时间")
    private Date clientSecretExpiresAt;

    @Schema(description = "客户端名称")
    private String clientName;

    @Schema(description = "认证方法")
    private Set<ClientAuthenticationMethod> clientAuthenticationMethods;

    @Schema(description = "认证类型")
    private Set<AuthorizationGrantType> authorizationGrantTypes;

    @Schema(description = "跳转地址")
    private Set<String> redirectUris;

    @Schema(description = "作用域")
    private Set<String> scopes;

    @Schema(description = "客户端设置")
    private ClientSettingsDTO clientSettings;

    @Schema(description = "令牌设置")
    private TokenSettingsDTO tokenSettings;

}
