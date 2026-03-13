package ua.mai.zine.redis.dto;

import java.io.Serializable;

public record EventDto(
        String id,
        String title,
        String description
) implements Serializable {}
