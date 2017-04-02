package net.aaronbrown.wsprstatistics.services;

import net.aaronbrown.wsprstatistics.dao.SpotsDAO;
import net.aaronbrown.wsprstatistics.models.Statistics;
import net.aaronbrown.wsprstatistics.models.WSPRSpot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by aaron on 14/12/16.
 */
@Service
public class StatisticsService {

    @Autowired
    private SpotsDAO spotsDAO;

    public Map<Integer, Double> distanceByBand(String callsign) {
        List<WSPRSpot> spotList = spotsDAO.findByCallsign(callsign);
        //TODO
        return null;
    }

    public Map<String, Statistics> distanceStatsByBand(String callsign) {
        return spotsDAO.statisticsByBand(callsign);

    }


    //TODO
    public Map<Integer, Statistics> allDistanceStatsByBand() {
        return null;
    }
}
