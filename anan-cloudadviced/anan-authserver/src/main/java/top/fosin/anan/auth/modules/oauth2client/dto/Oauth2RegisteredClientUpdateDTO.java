package top.fosin.anan.auth.modules.oauth2client.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.data.entity.Id;

import javax.validation.constraints.NotBlank;
import java.util.Date;
        
/**
 * OAUTH2认证客户端注册表(oauth2_registered_client)更新DTO
 *
 * @author fosin
 * @date 2023-05-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@ApiModel(value = "OAUTH2认证客户端注册表更新DTO", description = "OAUTH2认证客户端注册表(oauth2_registered_client)更新DTO")
public class Oauth2RegisteredClientUpdateDTO extends Id<String> {
    private static final long serialVersionUID = -79138935854979023L;
    
    @NotBlank(message = "客户端序号" + "{javax.validation.constraints.NotBlank.message}")
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

    @NotBlank(message = "客户端名称" + "{javax.validation.constraints.NotBlank.message}")
    @ApiModelProperty(value = "客户端名称", required = true, example = "String")
    private String clientName;

    @NotBlank(message = "认证方法" + "{javax.validation.constraints.NotBlank.message}")
    @ApiModelProperty(value = "认证方法", required = true, example = "String")
    private String clientAuthenticationMethods;

    @NotBlank(message = "认证类型" + "{javax.validation.constraints.NotBlank.message}")
    @ApiModelProperty(value = "认证类型", required = true, example = "String")
    private String authorizationGrantTypes;

    @ApiModelProperty(value = "跳转地址", example = "String")
    private String redirectUris;

    @NotBlank(message = "作用域" + "{javax.validation.constraints.NotBlank.message}")
    @ApiModelProperty(value = "作用域", required = true, example = "String")
    private String scopes;

    @NotBlank(message = "客户端设置" + "{javax.validation.constraints.NotBlank.message}")
    @ApiModelProperty(value = "客户端设置", required = true, example = "String")
    private ClientSettings clientSettings;

    @NotBlank(message = "令牌设置" + "{javax.validation.constraints.NotBlank.message}")
    @ApiModelProperty(value = "令牌设置", required = true, example = "String")
    private TokenSettings tokenSettings;

}
