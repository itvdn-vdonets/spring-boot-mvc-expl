package com.exmpl.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.exmpl.demo.exceptions.CarNotFoundException;
import com.exmpl.demo.models.Car;
import com.exmpl.demo.services.CarService;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/")
public class CarController {
    final CarService service;

    public CarController(CarService service) {
        this.service = service;
    }

    @GetMapping
    public @ResponseBody List<Car> getAllCars(ModelAndView model) {
        List<Car> list = service.getAllCars();
        model.addObject("cars", list);
        model.setViewName("cars-table");
        return list;
    }


    @GetMapping("/search")
    public @ResponseBody List<Car> getAllCarsByPartialMark(@RequestParam("mark") String line) {
        return service.getAllCarsStartsWith(line);
    }


    @GetMapping("/paged")
    public @ResponseBody List<Car> getAllCarsByPage(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return service.getAllCarsPaged(pageNumber, pageSize, sortBy);
    }


    @GetMapping({"/edit", "/edit/{id}"})
    public String editCarById(Model model, @PathVariable("id") Optional<Long> id)
            throws CarNotFoundException {
        if (id.isPresent()) {
            Car entity = service.getCarById(id.get());
            model.addAttribute("car", entity);
        } else {
            model.addAttribute("car", new Car());
        }
        return "add-update-car";
    }

    @GetMapping(path = "/delete/{id}")
    public String deleteCarById(@PathVariable("id") Long id)
            throws CarNotFoundException {
        service.deleteCarById(id);
        return "redirect:/";
    }

    @PostMapping(path = "/addCar")
    public String createOrUpdateCar(Car car) {
        service.createOrUpdateCar(car);
        return "redirect:/";
    }
}
