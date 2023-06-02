package top.fosin.anan.auth.modules.oauth2client.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.data.valid.group.Create;
import top.fosin.anan.data.valid.group.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
@ApiModel(value = "OAUTH2认证客户端注册表创建DTO", description = "OAUTH2认证客户端注册表(oauth2_registered_client)创建DTO")
public class Oauth2RegisteredClientCreateDTO {
    private static final long serialVersionUID = 372060057450849410L;

    @NotBlank(message = "客户端序号" + "{javax.validation.constraints.NotBlank.message}", groups = Create.class)
    @ApiModelProperty(value = "客户端序号", required = true, example = "String")
    private String clientId;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "发布日期", required = true, example = "Date")
    private Date clientIdIssuedAt;

    @ApiModelProperty(value = "客户端密钥", example = "String")
    private String clientSecret;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "过期时间", example = "Date")
    private Date clientSecretExpiresAt;

    @NotBlank(message = "客户端名称" + "{javax.validation.constraints.NotBlank.message}", groups = Create.class)
    @ApiModelProperty(value = "客户端名称", required = true, example = "String")
    private String clientName;

    @NotEmpty(message = "认证方法" + "{javax.validation.constraints.NotEmpty.message}", groups = Update.class)
    @ApiModelProperty(value = "认证方法", required = true, example = "String")
    private Set<ClientAuthenticationMethod> clientAuthenticationMethods;

    @NotEmpty(message = "认证类型" + "{javax.validation.constraints.NotEmpty.message}", groups = Update.class)
    @ApiModelProperty(value = "认证类型", required = true, example = "String")
    private Set<AuthorizationGrantType> authorizationGrantTypes;

    @ApiModelProperty(value = "跳转地址", example = "String")
    @NotEmpty(message = "跳转地址" + "{javax.validation.constraints.NotEmpty.message}", groups = Update.class)
    private Set<String> redirectUris;

    @NotEmpty(message = "作用域" + "{javax.validation.constraints.NotEmpty.message}", groups = Update.class)
    @ApiModelProperty(value = "作用域", required = true, example = "String")
    private Set<String> scopes;

    @NotNull(message = "客户端设置" + "{javax.validation.constraints.NotNull.message}", groups = Update.class)
    @ApiModelProperty(value = "客户端设置", required = true, example = "String")
    private ClientSettingsDTO clientSettings;

    @NotNull(message = "令牌设置" + "{javax.validation.constraints.NotNull.message}", groups = Update.class)
    @ApiModelProperty(value = "令牌设置", required = true, example = "String")
    private TokenSettingsDTO tokenSettings;

}
