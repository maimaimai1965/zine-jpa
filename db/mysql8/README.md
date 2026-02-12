# mysql8  

Контейнер MySQL 8.4.6

### Installation steps

1. Необходимо в начале создать network *zine-net*:

$ docker network create --attachable --driver bridge zine-net

2. Выполняем [docker-compose.yaml](docker\docker-compose.yaml) 

### By default, the stack exposes the following ports:

* **3316**: MySQL 8.4.6
    * Credential: (root/root)

### Authors

mai
