package top.fosin.anan.auth.modules.oauth2client.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.stereotype.Service;
import top.fosin.anan.auth.modules.oauth2client.dao.Oauth2RegisteredClientDao;
import top.fosin.anan.auth.modules.oauth2client.dto.Oauth2RegisteredClientCreateDTO;
import top.fosin.anan.auth.modules.oauth2client.dto.Oauth2RegisteredClientDTO;
import top.fosin.anan.auth.modules.oauth2client.dto.Oauth2RegisteredClientUpdateDTO;
import top.fosin.anan.auth.modules.oauth2client.service.inter.Oauth2RegisteredClientService;

import java.time.Duration;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * OAUTH2认证客户端注册表(oauth2_registered_client)服务实现类
 *
 * @author fosin
 * @date 2023-05-13
 */
@Service
@Lazy
public class Oauth2RegisteredClientServiceImpl implements Oauth2RegisteredClientService {

    private final Oauth2RegisteredClientDao oauth2RegisteredClientDao;
    private final RegisteredClientRepository registeredClientRepository;
    private final PasswordEncoder passwordEncoder;

    public Oauth2RegisteredClientServiceImpl(
            Oauth2RegisteredClientDao oauth2RegisteredClientDao,
            RegisteredClientRepository registeredClientRepository, PasswordEncoder passwordEncoder) {
        this.oauth2RegisteredClientDao = oauth2RegisteredClientDao;
        this.registeredClientRepository = registeredClientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Oauth2RegisteredClientDTO create(Oauth2RegisteredClientCreateDTO createDTO) {
        String clientId = createDTO.getClientId();
        RegisteredClient registeredClient = registeredClientRepository.findByClientId(clientId);
        if (registeredClient == null) {
            RegisteredClient.Builder builder = RegisteredClient.withId(UUID.randomUUID().toString())
                    .clientId(clientId)
                    .clientSecret(passwordEncoder.encode(createDTO.getClientSecret()))
                    .clientName(createDTO.getClientName())
                    .clientSecretExpiresAt(createDTO.getClientSecretExpiresAt().toInstant())
                    .clientAuthenticationMethod(new ClientAuthenticationMethod(createDTO.getClientAuthenticationMethods()))
                    .clientIdIssuedAt(createDTO.getClientIdIssuedAt().toInstant());

            builder.authorizationGrantTypes(authorizationGrantTypes -> {
                authorizationGrantTypes.addAll(Arrays.stream(createDTO.getAuthorizationGrantTypes().split(",")).map(AuthorizationGrantType::new).collect(Collectors.toSet()));
            });
            builder.redirectUris(redirectUris -> {
                redirectUris.addAll(Arrays.stream(createDTO.getRedirectUris().split(",")).collect(Collectors.toSet()));
            });
            builder.scopes(redirectUris -> {
                redirectUris.addAll(Arrays.stream(createDTO.getScopes().split(",")).collect(Collectors.toSet()));
            });

            registeredClient = builder
                    .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
                    .tokenSettings(TokenSettings.builder()
                            //使用透明方式，默认是 OAuth2TokenFormat SELF_CONTAINED
                            .accessTokenFormat(OAuth2TokenFormat.REFERENCE)
                            // 授权码的有效期
                            .accessTokenTimeToLive(Duration.ofDays(1))
                            // 刷新token的有效期
                            .refreshTokenTimeToLive(Duration.ofDays(3))
                            .reuseRefreshTokens(true)
                            .build())
                    .build();

            registeredClientRepository.save(registeredClient);
        }
        return Oauth2RegisteredClientService.super.create(createDTO);
    }

    @Override
    public void update(Oauth2RegisteredClientUpdateDTO updateDTO, String... ignoreProperties) {
        Oauth2RegisteredClientService.super.update(updateDTO, ignoreProperties);
    }

    private void save() {

    }

    /**
     * 默认DAO
     */
    @Override
    public Oauth2RegisteredClientDao getDao() {
        return oauth2RegisteredClientDao;
    }
}
