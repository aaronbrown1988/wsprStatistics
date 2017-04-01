package net.aaronbrown.wsprstatistics.controller;

import net.aaronbrown.wsprstatistics.models.Statistics;
import net.aaronbrown.wsprstatistics.services.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.DoubleSummaryStatistics;
import java.util.Map;

/**
 * Created by aaron on 14/12/16.
 */
@RestController
@RequestMapping("/api/stats")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @RequestMapping("/{callsign}/distance/band/avg")
    public Map<Integer, Double> avgDistanceByBandForCallsign(@PathVariable String callsign) {
        return statisticsService.distanceByBand(callsign);
    }

    @RequestMapping("/{callsign}/distance/band/all")
    public Map<String, Statistics> maxDistanceByBandForCallsign(@PathVariable String callsign) {
        return statisticsService.distanceStatsByBand(callsign);
    }

    @RequestMapping("/all/distance/band")
    public Map<Integer, DoubleSummaryStatistics> maxDistanceByBandForCallsign() {
        return statisticsService.allDistanceStatsByBand();
    }

}
