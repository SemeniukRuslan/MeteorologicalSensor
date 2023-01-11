package com.example.demo.services;

import com.example.demo.models.Measuring;
import com.example.demo.repositories.MeasuringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasuringService {

    private final MeasuringRepository measurementRepository;
    private final SensorService sensorService;

    @Autowired
    public MeasuringService(MeasuringRepository measurementRepository,
                            SensorService sensorService) {
        this.measurementRepository = measurementRepository;
        this.sensorService = sensorService;
    }

    public List<Measuring> findAll(){
        return measurementRepository.findAll();
    }

    @Transactional
    public void addMeasuring(Measuring measuring){
        enrichMeasuring(measuring);
        measurementRepository.save(measuring);
    }

    private void enrichMeasuring(Measuring measuring) {
        measuring.setSensor_name(sensorService.findByName(
                measuring.getSensor_name().getName()).get());
        measuring.setMeasuringDateTime((LocalDateTime.now()));
    }
}
