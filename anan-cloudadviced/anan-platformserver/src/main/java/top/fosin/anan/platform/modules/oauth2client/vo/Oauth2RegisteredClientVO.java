package top.fosin.anan.platform.modules.oauth2client.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.data.entity.Id;

import java.util.Date;

        /**
 * OAUTH2认证客户端注册表(oauth2_registered_client)单体VO
 *
 * @author fosin
 * @date 2023-05-13
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "OAUTH2认证客户端注册表单体VO", description = "OAUTH2认证客户端注册表(oauth2_registered_client)单体VO")
public class Oauth2RegisteredClientVO extends Id<String> {
    private static final long serialVersionUID = -74495448187040209L;
    @ApiModelProperty(value = "客户端序号")
    private String clientId;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "发布日期")
    private Date clientIdIssuedAt;

    @ApiModelProperty(value = "客户端密钥")
    private String clientSecret;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "过期时间")
    private Date clientSecretExpiresAt;

    @ApiModelProperty(value = "客户端名称")
    private String clientName;

    @ApiModelProperty(value = "认证方法")
    private String clientAuthenticationMethods;

    @ApiModelProperty(value = "认证类型")
    private String authorizationGrantTypes;

    @ApiModelProperty(value = "跳转地址")
    private String redirectUris;

    @ApiModelProperty(value = "作用域")
    private String scopes;

    @ApiModelProperty(value = "客户端设置")
    private String clientSettings;

    @ApiModelProperty(value = "令牌设置")
    private String tokenSettings;

}
