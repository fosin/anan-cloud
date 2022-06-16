package top.fosin.anan.platform.modules.pub.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import top.fosin.anan.model.module.LogicalQueryRule;
import top.fosin.anan.model.module.SortRule;
import top.fosin.anan.model.prop.IdProp;
import top.fosin.anan.model.prop.QuerySortRuleProp;
import top.fosin.anan.model.valid.group.Create;
import top.fosin.anan.model.valid.group.DynamicQuery;
import top.fosin.anan.model.valid.group.SortQuery;
import top.fosin.anan.model.valid.group.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * OAuth2客户端接入配置(OauthClientDetails)更新DTO
 *
 * @author fosin
 * @date 2021-05-08 13:12:16
 * @since 1.0.0
 */
@Data
@ApiModel(value = "OAuth2客户端接入配置请求DTO", description = "OAuth2客户端接入配置的请求DTO")
public class OauthClientDetailsReqDto implements IdProp<String>,
        QuerySortRuleProp<LogicalQueryRule, SortRule> {
    private static final long serialVersionUID = -19073929038045745L;

    @NotBlank(message = "客户端序号" + "{javax.validation.constraints.NotBlank.message}",
            groups = {Create.class, Update.class})
    @ApiModelProperty(value = "客户端序号", example = "String")
    private String clientId;

    @ApiModelProperty(value = "权限资源ID清单", example = "String")
    private String resourceIds;

    @NotBlank(message = "客户端密钥" + "{javax.validation.constraints.NotBlank.message}",
            groups = {Create.class, Update.class})
    @ApiModelProperty(value = "客户端密钥", example = "String")
    private String clientSecret;

    @NotBlank(message = "权限作用域" + "{javax.validation.constraints.NotBlank.message}",
            groups = {Create.class, Update.class})
    @ApiModelProperty(value = "权限作用域", example = "String")
    private String scope;

    @NotBlank(message = "授权类型清单" + "{javax.validation.constraints.NotBlank.message}",
            groups = {Create.class, Update.class})
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

    @NotBlank(message = "自动授权" + "{javax.validation.constraints.NotBlank.message}",
            groups = {Create.class, Update.class})
    @ApiModelProperty(value = "自动授权", example = "String")
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


    @ApiModelProperty(value = QUERY_RULE_DESCRIPTION)
    @NotNull(message = QUERY_RULE_DESCRIPTION + "{javax.validation.constraints.NotNull.message}",
            groups = {DynamicQuery.class})
    private LogicalQueryRule queryRule;

    @ApiModelProperty(value = SORT_RULE_DESCRIPTION)
    @NotEmpty(message = SORT_RULE_DESCRIPTION + "{javax.validation.constraints.NotEmpty.message}",
            groups = {SortQuery.class})
    private List<SortRule> sortRules;

}
