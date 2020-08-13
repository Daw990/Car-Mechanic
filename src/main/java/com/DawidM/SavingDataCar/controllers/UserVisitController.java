package com.DawidM.SavingDataCar.controllers;

import com.DawidM.SavingDataCar.Services.CarService;
import com.DawidM.SavingDataCar.Services.RepairService;
import com.DawidM.SavingDataCar.Services.SignUpService;
import com.DawidM.SavingDataCar.Services.VisitService;
import com.DawidM.SavingDataCar.custom.DateAndTime;
import com.DawidM.SavingDataCar.entity.Car;
import com.DawidM.SavingDataCar.entity.Repair;
import com.DawidM.SavingDataCar.entity.User;
import com.DawidM.SavingDataCar.entity.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/makeVisit") //patch u see in browse
    public String makeVisit(@RequestParam("idRep") Long id, Model model) {

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

    @GetMapping("/visitList")
    public String myVisits(Model model, Authentication authentication){

        User user = (User) userDetailsService.loadUserByUsername(authentication.getName());
        List<Visit> visits = user.getVisits();
        model.addAttribute("visits", visits);
        return "user/visit-list";
    }
}
