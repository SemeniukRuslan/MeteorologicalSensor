package com.example.demo.dto;

import com.example.demo.models.Sensor;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MeasuringDTO {

    @NotNull(message = "Should be not null")
    @Min(-75)
    @Max(75)
    private Double temperature;

    @NotNull(message = "Should be not null")
    private Boolean rain;

    @NotNull
    private SensorDTO sensor;
}
