package com.DawidM.SavingDataCar.controllers;

import com.DawidM.SavingDataCar.Services.CarService;
import com.DawidM.SavingDataCar.Services.RepairService;
import com.DawidM.SavingDataCar.Services.SignUpService;
import com.DawidM.SavingDataCar.Services.VisitService;
import com.DawidM.SavingDataCar.entity.Repair;
import com.DawidM.SavingDataCar.entity.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/user/visit")
public class UserVisitController {

    CarService carService;
    UserDetailsService userDetailsService;
    SignUpService signUpService;
    VisitService visitService;
    RepairService repairService;


    @Autowired
    public UserVisitController(CarService carService, UserDetailsService userDetailsService,
                               SignUpService signUpService, VisitService visitService,
                               RepairService repairService){
        this.carService = carService;
        this.userDetailsService = userDetailsService;
        this.signUpService = signUpService;
        this.visitService = visitService;
        this.repairService = repairService;
    }


}
