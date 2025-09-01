package com.carbackend.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarDto {
    private Long id;
    private String brand;
    private String model;
    private String color;
    private String registrationNumber;
    private Integer modelYear;
    private Integer price;

}
