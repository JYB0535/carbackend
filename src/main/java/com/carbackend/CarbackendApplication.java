package com.carbackend;

import com.carbackend.domain.Car;
import com.carbackend.domain.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j //로그 남겨줌 시스아웃이랑 다르게
@SpringBootApplication
@RequiredArgsConstructor
public class CarbackendApplication implements CommandLineRunner {

    private final CarRepository carRepository; //car에다가 더미 데이터 넣고 싶음

    public static void main(String[] args) {
        SpringApplication.run(CarbackendApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // 애플리케이션 로딩(스프링 컨텍스트 등) 후 실행하고 싶은 코드 //나중에 없어져야할 부분이다

        carRepository.save(Car.builder()
                .brand("Ford")
                .model("Mustang")
                .color("Red")
                .registrationNumber("ADF-1121")
                .modelYear(2023)
                .price(59000)
                .build());

        carRepository.save(Car.builder()
                .brand("bmw")
                .model("poly")
                .color("white")
                .registrationNumber("BCD-1234")
                .modelYear(2024)
                .price(69000)
                .build());

        carRepository.save(Car.builder()
                .brand("kia")
                .model("water")
                .color("blue")
                .registrationNumber("CDF-2345")
                .modelYear(2025)
                .price(79000)
                .build());
                                    //내장 메서드??
        for (Car car : carRepository.findAll()) {
            log.info("brand: {}, model:{}", car.getBrand(), car.getModel());

        }


    }
}
