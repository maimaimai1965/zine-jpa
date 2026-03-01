package ua.mai.zine.kafka.producer.controller;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.mai.zine.kafka.dto.ProductCreateDto;
import ua.mai.zine.kafka.producer.service.ProductService;
import ua.mai.zine.kafka.producer.service.impl.ProductServiceImpl;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    public static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody ProductCreateDto productCreateDto) {
        String productId = null;
        try {
            productId = productService.createProduct(productCreateDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(productId);
        } catch (Exception e) {
            log.error("Error creating product: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ErrorMessage(LocalDateTime.now(), e.getMessage()));
        }
    }

}
