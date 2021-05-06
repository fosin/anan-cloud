package top.fosin.anan.platform.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.fosin.anan.model.module.QueryRule;
import top.fosin.anan.model.module.QuerySortRuleEntity;
import top.fosin.anan.model.module.SortRule;

import java.io.Serializable;

/**
 * (OauthClientDetails)查询DTO
 *
 * @author fosin
 * @date 2021-05-03 19:01:57
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "$tableInfo.comment查询DTO", description = "表(oauth_client_details)的对应的查询DTO")
public class OauthClientDetailsRetrieveDto extends QuerySortRuleEntity<QueryRule,SortRule> implements Serializable {
    private static final long serialVersionUID = 890552174384794357L;

    @ApiModelProperty(value = "${column.comment}", example = "String")
    private String clientId;

    @ApiModelProperty(value = "${column.comment}", example = "String")
    private String resourceIds;

    @ApiModelProperty(value = "${column.comment}", example = "String")
    private String clientSecret;

    @ApiModelProperty(value = "${column.comment}", example = "String")
    private String scope;

    @ApiModelProperty(value = "${column.comment}", example = "String")
    private String authorizedGrantTypes;

    @ApiModelProperty(value = "${column.comment}", example = "String")
    private String webServerRedirectUri;

    @ApiModelProperty(value = "${column.comment}", example = "String")
    private String authorities;

    @ApiModelProperty(value = "${column.comment}", example = "Integer")
    private Integer accessTokenValidity;

    @ApiModelProperty(value = "${column.comment}", example = "Integer")
    private Integer refreshTokenValidity;

    @ApiModelProperty(value = "${column.comment}", example = "String")
    private String additionalInformation;

    @ApiModelProperty(value = "${column.comment}", example = "String")
    private String autoapprove;

}
