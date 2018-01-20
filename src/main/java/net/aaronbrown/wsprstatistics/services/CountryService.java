package net.aaronbrown.wsprstatistics.services;

import net.aaronbrown.wsprstatistics.dao.CountryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
}
