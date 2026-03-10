package ua.mai.zine.redis.repository;

import ua.mai.zine.redis.entity.EventRedisEntity;
import org.springframework.data.repository.CrudRepository;

public interface EventRedisRepository extends CrudRepository<EventRedisEntity, String> {}
