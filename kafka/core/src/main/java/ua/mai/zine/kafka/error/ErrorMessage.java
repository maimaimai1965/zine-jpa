package ua.mai.zine.kafka.error;

import java.time.LocalDateTime;

public record ErrorMessage(
        LocalDateTime timestamp,
        String message
) {
}
