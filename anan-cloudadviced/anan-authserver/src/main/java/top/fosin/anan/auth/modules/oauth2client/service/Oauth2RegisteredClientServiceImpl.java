package top.fosin.anan.auth.modules.oauth2client.service;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import top.fosin.anan.auth.modules.oauth2client.dao.Oauth2RegisteredClientDao;
import top.fosin.anan.auth.modules.oauth2client.dto.*;
import top.fosin.anan.auth.modules.oauth2client.po.Oauth2RegisteredClient;
import top.fosin.anan.auth.modules.oauth2client.service.inter.Oauth2RegisteredClientService;
import top.fosin.anan.core.util.BeanUtil;
import top.fosin.anan.data.entity.req.PageQuery;
import top.fosin.anan.data.result.PageResult;
import top.fosin.anan.data.result.ResultUtils;

import java.io.Serializable;
import java.time.Instant;
import java.util.*;
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

    public Oauth2RegisteredClientServiceImpl(Oauth2RegisteredClientDao oauth2RegisteredClientDao, RegisteredClientRepository registeredClientRepository, PasswordEncoder passwordEncoder) {
        this.oauth2RegisteredClientDao = oauth2RegisteredClientDao;
        this.registeredClientRepository = registeredClientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Oauth2RegisteredClientDTO create(Oauth2RegisteredClientCreateDTO createDTO) {
        String clientId = createDTO.getClientId();
        RegisteredClient registeredClient = registeredClientRepository.findByClientId(clientId);
        Assert.isTrue(registeredClient == null, "该客户端编号已存在：" + clientId);
        Oauth2RegisteredClientUpdateDTO updateDTO = BeanUtil.copyProperties(createDTO, Oauth2RegisteredClientUpdateDTO.class);
        registeredClient = save(updateDTO);
        return BeanUtil.copyProperties(registeredClient, Oauth2RegisteredClientDTO.class);
    }

    @Override
    public void update(Oauth2RegisteredClientUpdateDTO updateDTO, String... ignoreProperties) {
        String id = updateDTO.getId();
        RegisteredClient registeredClient = registeredClientRepository.findById(id);
        Assert.notNull(registeredClient, "该客户端序号不存在：" + id);
        save(updateDTO);
    }

    /**
     * 转换为Spring的写法并保存
     */
    private RegisteredClient save(Oauth2RegisteredClientUpdateDTO updateDTO) {
        // @formatter:off
        RegisteredClient.Builder builder = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId(updateDTO.getClientId())
                .clientSecret(passwordEncoder.encode(updateDTO.getClientSecret()))
                .clientName(updateDTO.getClientName())
                .clientSecretExpiresAt(updateDTO.getClientSecretExpiresAt().toInstant())
                .clientAuthenticationMethods(clientAuthenticationMethods -> clientAuthenticationMethods.addAll(updateDTO.getClientAuthenticationMethods()))
                .clientIdIssuedAt(updateDTO.getClientIdIssuedAt().toInstant());

        builder.authorizationGrantTypes(authorizationGrantTypes -> authorizationGrantTypes.addAll(updateDTO.getAuthorizationGrantTypes()));
        builder.redirectUris(redirectUris -> redirectUris.addAll(updateDTO.getRedirectUris()));
        builder.scopes(redirectUris -> redirectUris.addAll(updateDTO.getScopes()));

        ClientSettingsDTO clientSettings = updateDTO.getClientSettings();
        if (clientSettings != null) {

            builder.clientSettings(ClientSettings.builder()
                    .requireAuthorizationConsent(clientSettings.getRequireAuthorizationConsent())
                    .jwkSetUrl(clientSettings.getJwkSetUrl())
                    .requireProofKey(clientSettings.getRequireProofKey())
                    .tokenEndpointAuthenticationSigningAlgorithm(clientSettings.getTokenEndpointAuthenticationSigningAlgorithm())
                    .build());
        }
        TokenSettingsDTO tokenSettingsDTO = updateDTO.getTokenSettings();
        if (tokenSettingsDTO != null) {
            builder.tokenSettings(TokenSettings.builder()
                    .accessTokenFormat(tokenSettingsDTO.getAccessTokenFormat())
                    .accessTokenTimeToLive(tokenSettingsDTO.getAccessTokenTimeToLive())
                    .refreshTokenTimeToLive(tokenSettingsDTO.getRefreshTokenTimeToLive())
                    .reuseRefreshTokens(tokenSettingsDTO.getReuseRefreshTokens())
                    .idTokenSignatureAlgorithm(tokenSettingsDTO.getIdTokenSignatureAlgorithm())
                    .build());
        }
        RegisteredClient registeredClient = builder.build();
        registeredClientRepository.save(registeredClient);
        // @formatter:on
        return registeredClient;
    }

    @Override
    public Oauth2RegisteredClientDTO findOneById(String id, boolean... findRefs) {
        return toOauth2RegisteredClientDTO(registeredClientRepository.findById(id));
    }

    @NotNull
    private static Oauth2RegisteredClientDTO toOauth2RegisteredClientDTO(RegisteredClient registeredClient) {
        Oauth2RegisteredClientDTO dto = new Oauth2RegisteredClientDTO();
        if (registeredClient != null) {
            dto.setId(registeredClient.getId());
            dto.setClientId(registeredClient.getClientId());
            dto.setClientName(registeredClient.getClientName());
            dto.setClientSecret(registeredClient.getClientSecret());
            Instant clientIdIssuedAt = registeredClient.getClientIdIssuedAt();
            dto.setClientIdIssuedAt(clientIdIssuedAt == null ? null : Date.from(clientIdIssuedAt));
            dto.setScopes(registeredClient.getScopes());
            dto.setRedirectUris(registeredClient.getRedirectUris());
            dto.setClientAuthenticationMethods(registeredClient.getClientAuthenticationMethods());
            dto.setAuthorizationGrantTypes(registeredClient.getAuthorizationGrantTypes());
            Instant clientSecretExpiresAt = registeredClient.getClientSecretExpiresAt();
            dto.setClientSecretExpiresAt(clientSecretExpiresAt == null ? null : Date.from(clientSecretExpiresAt));
            dto.setClientSettings(registeredClient.getClientSettings());
            dto.setTokenSettings(registeredClient.getTokenSettings());
        }
        return dto;
    }

    @NotNull
    private Oauth2RegisteredClientDTO toOauth2RegisteredClientDTO(Oauth2RegisteredClient registeredClient) {
        Oauth2RegisteredClientDTO dto = new Oauth2RegisteredClientDTO();
        if (registeredClient != null) {
            dto.setId(registeredClient.getId());
            dto.setClientId(registeredClient.getClientId());
            dto.setClientName(registeredClient.getClientName());
            dto.setClientSecret(registeredClient.getClientSecret());
            dto.setClientIdIssuedAt(registeredClient.getClientIdIssuedAt());
            dto.setScopes(Arrays.stream(registeredClient.getScopes().split(",")).collect(Collectors.toSet()));
            dto.setRedirectUris(Arrays.stream(registeredClient.getRedirectUris().split(",")).collect(Collectors.toSet()));
            dto.setClientAuthenticationMethods(Arrays.stream(registeredClient.getClientAuthenticationMethods().split(",")).map(ClientAuthenticationMethod::new).collect(Collectors.toSet()));
            dto.setAuthorizationGrantTypes(Arrays.stream(registeredClient.getAuthorizationGrantTypes().split(",")).map(AuthorizationGrantType::new).collect(Collectors.toSet()));
            dto.setClientSecretExpiresAt(registeredClient.getClientSecretExpiresAt());
            dto.setClientSettings(registeredClient.getClientSettings());
            dto.setTokenSettings(registeredClient.getTokenSettings());
        }
        return dto;
    }

    @Override
    public Oauth2RegisteredClientDTO findOneByEntity(Object req, boolean... findRefs) {
        throw new UnsupportedOperationException("不支持该方法！");
    }

    @Override
    public Oauth2RegisteredClientDTO findOneByField(String fieldName, Serializable fieldValue, boolean... findRefs) {
        if (fieldName.equals("clientId")) {
            return toOauth2RegisteredClientDTO(registeredClientRepository.findByClientId((String) fieldValue));
        }
        throw new UnsupportedOperationException("不支持的属性：" + fieldName);
    }

    @Override
    public List<Oauth2RegisteredClientDTO> listByIds(Collection<String> ids) {
        throw new UnsupportedOperationException("不支持该方法！");
    }

    @Override
    public List<Oauth2RegisteredClientDTO> listByEntity(Object req, boolean... allowEmpty) {
        throw new UnsupportedOperationException("不支持该方法！");
    }

    @Override
    public PageResult<Oauth2RegisteredClientDTO> findPage(PageQuery<?> pageQuery) {
        int pageSizeLimit = getPageSizeLimit();
        PageRequest pageable = toPage(pageQuery);
        Page<Oauth2RegisteredClient> page = getDao().findAll(buildQueryRules(pageQuery.getParams(), true), pageable);
        List<Oauth2RegisteredClientDTO> clientDTOS = page.getContent().stream().map(this::toOauth2RegisteredClientDTO).collect(Collectors.toList());
        return ResultUtils.success(page.getTotalElements(), page.getTotalPages(), pageSizeLimit, clientDTOS);
    }

    /**
     * 默认DAO
     */
    @Override
    public Oauth2RegisteredClientDao getDao() {
        return oauth2RegisteredClientDao;
    }
}
