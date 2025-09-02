package com.carbackend.controller;

import com.carbackend.domain.repository.CarRepository;
import com.carbackend.dto.CarDto;
import com.carbackend.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping("/cars")
    public List<CarDto> getCars() {
        return carService.findAll();

    }

    @PostMapping("/cars")
    public CarDto addCar(@RequestBody CarDto carDto) {
        return carService.addCar(carDto);
    }

    @PutMapping("/cars")
    public CarDto updateCar(@RequestBody CarDto carDto) {
        return carService.updateCar(carDto);
    }

    @DeleteMapping("/cars/{carId}")
    public Long deleteCar(@PathVariable("carId") Long carId) {
        return carService.deleteCar(carId);

    }
}
