package net.aaronbrown.wsprstatistics.controller;

import net.aaronbrown.wsprstatistics.models.Statistics;
import net.aaronbrown.wsprstatistics.services.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

/**
 * Created by aaron on 14/12/16.
 */
@Controller
@RequestMapping("/api/stats")
public class DistanceController {

    @Autowired
    private StatisticsService statisticsService;

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping("/{callsign}/distance/band/all")
    public
    @ResponseBody
    Map<String, Statistics> maxDistanceByBandForCallsign(@PathVariable String callsign) {
        return statisticsService.distanceStatsByBand(callsign);
    }

}
