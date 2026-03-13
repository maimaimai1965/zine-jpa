package ua.mai.zine.redis.repository;

import ua.mai.zine.redis.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<EventEntity, String> {
}
