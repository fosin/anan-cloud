package top.fosin.anan.cloudgateway.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.fosin.anan.cloudgateway.dto.PageURI;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;

/**
 * @author fosin
 * @date 2018.8.20
 */
@RestController
@RequestMapping("v1/application")
//@Api(value = "v1/application", tags = "应用集群相关信息获取", description = "用于应用集群相关的信息获取")
public class ApplicationController {
    private final DiscoveryClient discoveryClient;

    @Value("${spring.application.name}")
    private String serviceCode;

    public ApplicationController(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

//    @Autowired
//    private IRule iRule;

    //    @ApiOperation(value = "获取服务名称列表", notes = "获取当前注册到Eureka注册中心的所有节点服务名称")
    @RequestMapping(value = "/serviceNames", method = {RequestMethod.POST})
    public ResponseEntity<List<String>> getServiceNames() {
        Assert.notNull(discoveryClient, "discoveryClient不能为空!");
        List<String> services = discoveryClient.getServices();
        return ResponseEntity.ok(services);
    }

    //    @ApiOperation(value = "根据服务名称获取对应实例管理web地址", notes = "获取当前注册到Eureka注册中心的实例地址")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "serviceId",
//                    value = "注册Eureka注册中心的服务名称，一般等于spring.application.name"),
//            @ApiImplicitParam(name = "path",
//                    value = "当前服务节点管理端点的url")
//    })
    @RequestMapping(value = "/ui/url", method = {RequestMethod.POST})
    public ResponseEntity<PageURI> uiUrl(@RequestParam String serviceId, @RequestParam(value = "path", required = false) String path) {
        Assert.isTrue(StringUtils.hasText(serviceId), "serviceId不能为空!");
        ServiceInstance serviceInstance = getServiceInstance(serviceId);
        Assert.notNull(serviceInstance, "未找到可用的服务实例!");
        String url = "http://" + serviceInstance.getHost() +
                ":" + serviceInstance.getPort();
        if (StringUtils.hasText(path)) {
            url += "/" + path;
        }

        HttpHeaders headers = getHeaders(serviceInstance);

        return ResponseEntity.ok(new PageURI(url, headers));
    }

    private ServiceInstance getServiceInstance(String serviceId) {
        ServiceInstance rc = null;
        if (discoveryClient == null) {
            return rc;
        }
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serviceId);
        if (serviceInstances.size() == 0) {
            return rc;
        }
        if (serviceInstances.size() == 1) {
            return serviceInstances.get(0);
        }

        if (serviceId.equals(serviceCode)) {
            for (ServiceInstance s : serviceInstances) {
                if (s.getServiceId().equals(serviceCode)) {
                    rc = s;
                    break;
                }
            }
        }

        if (rc == null) {
            Random random = new Random();
            int nextInt = random.nextInt(serviceInstances.size());
            rc = serviceInstances.get(nextInt);
        }

        return rc;
    }

    private HttpHeaders getHeaders(ServiceInstance si) {
        HttpHeaders headers = new HttpHeaders();
        String username = si.getMetadata().get("user.name");
        String password = si.getMetadata().get("user.password");
        if (StringUtils.hasText(username) && StringUtils.hasText(password)) {
            headers.set(HttpHeaders.AUTHORIZATION, encode(username, password));
        }
        return headers;
    }

    private String encode(String username, String password) {
        String token = Base64Utils
                .encodeToString((username + ":" + password).getBytes(StandardCharsets.UTF_8));
        return "Basic " + token;
    }
}
