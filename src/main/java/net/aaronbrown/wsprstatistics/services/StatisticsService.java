package net.aaronbrown.wsprstatistics.services;

import net.aaronbrown.wsprstatistics.dao.SpotsDao;
import net.aaronbrown.wsprstatistics.models.WSPRSpot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by aaron on 14/12/16.
 */
@Service
public class StatisticsService {

    @Autowired
    private SpotsDao spotsDao;

    public Map<Integer, Double> distanceByBand(String callsign) {
        List<WSPRSpot> spotList = spotsDao.getSpotsForCall(callsign);
        return spotList.stream().collect(Collectors.groupingBy(WSPRSpot::getBand, Collectors.averagingDouble(WSPRSpot::getDistance)));
    }

    public Map<Integer, DoubleSummaryStatistics> distanceStatsByBand(String callsign) {
        List<WSPRSpot> spotList = spotsDao.getSpotsForCall(callsign);
        return spotList.stream().collect(Collectors.groupingBy(WSPRSpot::getBand, Collectors.summarizingDouble(WSPRSpot::getDistance)));
    }

    //TODO
    public Map<Integer, DoubleSummaryStatistics> allDistanceStatsByBand() {
        return null;
    }
}
