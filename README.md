

# Набор примеров приложений по разным технологиям

Kafka:
  - [проект kafka](kafka/README.md)<br/>
    Producer, Consumer, пересылка сообщения, retray сообщения, попадание с ообщения в DLQ, интеграционные тесты.
  - [проект kafka-transaction](kafka-transaction/README.md)<br/>
    Пример использования kafka транзакции при одновременной пересылке двух сообщений для двух
    consumer-ов через kafka.



## Some commands for working with the Kafka message broker.

```
# Удаление контейнеров, сети и volumes
docker-compose down -v

docker compose --build
```