
## Установка Kafka

Скачиваем с https://kafka.apache.org/community/downloads/

Версия 4.2.0: https://dlcdn.apache.org/kafka/4.2.0/kafka_2.13-4.2.0.tgz

Версия 3.9.1: https://dlcdn.apache.org/kafka/3.9.2/kafka_2.13-3.9.2.tgz


### Настройка KRaft

В каталоге _\config\kraft_ (4.2.0), _\config\kraft_ (3.9.1),  файлы:

  - _broker.properties_ - конфигурация сервера, который действует как брокер (Настройка топиков и партиций)<br>
  - _controller.properties_ - конфигурация сервера, который действует как контроллер (управляет метаданными кластеоа,
    координирует лидеров)<br>
  - _server.properties_- конфигурация сервера, который действует как контроллер и как брокер<br>
  - _reconfig-server.properties_

4.2.0, 3.9.1 (localhost:9092):
```
process.roles=broker,controller
node.id=1
controller.quorum.voters=1@localhost:9093
listeners=PLAINTEXT://:9092,CONTROLLER://:9093
advertised.listeners=PLAINTEXT://localhost:9092,CONTROLLER://localhost:9093
```

### Настройка в режиме KRaft server

1. Переходим в _bin\windows_.

2. Получаем id:
```
kafka-storage.bat random-uuid
```
S8_YcqRnT766wJOydSTM9w


3. Форматируем логи для совместимости с KRaft:<br>
   - 4.2.0:
```
kafka-storage.bat format -t S8_YcqRnT766wJOydSTM9w --standalone -c ../../config/server.properties

Создается каталог (с данными) - C:\tmp\kafka-4.2\kraft-combined-logs 
```
   - 3.9.1:
```
kafka-storage.bat format -t deQlPYbjTBaEOKLQ4AbpXQ -c ../../config/kraft/server.properties

Создается каталог (с данными) - C:\tmp\kraft-combined-logs 
```

4. Запуск kafka с дефолтным конфигом:<br>
   - 4.2.0:
```
kafka-server-start.bat ../../config/server.properties
```
   - 3.9.1:
```
`kafka-server-start.bat ../../config/kraft/server.properties`
```

### Запуск установленного сервера
- 4.2.0:
```
cd C:/Java/kafka-4.2.0/bin/windows 
kafka-server-start.bat ../../config/server.properties
```



### Настройка нескольких серверов Kafka (cluster)  - !НЕ ПОЛУЧИЛОСЬ!

1. Делаем config файл под каждый сервер. Копируем файл _server.properties_:<br>
   _server1.properties_<br>
   _server2.properties_<br>
   _server3.properties_<br>

2. В каждом из файлов обновляем свойства:

   - _nodeId_ - определяет уникальный идентификатор для сервера Kafka в кластере;<br>

   - _listeners_ - определяет список адресов (host:port) для брокеров и контроллеров. Это сетевые интерфейсы, которые Kafka
     использует для общения с другими серверами и клиентами;<br>

   - _controller.quorum.voters_ - список voters (избирателей), которые составляют кворум в кластере, принимая решение по
     согласованности данных и отказоустойчивости;<br>

   - _advertised.listeners_ - список адресов для соединения с брокером (отличается от свойства listeners). Определяет
     адреса и порты, которые брокер Kafka использует для прослушивания входящих подключений;

   - _log.dirs_ - локальная директория метаданных, логов, снапшотов данного сервера.

_server1.properties_:<br>
```
node.id=1
listeners=PLAINTEXT://:9072,CONTROLLER://:9073
controller.quorum.voters=1@localhost:9073,2@localhost:9075,3@localhost:9077
advertised.listeners=PLAINTEXT://localhost:9072,CONTROLLER://localhost:9073
log.dirs=/tmp/server1/kraft-combined-logs
```
_server2.properties_:<br>
```
node.id=2
listeners=PLAINTEXT://:9074,CONTROLLER://:9075
controller.quorum.voters=1@localhost:9073,2@localhost:9075,3@localhost:9077
advertised.listeners=PLAINTEXT://localhost:9074,CONTROLLER://localhost:9075
log.dirs=/tmp/server2/kraft-combined-logs
```
_server3.properties_:<br>
```
node.id=3
listeners=PLAINTEXT://:9076,CONTROLLER://:9077
controller.quorum.voters=1@localhost:9073,2@localhost:9075,3@localhost:9077
advertised.listeners=PLAINTEXT://localhost:9076,CONTROLLER://localhost:9077
log.dirs=/tmp/server3/kraft-combined-logs
```

4. В каталоге _bin\windows_ выполняем комманду по получению id:

> kafka-storage.bat random-uuid<br>
afOCbtYlQfiJD5DKvqz8Fw

5. Форматируем логи для совместимости с KRaft:
```
kafka-storage.bat format -t afOCbtYlQfiJD5DKvqz8Fw -c ../../config/kraft/server1.properties
kafka-storage.bat format -t afOCbtYlQfiJD5DKvqz8Fw -c ../../config/kraft/server2.properties
kafka-storage.bat format -t afOCbtYlQfiJD5DKvqz8Fw -c ../../config/kraft/server3.properties
```

6. Запуск серверов kafka:
```
kafka-server-start.bat ../../config/kraft/server1.properties
kafka-server-start.bat ../../config/kraft/server2.properties
kafka-server-start.bat ../../config/kraft/server3.properties
```

## Команды Kafka

### Topics

```
# Создание топика c 3 партициями:
kafka-topics.bat --create --topic payments-created-events-topic --partitions 3 --bootstrap-server localhost:9092

# Список топиков:
kafka-topics.bat --list --bootstrap-server localhost:9092

# Описание топиков:
kafka-topics.bat --describe --bootstrap-server localhost:9092

# Удаление топика:
kafka-topics.bat --delete --topic payments-created-events-topic --bootstrap-server localhost:9092

```
Если после удаления топика Kafka упала, то нужно удалить временные каталоги сервера и создать их заново. 

### Messages

#### Отсылка сообщений через producer
```
# Отсылка сообщения (без указания key):
kafka-console-producer.bat --bootstrap-server localhost:9092 --topic payments-created-events-topic
  Вводим сообщение после ">" и нажимаем Enter. Например:
  >{"id":1,"amount":1000,"currency":"USD"}

# Отсылка сообщения в партицию (с указанием key):
kafka-console-producer.bat --bootstrap-server localhost:9092 --topic payments-created-events-topic --property parse.key=true --property key.separator=:
  Вводим сообщение после ">" и нажимаем Enter. Например:
  >order-1:{"amount":100}
```
#### Чтение сообщений через producer
```
# Чтение сообщений (без показа key):
kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic payments-created-events-topic --from-beginning

# Чтение сообщений (с показом key):
kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic payments-created-events-topic --property print.key=true
```



## Some commands for working with the Kafka message broker.

```
# Describe the configuration of a topic
bin/kafka-configs.sh --describe --entity-type topics --entity-name my-topic --bootstrap-server localhost:9092

# Alter the configuration of a topic
bin/kafka-configs.sh --alter --entity-type topics --entity-name my-topic --add-config retention.ms=604800000 --bootstrap-server localhost:9092

# Start a Kafka producer
bin/kafka-console-producer.sh --topic my-topic --bootstrap-server localhost:9092

# Start a Kafka consumer
bin/kafka-console-consumer.sh --topic my-topic --from-beginning --bootstrap-server localhost:9092

# Check consumer groups
bin/kafka-consumer-groups.sh --list --bootstrap-server localhost:9092

# Describe a consumer group
bin/kafka-consumer-groups.sh --describe --group my-group --bootstrap-server localhost:9092

# Delete a consumer group
bin/kafka-consumer-groups.sh --delete --group my-group --bootstrap-server localhost:9092

# Alter the configuration of a broker
bin/kafka-configs.sh --alter --entity-type brokers --entity-name <broker-id> --add-config log.retention.hours=168 --bootstrap-server localhost:9092

# Describe the configuration of a broker
bin/kafka-configs.sh --describe --entity-type brokers --entity-name <broker-id> --bootstrap-server localhost:9092
```

## Создание Producer

