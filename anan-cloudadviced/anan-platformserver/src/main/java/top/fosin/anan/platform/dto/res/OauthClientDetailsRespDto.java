package top.fosin.anan.platform.dto.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * OAuth2客户端接入配置(OauthClientDetails)响应DTO
 *
 * @author fosin
 * @date 2021-05-16 13:00:18
 * @since 2.6.0
 */
@Data
@ApiModel(value = "OAuth2客户端接入配置响应DTO", description = "表(oauth_client_details)的响应DTO")
public class OauthClientDetailsRespDto implements Serializable {
    private static final long serialVersionUID = 692552529395562413L;
    @ApiModelProperty(value = "客户端ID", example = "String")
    private String clientId;

    @ApiModelProperty(value = "权限资源ID清单", example = "String")
    private String resourceIds;

    @ApiModelProperty(value = "客户端密钥", example = "String")
    private String clientSecret;

    @ApiModelProperty(value = "权限作用域", example = "String")
    private String scope;

    @ApiModelProperty(value = "授权类型清单", example = "String")
    private String authorizedGrantTypes;

    @ApiModelProperty(value = "跳转地址", example = "String")
    private String webServerRedirectUri;

    @ApiModelProperty(value = "授权ID清单", example = "String")
    private String authorities;

    @ApiModelProperty(value = "访问Token有效期", example = "Integer")
    private Integer accessTokenValidity;

    @ApiModelProperty(value = "刷新Token有效期", example = "Integer")
    private Integer refreshTokenValidity;

    @ApiModelProperty(value = "附加信息", example = "String")
    private String additionalInformation;

    @ApiModelProperty(value = "自动授权", example = "String")
    private String autoapprove;

}
