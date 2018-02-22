package net.aaronbrown.wsprstatistics.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.aaronbrown.wsprstatistics.dao.CountryDAO;

/**
 * Created by aaron on 21/01/18.
 */
@Component
public class CountryService {

    @Autowired
    private CountryDAO countryDAO;

    @Autowired
    private CacheService cacheService;


    public List<String> getCountryList() {
        List<String> countryList = (List<String>) cacheService.getObject("countryList");
        if (countryList == null) {
            countryList = countryDAO.countryList();
            cacheService.putObject("countryList", countryList);
        }
        return countryList;
    }

    public Map<String, Double> bandForCountry(String tx, String rx) {
        String key = "BandFor"+tx+"to"+rx;
        Map<String, Double> bandList = (Map<String, Double>) cacheService.getObject(key);
        if(bandList == null) {
            bandList = countryDAO.bandForCountry(tx,rx);
            cacheService.putObject(key, bandList);
        }
        return bandList;
    }
}
