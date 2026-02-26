package ua.mai.zine.jpa.zoo.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.mai.zine.jpa.zoo.service.FoodService;
import ua.mai.zine.jpa.zoo.service.dto.FoodOrderDto;

import java.util.Date;

@RestController
@AllArgsConstructor
@RequestMapping("food")
public class FoodController {

    private final FoodService foodService;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody FoodOrderDto foodOrderDto){

        FoodOrderDto addedDto;
        try {
            addedDto = foodService.addFoodOrder(foodOrderDto, true);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(new ErrorMessage(new Date(), e.getMessage()));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(addedDto);
    }

}
