package top.fosin.anan.cloudresource.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.fosin.anan.cloudresource.service.ReactiveCurrentUserService;

import java.net.URISyntaxException;

/**
 * @author fosin
 * @date 2020/9/2
 * @since 2.1.0
 */
@Configuration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
public class ReactiveResourceAutoConfiguration {

//    @Value("${spring.application.name}")
//    private final String applicationName = null;
//    private final DiscoveryClient discoveryClient;
//
//    public ReactiveResourceAutoConfiguration(DiscoveryClient discoveryClient) {
//        this.discoveryClient = discoveryClient;
//    }

    @Bean
    @ConditionalOnMissingBean
    public ReactiveCurrentUserService reactiveUserInfoService() {
        return new ReactiveCurrentUserService();
    }

    /**
     * TODO
     *  如果bean名称是ananProgramAuthorities，则会出现源码跑无问题，编译打包运行报错：
     *  The bean 'ananProgramAuthorities', defined in class path resource [top/fosin/anan/cloudgateway/config/GatewayAutoConfiguration.class], could not be registered. A bean with that name has already been defined in class path resource [top/fosin/anan/security/configurer/AnanSecurityAutoConfiguration.class] and overriding is disabled.
     * Action: Consider renaming one of the beans or enabling overriding by setting
     * spring.main.allow-bean-definition-overriding=true
     * <p>
     * 即使设置了spring.main.allow-bean-definition-overriding=true还是会出现这个问题。
     *
     * @return AnanProgramAuthorities 编程权限
     * @throws URISyntaxException 异常
     */
//    @Bean
//    @Primary
//    public AnanProgramAuthorities ananProgramAuthoritiesNew() throws URISyntaxException {
//        //List<PermissionRespDTO> dtos = permissionFeignService.findByServiceCodes(hosts).getData();
//        List<PermissionRespDTO> dtos = getPermissionsByRest(hosts);
//        List<AnanSecurityProperties.Authority> authorities = new ArrayList<>();
//        Objects.requireNonNull(dtos).forEach(dto -> {
//            String path = dto.getPath();
//            if (StringUtils.hasText(path)) {
//                String method = dto.getMethod();
//                AnanSecurityProperties.Authority authority = new AnanSecurityProperties.Authority();
//                authority.setPaths(path);
//                authority.setMethods(method);
//                authority.setPermission(AuthorityPermission.hasAuthority.getName() + SecurityConstant.AUTHORITY_SPLIT + dto.getId());
//                authorities.add(authority);
//            }
//        });
//        return new AnanProgramAuthorities(authorities);
//    }
//
//    private ServiceInstance getServiceRandomInstance() {
//        List<ServiceInstance> serviceInstances = discoveryClient.getInstances(ServiceConstant.ANAN_PLATFORMSERVER);
//        Assert.isTrue(serviceInstances.size() > 0, "未找到服务的实例：" + ServiceConstant.ANAN_PLATFORMSERVER);
//        Random random = new Random();
//        int nextInt = random.nextInt(serviceInstances.size());
//        return serviceInstances.get(nextInt);
//    }
//
//    public List<PermissionRespDTO> getPermissionsByRest(List<String> hosts) throws URISyntaxException {
//        ServiceInstance instance = getServiceRandomInstance(this.applicationName);
//        String scheme = instance.getScheme();
//        URI uri = new URI(scheme == null ? "http" : scheme, null, instance.getHost(), instance.getPort(), "/" + PathPrefixConstant.PERMISSION + PathSuffixConstant.SERVICE_CODE + "/" + this.applicationName, PathPrefixConstant.DEFAULT_VERSION_PARAM, null);
//        RequestEntity<List<String>> request = RequestEntity
//                .post(uri)
//                .accept(MediaType.APPLICATION_JSON)
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(hosts);
//        ResponseEntity<MultResult<PermissionRespDTO>> res = restTemplate()
//                .exchange(uri.toString(), HttpMethod.POST, request, new ParameterizedTypeReference<>() {
//                });
//        return Objects.requireNonNull(res.getBody()).orElseThrow();
//    }

}
