package net.aaronbrown.wsprstatistics.controller;

import net.aaronbrown.wsprstatistics.services.CountryService;
import net.aaronbrown.wsprstatistics.services.SpotLoadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aaron on 12/8/2016.
 */
@RestController
@RequestMapping("/api")
public class LoadDataController {

    private final List<Thread> workers = new ArrayList<>();

    @Autowired
    private SpotLoadingService spotLoadingService;

    @Autowired
    private CountryService countryService;

    @RequestMapping("/loadData")
    public String loadData() {
        workers.addAll(spotLoadingService.processAll());
        return "Launched "+workers.size()+" threads";
    }

    @RequestMapping("/loadCountry")
    public String loadCountry(@RequestParam String filename) {
        countryService.loadCountryData("/home/aaron/IdeaProjects/wsprStatistics/countrylist.csv");
        return "Country Data loaded";
    }

    @RequestMapping("/killAll")
    public String killAll() {
       for(Thread worker: workers) {
           worker.stop();
        }
        return "Launched "+workers.size()+" threads";

    }


}
