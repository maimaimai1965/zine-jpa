package ua.mai.zine.redis.repository;

import ua.mai.zine.redis.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {
}
