package com.carbackend.service;

import com.carbackend.domain.Car;
import com.carbackend.domain.repository.CarRepository;
import com.carbackend.dto.CarDto;
import jakarta.persistence.EntityNotFoundException;
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

    public CarDto addCar(CarDto carDto) {
        Car car = Car.builder() //세이브 해야해서 만든다.
                .brand(carDto.getBrand())
                .model(carDto.getModel())
                .color(carDto.getColor())
                .modelYear(carDto.getModelYear())
                .registrationNumber(carDto.getRegistrationNumber())
                .price(carDto.getPrice())
                .build();
        Car savedCar = carRepository.save(car);
        carDto.setId(savedCar.getId());
        return carDto;
    }

    //업데이트가 특히 트랜잭션이 필요한데 클래스에 붙여놔서 메서드에 따로 안 붙이는것.
    public CarDto updateCar(CarDto carDto) {
        //아이디로 해당 레코드 조회해서 영속성 컨텍스트로 끌고 와야한다.
        Car car = carRepository.findById(carDto.getId())
                .orElseThrow(EntityNotFoundException::new);
        //영속성 컨텍스트로 들어온 카 엔티티 바꿔줘야함
        car.updateCar(carDto);
        return carDto;

    }

    public Long deleteCar(Long carId) {
        carRepository.deleteById(carId); //deleteById 라는 메서드는 기본 제공되는 메서드다
        return carId;
    }
}
