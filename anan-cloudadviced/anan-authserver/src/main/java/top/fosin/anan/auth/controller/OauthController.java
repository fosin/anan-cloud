package top.fosin.anan.auth.controller;

import cn.hutool.core.util.NumberUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.core.AbstractOAuth2Token;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.oauth2.provider.endpoint.TokenKeyEndpoint;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;
import top.fosin.anan.core.util.crypt.AesUtil;
import top.fosin.anan.model.result.ResultUtils;
import top.fosin.anan.model.result.SingleResult;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Map;

/**
 * @author fosin
 */
@RestController
@RequestMapping("/oauth")
@AllArgsConstructor
@Api(value = "/oauth", tags = "OAuth认证相关,获取令牌、刷新令牌、注销令牌")
public class OauthController {
    private final TokenEndpoint tokenEndpoint;
    private final TokenKeyEndpoint tokenKeyEndpoint;
    private final DefaultTokenServices consumerTokenServices;


    @RequestMapping(value = "/token", method = {RequestMethod.POST, RequestMethod.GET})
    @ApiOperation(value = "获取令牌", notes = "获取Oauth2.0令牌，通常用于前端的认证、登录操作")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Basic认证信息,格式例如：Basic ouZTJoQk5BQVFLUjVVemlJSw==", required = true, dataTypeClass = String.class, paramType = "header"),
            @ApiImplicitParam(name = "grant_type", value = "授权类型,可选值(password、refresh_token、client_credentials、authorization_code)", required = true, dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(name = "refresh_token", value = "早前收到的更新令牌，当grant_type=refresh_token时必选项。", dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(name = "scope", value = "作用域,表示申请的授权范围", dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(name = "redirect_uri", value = "重定向URI，当grant_type=authorization_code时才需要填写", dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(name = "client_id", value = "客户端的ID，当grant_type=authorization_code时必选项", dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(name = "state", value = "客户端的当前状态，可以指定任意值，认证服务器会原封不动地返回这个值，当grant_type" +
                    "=authorization_code时才需要填写", dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(name = "a", value = "cipheru用户加密算法", dataTypeClass = String.class, required = true,
                    paramType = "query"),
            @ApiImplicitParam(name = "b", value = "cipherp用户密码加密算法", dataTypeClass = String.class,
                    required = true, paramType = "query"),
            @ApiImplicitParam(name = "c", value = "passphrase口令", dataTypeClass = String.class, required = true,
                    paramType = "query"),
            @ApiImplicitParam(name = "d", value = "iv密钥偏移量", dataTypeClass = String.class,
                    required = true, paramType = "query"),
            @ApiImplicitParam(name = "e", value = "salt盐值", dataTypeClass = String.class, required = true,
                    paramType = "query"),
            @ApiImplicitParam(name = "f", value = "keysize值大小", dataTypeClass = String.class,
                    required = true, paramType = "query"),
            @ApiImplicitParam(name = "g", value = "iterationCount密钥加密次数", dataTypeClass = String.class,
                    required = true, paramType = "query"),
    })
    public SingleResult<OAuth2AccessToken> secretAccessToken(Principal principal, HttpServletRequest request,
                                                               @ApiIgnore @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {

        String cipheru = parameters.get("a");
        String cipherp = parameters.get("b");
        String passphrase = parameters.get("c");
        int keysize = NumberUtil.parseInt(parameters.get("f"));
        String iv = parameters.get("d");
        int iterationCount = NumberUtil.parseInt(parameters.get("g"));
        String salt = parameters.get("e");
        Assert.isTrue(StringUtils.hasText(cipheru)
                        && StringUtils.hasText(cipherp)
                        && StringUtils.hasText(passphrase)
                        && StringUtils.hasText(iv)
                        && StringUtils.hasText(salt)
                        && iterationCount > 0
                        && keysize > 0
                , "参数异常!");

        AesUtil aesUtil = new AesUtil(keysize, iterationCount);
        String username = aesUtil.decrypt(salt, iv, passphrase, cipheru);
        String password = aesUtil.decrypt(salt, iv, passphrase, cipherp);
        request.setAttribute("username", username);
        request.setAttribute("password", password);
        parameters.put("username", username);
        parameters.put("password", password);
        return accessToken(principal, request, parameters);

    }

    @RequestMapping(value = "/token/real", method = {RequestMethod.POST, RequestMethod.GET})
    @ApiOperation(value = "获取令牌", notes = "获取Oauth2.0令牌，通常用于前端的认证、登录操作")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Basic认证信息,格式例如：Basic ouZTJoQk5BQVFLUjVVemlJSw==", required = true, dataTypeClass = String.class, paramType = "header"),
            @ApiImplicitParam(name = "grant_type", value = "授权类型,可选值(password、refresh_token、client_credentials、authorization_code)", required = true, dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(name = "username", value = "用户名，当grant_type=password时必选项", dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(name = "password", value = "用户密码，当grant_type=password时必选项", dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(name = "refresh_token", value = "早前收到的更新令牌，当grant_type=refresh_token时必选项。", dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(name = "scope", value = "作用域,表示申请的授权范围", dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(name = "redirect_uri", value = "重定向URI，当grant_type=authorization_code时才需要填写", dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(name = "client_id", value = "客户端的ID，当grant_type=authorization_code时必选项", dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(name = "state", value = "客户端的当前状态，可以指定任意值，认证服务器会原封不动地返回这个值，当grant_type=authorization_code时才需要填写", dataTypeClass = String.class, paramType = "query")
    })
    public SingleResult<OAuth2AccessToken> accessToken(Principal principal, HttpServletRequest request, @ApiIgnore @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        OAuth2AccessToken body;
        if (request.getMethod().equals(RequestMethod.POST.toString())) {
            body = tokenEndpoint.postAccessToken(principal, parameters).getBody();
        } else {
            body = tokenEndpoint.getAccessToken(principal, parameters).getBody();
        }
        return ResultUtils.success(body);
    }

    @RequestMapping(value = "/userinfo/jwt", method = {RequestMethod.POST, RequestMethod.GET})
    @ApiOperation(value = "根据JWT令牌获取当前认证用户信息", notes = "根据JWT令牌获取当前认证用户信息，包括用户信息、客户端信息相关信息")
    @ApiImplicitParam(name = "Authorization", value = "认证信息,格式例如：Bearer c05b6fed-256c-4cd0-a55d-ae8ffdafbf75", required = true, dataTypeClass = String.class, paramType = "header")
    public SingleResult<Jwt> index(@AuthenticationPrincipal Jwt jwt) {
        return ResultUtils.success(jwt);
    }

    @RequestMapping(value = "/userinfo/principal", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "根据令牌获取当前认证用户信息", notes = "根据令牌获取当前认证用户信息，包括用户信息、客户端信息、Oauth2.0相关信息")
    @ApiImplicitParam(name = "principal", value = "认证信息,格式例如：Bearer c05b6fed-256c-4cd0-a55d-ae8ffdafbf75", required = true, dataTypeClass = String.class, paramType = "header")
    public SingleResult<Principal> principal(Principal principal) {
        return ResultUtils.success(principal);
    }

    @RequestMapping(value = "/token_key", method = RequestMethod.GET)
    @ApiOperation(value = "根据令牌获取JWK", notes = "根据令牌获取JWT的公钥信息")
    @ApiImplicitParam(name = "Authorization", value = "认证信息,格式例如：Bearer c05b6fed-256c-4cd0-a55d-ae8ffdafbf75", required = true, dataTypeClass = String.class, paramType = "header")
    public SingleResult<Map<String, String>> getKey(Principal principal) {
        return ResultUtils.success(tokenKeyEndpoint.getKey(principal));
    }

    @RequestMapping(value = "/removeToken", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "移除指定令牌信息", notes = "移除指定令牌信息，通常用于前端的退出登录操作")
    @ApiImplicitParam(name = "Authorization", value = "认证信息,格式例如：Bearer c05b6fed-256c-4cd0-a55d-ae8ffdafbf75", required = true, dataTypeClass = String.class, paramType = "header")
    public SingleResult<Boolean> removeToken(Authentication authentication) {
        boolean rc = false;
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            String token = null;
            if (principal instanceof OAuth2AuthenticationDetails) {
                token = ((OAuth2AuthenticationDetails) principal).getTokenValue();
            }
            if (principal instanceof AbstractOAuth2Token) {
                token = ((AbstractOAuth2Token) principal).getTokenValue();
            }
            Assert.isTrue(StringUtils.hasText(token), "token不能为空!");
            rc = consumerTokenServices.revokeToken(token);
        }
        return ResultUtils.success(rc);
    }

//    //获取jwt的公钥
//    @GetMapping("/.well-known/jwks.json")
//    public Map<String, Object> keys() {
//        return jwkSet().toJSONObject();
//    }

//    @Bean
//    public JWKSet jwkSet() {
//        RSAKey.Builder builder = new RSAKey.Builder((RSAPublicKey) keyPair().getPublic())
//                .keyUse(KeyUse.SIGNATURE)
//                .algorithm(JWSAlgorithm.RS256)
//                .keyID("bael-key-id");
//        return new JWKSet(builder.build());
//    }

//    @RequestMapping(value = "/authorize", method = {RequestMethod.GET, RequestMethod.POST})
//    @ApiOperation(value = "根据令牌获取当前认证用户信息", notes = "根据令牌获取当前认证用户信息，包括用户信息、客户端信息、Oauth2.0相关信息")
//    @ApiImplicitParam(name = "Authorization", value = "Basic认证信息,格式例如：Basic ouZTJoQk5BQVFLUjVVemlJSw==", required = true, dataTypeClass = String.class, paramType = "header")
//    public SingleResult<Principal> authorize(Principal principal) {
//        return ResultUtils.success(principal);
//    }

}
