package com.example.demo.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SensorDTO {

    @NotEmpty(message = "Should be not empty")
    @Size(min = 3, max = 30, message = "The name must be between 3 and 30 characters")
    private String name;
}
