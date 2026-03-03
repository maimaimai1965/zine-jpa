package ua.mai.zine.kafka.event;

import ua.mai.zine.kafka.dto.ProductCreateDto;
import java.math.BigDecimal;

public record ProductCreateEvent(
        String productId,
        String title,
        BigDecimal price,
        Integer quantity
) {
    public static ProductCreateEvent create(String productId, ProductCreateDto dto) {
        return new ProductCreateEvent(productId, dto.title(), dto.price(), dto.quantity());
    }

}
