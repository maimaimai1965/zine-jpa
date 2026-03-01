package ua.mai.zine.kafka.producer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
public class ProductCreateDto {
    private String title;
    private BigDecimal price;
    private Integer quantity;
}
