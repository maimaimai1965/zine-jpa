package ua.mai.zine.kafka.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.mai.zine.kafka.dto.ProductCreateDto;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductCreateEvent {
    private String productId;
    private String title;
    private BigDecimal price;
    private Integer quantity;

    public static ProductCreateEvent create(String productId, ProductCreateDto dto) {
        return new ProductCreateEvent(productId, dto.getTitle(), dto.getPrice(), dto.getQuantity());
    }
}
