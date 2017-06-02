package net.aaronbrown.wsprstatistics.services;

import net.aaronbrown.wsprstatistics.dao.SpotsDAO;
import net.aaronbrown.wsprstatistics.models.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by aaron on 14/12/16.
 */
@Service
public class StatisticsService {

    @Autowired
    private SpotsDAO spotsDAO;

    @Autowired
    CacheService cacheService;

    public Map<String, Statistics> distanceStatsByBand(String callsign) {
        Map<String, Statistics> stats = (Map<String, Statistics>) cacheService.getObject(callsign + "-statsByBand");
        if (stats == null) {
            Logger.getLogger("StatisticsService").info("Not in cache");
            stats = spotsDAO.statisticsByBand(callsign);
            cacheService.putObject(callsign + "-statsByBand", stats);
        }
        return stats;
    }

    public Map<String, Double> countryByCallsign(String callsign) {
        Map<String, Double> stats = (Map<String, Double>) cacheService.getObject(callsign + "-countriesAll");
        if (stats == null) {
            stats = spotsDAO.getCountryByBand(callsign, null);
            cacheService.putObject(callsign + "countriesAll", stats);
        }
        return stats;
    }

    public Map<String, Double> countryByCallsignAndBand(String callsign, Integer band) {

        Map<String, Double> stats = (Map<String, Double>) cacheService.getObject(callsign + "-countries-" + band);
        if (stats == null) {
            stats = spotsDAO.getCountryByBand(callsign, band);
            cacheService.putObject(callsign + "-countries-" + band, stats);
        }
        return stats;
    }
}
