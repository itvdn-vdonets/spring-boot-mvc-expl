package com.exmpl.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
        Iterable<Car> all = repository.findAll();
        return (List<Car>) all;
    }


    public List<Car> getAllCarsPaged(Integer pageNumber, Integer pageSize, String sortBy) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        Page<Car> page = repository.findAll(pageRequest);
        if (page.hasContent()) {
            return page.getContent();
        } else {
            return new ArrayList<>();
        }
    }

    public List<Car> getAllCarsStartsWith(String line) {
        return repository.findAllByMarkStartingWith(line);
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