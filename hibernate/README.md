


## Описание

Примеры организации репозитория-интерфейса [EmployeeRepository](src/main/java/ua/mai/zine/hibernate/repository/EmployeeRepository.java)
через расширение интерфейса _JpaRepository_ и расширение его через дополнительный интерфейс
[EmployeeRepositoryCustom](src/main/java/ua/mai/zine/hibernate/repository/EmployeeRepositoryCustom.java) с имплементацией
последнего в [EmployeeRepositoryImpl](src/main/java/ua/mai/zine/hibernate/repository/EmployeeRepositoryImpl.java).

Приведены примеры различных [способов получения данных в репозитории](#способы-получения-данных-в-репозитории).

Показаны примеры интеграционных тестов с использованием [test container](#test-container). 

Используется PostgreSQL 17.9 разворачиваемая в контейнере docker.

Используется [flyway](#flyway) для накатывания скриптов в БД.

Для разворачивания приложения в контейнере docker используется [Dockerfile](Dockerfile).



## Технологии

- [flyway](#flyway)
- [JOOQ](#jooq)
- [Querydsl](#querydsl)
- [Docker](#docker)
- [Docker compose](#docker-compose)
- [Test container](#test-container)



## Docker

Контейнеры:
- Приложение _JpaApplication_
- PostgreSQL 17.9



## Docker compose

```
# Создание и старт контейнеров в своем docker stack:
docker compose up --build

# Удаление контейнеров, сети и volumes в своем docker stack:
docker compose down -v

# Postges
docker run --name postgres-zine -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=zine-jpa -p 5432:5432 -d postgres:17.9
```

## flyway

Настройка в _pom.xml_:

        <flyway.version>11.20.2</flyway.version
...

        <dependency>
            <groupId>org.flywaydb</groupId>
            <artifactId>flyway-core</artifactId>
            <version>${flyway.version}</version>
        </dependency>
        <dependency>
            <groupId>org.flywaydb</groupId>
            <artifactId>flyway-database-postgresql</artifactId>
            <version>${flyway.version}</version>
            <scope>runtime</scope>
        </dependency>

## JOOQ
https://www.jooq.org/



## Querydsl
http://querydsl.com/



## Способы получения данных в репозитории

См. [EmployeeRepository](src/main/java/ua/mai/zine/hibernate/repository/EmployeeRepository.java):

1. Построение запроса по ключевым словам (см. https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html).
```java
   Optional<EmployeeEntity> findByFirstNameContaining(String firstName);
```

 2. Запрос на  JPQL (Java Persistence Query Language)
 ```java
  @Query("select e from EmployeeEntity e where e.firstName = :name and e.salary = :salary")
   List<EmployeeEntity> findAllByFirstNameAndSalary(@Param("name") String firstName, @Param("salary") Integer salary);
```
3. Native SQL query
```java
   @Query(value = "select e.* from employee e where e.first_name = :name and e.salary = :salary",
   nativeQuery = true)
   List<EmployeeEntity> findAllByFirstNameAndSalaryNative(@Param("name") String firstName, @Param("salary") Integer salary);
```
4. Построение запроса по ключевым словам + Projection
```java
   List<EmployeeNameView> findAllBySalaryGreaterThan(Integer salary);
```
5.Native SQL query + Projection
```java
    @Query(value = """
                   select e.id as id,
                          e.first_name || e.last_name as fullName
                   from employee e
                   where e.salary > :salary
                   """,
           nativeQuery = true)
    List<EmployeeNativeView> findAllBySalaryGreaterThanNative(@Param("salary") Integer salary);
```
6. Использование JOOQ.
```java
    @Override
    public List<EmployeeEntity> findByFilterWithJooq(EmployeeFilter filter) {
        List<EmployeeEntity> list = dslContext.select(EMPLOYEE.fields())
                .from(EMPLOYEE)
                .where(EMPLOYEE.FIRST_NAME.equalIgnoreCase(filter.getFirstName()))
                .fetch(record -> record.into(EmployeeEntity.class));
        return list;
    }
```
См. [EmployeeRepositoryImpl](src/main/java/ua/mai/zine/hibernate/repository/EmployeeRepositoryImpl.java)

7. Использование JPAQuery.

[EmployeeFilter](src/main/java/ua/mai/zine/hibernate/dto/EmployeeFilter.java):
```java
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeFilter {

    private String firstName;
    private String lastName;
    private Integer salary;

}
```
[EmployeeRepositoryImpl](src/main/java/ua/mai/zine/hibernate/repository/EmployeeRepositoryImpl.java):
```java
    @Override
public List<EmployeeEntity> findByFilterWithJpaQuery(EmployeeFilter filter) {
   return new JPAQuery<EmployeeEntity>(entityManager)
           .select(employeeEntity)
           .from(employeeEntity)
           .where(employeeEntity.firstName.containsIgnoreCase(filter.getFirstName()))
           .fetch();
}
```



## Интеграционное тестирование

У интеграционных тестов название класса заканчивается на **IT**.<br/>
Для выполнения интеграционных тестов используется _maven-failsafe-plugin_ с goals _integration-test_, _verify_.<br/>
Чтобы интеграционные тесты не выполнялись фазе _test_ (проверки Unit тестов) в плагине _maven-surefire-plugin_
исключены классы заканчивается на **IT** и **ITCase**:

        <configuration>
            <excludes>
                <exclude>**/*IT.java</exclude>
                <exclude>**/*ITCase.java</exclude>
            </excludes>
        </configuration>


### Управление транзакциями в тестах
https://javarush.com/quests/lectures/questspring.level02.lecture09#transaction_rollback


### Test container

Для интеграционного тестирования используется _testcontainers-postgresql_:

        <dependency>
            <groupId>org.testcontainers</groupId>
            <artifactId>testcontainers-postgresql</artifactId>
            <version>2.0.3</version>
            <scope>test</scope>
        </dependency>

1. Определяем Initializer для этого контейнера в классе [Postgres](src/test/java/ua/mai/zine/hibernate/initializer/Postgres.java).
   В нем определяем версию PostgreSQL и название нашей БД. <br/>
   Также прописываем свойства контейнера в наши параметры из yml конфигурации:
     - _spring.datasource.url_
     - _spring.datasource.username_
     - _spring.datasource.password_
2. В класс, являющийся родителем классов с интеграционными тестами ([IntegrationTestBase](src/test/java/ua/mai/zine/hibernate/IntegrationTestBase.java))
   добавляем аннотацию _@ContextConfiguration_ и _@BeforeAll_ метод:
```
@SpringBootTest
@ContextConfiguration(initializers = {
        Postgres.Initializer.class
})
public abstract class IntegrationTestBase {

    @BeforeAll
    static void init() {
        Postgres.container.start();
    }
}
```

