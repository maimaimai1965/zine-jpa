package ua.mai.zine.kafka.producer.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.mai.zine.kafka.producer.dto.CreateProductDto;
import ua.mai.zine.kafka.producer.service.ProductService;

@Controller
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody CreateProductDto createProductDto) {
        String productId = productService.createProduct(createProductDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productId);
    }

}
