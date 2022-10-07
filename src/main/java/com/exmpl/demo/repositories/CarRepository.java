package com.exmpl.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.exmpl.demo.models.Car;

import java.util.List;

@Repository
public interface CarRepository extends PagingAndSortingRepository<Car, Long> {
    List<Car> findAllByMarkStartingWith(String line);


}
