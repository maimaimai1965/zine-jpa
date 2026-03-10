package ua.mai.zine.redis.mapper;

import org.mapstruct.Mapper;
import ua.mai.zine.redis.dto.EventDto;
import ua.mai.zine.redis.entity.EventJpaEntity;
import ua.mai.zine.redis.entity.EventRedisEntity;

@Mapper(componentModel = "spring")
public interface EventMapper {

    // Redis
    EventDto toDto(EventRedisEntity event);
    EventRedisEntity toRedisEntity(EventDto dto);

    // JPA
    EventDto toDto(EventJpaEntity event);
    EventJpaEntity toJpaEntity(EventDto dto);
}
