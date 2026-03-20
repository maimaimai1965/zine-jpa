package ua.mai.zine.redis.mapper;

import org.mapstruct.Mapper;
import ua.mai.zine.redis.dto.EventDto;
import ua.mai.zine.redis.entity.EventEntity;

@Mapper(componentModel = "spring")
public interface EventMapper {

    // JPA
    EventDto toDto(EventEntity event);
    EventEntity toJpaEntity(EventDto dto);
}
