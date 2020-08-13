package com.DawidM.SavingDataCar.controllers;

import com.DawidM.SavingDataCar.custom.DateAndTime;
import com.DawidM.SavingDataCar.Services.SignUpService;
import com.DawidM.SavingDataCar.entity.User;
import com.DawidM.SavingDataCar.entity.UserData;
import com.DawidM.SavingDataCar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/")
public class SignUpController {

    private SignUpService signUpService;
    private UserRepository userRepository;

    @Autowired
    public SignUpController(SignUpService signUpService, UserRepository userRepository){
        this.signUpService = signUpService;
        this.userRepository = userRepository;
    }

    @PostMapping(value="/sign_up")
    public String signUp(@RequestParam("username") String username, @RequestParam("password") String password, //username - email
                         @RequestParam("phoneNumber") String phoneNumber, @RequestParam("firstName") String firstName,
                         @RequestParam("secondName") String secondName){

        Optional<User> optionalUser = userRepository.findByEmail(username);

        if(!optionalUser.isPresent()) {
            User user = User.of(username, password);
            UserData userData = new UserData(firstName, secondName, phoneNumber, DateAndTime.getDateAndTime());
            signUpService.signUpUser(user, userData);
            return "redirect:/login?signUpSuccess";
        }else
            return "redirect:/sign_up?emailError";

    }

    @GetMapping(value = "/sign_up")
    public String signUp(){
        return "user/user-sign-up";
    }


}
