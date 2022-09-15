package com.fundar.coronavirus_tracker.Controller;


import com.fundar.coronavirus_tracker.Service.CoronaVirusTotalData;
import com.fundar.coronavirus_tracker.Service.CoronavirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/coronavirus")
public class CoronaVirusTrackerController {

    @Autowired
    CoronavirusDataService service;

    @Autowired
    CoronaVirusTotalData totalData;

    @GetMapping()
    public String virus(Model model) throws IOException, InterruptedException {
        model.addAttribute ("locationstats" ,  service.getAllLocationsats ());
        model.addAttribute ("totalCases", totalData.getTotalStats ());

        return "index";
    }
}
