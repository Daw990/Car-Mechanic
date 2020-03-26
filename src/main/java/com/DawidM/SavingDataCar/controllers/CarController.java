package com.DawidM.SavingDataCar.controllers;

import com.DawidM.SavingDataCar.Services.CarService;
import com.DawidM.SavingDataCar.domain.Car;
import com.DawidM.SavingDataCar.domain.repository.WorkDayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CarController {

    @Autowired
    CarService service;

    @RequestMapping("/cars")
    public String getCars(Model model){
        List<Car> allCars = service.getAllCars();
        model.addAttribute("cars", allCars);
        return "cars";
    }

    @RequestMapping("/car/editCar")
    public String getCar(@PathParam("id") Integer id, Model model) {
        Car car = service.getCar(id);
        model.addAttribute("car", car);
        return "carEdit";
    }
    @RequestMapping(value="/editcars", method = RequestMethod.POST)
    public String editCar(Car car){
        service.updateCar(car);
        return "redirect:/cars";
    }

    @RequestMapping("/car/newCar")
    public String createCar(Model model) {
        model.addAttribute("car", new Car());
        return "carform";
    }

    @RequestMapping(value="/saveCar", method = RequestMethod.POST)
    public String saveCar(Car car){
        service.saveCar(car);
        return "redirect:/cars";
    }

    @RequestMapping(value="/car/delete/{id}")
    public String deleteCar(@PathVariable("id") Integer id){
        service.deleteCar(id);
        return "redirect:/cars";
    }

//    @RequestMapping("/editCar/{id}")
//    public String editCar(Model model) {
//        model.getAttribute(service.getCar(id));
//        service.getCar(id);
//        return "carform";
//    }

}
