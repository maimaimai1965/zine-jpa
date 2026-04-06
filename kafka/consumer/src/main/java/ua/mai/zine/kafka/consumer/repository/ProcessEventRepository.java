package ua.mai.zine.kafka.consumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.mai.zine.kafka.consumer.persistence.entity.ProcessedEventEntity;

@Repository
public interface ProcessEventRepository extends JpaRepository<ProcessedEventEntity, Long> {

    ProcessedEventEntity findByMessageId(String messageId);
}
