

## Redis


```
# Заходим в контейнер в cli
docker exec -it zine-redis redis-cli 
docker exec -it proselyte-redis redis-cli 
```

### Redis Inside
Просмотр Redis: http://localhost:5540/

В окне "Add database" в поле "Connection URL" меняем:
redis://default@127.0.0.1:6379 -> redis://default@redis:6379

### Docker

```
# Собрать образ из Dockerfile
docker build -t my-image:1.0 .

# Запустить контейнер из образа
docker run -d -p 8080:8080 --name my-container my-image:1.0
```


### Docker Compose

```
# Создание и старт контейнеров в docker stack с именем zine-redis
docker compose up --build

# Удаление контейнеров, сети и volumes
docker compose down -v


# Создание и старт контейнеров в docker stack с именем zine-redis-learnig (флаг -p)
docker compose -p zine-redis-learnig up --build

# Удаление контейнеров, сети и volumes в docker stack с именем zine-redis-learnig
docker compose -p zine-redis-learnig down -v
```

### Алгоритм хэширования Cache-Aside

Реализация алгоритма:

1. Для Redis Entity должна использоваться аннотация [@Cacheable](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/cache/annotation/Cacheable.html) -
   [_**EventRedisEntity**_](cache-aside/src/main/java/ua/mai/zine/redis/entity/EventRedisEntity.java),
   [_**UserRedisEntity**_](cache-aside/src/main/java/ua/mai/zine/redis/entity/UserRedisEntity.java).
2. Должны быть описаны интерфейсы репозиториев для этих entity -
   [_**EventRedisRepository**_](cache-aside/src/main/java/ua/mai/zine/redis/repository/EventRedisRepository.java),
   [_**UserRedisRepository**_](cache-aside/src/main/java/ua/mai/zine/redis/repository/UserRedisRepository.java).
3. В сервисе должна быть реализована логика по сохранению значений в кэше и их получению из кэша -
   [_**EventRedisService**_](cache-aside/src/main/java/ua/mai/zine/redis/service/EventService.java),
   [_**UserRedisService**_](cache-aside/src/main/java/ua/mai/zine/redis/service/UserService.java). См.методы _create()_,
   _update()_, _delete()_.

### Алгоритм хэширования Read-Through

Реализация алгоритма:

1. Для приложения устанавливаем аннотацию [@EnableCaching](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/cache/annotation/EnableCaching.html) -
   [_**RedisCacheAsideApplication**_](read-through/src/main/java/ua/mai/zine/redis/RedisCacheAsideApplication.java),
2. DTO объекты должны реализовывать интерфейс _Serializable_ -
   [_**EventDto**_](read-through/src/main/java/ua/mai/zine/redis/dto/EventDto.java),
   [_**UserDto**_](read-through/src/main/java/ua/mai/zine/redis/dto/UserDto.java).
3. В сервисе должна быть реализована логика по сохранению значений в кэше и их получению через кэш. Эта логика
   описывается через аннотации _@Cacheable_ (метод _get()_), _@CachePut_ (метод _update()_) и _@CacheEvict_ (метод _delete()_) - 
   [_**EventService**_](read-through/src/main/java/ua/mai/zine/redis/service/EventService.java),
   [_**UserService**_](read-through/src/main/java/ua/mai/zine/redis/service/UserService.java).

Примечание. Не нужны Redis Entity, Redis Repository и аннотация _@Cacheable_!