package ua.mai.zine.redis.dto;

import java.util.Set;

public record UserDto(
        String id,
        String name,
        int age,
        Set<String> events
) {}
