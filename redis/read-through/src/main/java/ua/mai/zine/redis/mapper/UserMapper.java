package ua.mai.zine.redis.mapper;

import org.mapstruct.Mapper;
import ua.mai.zine.redis.dto.UserDto;
import ua.mai.zine.redis.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // JPA
    UserDto toDto(UserEntity user);
    UserEntity toJpaEntity(UserDto dto);
}
