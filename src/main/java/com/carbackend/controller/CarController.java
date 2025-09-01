package com.carbackend.controller;

import com.carbackend.domain.repository.CarRepository;
import com.carbackend.dto.CarDto;
import com.carbackend.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping("/cars")
    public List<CarDto> getCars() {
        return carService.findAll();

    }
}
