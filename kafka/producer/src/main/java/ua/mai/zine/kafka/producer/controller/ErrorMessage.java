package ua.mai.zine.kafka.producer.controller;


import java.time.LocalDateTime;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ErrorMessage {
    private LocalDateTime timestamp;
    private String message;
}
