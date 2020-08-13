package com.DawidM.SavingDataCar.controllers;

import com.DawidM.SavingDataCar.Services.*;
import com.DawidM.SavingDataCar.entity.Repair;
import com.DawidM.SavingDataCar.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private SignUpService signUpService;
    private RepairService repairService;
    private VisitService visitService;
    private CarService carService;
    private UserDetailsService userDetailsService;

    @Autowired
    public UserController(SignUpService signUpService, RepairService repairService,
                          VisitService visitService, CarService carService,
                          UserDetailsService userDetailsService){

        this.repairService = repairService;
        this.signUpService = signUpService;
        this.visitService = visitService;
        this.carService = carService;
        this.userDetailsService = userDetailsService;
    }

    @RequestMapping("/userPanel") //patch u see in browse
    public String userPanel(Model model, User user) {

        long userId = signUpService.getAuthenticatedUserId();
        user = signUpService.findById(userId);
        model.addAttribute("user", user);
        return "user/user-panel";  // name of file html
    }

    @RequestMapping("/repairsList")
    public String getRepairs(Model model, Repair repair){

        List <Repair> repairsVulcanization = repairService.findByCategory("vulcanization");
        List <Repair> repairsMechanics = repairService.findByCategory("mechanics");
        List <Repair> repairsElectrics = repairService.findByCategory("electrics");
        List <Repair> repairsAirCon = repairService.findByCategory("airConditioning");

        model.addAttribute("repairsVulcanization", repairsVulcanization);
        model.addAttribute("repairsMechanics", repairsMechanics);
        model.addAttribute("repairsAirCon", repairsAirCon);
        model.addAttribute("repairsElectrics", repairsElectrics);

        model.addAttribute("repair", new Repair());
        return "user/repair-list";
    }

    @RequestMapping("/newRepair") //patch u see in browse
    public String createRepair(Model model) {

        model.addAttribute("repair", new Repair());
        return "user/repair-form";  // name of file html
    }

    @PostMapping("/saveRepair") //action for "dodaj" in carform.html
    public String saveRepair(@ModelAttribute("repair") Repair repair){

        repairService.save(repair);
        return "redirect:/user/repairsList";
    }

    @GetMapping("/deleteRepair")
    public String delete(@RequestParam("idRepair") Long id){

        repairService.deleteById(id);
        return "redirect:/user/repairsList";
    }

    @GetMapping("/editRepair") //patch u see in browse
    public String getRepair(@RequestParam("id") Long id, Model model) {

        Repair repair = repairService.findById(id);
        model.addAttribute("repair", repair);
        return "user/repair-form"; // name of file html
    }

}
