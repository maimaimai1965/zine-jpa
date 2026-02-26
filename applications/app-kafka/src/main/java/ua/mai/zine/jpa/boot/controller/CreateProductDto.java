package ua.mai.zine.jpa.boot.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
public class CreateProductDto {
    private String title;
    private BigDecimal price;
    private Integer quantity;
}
