/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package top.fosin.anan.auth.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.log.LogMessage;
import org.springframework.security.web.PortResolver;
import org.springframework.security.web.PortResolverImpl;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedCookie;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.security.web.util.matcher.AnyRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.stream.Collectors;

/**
 * {@code RequestCache} which stores the {@code SavedRequest} in the HttpSession.
 * <p>
 * The {@link DefaultSavedRequest} class is used as the implementation.
 *
 * @author Luke Taylor
 * @author Eddú Meléndez
 * @since 3.0
 */
public class HttpSessionRequestCache implements RequestCache {

    static final String SAVED_REQUEST = "SPRING_SECURITY_SAVED_REQUEST";

    protected final Log logger = LogFactory.getLog(this.getClass());

    private PortResolver portResolver = new PortResolverImpl();

    private boolean createSessionAllowed = true;

    private RequestMatcher requestMatcher = AnyRequestMatcher.INSTANCE;

    private String sessionAttrName = SAVED_REQUEST;

    /**
     * Stores the current request, provided the configuration properties allow it.
     */
    @Override
    public void saveRequest(HttpServletRequest request, HttpServletResponse response) {
        if (!this.requestMatcher.matches(request)) {
            if (this.logger.isTraceEnabled()) {
                this.logger.trace(
                        LogMessage.format("Did not save request since it did not match [%s]", this.requestMatcher));
            }
            return;
        }
        DefaultSavedRequest savedRequest = new DefaultSavedRequest(request, this.portResolver);
        if (this.createSessionAllowed || request.getSession(false) != null) {
            // Store the HTTP request itself. Used by
            // AbstractAuthenticationProcessingFilter
            // for redirection after successful authentication (SEC-29)
            request.getSession().setAttribute(this.sessionAttrName, savedRequest);
            if (this.logger.isDebugEnabled()) {
                this.logger.debug(LogMessage.format("Saved request %s to session", savedRequest.getRedirectUrl()));
            }
        } else {
            this.logger.trace("Did not save request since there's no session and createSessionAllowed is false");
        }
    }

    @Override
    public SavedRequest getRequest(HttpServletRequest currentRequest, HttpServletResponse response) {
        HttpSession session = currentRequest.getSession(false);
        SavedRequest savedRequest = null;
        if (session != null) {
            Object attribute = session.getAttribute(this.sessionAttrName);

            if (attribute instanceof String) {
                String rediectPath = (String) attribute;
                DefaultSavedRequest.Builder builder = new DefaultSavedRequest.Builder();
                builder.setCookies(Arrays.stream(currentRequest.getCookies()).map(SavedCookie::new).collect(Collectors.toList()));

                Enumeration<Locale> locales = currentRequest.getLocales();
                List<Locale> localeList = new ArrayList<>();
                while (locales.hasMoreElements()) {
                    Locale locale = locales.nextElement();
                    localeList.add(locale);
                }
                builder.setLocales(localeList);
                builder.setParameters(currentRequest.getParameterMap());

                builder.setMethod(currentRequest.getMethod());
                builder.setPathInfo(currentRequest.getPathInfo());
                builder.setQueryString(currentRequest.getQueryString());
                builder.setRequestURI(rediectPath);
                builder.setServerPort(portResolver.getServerPort(currentRequest));
                builder.setRequestURL(currentRequest.getRequestURL().toString());
                builder.setScheme(currentRequest.getScheme());
                builder.setServerName(currentRequest.getServerName());
                builder.setContextPath(currentRequest.getContextPath());
                builder.setServletPath(currentRequest.getServletPath());

                savedRequest = builder.build();
            } else if (attribute instanceof SavedRequest) {
                savedRequest = (SavedRequest) attribute;
            }
        }

        return savedRequest;
    }

    @Override
    public void removeRequest(HttpServletRequest currentRequest, HttpServletResponse response) {
        HttpSession session = currentRequest.getSession(false);
        if (session != null) {
            this.logger.trace("Removing DefaultSavedRequest from session if present");
            session.removeAttribute(this.sessionAttrName);
        }
    }

    @Override
    public HttpServletRequest getMatchingRequest(HttpServletRequest request, HttpServletResponse response) {
        SavedRequest saved = getRequest(request, response);
        if (saved == null) {
            this.logger.trace("No saved request");
            return null;
        }
        if (!matchesSavedRequest(request, saved)) {
            if (this.logger.isTraceEnabled()) {
                this.logger.trace(LogMessage.format("Did not match request %s to the saved one %s",
                        UrlUtils.buildRequestUrl(request), saved));
            }
            return null;
        }
        removeRequest(request, response);
        if (this.logger.isDebugEnabled()) {
            this.logger.debug(LogMessage.format("Loaded matching saved request %s", saved.getRedirectUrl()));
        }
        return new SavedRequestAwareWrapper(saved, request);
    }

    private boolean matchesSavedRequest(HttpServletRequest request, SavedRequest savedRequest) {
        if (savedRequest instanceof DefaultSavedRequest) {
            DefaultSavedRequest defaultSavedRequest = (DefaultSavedRequest) savedRequest;
            return defaultSavedRequest.doesRequestMatch(request, this.portResolver);
        }
        String currentUrl = UrlUtils.buildFullRequestUrl(request);
        return savedRequest.getRedirectUrl().equals(currentUrl);
    }

    /**
     * Allows selective use of saved requests for a subset of requests. By default any
     * request will be cached by the {@code saveRequest} method.
     * <p>
     * If set, only matching requests will be cached.
     *
     * @param requestMatcher a request matching strategy which defines which requests
     *                       should be cached.
     */
    public void setRequestMatcher(RequestMatcher requestMatcher) {
        this.requestMatcher = requestMatcher;
    }

    /**
     * If <code>true</code>, indicates that it is permitted to store the target URL and
     * exception information in a new <code>HttpSession</code> (the default). In
     * situations where you do not wish to unnecessarily create <code>HttpSession</code>s
     * - because the user agent will know the failed URL, such as with BASIC or Digest
     * authentication - you may wish to set this property to <code>false</code>.
     */
    public void setCreateSessionAllowed(boolean createSessionAllowed) {
        this.createSessionAllowed = createSessionAllowed;
    }

    public void setPortResolver(PortResolver portResolver) {
        this.portResolver = portResolver;
    }

    /**
     * If the {@code sessionAttrName} property is set, the request is stored in the
     * session using this attribute name. Default is "SPRING_SECURITY_SAVED_REQUEST".
     *
     * @param sessionAttrName a new session attribute name.
     * @since 4.2.1
     */
    public void setSessionAttrName(String sessionAttrName) {
        this.sessionAttrName = sessionAttrName;
    }

}
