package com.github.fosin.anan;

import com.github.fosin.anan.core.banner.AnanBanner;
import com.github.fosin.anan.redis.annotation.EnableAnanRedis;
import com.github.fosin.anan.security.annotation.EnableAnanFormLogin;
import com.github.fosin.anan.security.annotation.EnableAnanSecurityOauth2;
import com.github.fosin.anan.swagger.annotation.EnableAnanSwagger2;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;

/**
 * Description
 *
 * @author fosin
 */
@SpringBootApplication
@EnableAnanRedis
@EnableAnanSwagger2
@EnableAnanFormLogin
@EnableAnanSecurityOauth2
public class AuthServerApplication {
//    public static void setLogFile(String appName, String name) {
//        LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
//        Configuration config = ctx.getConfiguration();
//
//        if (config.getAppender(appName) != null) {
//            RollingFileAppender rollingFileAppender = config.getAppender(appName);
//            config.getLoggerConfig("").removeAppender(appName);
//            rollingFileAppender.stop();
//            Appender appender = RollingFileAppender.newBuilder()
//                    .setName(appName+"_rebuild")
//                    .withFileName(name)
//                    .withFilePattern(rollingFileAppender.getFilePattern())
//                    .setLayout(rollingFileAppender.getLayout())
//                    .withPolicy(rollingFileAppender.getTriggeringPolicy())
//                    .build();
//            appender.start();
//            config.getLoggerConfig("").addAppender(appender, config.getRootLogger().getLevel(), null);
//            ctx.updateLoggers(config);
//        }
//    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(AuthServerApplication.class)
                .banner(new AnanBanner("AnAn Auth Server"))
                .logStartupInfo(true)
                .run(args);

//        SpringApplication app = new SpringApplicationBuilder(AuthServerApplication.class)
//                .banner(new AnanBanner("AnAn Auth Server"))
//                .logStartupInfo(true)
//                .application();
//        app.addListeners(new LogApplicationEventListener());
//        app.run(args);

    }

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /**
     * 将用户rememberme信息存在数据库中
     *
     * @return PersistentTokenRepository
     */
    @Bean
    @ConditionalOnMissingBean
    public PersistentTokenRepository persistentTokenRepository(DataSource dataSource) {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        // 此处需要设置数据源，否则无法从数据库查询验证信息
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }

}
