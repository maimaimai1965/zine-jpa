package ua.mai.zine.jpa.zoo.repository.food_order;

import java.util.Optional;import java.util.stream.Stream;

public enum FoodOrderState {
    NEW ("N"),
    IN_PROGRESS ("I"),
    REGISTERED("R");

    final String code;


    FoodOrderState(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static Optional<FoodOrderState> fromCode(final String code) {
        return Stream.of(FoodOrderState.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst();
    }

}
