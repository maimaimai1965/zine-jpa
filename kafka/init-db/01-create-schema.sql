-- Создаём схему (замените zine_schema на нужное вам имя)
CREATE SCHEMA IF NOT EXISTS zine_kafka;

-- Делаем схему доступной по умолчанию для нашей базы
ALTER DATABASE "zine-kafka" SET search_path TO zine_kafka, public;

-- Даём все права пользователю postgres на новую схему
GRANT ALL PRIVILEGES ON SCHEMA zine_kafka TO postgres;

-- Опционально: комментарий для удобства
COMMENT ON SCHEMA zine_kafka IS 'Основная схема приложения Zine Kafka';