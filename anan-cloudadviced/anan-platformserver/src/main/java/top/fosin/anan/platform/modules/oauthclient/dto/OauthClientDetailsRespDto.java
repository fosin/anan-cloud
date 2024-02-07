package top.fosin.anan.platform.modules.oauthclient.dto;


import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import top.fosin.anan.data.prop.IdProp;

/**
 * OAuth2客户端接入配置(OauthClientDetails)响应DTO
 *
 * @author fosin
 * @date 2021-05-16 13:00:18
 * @since 2.6.0
 */
@Data
@Schema(description = "OAuth2客户端接入配置的响应DTO")
public class OauthClientDetailsRespDto implements IdProp<String> {
    private static final long serialVersionUID = 692552529395562413L;
    @Schema(description = "客户端序号", example = "String")
    private String clientId;

    @Schema(description = "权限资源ID清单", example = "String")
    private String resourceIds;

    @Schema(description = "客户端密钥", example = "String")
    private String clientSecret;

    @Schema(description = "权限作用域", example = "String")
    private String scope;

    @Schema(description = "授权类型清单", example = "String")
    private String authorizedGrantTypes;

    @Schema(description = "跳转地址", example = "String")
    private String webServerRedirectUri;

    @Schema(description = "授权ID清单", example = "String")
    private String authorities;

    @Schema(description = "访问Token有效期", example = "Integer")
    private Integer accessTokenValidity;

    @Schema(description = "刷新Token有效期", example = "Integer")
    private Integer refreshTokenValidity;

    @Schema(description = "附加信息", example = "String")
    private String additionalInformation;

    @Schema(description = "自动授权", example = "String")
    private String autoapprove;

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    public String getId() {
        return clientId;
    }

    /**
     * 获取主键属性名
     *
     * @return 属性名
     */
    @Override
    public String getIdName() {
        return "clientId";
    }

}
