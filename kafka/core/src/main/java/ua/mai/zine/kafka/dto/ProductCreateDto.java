package ua.mai.zine.kafka.dto;

import java.math.BigDecimal;

public record ProductCreateDto (
        String title,
        BigDecimal price,
        Integer quantity
){}
