package top.fosin.anan.platform.modules.oauthclient.po;


import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * (OauthClientDetails)实体类
 *
 * @author fosin
 * @date 2018-11-29 18:07:27
 * @since 1.0.0
 */
@Data
@Entity
@DynamicUpdate
@Table(name = "oauth_client_details")
@Schema(description = "OAuth2客户端接入配置的实体类")
public class OauthClientDetails implements Serializable {
    private static final long serialVersionUID = -51110701713375430L;

    @Id
    @Column(name = "client_id")
    @Schema(description = "客户端序号", example = "String")
    @Pattern(regexp = "[\\w]{1,64}", message = "客户端ID只能大小写字母、数字、下杠(_)组合而成,长度不超过64位")
    private String clientId;

    @Basic
    @Column(name = "resource_ids")
    @Schema(description = "权限资源ID清单", example = "String")
    private String resourceIds;

    @Basic
    @Column(name = "client_secret")
    @Schema(description = "客户端密钥", example = "String")
    private String clientSecret;

    @Basic
    @Column(name = "scope")
    @Schema(description = "权限作用域", example = "String")
    private String scope;

    @Basic
    @Column(name = "authorized_grant_types")
    @Schema(description = "授权类型清单", example = "String")
    private String authorizedGrantTypes;

    @Basic
    @Column(name = "web_server_redirect_uri")
    @Schema(description = "跳转地址", example = "String")
    private String webServerRedirectUri;

    @Basic
    @Column(name = "authorities")
    @Schema(description = "授权ID清单", example = "String")
    private String authorities;

    @Basic
    @Column(name = "access_token_validity")
    @Schema(description = "访问Token有效期", example = "0")
    private Integer accessTokenValidity;

    @Basic
    @Column(name = "refresh_token_validity")
    @Schema(description = "刷新Token有效期", example = "0")
    private Integer refreshTokenValidity;

    @Basic
    @Column(name = "additional_information")
    @Schema(description = "附加信息", example = "String")
    private String additionalInformation;

    @Basic
    @Column(name = "autoapprove")
    @Schema(description = "自动授权", example = "String")
    private String autoapprove;

}
