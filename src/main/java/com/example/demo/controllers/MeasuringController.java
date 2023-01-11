package com.example.demo.controllers;

import com.example.demo.dto.MeasuringDTO;
import com.example.demo.models.Measuring;
import com.example.demo.services.MeasuringService;
import com.example.demo.utils.MeasuringErrorResponse;
import com.example.demo.utils.MeasuringValidator;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.demo.utils.ErrorsUtil.returnErrorsToClient;

@RestController
@RequestMapping("/measuring")
public class MeasuringController {

    private final MeasuringService measurementService;
    private final MeasuringValidator measuringValidator;
    private final ModelMapper modelMapper;

    @Autowired
    public MeasuringController(MeasuringService measurementService,
                               MeasuringValidator measuringValidator, ModelMapper modelMapper) {
        this.measurementService = measurementService;
        this.measuringValidator = measuringValidator;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody @Valid MeasuringDTO measuringDTO,
                                          BindingResult bindingResult) {
        Measuring measuringToAdd = convertToMeasuring(measuringDTO);
        measuringValidator.validate(measuringToAdd, bindingResult);
        if (bindingResult.hasErrors())
            returnErrorsToClient(bindingResult);
        measurementService.addMeasuring(measuringToAdd);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("getAll")
    public List<MeasuringDTO> getMeasuring() {
        return measurementService
                .findAll()
                .stream()
                .map(this::convertToMeasuringDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/rainyDaysCount")
    public Long getRainyDaysCount() {
        return measurementService
                .findAll()
                .stream()
                .filter(Measuring::getRain)
                .count();
    }

    @ExceptionHandler
    private ResponseEntity<MeasuringErrorResponse> handleException(Exception e) {
        MeasuringErrorResponse response = new MeasuringErrorResponse(
                e.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Measuring convertToMeasuring(MeasuringDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measuring.class);
    }

    private MeasuringDTO convertToMeasuringDTO(Measuring measuring) {
        return modelMapper.map(measuring, MeasuringDTO.class);
    }
}
