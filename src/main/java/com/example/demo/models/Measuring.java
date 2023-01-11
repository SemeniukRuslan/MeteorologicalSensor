package com.example.demo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "Measuring")
@Getter
@Setter
public class Measuring {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "temperature")
    @NotNull(message = "Should be not null")
    @Min(-75)
    @Max(75)
    private Double temperature;

    @Column(name = "rain")
    @NotNull(message = "Should be not null")
    private Boolean rain;

    @Column(name = "measurement_date_time")
    @NotNull(message = "Should be not null")
    private LocalDateTime MeasuringDateTime;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "sensor_name", referencedColumnName = "name")
    //not working, need Serializable impl. class sensor,
    // because JoinColumn not a primitive
    private Sensor sensor_name;
}
