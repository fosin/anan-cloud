package top.fosin.anan.cloudresource.config;


import lombok.extern.slf4j.Slf4j;

/**
 * @author fosin
 * @date 2019/3/27
 */
 @Slf4j
public class Oauth2FeignConfigure {

    //@Bean
    //public CircuitBreakerNameResolver circuitBreakerNameResolver() {
    //    return (String feignClientName, Target<?> target, Method method) -> feignClientName + "_" + method.getName();
    //}
    //@Bean
    //Capability customCapability() {
    //    return new CustomCapability();
    //}
//    // feign的OAuth2ClientContext
//    private OAuth2ClientContext feignOauth2ClientContext = new DefaultOAuth2ClientContext();
//    private final ClientCredentialsResourceDetails clientCredentialsResourceDetails;
//    private final ObjectFactory<HttpMessageConverters> messageConverters;
//
//    public Oauth2FeignConfigure(ClientCredentialsResourceDetails clientCredentialsResourceDetails, ObjectFactory<HttpMessageConverters> messageConverters) {
//        this.clientCredentialsResourceDetails = clientCredentialsResourceDetails;
//        this.messageConverters = messageConverters;
//    }
//
//    @Bean
//    public OAuth2RestTemplate clientCredentialsRestTemplate() {
//        return new OAuth2RestTemplate(clientCredentialsResourceDetails);
//    }
//
//    @Bean
//    public RequestInterceptor oauth2FeignRequestInterceptor() {
//        return new OAuth2FeignRequestInterceptor(feignOauth2ClientContext, clientCredentialsResourceDetails);
//    }
//
//    @Bean
//    public Logger.Level feignLoggerLevel() {
//        return Logger.Level.INFO;
//    }
//
//
//    @Bean
//    public Retryer retry() {
//        // default Retryer will retry 5 times waiting waiting
//        // 100 ms per retry with a 1.5* back off multiplier
//        return new Retryer.Default(100, SECONDS.toMillis(1), 3);
//    }
//
//
//    @Bean
//    public Decoder feignDecoder() {
//        return new CustomResponseEntityDecoder(new SpringDecoder(this.messageConverters), feignOauth2ClientContext);
//    }
//
//
//    /**
//     * Http响应成功 但是token失效，需要定制 ResponseEntityDecoder
//     *
//     * @author maxianming
//     * @date 2018/10/30 9:47
//     */
//    class CustomResponseEntityDecoder implements Decoder {
//        private Decoder decoder;
//
//        private OAuth2ClientContext context;
//
//        public CustomResponseEntityDecoder(Decoder decoder, OAuth2ClientContext context) {
//            this.decoder = decoder;
//            this.context = context;
//        }
//
//        @Override
//        public Object decode(final Response response, Type type) throws IOException, FeignException {
//            if (log.isDebugEnabled()) {
//                log.debug("feign decode type:{}，reponse:{}", type, response.body());
//            }
//            if (isParameterizeHttpEntity(type)) {
//                type = ((ParameterizedType) type).getActualTypeArguments()[0];
//                Object decodedObject = decoder.decode(response, type);
//                return createResponse(decodedObject, response);
//            } else if (isHttpEntity(type)) {
//                return createResponse(null, response);
//            } else {
//                // custom ResponseEntityDecoder if token is valid then go to errorDecoder
//                String body = Util.toString(response.body().asReader());
//                if (body.toLowerCase().contains("invalid token")) {
//                    clearTokenAndRetry(response, body);
//                }
//                return decoder.decode(response, type);
//            }
//        }
//
//        /**
//         * token失效 则将token设置为null 然后重试
//         *
//         * @param
//         * @return
//         * @author maxianming
//         * @date 2018/10/30 10:05
//         */
//        private void clearTokenAndRetry(Response response, String body) throws FeignException {
//            log.error("接收到Feign请求资源响应,响应内容:{}", body);
//            context.setAccessToken(null);
//            throw new RetryableException(HttpStatus.SC_INTERNAL_SERVER_ERROR, "access_token过期，即将进行重试", Request.HttpMethod.CONNECT, new Date(), response.request());
//        }
//
//        private boolean isParameterizeHttpEntity(Type type) {
//            if (type instanceof ParameterizedType) {
//                return isHttpEntity(((ParameterizedType) type).getRawType());
//            }
//            return false;
//        }
//
//        private boolean isHttpEntity(Type type) {
//            if (type instanceof Class) {
//                Class c = (Class) type;
//                return HttpEntity.class.isAssignableFrom(c);
//            }
//            return false;
//        }
//
//        @SuppressWarnings("unchecked")
//        private <T> ResponseEntity<T> createResponse(Object instance, Response response) {
//
//            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
//            for (String key : response.headers().keySet()) {
//                headers.put(key, new LinkedList<>(response.headers().get(key)));
//            }
//            return new ResponseEntity<>((T) instance, headers, org.springframework.http.HttpStatus.valueOf(response
//                    .status()));
//        }
//    }
//
//
//    @Bean
//    public ErrorDecoder errorDecoder() {
//        return new RestClientErrorDecoder(feignOauth2ClientContext);
//    }
//
//    /**
//     * Feign调用HTTP返回响应码错误时候，定制错误的解码
//     *
//     * @author maxianming
//     * @date 2018/10/30 9:45
//     */
//    class RestClientErrorDecoder implements ErrorDecoder {
//
//        private OAuth2ClientContext context;
//
//        RestClientErrorDecoder(OAuth2ClientContext context) {
//            this.context = context;
//        }
//
//        @Override
//        public Exception decode(String methodKey, Response response) {
//            log.error("Feign调用异常，异常methodKey:{}, token:{}, response:{}", methodKey, context.getAccessToken(), response.body());
//            if (HttpStatus.SC_UNAUTHORIZED == response.status()) {
//                log.error("接收到Feign请求资源响应401，access_token已经过期，重置access_token为null待重新获取。");
//                context.setAccessToken(null);
//                return new RetryableException(HttpStatus.SC_INTERNAL_SERVER_ERROR, "疑似access_token过期，即将进行重试", Request.HttpMethod.CONNECT, new Date(), response.request());
//            }
//            return FeignException.errorStatus(methodKey, response);
//        }
//    }
}
