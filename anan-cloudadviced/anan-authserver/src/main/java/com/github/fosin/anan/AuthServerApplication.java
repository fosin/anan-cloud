package com.github.fosin.anan;

import com.github.fosin.anan.core.banner.AnanBanner;
import com.github.fosin.anan.redis.annotation.EnableAnanRedis;
import com.github.fosin.anan.security.annotation.EnableAnanFormLogin;
import com.github.fosin.anan.security.annotation.EnableAnanSecurityOauth2;
import com.github.fosin.anan.swagger.annotation.EnableAnanSwagger2;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

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
//    public static void setLogFile(String serviceCode, String name) {
//        LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
//        Configuration config = ctx.getConfiguration();
//
//        if (config.getAppender(serviceCode) != null) {
//            RollingFileAppender rollingFileAppender = config.getAppender(serviceCode);
//            config.getLoggerConfig("").removeAppender(serviceCode);
//            rollingFileAppender.stop();
//            Appender appender = RollingFileAppender.newBuilder()
//                    .setName(serviceCode+"_rebuild")
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


}
