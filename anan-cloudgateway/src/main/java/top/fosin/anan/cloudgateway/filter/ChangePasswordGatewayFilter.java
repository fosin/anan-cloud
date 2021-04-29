package top.fosin.anan.cloudgateway.filter;

import top.fosin.anan.cloudresource.constant.SystemConstant;
import top.fosin.anan.core.util.crypt.AesUtil;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 修改密码过滤器-密码解密
 * @author fosin
 * @date 2019/6/28
 * @since 2.0.0
 */
public class ChangePasswordGatewayFilter implements GatewayFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        MultiValueMap<String, String> params = request.getQueryParams();
        String uri = exchange.getRequest().getURI().toString();

        //只处理修改密码请求
        if (uri.contains("/user/changePassword")) {
            String id = getRequestParam(params, "i");
            if (StringUtils.isEmpty(id)) {
                return null;
            }

            String password = getRequestParam(params, "a");
            if (StringUtils.isEmpty(password)) {
                return null;
            }
            String confirmpassword1 = getRequestParam(params, "b");
            if (StringUtils.isEmpty(confirmpassword1)) {
                return null;
            }
            String confirmpassword2 = getRequestParam(params, "h");
            if (StringUtils.isEmpty(confirmpassword2)) {
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

            List<String> idList = new ArrayList<>();
            idList.add(id);
            params.put(SystemConstant.ID_NAME, idList);

            List<String> passwordList = new ArrayList<>();
            passwordList.add(aesUtil.decrypt(salt, iv, passphrase, password));
            params.put("password", passwordList);

            List<String> confirmPasswordList1 = new ArrayList<>();
            confirmPasswordList1.add(aesUtil.decrypt(salt, iv, passphrase, confirmpassword1));
            params.put("confirmPassword1", confirmPasswordList1);

            List<String> confirmPasswordList2 = new ArrayList<>();
            confirmPasswordList2.add(aesUtil.decrypt(salt, iv, passphrase, confirmpassword2));
            params.put("confirmPassword2", confirmPasswordList2);

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
