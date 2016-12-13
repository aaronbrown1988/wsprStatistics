package net.aaronbrown.wsprdynamo.controller;

import net.aaronbrown.wsprdynamo.services.SpotLoadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aaron on 12/8/2016.
 */
@RestController
@RequestMapping("/api")
public class LoadDataController {

    private List<Thread> workers = new ArrayList<>();

    @Autowired
    private SpotLoadingService spotLoadingService;

    @RequestMapping("/loadData")
    public String loadData() {
        workers.addAll(spotLoadingService.processAll());
        return "Launched "+workers.size()+" threads";
    }

    @RequestMapping("/killAll")
    public String killAll() {
       for(Thread worker: workers) {
           worker.stop();
        }
        return "Launched "+workers.size()+" threads";

    }


}
