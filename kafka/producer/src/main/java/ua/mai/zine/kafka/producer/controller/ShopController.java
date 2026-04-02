package ua.mai.zine.kafka.producer.controller;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.mai.zine.kafka.dto.ProductCreateDto;
import ua.mai.zine.kafka.dto.ShopCreateDto;
import ua.mai.zine.kafka.error.ErrorMessage;
import ua.mai.zine.kafka.producer.service.ShopService;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/shops")
@AllArgsConstructor
public class ShopController {

    public static final Logger log = LoggerFactory.getLogger(ShopController.class);

    private final ShopService shopService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> create(@RequestBody ShopCreateDto shopCreateDto) {
        String shopId = null;
        try {

            shopId = shopService.createShop(shopCreateDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(shopId);

        } catch (Exception e) {
            log.error("Error creating shop: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ErrorMessage(LocalDateTime.now(), e.getMessage()));
        }
    }

}
