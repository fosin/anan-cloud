package top.fosin.anan.auth.modules.oauth2client.dto;



import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.Duration;

@Data
@EqualsAndHashCode
@ToString(callSuper = true)
@Schema(description = "OAUTH2令牌设置")
public class TokenSettingsDTO {

    @NotNull(message = "令牌格式" + "{jakarta.validation.constraints.NotNull.message}")
    @Positive(message = "令牌格式" + "{jakarta.validation.constraints.Positive.message}")
    @Schema(description = "令牌格式", example = "reference")
    //使用透明方式，默认是 OAuth2TokenFormat SELF_CONTAINED
    private OAuth2TokenFormat accessTokenFormat = OAuth2TokenFormat.REFERENCE;

    @NotNull(message = "授权令牌有效期" + "{jakarta.validation.constraints.NotNull.message}")
    @Positive(message = "刷新令牌有效期" + "{jakarta.validation.constraints.Positive.message}")
    @Schema(description = "授权令牌有效期", example = "1")
    private Duration accessTokenTimeToLive;

    @NotNull(message = "刷新令牌有效期" + "{jakarta.validation.constraints.NotNull.message}")
    @Positive(message = "刷新令牌有效期" + "{jakarta.validation.constraints.Positive.message}")
    @Schema(description = "刷新令牌有效期", example = "1")
    private Duration refreshTokenTimeToLive;

    @NotNull(message = "授权码有效期" + "{jakarta.validation.constraints.NotNull.message}")
    @Positive(message = "授权码有效期" + "{jakarta.validation.constraints.Positive.message}")
    @Schema(description = "授权码有效期", example = "1")
    private Duration authorizationCodeTimeToLive;

    @NotNull(message = "重用刷新令牌" + "{jakarta.validation.constraints.NotNull.message}")
    @Schema(description = "重用刷新令牌", example = "true")
    private Boolean reuseRefreshTokens;

    @NotNull(message = "ID令牌加密算法" + "{jakarta.validation.constraints.NotNull.message}")
    @Schema(description = "ID令牌加密算法", example = "Integer")
    private SignatureAlgorithm idTokenSignatureAlgorithm;

}
