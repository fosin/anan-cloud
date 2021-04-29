package top.fosin.anan.cloudgateway.filter;

import org.springframework.util.StringUtils;
import top.fosin.anan.core.util.crypt.AesUtil;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *  用户登录过滤器-密码解密
 * @author fosin
 * @date 2019/6/28
 * @since 2.0.0
 */
public class LoginGatewayFilter implements GatewayFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        MultiValueMap<String, String> params = request.getQueryParams();
        String uri = exchange.getRequest().getURI().toString();

        //只处理认证请求
        if (uri.contains("/oauth/token")) {
            String cipheru = getRequestParam(params, "a");
            if (StringUtils.isEmpty(cipheru)) {
                return null;
            }
            String cipherp = getRequestParam(params, "b");
            if (StringUtils.isEmpty(cipherp)) {
                return null;
            }
            String passphrase = getRequestParam(params, "c");
            if (StringUtils.isEmpty(passphrase)) {
                return null;
            }
            int keysize = Integer.parseInt(Objects.requireNonNull(getRequestParam(params, "f")));
            if (keysize < 1) {
                return null;
            }
            String iv = getRequestParam(params, "d");
            if (StringUtils.isEmpty(iv)) {
                return null;
            }
            int iterationcount = Integer.parseInt(Objects.requireNonNull(getRequestParam(params, "g")));
            if (iterationcount < 1) {
                return null;
            }
            String salt = getRequestParam(params, "e");
            if (StringUtils.isEmpty(salt)) {
                return null;
            }
            AesUtil aesUtil = new AesUtil(keysize, iterationcount);
            String username = aesUtil.decrypt(salt, iv, passphrase, cipheru);
            List<String> usernameList = new ArrayList<>();
            usernameList.add(username.trim());
            params.put("username", usernameList);
            String password = aesUtil.decrypt(salt, iv, passphrase, cipherp);
            List<String> passwordList = new ArrayList<>();
            passwordList.add(password);
            params.put("password", passwordList);
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }

    private String getRequestParam(MultiValueMap<String, String> params, String pName) {
        List<String> parmList = params.get(pName);
        if (parmList == null || parmList.size() == 0) {
            return null;
        }
        return parmList.get(0);
    }
}
