package com.exmpl.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.exmpl.demo.models.Car;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {

}
