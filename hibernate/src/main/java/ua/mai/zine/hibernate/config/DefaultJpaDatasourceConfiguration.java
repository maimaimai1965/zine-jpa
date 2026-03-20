//package ua.mai.zine.hibernate.config;
//
////@Configuration
//public class DefaultJpaDatasourceConfiguration {
//
//    @Bean
//    @ConfigurationProperties("jpa.datasource.zine")
//    public DataSourceProperties defaultDataSourceProperties() {
//        return new DataSourceProperties();
//    }
//
//    @Bean
//    @Primary
//    public TransactionTemplate defaultTransactionTemplate(PlatformTransactionManager transactionManager) {
//        return new TransactionTemplate(transactionManager);
//    }
//}
