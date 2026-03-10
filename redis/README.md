

## Redis


```
# Заходим в контейнер в cli
docker exec -it zine-redis redis-cli 
docker exec -it proselyte-redis redis-cli 
```

Redis Inside:
http://localhost:5540/

В окне "Add database" в поле "Connection URL" меняем:
redis://default@127.0.0.1:6379 -> redis://default@redis:6379


```
# Создание и старт контейнеров в docker stack с именем zine-redis
docker compose -p zine-redis-learnig up --build

# Удаление контейнеров, сети и volumes в docker stack с именем zine-redis-learnig
docker compose -p zine-redis-learnig down -v


# Создание и старт контейнеров в docker stack с именем zine-redis (в docker stack с именем docker)
docker compose up --build

# Удаление контейнеров, сети и volumes (в docker stack с именем docker)
docker compose down -v

``
