package com.starlight.cdp;

import com.starlight.cdp.core.banner.CDPBanner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author fosin
 */
@SpringBootApplication
@EnableEurekaServer
@Slf4j
public class EurekaServerApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(EurekaServerApplication.class)
                .banner(new CDPBanner("CDP Eureka Server"))
                .logStartupInfo(true)
                .run(args);
    }

    /**
     * 设置跨域过滤器
     * @return 跨域过滤器
     */
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //* 这个表示任意域名都可以访问，这样写不能携带cookie了
        corsConfiguration.addAllowedOrigin(CorsConfiguration.ALL);
        //设置允许请求头信息，例如Content-Type, Content-Length, Authorization, Accept, X-Requested-With , yourHeaderFeild
        corsConfiguration.addAllowedHeader(CorsConfiguration.ALL);
        //设置HTTP请求方法，例如PUT, POST, GET, DELETE, OPTIONS
        corsConfiguration.addAllowedMethod(CorsConfiguration.ALL);
        // 允许服务器端发送Cookie数据,
        // 若前端上axios.defaults.withCredentials = true设置为true了，
        // 则Access-Control-Allow-Credentials必须为true,否则会请求失败，报错
        corsConfiguration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",corsConfiguration);
        return new CorsFilter(source);
    }
}
