package ua.mai.zine.redis.mapper;

import org.mapstruct.Mapper;
import ua.mai.zine.redis.dto.UserDto;
import ua.mai.zine.redis.entity.UserJpaEntity;
import ua.mai.zine.redis.entity.UserRedisEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // Redis
    UserDto toDto(UserRedisEntity user);
    UserRedisEntity toRedisEntity(UserDto dto);

    // JPA
    UserDto toDto(UserJpaEntity user);
    UserJpaEntity toJpaEntity(UserDto dto);
}
