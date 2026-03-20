package ua.mai.zine.redis;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@EnableCaching
@SpringBootApplication
public class RedisReadThroughAndWriteThroughApplication {

    private static final Logger log = LoggerFactory.getLogger(RedisReadThroughAndWriteThroughApplication.class);

    public static void main(String[] args) {

        java.util.TimeZone.setDefault(java.util.TimeZone.getTimeZone("UTC"));
        System.out.println("Set JVM timezone: " + java.util.TimeZone.getDefault().getID());

        ConfigurableApplicationContext context = SpringApplication.run(RedisReadThroughAndWriteThroughApplication.class, args);
        try {
            Environment env = context.getEnvironment();
            logApplicationStartup(env);

//            checkRepository(context);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void logApplicationStartup(Environment env) {
        String protocol = "http";
        if (env.getProperty("server.ssl.key-store") != null) {
            protocol = "https";
        }
        String serverPort = env.getProperty("server.port");
        String contextPath = env.getProperty("server.servlet.context-path");
        if (StringUtils.isBlank(contextPath)) {
            contextPath = "/";
        }
        String hostAddress = "localhost";
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            log.warn("The host name could not be determined, using `localhost` as fallback");
        }
        log.info("""
                 ----------------------------------------------------------
                 Application '{}' is running! Access URLs:
                 Local:       {}://localhost:{}{}
                 External:    {}://{}:{}{}
                 Profile(s):  {}
                 Postges:     url:  {}
                 Redis:       host: {}  port: {} 
                 ----------------------------------------------------------
                 """,
                env.getProperty("spring.application.name"),
                protocol,
                serverPort,
                contextPath,
                protocol,
                hostAddress,
                serverPort,
                contextPath,
                env.getActiveProfiles(),
                env.getProperty("spring.datasource.url"),
                env.getProperty("spring.data.redis.host"),
                env.getProperty("spring.data.redis.host")
        );
    }

}
