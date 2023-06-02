package top.fosin.anan.auth.modules.oauth2client.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.jpa.po.IdPO;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * OAUTH2认证客户端注册表(oauth2_registered_client)数据库实体类
 *
 * @author fosin
 * @date 2023-05-13
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@DynamicUpdate
@Table(name = "oauth2_registered_client")
public class Oauth2RegisteredClient extends IdPO<String> {
    private static final long serialVersionUID = -28612467912748541L;

    /**
     * 客户端序号
     */
    @Basic
    @Column(name = "client_id", nullable = false)
    private String clientId;

    /**
     * 发布日期
     */
    @Basic
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Column(name = "client_id_issued_at", nullable = false)
    private Date clientIdIssuedAt;

    /**
     * 客户端密钥
     */
    @Basic
    @Column(name = "client_secret")
    private String clientSecret;

    /**
     * 过期时间
     */
    @Basic
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Column(name = "client_secret_expires_at")
    private Date clientSecretExpiresAt;

    /**
     * 客户端名称
     */
    @Basic
    @Column(name = "client_name", nullable = false)
    private String clientName;

    /**
     * 认证方法
     */
    @Basic
    @Column(name = "client_authentication_methods", nullable = false)
    private String clientAuthenticationMethods;

    /**
     * 认证类型
     */
    @Basic
    @Column(name = "authorization_grant_types", nullable = false)
    private String authorizationGrantTypes;

    /**
     * 跳转地址
     */
    @Basic
    @Column(name = "redirect_uris")
    private String redirectUris;

    /**
     * 作用域
     */
    @Basic
    @Column(name = "scopes", nullable = false)
    private String scopes;

    /**
     * 客户端设置
     */
    @Basic
    @Column(name = "client_settings", nullable = false)
    private ClientSettings clientSettings;

    /**
     * 令牌设置
     */
    @Basic
    @Column(name = "token_settings", nullable = false)
    private TokenSettings tokenSettings;

}
