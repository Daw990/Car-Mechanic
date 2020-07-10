package com.DawidM.SavingDataCar.controllers;

import com.DawidM.SavingDataCar.Services.SignUpService;
import com.DawidM.SavingDataCar.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    private SignUpService signUpService;

    @Autowired
    public UserController(SignUpService signUpService){
        this.signUpService = signUpService;
    }

    @RequestMapping("/userPanel") //patch u see in browse
    public String userPanel(Model model, User user) {
        
        long userId = signUpService.getAuthenticatedUserId();
        model.addAttribute("userId", userId);
        return "user/user-panel";  // name of file html
    }

    @RequestMapping("/adminPanel") //patch u see in browse
    public String adminPanel() {

        return "user/admin-panel";  // name of file html
    }
}
