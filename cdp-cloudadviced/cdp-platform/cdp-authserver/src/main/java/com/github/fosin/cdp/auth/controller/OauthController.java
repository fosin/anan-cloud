package com.github.fosin.cdp.auth.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.util.Assert;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Map;

/**
 * Description
 *
 * @author fosin
 */
@RestController
@Slf4j
@RequestMapping("/oauth")
@Api(value = "/oauth", tags = "OAuth认证相关", description = "获取令牌、刷新令牌、注销令牌")
public class OauthController {
    @Autowired
    TokenEndpoint tokenEndpoint;

    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @RequestMapping(value = "/token", method = {RequestMethod.POST})
    @ApiOperation(value = "获取令牌", notes = "获取Oauth2.0令牌，通常用于前端的认证、登录操作")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Basic认证信息,格式例如：Basic ouZTJoQk5BQVFLUjVVemlJSw==", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "grant_type", value = "授权类型,可选值(password、refresh_token、client_credentials、authorization_code)", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "username", value = "用户名，当grant_type=password时必选项", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "用户密码，当grant_type=password时必选项", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "refresh_token", value = "早前收到的更新令牌，当grant_type=refresh_token时必选项。", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "scope", value = "作用域,表示申请的授权范围", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "redirect_uri", value = "重定向URI，当grant_type=authorization_code时才需要填写", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "client_id", value = "客户端的ID，当grant_type=authorization_code时必选项", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "state", value = "客户端的当前状态，可以指定任意值，认证服务器会原封不动地返回这个值，当grant_type=authorization_code时才需要填写", dataType = "string", paramType = "query")
    })
    public ResponseEntity<OAuth2AccessToken> accessToken(Principal principal, HttpServletRequest request, @ApiIgnore @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        if (request.getMethod().equals(RequestMethod.POST.toString())) {
            return tokenEndpoint.postAccessToken(principal, parameters);
        } else {
            return tokenEndpoint.getAccessToken(principal, parameters);
        }
    }

    @RequestMapping(value = "/principal", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "根据令牌获取当前认证用户信息", notes = "根据令牌获取当前认证用户信息，包括用户信息、客户端信息、Oauth2.0相关信息")
    @ApiImplicitParam(name = "Authorization", value = "Basic认证信息,格式例如：Basic ouZTJoQk5BQVFLUjVVemlJSw==", required = true, dataType = "string", paramType = "header")
    public ResponseEntity<Principal> principal(Principal principal) {
        return ResponseEntity.ok(principal);
    }

//    @RequestMapping("/authentication/principal")
//    public ResponseEntity<Object> authenticationPrincipal(Authentication authentication) {
//        CdpUserDetail userDetail = LoginUserUtil.getUserDetail();
//        return ResponseEntity.ok(authentication.getPrincipal());
//    }

    @RequestMapping(value = "/removeToken", method = {RequestMethod.POST})
    @ResponseBody
    @ApiOperation(value = "移除指定令牌信息", notes = "移除指定令牌信息，通常用于前端的退出登录操作")
    @ApiImplicitParam(name = "Authorization", value = "Basic认证信息,格式例如：Basic ouZTJoQk5BQVFLUjVVemlJSw==", required = true, dataType = "string", paramType = "header")
    public ResponseEntity<Boolean> removeToken(Principal principal) {
        Assert.notNull(principal, "principal不能为空，可能是认证失败!");
        Assert.isTrue(principal instanceof OAuth2Authentication, "principal必须是OAuth2Authentication类型!");
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) ((OAuth2Authentication) principal).getDetails();
        return ResponseEntity.ok(consumerTokenServices.revokeToken(details.getTokenValue()));
    }

//    @Autowired
//    private static RedisTokenStore redisTokenStore;

//    @RequestMapping("/onlineUsers")
//    public ResponseEntity<List<Map<String,Object>>> onlineUsers() {
//        List<Map<String,Object>> result = new ArrayList<>();
//        Collection<String> cacheNames = CacheUtil.getCacheNames();
//        for (String name : cacheNames) {
//            Object cacheObject = CacheUtil.get("auth", name);
//
//        }
//        Cache auth = CacheUtil.getCache("auth");
//        RedisTemplate nativeCache = (RedisTemplate)auth.getNativeCache();
//        return ResponseEntity.ok(result);
//    }

//    @RequestMapping("/oauth2authentication")
//    public ResponseEntity<OAuth2Authentication> oAuth2Authentication(@RequestHeader("Authorization") String authorization) {
//        String[] arrays = authorization.split(" ");
//        String key = arrays[0];
//        if (arrays.length > 1) {
//            key = arrays[1];
//        }
//        OAuth2Authentication auth2Authentication =  redisTokenStore.readAuthentication(key);
//        return ResponseEntity.ok(auth2Authentication);
//    }
}
