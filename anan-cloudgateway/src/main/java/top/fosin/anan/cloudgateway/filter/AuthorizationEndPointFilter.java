package top.fosin.anan.cloudgateway.filter;

import com.google.common.base.Joiner;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import top.fosin.anan.cloudresource.constant.ServiceConstant;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

/**
 * 用户登录过滤器
 *
 * @author fosin
 * @date 2023/3/1
 * @since 3.3.0
 */
@AllArgsConstructor
//@Component
@Slf4j
public class AuthorizationEndPointFilter implements GlobalFilter, Ordered {

    private final DiscoveryClient discoveryClient;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse originalResponse = exchange.getResponse();
        String uri = exchange.getRequest().getURI().toString();
        //只处理认证请求
        if (uri.contains("/oauth2/authorize")) {
            URI location = Objects.requireNonNull(originalResponse.getHeaders().getLocation());
            List<ServiceInstance> instances = discoveryClient.getInstances(ServiceConstant.ANAN_AUTHSERVER);
            if (instances.stream().anyMatch(serviceInstance -> location.toString().contains(serviceInstance.getUri().toString()))) {
                URI requestURI = request.getURI();
                String newLocation = location.toString().replace(location.getHost(), requestURI.getHost()).replace(location.getPort() + "", requestURI.getPort() + "").replace(location.getScheme(), requestURI.getScheme());
                try {
                    originalResponse.getHeaders().setLocation(new URI(newLocation));
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return chain.filter(exchange);

        //DataBufferFactory bufferFactory = originalResponse.bufferFactory();
        //ServerHttpResponseDecorator response = new ServerHttpResponseDecorator(originalResponse) {
        //    @NotNull
        //    @Override
        //    public Mono<Void> writeWith(@NotNull Publisher<? extends DataBuffer> body) {
        //        if (Objects.equals(getStatusCode(), HttpStatus.OK) && body instanceof Flux) {
        //            // 获取ContentType，判断是否返回JSON格式数据
        //            String originalResponseContentType = exchange.getAttribute(ORIGINAL_RESPONSE_CONTENT_TYPE_ATTR);
        //            if (StringUtils.isNotBlank(originalResponseContentType) && originalResponseContentType.contains("application/json")) {
        //                Flux<? extends DataBuffer> fluxBody = Flux.from(body);
        //                //（返回数据内如果字符串过大，默认会切割）解决返回体分段传输
        //                return super.writeWith(fluxBody.buffer().map(dataBuffers -> {
        //                    List<String> list = Lists.newArrayList();
        //                    dataBuffers.forEach(dataBuffer -> {
        //                        try {
        //                            byte[] content = new byte[dataBuffer.readableByteCount()];
        //                            dataBuffer.read(content);
        //                            DataBufferUtils.release(dataBuffer);
        //                            list.add(new String(content, StandardCharsets.UTF_8));
        //                        } catch (Exception e) {
        //                            log.info("加载Response字节流异常，失败原因：{}", Throwables.getStackTraceAsString(e));
        //                        }
        //                    });
        //                    String responseData = joiner.join(list);
        //
        //                    log.trace("responseData：" + responseData);
        //
        //                    byte[] uppedContent = new String(responseData.getBytes(), StandardCharsets.UTF_8).getBytes();
        //                    originalResponse.getHeaders().setContentLength(uppedContent.length);
        //                    return bufferFactory.wrap(uppedContent);
        //                }));
        //            }
        //        }
        //        return super.writeWith(body);
        //    }
        //
        //    @NotNull
        //    @Override
        //    public Mono<Void> writeAndFlushWith(@NotNull Publisher<? extends Publisher<? extends DataBuffer>> body) {
        //        return writeWith(Flux.from(body).flatMapSequential(p -> p));
        //    }
        //};
        //return chain.filter(exchange.mutate().response(response).build());
    }

    @Override
    public int getOrder() {
        return -2;
    }

    private final Joiner joiner = Joiner.on("");
}
