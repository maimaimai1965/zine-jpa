package ua.mai.zine.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RedisApplication {

    public static void main(String[] args) {


        java.util.TimeZone.setDefault(java.util.TimeZone.getTimeZone("UTC"));
        System.out.println("Set JVM timezone: " + java.util.TimeZone.getDefault().getID());

        SpringApplication.run(RedisApplication.class, args);
    }

}
