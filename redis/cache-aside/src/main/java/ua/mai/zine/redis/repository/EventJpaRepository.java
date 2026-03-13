package ua.mai.zine.redis.repository;

import ua.mai.zine.redis.entity.EventJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventJpaRepository extends JpaRepository<EventJpaEntity, String> {
}
