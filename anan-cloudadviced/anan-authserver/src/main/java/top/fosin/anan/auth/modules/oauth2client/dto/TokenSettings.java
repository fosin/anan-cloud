package top.fosin.anan.auth.modules.oauth2client.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.Duration;

@Data
@EqualsAndHashCode
@ToString(callSuper = true)
@ApiModel(value = "OAUTH2令牌设置", description = "OAUTH2令牌设置")
public class TokenSettings {

    @NotNull(message = "令牌格式" + "{javax.validation.constraints.NotNull.message}")
    @Positive(message = "令牌格式" + "{javax.validation.constraints.Positive.message}")
    @ApiModelProperty(value = "令牌格式", required = true, example = "reference")
    private OAuth2TokenFormat accessTokenFormat = OAuth2TokenFormat.REFERENCE;

    @NotNull(message = "授权令牌有效期" + "{javax.validation.constraints.NotNull.message}")
    @Positive(message = "刷新令牌有效期" + "{javax.validation.constraints.Positive.message}")
    @ApiModelProperty(value = "授权令牌有效期", required = true, example = "1")
    private Duration accessTokenTimeToLive;

    @NotNull(message = "刷新令牌有效期" + "{javax.validation.constraints.NotNull.message}")
    @Positive(message = "刷新令牌有效期" + "{javax.validation.constraints.Positive.message}")
    @ApiModelProperty(value = "刷新令牌有效期", required = true, example = "1")
    private Duration refreshTokenTimeToLive;

    @NotNull(message = "授权码有效期" + "{javax.validation.constraints.NotNull.message}")
    @Positive(message = "授权码有效期" + "{javax.validation.constraints.Positive.message}")
    @ApiModelProperty(value = "授权码有效期", required = true, example = "1")
    private Duration authorizationCodeTimeToLive;

    @NotNull(message = "重用刷新令牌" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "重用刷新令牌", required = true, example = "true")
    private Boolean reuseRefreshTokens;

    @NotNull(message = "ID令牌加密算法" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "ID令牌加密算法", required = true, example = "Integer")
    private SignatureAlgorithm idTokenSignatureAlgorithm = SignatureAlgorithm.RS256;

}
