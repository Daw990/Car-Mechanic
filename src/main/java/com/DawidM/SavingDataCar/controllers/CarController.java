package com.DawidM.SavingDataCar.controllers;

import com.DawidM.SavingDataCar.Services.CarService;
import com.DawidM.SavingDataCar.Services.SignUpService;
import com.DawidM.SavingDataCar.entity.Car;
import com.DawidM.SavingDataCar.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cars")
public class CarController {

    CarService carService;
    UserDetailsService userDetailsService;
    SignUpService signUpService;


    @Autowired
    public CarController(CarService carService, UserDetailsService userDetailsService, SignUpService signUpService){
        this.carService = carService;
        this.userDetailsService = userDetailsService;
        this.signUpService = signUpService;
    }

    @RequestMapping("/list") //patch to list of cars
    public String getCars(Model model, Authentication authentication){

//        long idUser = signUpService.getAuthenticatedUserId();
//        List<Car> allCars = carService.getAuthenticatedUserCars(idUser);
        User user = (User) userDetailsService.loadUserByUsername(authentication.getName());
        List<Car> allCars = user.getCars();
        model.addAttribute("cars", allCars);

        return "actionsCars/cars-list";
    }

    @RequestMapping("/newCar") //patch u see in browse
    public String createCar(Model model) {
        model.addAttribute("car", new Car());
        return "actionsCars/carform";  // name of file html
    }

    @GetMapping("/editCar") //patch u see in browse
    public String getCar(@RequestParam("id") Long id, Model model) {
        Car car = carService.findById(id);
        model.addAttribute("car", car);

        return "actionsCars/carform"; // name of file html
    }

//    @RequestMapping(value="/editcars", method = RequestMethod.POST) //click button in site /car/editcar
//    public String editCar(Car car){
//        carService.save(car);
//        return "redirect:/cars/list"; //back to list of cars
//    }

    @PostMapping("/save") //action for "dodaj" in carform.html
    public String saveCar(@ModelAttribute("car") Car car, Authentication authentication){

        User user = (User) userDetailsService.loadUserByUsername(authentication.getName());

        if(car.getIdCar() == null ){
            user.addCar(car);
        }
        car.setUser(user);

        carService.save(car);
        return "redirect:/cars/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("idCar") Long id){

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
