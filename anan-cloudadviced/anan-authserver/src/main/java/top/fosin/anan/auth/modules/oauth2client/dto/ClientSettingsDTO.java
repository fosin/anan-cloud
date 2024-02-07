package top.fosin.anan.auth.modules.oauth2client.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.security.oauth2.jose.jws.JwsAlgorithm;

import jakarta.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode
@ToString(callSuper = true)
@Schema(description = "OAUTH2客户端设置")
public class ClientSettingsDTO {

    @NotNull(message = "需要用户权限确认" + "{jakarta.validation.constraints.NotNull.message}")
    @Schema(description = "需要用户权限确认", example = "true")
    private Boolean requireAuthorizationConsent;


    @NotNull(message = "需要ProofKey" + "{jakarta.validation.constraints.NotNull.message}")
    @Schema(description = "需要ProofKey", example = "true")
    private Boolean requireProofKey;

    @NotBlank(message = "jwk地址" + "{jakarta.validation.constraints.NotBlank.message}")
    @Schema(description = "jwk地址", example = "http://127.0.0.1/jwks")
    private String jwkSetUrl;

    @NotNull(message = "ID令牌加密算法" + "{jakarta.validation.constraints.NotNull.message}")
    @Schema(description = "ID令牌加密算法", example = "")
    private JwsAlgorithm tokenEndpointAuthenticationSigningAlgorithm;
}
