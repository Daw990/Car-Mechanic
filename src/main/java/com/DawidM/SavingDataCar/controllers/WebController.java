package com.DawidM.SavingDataCar.controllers;

import com.DawidM.SavingDataCar.Services.SignUpService;
import com.DawidM.SavingDataCar.entity.User;
import com.DawidM.SavingDataCar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class WebController {

    UserRepository userRepository;

    @Autowired
    public WebController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping("/confirm_email")
    public String confirmEmail(@RequestParam(name="token") String token){

        Optional<User> optionalUser = userRepository.findByConfirmationToken(token);

        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            user.setEnabled(true);
            userRepository.save(user);
            return "redirect:/login?confirmEmailSuccess";
        }else{
            return "redirect:/login?errorConfirmEmail";
        }
    }


}
