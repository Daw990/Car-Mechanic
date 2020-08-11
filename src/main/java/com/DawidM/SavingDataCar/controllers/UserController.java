package com.DawidM.SavingDataCar.controllers;

import com.DawidM.SavingDataCar.Services.*;
import com.DawidM.SavingDataCar.custom.DateAndTime;
import com.DawidM.SavingDataCar.custom.VisitHours;
import com.DawidM.SavingDataCar.entity.Car;
import com.DawidM.SavingDataCar.entity.Repair;
import com.DawidM.SavingDataCar.entity.User;
import com.DawidM.SavingDataCar.entity.Visit;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        model.addAttribute("userId", userId);
        return "user/user-panel";  // name of file html
    }

    @RequestMapping("/adminPanel") //patch u see in browse
    public String adminPanel() {

        return "user/admin-panel";  // name of file html
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

    @GetMapping("/makeVisit") //patch u see in browse
    public String makeVisit(@RequestParam("idRep") Long id, Model model, String date) {

        Repair repair = repairService.findById(id);
        model.addAttribute("repair", repair);
        return "user/visit-form"; // name of file html
    }

    @GetMapping("/checkHours") //patch u see in browse
    public String chceckHours(@RequestParam("date") String date,
                              @RequestParam("idRepair") Long idRepair, Model model) {
        Visit visit = new Visit();
        // visit repair
        Repair repair = repairService.findById(idRepair);

        int repairLength = repair.getRepairTime()/30;

        visit.setVisitDate(date);

        List<Visit> visits = visitService.getVisitsByDate(date);
        long idUser = signUpService.getAuthenticatedUserId();
        List<Car> allCars = carService.getAuthenticatedUserCars(idUser);

        Map<LocalTime, Integer> hoursMap = visitService.fillHoursMap();
        List<LocalTime> hours = visitService.getFreeHoursInList(hoursMap, repairLength);


        if(!visits.isEmpty()){

            for(Visit visit2: visits){

                Repair repairVisitInList = visit2.getRepair();
                //get this visit started time
                LocalTime visitTime = visit2.getVisitTime();

                Integer repairVisitInListTime = repairVisitInList.getRepairTime();

                int howManyIndexesDelete = repairVisitInListTime/30;

                hoursMap = visitService.changeValuesInHoursMap(hoursMap, howManyIndexesDelete, visitTime);

            }
            hours = visitService.getFreeHoursInList(hoursMap, repair.getRepairTime()/30);
            model.addAttribute("hours", hours);

        }else{

            model.addAttribute("hours", hours);
        }

        model.addAttribute("cars", allCars);
        model.addAttribute("repair", repair);
        model.addAttribute("visit", visit);
        return "user/visit-hours-form";
    }

    @PostMapping("/saveVisit")
    public String saveVisit(@ModelAttribute("visit") Visit visit,
                            @RequestParam("idRepair") Long idRepair,
                            @RequestParam("date") String date,
                            Authentication authentication){
        //set repair to visit
        Repair repair = repairService.findById(idRepair);
        visit.setRepair(repair);
        // set date to visit
        visit.setVisitDate(date);

        visit.setVisitStartedDate(DateAndTime.getDateAndTime());
        User user = (User) userDetailsService.loadUserByUsername(authentication.getName());
        user.addVisit(visit);
        visitService.save(visit);
        return "redirect:/user/userPanel";
    }

}
