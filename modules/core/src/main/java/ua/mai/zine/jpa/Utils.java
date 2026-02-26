package ua.mai.zine.jpa;

import java.math.BigDecimal;

public interface Utils {

    static Double toDouble(BigDecimal value) {
        return value != null ? value.doubleValue() : null;
    }

    static BigDecimal toBigDecimal(Double value) {
        return value != null ? BigDecimal.valueOf(value) : null;
    }

}
