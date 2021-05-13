package top.fosin.anan.platform.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.fosin.anan.model.module.QueryRule;
import top.fosin.anan.model.dto.QuerySortRuleDto;
import top.fosin.anan.model.module.SortRule;

import java.io.Serializable;

/**
 * OAuth2客户端接入配置(OauthClientDetails)查询DTO
 *
 * @author fosin
 * @date 2021-05-08 13:12:17
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "OAuth2客户端接入配置查询DTO", description = "表(oauth_client_details)的对应的查询DTO")
public class OauthClientDetailsRetrieveDto extends QuerySortRuleDto<QueryRule, SortRule, String> implements Serializable {
    private static final long serialVersionUID = 326033612155817845L;

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

    @ApiModelProperty(value = "访问Token有效期", example = "0")
    private Integer accessTokenValidity;

    @ApiModelProperty(value = "刷新Token有效期", example = "0")
    private Integer refreshTokenValidity;

    @ApiModelProperty(value = "附加信息", example = "String")
    private String additionalInformation;

    @ApiModelProperty(value = "自动授权", example = "String")
    private String autoapprove;

}
