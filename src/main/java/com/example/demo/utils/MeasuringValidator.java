package com.example.demo.utils;

import com.example.demo.models.Measuring;
import com.example.demo.services.SensorService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MeasuringValidator implements Validator {

    private final SensorService sensorService;

    @Autowired
    public MeasuringValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(@NotNull Class<?> aClass) {
        return Measuring.class.equals(aClass);
    }

    @Override
    public void validate(@NotNull Object o, @NotNull Errors errors) {
        Measuring measurement = (Measuring) o;

        if (measurement.getSensor_name() == null) {
            return;
        }

        if (sensorService.findByName(measurement.getSensor_name().getName()).isEmpty())
            errors.rejectValue("sensor", "There is no registered sensor with this name!");
    }
}
