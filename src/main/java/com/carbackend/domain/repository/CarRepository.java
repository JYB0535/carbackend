package com.carbackend.domain.repository;

import com.carbackend.domain.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, Long> {
    //crudRepository는 JPA repository 보다 light한 기능을 가지고 있음
    
}
