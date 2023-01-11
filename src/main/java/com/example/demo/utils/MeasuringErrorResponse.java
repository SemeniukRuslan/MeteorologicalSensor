package com.example.demo.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class MeasuringErrorResponse {

    private String message;
    private LocalDateTime localDateTime;
}
