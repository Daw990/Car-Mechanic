package com.DawidM.SavingDataCar.controllers;

import com.DawidM.SavingDataCar.Services.VisitService;
import com.DawidM.SavingDataCar.entity.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestRestController {

    VisitService visitService;

    @Autowired
    public TestRestController(VisitService visitService){
        this.visitService = visitService;
    }

    private List<Visit> visits;


    @GetMapping("/visits")
    public List<Visit> getVisits(){
        List<Visit> visits;
        return visits = visitService.findAll();
    }


}
