package ua.mai.zine.hibernate;

import org.hibernate.cfg.Configuration;

public class HibernateRunner {

    public static void main(String[] args) {
        Configuration cfg = new Configuration();
        cfg.configure();
        System.out.println("Hibernate configuration is OK");

        try (var sessionFactory = cfg.buildSessionFactory()) {

        }
    }
}
