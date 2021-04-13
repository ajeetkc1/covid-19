package com.covid.Nepal.controller;

import com.covid.Nepal.models.LocationStats;
import com.covid.Nepal.services.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired  // Since CoronaVirusDataService is a service we can autowire this
    CoronaVirusDataService coronaVirusDataService;

    @GetMapping("/")
    /*
    Example:
      public String home(Model model){
        model.addAttribute("testcase", "test");
        return "CovidTracker";
     */

    public String home(Model model){

        List<LocationStats> allstats = coronaVirusDataService.getAllStats();
        int totalReportedCases = allstats.stream().mapToInt(stat -> stat.getTotalConfirmedCases()).sum();
        int totalNewCases = allstats.stream().mapToInt(stat -> stat.getDiffWithPrevDay()).sum();

        model.addAttribute("locationstats",allstats);
        // here we are giving data into a model that we have created named "locationstats". locationstats is now our model that we use
        //Note: we are looping through our data that is stored in this model locationstats && locationstat is just a variable we have created to loop
        model.addAttribute("totalReportedCases", totalReportedCases );
        model.addAttribute("totalNewCases",totalNewCases);
        return "CovidTracker";
    }
}
