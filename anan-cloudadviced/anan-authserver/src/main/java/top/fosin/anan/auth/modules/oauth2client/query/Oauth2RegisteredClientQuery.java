package top.fosin.anan.auth.modules.oauth2client.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.data.entity.req.LogiSortQuery;
import top.fosin.anan.data.module.LogiQueryRule;
import top.fosin.anan.data.module.SortRule;

import java.util.Date;

        /**
 * OAUTH2认证客户端注册表(oauth2_registered_client)通用查询DTO
 *
 * @author fosin
 * @date 2023-05-13
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "OAUTH2认证客户端注册表通用查询DTO", description = "OAUTH2认证客户端注册表(oauth2_registered_client)通用查询DTO")
public class Oauth2RegisteredClientQuery extends LogiSortQuery<LogiQueryRule,SortRule,String> {
    private static final long serialVersionUID = 164867452057956274L;
    
    @ApiModelProperty(value = "客户端序号", example = "String")
    private String clientId;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "发布日期", example = "Date")
    private Date clientIdIssuedAt;

    @ApiModelProperty(value = "客户端密钥", example = "String")
    private String clientSecret;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "过期时间", example = "Date")
    private Date clientSecretExpiresAt;

    @ApiModelProperty(value = "客户端名称", example = "String")
    private String clientName;

    @ApiModelProperty(value = "认证方法", example = "String")
    private String clientAuthenticationMethods;

    @ApiModelProperty(value = "认证类型", example = "String")
    private String authorizationGrantTypes;

    @ApiModelProperty(value = "跳转地址", example = "String")
    private String redirectUris;

    @ApiModelProperty(value = "作用域", example = "String")
    private String scopes;

    @ApiModelProperty(value = "客户端设置", example = "String")
    private String clientSettings;

    @ApiModelProperty(value = "令牌设置", example = "String")
    private String tokenSettings;

}
