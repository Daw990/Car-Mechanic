package com.DawidM.SavingDataCar.controllers;

import com.DawidM.SavingDataCar.DateAndTime;
import com.DawidM.SavingDataCar.Services.SignUpService;
import com.DawidM.SavingDataCar.entity.User;
import com.DawidM.SavingDataCar.entity.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class SignUpController {

    private SignUpService signUpService;

    @Autowired
    public SignUpController(SignUpService signUpService){
        this.signUpService = signUpService;
    }

    @PostMapping(value="/sign_up")
    public String signUp(@RequestParam("username") String username, @RequestParam("password") String password, //username - email
                         @RequestParam("phoneNumber") String phoneNumber, @RequestParam("firstName") String firstName,
                         @RequestParam("secondName") String secondName){
        User user = new User(username, password);
        UserData userData = new UserData(firstName, secondName, phoneNumber, DateAndTime.getDateAndTime());
        signUpService.signUpUser(user, userData);

        return "redirect:/login";
    }

    @GetMapping(value = "/sign_up")
    public String signUp(){
        return "user/user-sign-up2";
    }

//    @RequestMapping("/newUser") //patch u see in browser
//    public String createCar(Model model) {
//        model.addAttribute("user", new User());
//        model.addAttribute("userData", new UserData());
//        return "user/user-sign-up";  // name of file html
//    }
//
//    @PostMapping("/save") //action for "zarejestruj" in user-sign-up.html
//    public String saveUser(@ModelAttribute("user") User user, @ModelAttribute("userData") UserData userData){
//
//        userData.setDateRegistration(DateAndTime.getDateAndTime());
//        user.setUserData(userData);
//
//        userService.save(user);
//        userService.save(userData);
//
//        return "redirect:/user/userPanel";
//    }

}
