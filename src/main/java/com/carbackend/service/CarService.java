package com.carbackend.service;

import com.carbackend.domain.Car;
import com.carbackend.domain.repository.CarRepository;
import com.carbackend.dto.CarDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    public List<CarDto> findAll() {
        List<CarDto> carDtos = new ArrayList<>();
        for (Car car : carRepository.findAll()) {
            CarDto carDto = CarDto.builder()
                    .id(car.getId())
                    .brand(car.getBrand())
                    .model(car.getModel())
                    .color(car.getColor())
                    .registrationNumber(car.getRegistrationNumber())
                    .modelYear(car.getModelYear())
                    .price(car.getPrice())
                    .build();
            carDtos.add(carDto);
        }
        return carDtos;
    }
}
