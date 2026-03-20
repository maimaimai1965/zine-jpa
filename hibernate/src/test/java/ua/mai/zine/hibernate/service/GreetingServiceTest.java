package ua.mai.zine.hibernate.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ua.mai.zine.hibernate.JpaApplication;
import ua.mai.zine.hibernate.TestBase;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@SpringBootTest(classes = JpaApplication.class)
class GreetingServiceTest extends TestBase {

    @Autowired
    private GreetingService greetingService;

    @Test
    void greeting() {
        assertEquals("Hello World 2", greetingService.greeting());
    }

}