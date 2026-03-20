package ua.mai.zine.hibernate.repository;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import ua.mai.zine.hibernate.IntegrationTestBase;
import ua.mai.zine.hibernate.entity.CompanyEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CompanyRepositoryTestIT extends IntegrationTestBase {

    private static final Integer APPLE_ID = 1;

    @Autowired
    private CompanyRepository companyRepository;

    @BeforeAll
    static void setUpAll() {
    }

    @AfterAll
    static void tearDownAll() {
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testGetById() {
        Optional<CompanyEntity> company = companyRepository.findById(APPLE_ID);
        assertTrue(company.isPresent());
        company.ifPresent(entity -> {
            assertEquals("Apple", entity.getName());
        });
    }

    @Test
    void testSave() {
        CompanyEntity company = CompanyEntity.builder()
                .name("Fitbit")
                .build();
        companyRepository.save(company);
        assertNotNull(company.getId());
    }

}
