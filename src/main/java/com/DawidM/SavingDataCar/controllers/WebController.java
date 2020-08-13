package com.DawidM.SavingDataCar.controllers;

import com.DawidM.SavingDataCar.component.mailBuilder.SignUpMail;
import com.DawidM.SavingDataCar.entity.User;
import com.DawidM.SavingDataCar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class WebController {

    @Autowired
    private SignUpMail signUpMail;

    UserRepository userRepository;

    @Autowired
    public WebController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping(value = "/send_mail")
    public String test3() {
        signUpMail.sendMessage("test.web.application007@gmail.com", "temat Maila", "tresc maila");

        return "redirect:/";
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
