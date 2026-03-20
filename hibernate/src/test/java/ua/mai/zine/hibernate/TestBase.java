package ua.mai.zine.hibernate;

import org.junit.jupiter.api.BeforeAll;

import java.util.TimeZone;

public abstract class TestBase {

    @BeforeAll
    static void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

}
