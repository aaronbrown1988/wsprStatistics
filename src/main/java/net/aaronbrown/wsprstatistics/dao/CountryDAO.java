package net.aaronbrown.wsprstatistics.dao;

import net.aaronbrown.wsprstatistics.models.Country;
import org.springframework.stereotype.Component;

/**
 * Created by aaron on 1/04/17.
 */
@Component
public class CountryDAO {

    public Country getCountryForPrefix(Integer prefix) {
//        @Query("select country from Country country where country.startHash >= :prefix and country.endHash<=:prefix")
        return null;

    }

    public void save(Country country) {
    }

}
