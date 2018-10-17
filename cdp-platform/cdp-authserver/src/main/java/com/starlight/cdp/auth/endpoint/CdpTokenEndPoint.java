package com.starlight.cdp.auth.endpoint;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

/**
 * Description:
 *
 * @author fosin
 * @date 2018.7.10
 */
//@FrameworkEndpoint
//@Api(value = "认证信息", description = "")
public class CdpTokenEndPoint {

//    @Autowired
//    TokenEndpoint tokenEndpoint;

//    @Autowired
//    private ConsumerTokenServices consumerTokenServices;
//
//    @RequestMapping(value = "/oauth/removeToken", method = {RequestMethod.GET, RequestMethod.POST})
//    @ResponseBody
//    @ApiOperation(value = "移除指定令牌信息", notes = "移除指定令牌信息，通常用于前端的退出登录操作")
//    @ApiImplicitParam(value = "http头中的Authorization信息", name = "authorization")
//    public ResponseEntity<Boolean> removeToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
//        String[] arrays = authorization.split(" ");
//        String accessToken = arrays[0];
//        if (arrays.length > 1) {
//            accessToken = arrays[1];
//        }
//        return ResponseEntity.ok(consumerTokenServices.revokeToken(accessToken));
//    }

//    @RequestMapping(value = "/oauth/getToken", method = RequestMethod.POST)
//    @ResponseBody
//    public ResponseEntity<OAuth2AccessToken> postAccessToken(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
//        return tokenEndpoint.postAccessToken(principal, parameters);
//    }
//
//    @RequestMapping(value = "/oauth/getToken", method = RequestMethod.GET)
//    @ResponseBody
//    public ResponseEntity<OAuth2AccessToken> getAccessToken(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
//        return tokenEndpoint.getAccessToken(principal, parameters);
//    }
}