package top.fosin.anan.platform.modules.oauthclient.dto;


import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import top.fosin.anan.data.module.LogiQueryRule;
import top.fosin.anan.data.module.SortRule;
import top.fosin.anan.data.prop.IdProp;
import top.fosin.anan.data.prop.QuerySortRuleProp;
import top.fosin.anan.data.valid.group.Create;
import top.fosin.anan.data.valid.group.DynamicQuery;
import top.fosin.anan.data.valid.group.SortQuery;
import top.fosin.anan.data.valid.group.Update;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

/**
 * OAuth2客户端接入配置(OauthClientDetails)更新DTO
 *
 * @author fosin
 * @date 2021-05-08 13:12:16
 * @since 1.0.0
 */
@Data
@Schema(description = "OAuth2客户端接入配置的请求DTO")
public class OauthClientDetailsReqDto implements IdProp<String>,
        QuerySortRuleProp<LogiQueryRule, SortRule> {
    private static final long serialVersionUID = -19073929038045745L;

    @NotBlank(message = "客户端序号" + "{jakarta.validation.constraints.NotBlank.message}",
            groups = {Create.class, Update.class})
    @Schema(description = "客户端序号", example = "String")
    private String clientId;

    @Schema(description = "权限资源ID清单", example = "String")
    private String resourceIds;

    @NotBlank(message = "客户端密钥" + "{jakarta.validation.constraints.NotBlank.message}",
            groups = {Create.class, Update.class})
    @Schema(description = "客户端密钥", example = "String")
    private String clientSecret;

    @NotBlank(message = "权限作用域" + "{jakarta.validation.constraints.NotBlank.message}",
            groups = {Create.class, Update.class})
    @Schema(description = "权限作用域", example = "String")
    private String scope;

    @NotBlank(message = "授权类型清单" + "{jakarta.validation.constraints.NotBlank.message}",
            groups = {Create.class, Update.class})
    @Schema(description = "授权类型清单", example = "String")
    private String authorizedGrantTypes;

    @Schema(description = "跳转地址", example = "String")
    private String webServerRedirectUri;

    @Schema(description = "授权ID清单", example = "String")
    private String authorities;

    @Schema(description = "访问Token有效期", example = "0")
    private Integer accessTokenValidity;

    @Schema(description = "刷新Token有效期", example = "0")
    private Integer refreshTokenValidity;

    @Schema(description = "附加信息", example = "String")
    private String additionalInformation;

    @NotBlank(message = "自动授权" + "{jakarta.validation.constraints.NotBlank.message}",
            groups = {Create.class, Update.class})
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


    @Schema(description = QUERY_RULE_DESCRIPTION)
    @NotNull(message = QUERY_RULE_DESCRIPTION + "{jakarta.validation.constraints.NotNull.message}",
            groups = {DynamicQuery.class})
    private LogiQueryRule queryRule;

    @Schema(description = SORT_RULE_DESCRIPTION)
    @NotEmpty(message = SORT_RULE_DESCRIPTION + "{jakarta.validation.constraints.NotEmpty.message}",
            groups = {SortQuery.class})
    private List<SortRule> sortRules;

}
