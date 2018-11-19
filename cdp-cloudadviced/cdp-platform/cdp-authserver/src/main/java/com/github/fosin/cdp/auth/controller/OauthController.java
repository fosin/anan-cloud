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

    @RequestMapping(value = "/principal", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "根据令牌获取当前认证用户信息", notes = "根据令牌获取当前认证用户信息，包括用户信息、客户端信息、Oauth2.0相关信息")
    public ResponseEntity<Object> principal(Principal principal) {
        return ResponseEntity.ok(principal);
    }

    @Autowired
    TokenEndpoint tokenEndpoint;

    @RequestMapping(value = "/token", method = {RequestMethod.POST})
    @ApiOperation(value = "获取Oauth2.0令牌", notes = "获取Oauth2.0令牌，通常用于前端的认证、登录操作")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Basic认证信息,格式例如：Basic ouZTJoQk5BQVFLUjVVemlJSw==", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "parameters", value = "请求参数", required = true, paramType = "body", dataType = "string", example = "{\"grant_type\":\"password\"\n" +
                    "\"username\":\"user\"\n" +
                    "\"password\":\"123\"}")
    })
    public ResponseEntity<OAuth2AccessToken> accessToken(Principal principal, HttpServletRequest request, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        if (request.getMethod().equals(RequestMethod.POST.toString())) {
            return tokenEndpoint.postAccessToken(principal, parameters);
        } else {
            return tokenEndpoint.getAccessToken(principal, parameters);
        }
    }

    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @RequestMapping(value = "/removeToken", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    @ApiOperation(value = "移除指定令牌信息", notes = "移除指定令牌信息，通常用于前端的退出登录操作")
    public ResponseEntity<Boolean> removeToken(Principal principal) {
        Assert.notNull(principal, "principal不能为空，可能是认证失败!");
        Assert.isTrue(principal instanceof OAuth2Authentication, "principal必须是OAuth2Authentication类型!");
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) ((OAuth2Authentication) principal).getDetails();
        return ResponseEntity.ok(consumerTokenServices.revokeToken(details.getTokenValue()));
    }
//    @RequestMapping("/authentication/principal")
//    public ResponseEntity<Object> authenticationPrincipal(Authentication authentication) {
//        return ResponseEntity.ok(authentication.getPrincipal());
//    }
//
//    @RequestMapping("/authentication")
//    public ResponseEntity<Object> authentication(Authentication authentication) {
//        return ResponseEntity.ok(authentication);
//    }

//    public ResponseEntity<Object> principal(Authentication authentication) {
//        return ResponseEntity.ok(authentication.getPrincipal());
//    }
//    @Autowired
//    private IUserRoleService userRoleService;
//
//    @Autowired
//    private static RedisTokenStore redisTokenStore;

//    @RequestMapping("/userRoles")
//    public ResponseEntity<List<CdpSysRoleEntity>> getUserRoles(@RequestParam("usercode") String usercode, @RequestParam("password") String password) {
//        List<CdpSysUserRoleEntity> allByUsercodeAndPassword = userRoleService.findByUsercodeAndPassword(usercode, password);
//        List<CdpSysRoleEntity> cdpSysRoleEntities = new ArrayList<>();
//        for (CdpSysUserRoleEntity anAllByUsercodeAndPassword : allByUsercodeAndPassword) {
//            cdpSysRoleEntities.add(anAllByUsercodeAndPassword.getRole());
//        }
//        return ResponseEntity.ok(cdpSysRoleEntities);
//    }

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
