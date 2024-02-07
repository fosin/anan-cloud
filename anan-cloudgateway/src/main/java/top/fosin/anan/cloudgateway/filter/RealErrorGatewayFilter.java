package top.fosin.anan.cloudgateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author fosin
 * @date 2019/6/28
 * @since 2.0.0
 */
@Deprecated
public class RealErrorGatewayFilter implements GatewayFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        // ServerHttpResponse response = exchange.getResponse();
        // MultiValueMap<String, String> params = request.getQueryParams();
        String uri = request.getURI().toString();
        //只处理修改密码请求
        if (uri.contains("/user/changePassword")) {
//            try {
//                request.setAttribute("jakarta.servlet.error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//
//                log.warn("Error during filtering", throwable);
//                request.setAttribute("jakarta.servlet.error.exception", throwable);
//
//                if (StringUtils.hasText(throwable.getMessage())) {
//                    request.setAttribute("jakarta.servlet.error.message", throwable.getMessage());
//                }
//
//                RequestDispatcher dispatcher = request.getRequestDispatcher(
//                        this.errorPath);
//                if (dispatcher != null) {
//                    ctx.set(SEND_ERROR_FILTER_RAN, true);
//                    if (!ctx.getResponse().isCommitted()) {
//                        ctx.setResponseStatusCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//                        dispatcher.forward(request, ctx.getResponse());
//                    }
//                }
//            } catch (Exception ex) {
//                ReflectionUtils.rethrowRuntimeException(ex);
//            }

        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }

    // private Throwable getRealCause(Throwable throwable) {
    //     Throwable cause = throwable.getCause();
    //     if (cause != null) {
    //         return getRealCause(cause);
    //     }
    //     return throwable;
    // }
}
