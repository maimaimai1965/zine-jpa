package ua.mai.zine.redis.repository;

import ua.mai.zine.redis.entity.UserRedisEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRedisRepository extends CrudRepository<UserRedisEntity, String> {}
