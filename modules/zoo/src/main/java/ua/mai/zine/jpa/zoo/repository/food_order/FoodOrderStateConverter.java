package ua.mai.zine.jpa.zoo.repository.food_order;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class FoodOrderStateConverter implements AttributeConverter<FoodOrderState, String> {

    @Override
    public String convertToDatabaseColumn(FoodOrderState state) {
        if (state == null) {
            return null;
        }
        return state.getCode();
    }

    @Override
    public FoodOrderState convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }
        return FoodOrderState.fromCode(code)
                .orElseThrow(() -> new IllegalArgumentException("Illegal FoodOrderState code: " + code));

//        return Stream.of(FoodOrderState.values())
//          .filter(c -> c.getCode().equals(code))
//          .findFirst()
//          .orElseThrow(IllegalArgumentException::new);
    }

}
