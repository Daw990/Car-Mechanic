package com.DawidM.SavingDataCar.controllers;

import com.DawidM.SavingDataCar.Services.SignUpService;
import com.DawidM.SavingDataCar.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WebController {


    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping(value = "/test")
    public String test3() {
        return "user/date/index3";
    }


}
