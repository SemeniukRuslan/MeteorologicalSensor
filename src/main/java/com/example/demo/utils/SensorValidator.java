package com.example.demo.utils;

import com.example.demo.models.Sensor;
import com.example.demo.services.SensorService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SensorValidator implements Validator {

    private final SensorService sensorService;

    @Autowired
    public SensorValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(@NotNull Class<?> aClass) {
        return Sensor.class.equals(aClass);
    }

    @Override
    public void validate(@NotNull Object o, @NotNull Errors errors) {
        Sensor sensor = (Sensor) o;

        if (sensorService.findByName(sensor.getName()).isPresent())
            errors.rejectValue("name", "There is already a sensor with that name!");
    }
}
