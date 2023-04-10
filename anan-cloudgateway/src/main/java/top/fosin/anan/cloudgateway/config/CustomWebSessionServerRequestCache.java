package top.fosin.anan.cloudgateway.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.log.LogMessage;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.security.web.savedrequest.SavedCookie;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.security.web.server.savedrequest.WebSessionServerRequestCache;
import org.springframework.security.web.server.util.matcher.*;
import org.springframework.util.Assert;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Mono;

import javax.servlet.http.Cookie;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CustomWebSessionServerRequestCache extends WebSessionServerRequestCache {
    private static final String DEFAULT_SAVED_REQUEST_ATTR = "SPRING_SECURITY_SAVED_REQUEST";

    private static final Log logger = LogFactory.getLog(CustomWebSessionServerRequestCache.class);

    private final String sessionAttrName = DEFAULT_SAVED_REQUEST_ATTR;

    private ServerWebExchangeMatcher saveRequestMatcher = createDefaultRequestMacher();

    /**
     * Sets the matcher to determine if the request should be saved. The default is to
     * match on any GET request.
     */
    public void setSaveRequestMatcher(ServerWebExchangeMatcher saveRequestMatcher) {
        Assert.notNull(saveRequestMatcher, "saveRequestMatcher cannot be null");
        this.saveRequestMatcher = saveRequestMatcher;
    }

    @Override
    public Mono<Void> saveRequest(ServerWebExchange exchange) {
        return this.saveRequestMatcher.matches(exchange).filter(ServerWebExchangeMatcher.MatchResult::isMatch)
                .flatMap((m) -> exchange.getSession()).map(WebSession::getAttributes).doOnNext((attrs) -> {
                    SavedRequest savedRequest = getSavedRequest(exchange.getRequest());
                    attrs.put(this.sessionAttrName, savedRequest);
                    logger.debug(LogMessage.format("Request added to WebSession: '%s'", savedRequest));
                }).then();
    }

    private SavedRequest getSavedRequest(ServerHttpRequest request) {
        DefaultSavedRequest.Builder builder = new DefaultSavedRequest.Builder();
        List<SavedCookie> cookies = new ArrayList<>();
        request.getCookies().values()
                .forEach(httpCookies -> cookies.addAll(httpCookies.stream().map(httpCookie -> new SavedCookie(new Cookie(httpCookie.getName(), httpCookie.getValue())))
                        .collect(Collectors.toList())));
        builder.setCookies(cookies);
        //Enumeration<Locale> locales = request.get();
        //List<Locale> localeList = new ArrayList<>();
        //while (locales.hasMoreElements()) {
        //    Locale locale = locales.nextElement();
        //    localeList.add(locale);
        //}
        //builder.setLocales(localeList);
        //builder.setParameters(request.get());
        URI uri = request.getURI();
        builder.setMethod(request.getMethodValue());
        builder.setPathInfo(request.getPath().toString());
        builder.setQueryString(request.getQueryParams().toString());
        builder.setRequestURI(uri.toString());
        builder.setServerPort(uri.getPort());
        try {
            builder.setRequestURL(uri.toURL().toString());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        builder.setScheme(uri.getScheme());
        builder.setServerName(uri.getHost());
        builder.setContextPath(uri.getPath());
        builder.setServletPath(uri.getPath());

        return builder.build();
    }

    @Override
    public Mono<URI> getRedirectUri(ServerWebExchange exchange) {
        return exchange.getSession()
                .flatMap((session) -> Mono.justOrEmpty(Objects.requireNonNull(session.<SavedRequest>getAttribute(this.sessionAttrName)).getRedirectUrl()))
                .map(URI::create);
    }

    @Override
    public Mono<ServerHttpRequest> removeMatchingRequest(ServerWebExchange exchange) {
        return exchange.getSession().map(WebSession::getAttributes).filter((attributes) -> {
            SavedRequest savedRequest = getSavedRequest(exchange.getRequest());
            boolean removed = attributes.remove(this.sessionAttrName, savedRequest);
            if (removed) {
                logger.debug(LogMessage.format("Request removed from WebSession: '%s'", savedRequest));
            }
            return removed;

        }).map((attributes) -> exchange.getRequest());
    }

    private static String pathInApplication(ServerHttpRequest request) {
        String path = request.getPath().pathWithinApplication().value();
        String query = request.getURI().getRawQuery();
        return path + ((query != null) ? "?" + query : "");
    }

    private static ServerWebExchangeMatcher createDefaultRequestMacher() {
        ServerWebExchangeMatcher get = ServerWebExchangeMatchers.pathMatchers(HttpMethod.GET, "/**");
        ServerWebExchangeMatcher notFavicon = new NegatedServerWebExchangeMatcher(
                ServerWebExchangeMatchers.pathMatchers("/favicon.*"));
        MediaTypeServerWebExchangeMatcher html = new MediaTypeServerWebExchangeMatcher(MediaType.TEXT_HTML);
        html.setIgnoredMediaTypes(Collections.singleton(MediaType.ALL));
        return new AndServerWebExchangeMatcher(get, notFavicon, html);
    }

}
