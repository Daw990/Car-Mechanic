package com.DawidM.SavingDataCar.controllers;

import com.DawidM.SavingDataCar.Services.CarService;
import com.DawidM.SavingDataCar.Services.Implementation.CarServiceImpl;
import com.DawidM.SavingDataCar.entity.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@Controller
@RequestMapping("/cars")
public class CarController {

    CarService carService;

    @Autowired
    public CarController(CarService carService){
        this.carService = carService;
    }

    @RequestMapping("/list") //patch to list of cars
    public String getCars(Model model){
        List<Car> allCars = carService.findAll();
        model.addAttribute("cars", allCars);
        return "actionsCars/cars-list";
    }

    @RequestMapping("/newCar") //patch u see in browse
    public String createCar(Model model) {
        model.addAttribute("car", new Car());
        return "actionsCars/carform";  // name of file html
    }

    @RequestMapping("/editCar") //patch u see in browse
    public String getCar(@RequestParam("id") Long id, Model model) {
        Car car = carService.findById(id);
        model.addAttribute("car", car);
        return "actionsCars/carform"; // name of file html
    }

    @RequestMapping(value="/editcars", method = RequestMethod.POST) //click button in site /car/editcar
    public String editCar(Car car){
        carService.save(car);
        return "redirect:/cars/list"; //back to list of cars
    }



    @PostMapping("/save") //action for "dodaj" in carform.html
    public String saveCar(@ModelAttribute("car") Car car){
        carService.save(car);
        return "redirect:/cars/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("carId") int id){

        carService.deleteById(id);
        return "redirect:/cars/list";
    }

//    @RequestMapping("/editCar/{id}")
//    public String editCar(Model model) {
//        model.getAttribute(service.getCar(id));
//        service.getCar(id);
//        return "carform";
//    }

}
