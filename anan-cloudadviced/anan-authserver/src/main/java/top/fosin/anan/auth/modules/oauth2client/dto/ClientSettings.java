package top.fosin.anan.auth.modules.oauth2client.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.security.oauth2.jose.jws.JwsAlgorithm;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode
@ToString(callSuper = true)
@ApiModel(value = "OAUTH2客户端设置", description = "OAUTH2客户端设置")
public class ClientSettings {

    @NotNull(message = "需要用户权限确认" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "需要用户权限确认", required = true, example = "true")
    private Boolean requireAuthorizationConsent;


    @NotNull(message = "需要ProofKey" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "需要ProofKey", required = true, example = "true")
    private Boolean requireProofKey;

    @NotBlank(message = "jwk地址" + "{javax.validation.constraints.NotBlank.message}")
    @ApiModelProperty(value = "jwk地址", required = true, example = "true")
    private String jwkSetUrl;

    @NotNull(message = "ID令牌加密算法" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "ID令牌加密算法", required = true, example = "Integer")
    private JwsAlgorithm tokenEndpointAuthenticationSigningAlgorithm;
}
