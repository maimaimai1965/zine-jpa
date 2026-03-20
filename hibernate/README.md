

## Технологии

1. [Docker compose](#docker-compose)
2. [flyway](#flyway)
3. [JOOQ](#jooq)
4. [Querydsl](#querydsl)
5. [Построение методов с запросами к БД](#построение-методов-с-запросами-к-БД)
6. [Интеграционное тестирование](#интеграционное-тестирование)
   - [Управление транзакциями в тестах](#управление-транзакциями-в-тестах)
   - [Test container](#test-container)


## Docker compose

```
# Создание и старт контейнеров в своем docker stack:
docker compose up --build

# Удаление контейнеров, сети и volumes в своем docker stack:
docker compose down -v

# Создание и старт контейнеров в docker stack с именем zine-redis-learnig (флаг -p)
docker compose -p zine-redis-learnig up --build

# Удаление контейнеров, сети и volumes в docker stack с именем zine-redis-learnig
docker compose -p zine-redis-learnig down -v

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




## Построение методов с запросами к БД

См. [EmployeeRepository](src/main/java/ua/mai/zine/hibernate/repository/EmployeeRepository.java):

1. Построение запроса по ключевым словам (см. https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html).
```
   Optional<EmployeeEntity> findByFirstNameContaining(String firstName);
```

 2. Запрос на  JPQL (Java Persistence Query Language)
 ```
  @Query("select e from EmployeeEntity e where e.firstName = :name and e.salary = :salary")
   List<EmployeeEntity> findAllByFirstNameAndSalary(@Param("name") String firstName, @Param("salary") Integer salary);
```
3. Native SQL query
```
   @Query(value = "select e.* from employee e where e.first_name = :name and e.salary = :salary",
   nativeQuery = true)
   List<EmployeeEntity> findAllByFirstNameAndSalaryNative(@Param("name") String firstName, @Param("salary") Integer salary);
```
4. Построение запроса по ключевым словам + Projection
```
   List<EmployeeNameView> findAllBySalaryGreaterThan(Integer salary);
```
5.Native SQL query + Projection
```
    @Query(value = """
                   select e.id as id,
                          e.first_name || e.last_name as fullName
                   from employee e
                   where e.salary > :salary
                   """,
           nativeQuery = true)
    List<EmployeeNativeView> findAllBySalaryGreaterThanNative(@Param("salary") Integer salary);
```
См. [EmployeeRepositoryImpl](src/main/java/ua/mai/zine/hibernate/repository/EmployeeRepositoryImpl.java):

6. Использование JOOQ.



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

