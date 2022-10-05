package com.exmpl.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exmpl.demo.exceptions.CarNotFoundException;
import com.exmpl.demo.models.Car;
import com.exmpl.demo.repositories.CarRepository;

@Service
public class CarService {

    final CarRepository repository;

    public CarService(CarRepository repository) {
        this.repository = repository;
    }

    public List<Car> getAllCars() {
        return (List<Car>) repository.findAll();
    }

    public Car getCarById(Long id) throws CarNotFoundException {
        return repository.findById(id).orElseThrow(() ->
                new CarNotFoundException("No car exist for given id"));
    }

    public Car createOrUpdateCar(Car car) {
        if (car.getId() == null) {
            car = repository.save(car);
            return car;
        }
        Optional<Car> cars = repository.findById(car.getId());
        if (cars.isPresent()) {
            Car newCar = cars.get();
            newCar.setDescription(car.getDescription());
            newCar.setMark(car.getMark());
            newCar.setModel(car.getModel());
            newCar = repository.save(newCar);
            return newCar;
        } else {
            car = repository.save(car);
            return car;
        }
    }

    public void deleteCarById(Long id) throws CarNotFoundException {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new CarNotFoundException("No car exist for given id");
        }
    }
}