/*
 *    Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: lengleng (wangiegie@gmail.com)
 */

package com.github.fosin.cdp.zuulgateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.github.fosin.cdp.util.StringUtil;
import com.github.fosin.cdp.util.crypt.AesUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Description 解密前端传过来的密码登录信息
 *
 * @author fosin
 */
@Slf4j
@Component
public class LoginFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.SEND_ERROR_FILTER_ORDER + 2;
    }

    @Override
    public boolean shouldFilter() {
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        return request.getRequestURI().contains("/oauth/token");
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        Map<String, List<String>> params = ctx.getRequestQueryParams();
        if (params == null) {
            return null;
        }

        String cipheru = getRequestParam(params, "a");
        if (StringUtil.isEmpty(cipheru)) {
            return null;
        }
        String cipherp = getRequestParam(params, "b");
        if (StringUtil.isEmpty(cipherp)) {
            return null;
        }
        String passphrase = getRequestParam(params, "c");
        if (StringUtil.isEmpty(passphrase)) {
            return null;
        }
        int keysize = Integer.parseInt(Objects.requireNonNull(getRequestParam(params, "f")));
        if (keysize < 1) {
            return null;
        }
        String iv = getRequestParam(params, "d");
        if (StringUtil.isEmpty(iv)) {
            return null;
        }
        int iterationcount = Integer.parseInt(Objects.requireNonNull(getRequestParam(params, "g")));
        if (iterationcount < 1) {
            return null;
        }
        String salt = getRequestParam(params, "e");
        if (StringUtil.isEmpty(salt)) {
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
        ctx.setRequestQueryParams(params);
        return null;
    }

    private String getRequestParam(Map<String, List<String>> params, String pName) {
        List<String> parmList = params.get(pName);
        if (parmList == null || parmList.size() == 0) {
            return null;
        }
        return parmList.get(0);
    }
}
