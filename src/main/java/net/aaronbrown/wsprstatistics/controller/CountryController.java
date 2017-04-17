package net.aaronbrown.wsprstatistics.controller;

import net.aaronbrown.wsprstatistics.dao.SpotsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

/**
 * Created by aaron on 17/04/17.
 */
@Controller
@RequestMapping("/api/country")
public class CountryController {

    @Autowired
    private SpotsDAO spotsDAO;

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping("/{callsign}/{band}")
    public
    @ResponseBody
    Map<String, Double> countryByCallsignAndBand(@PathVariable String callsign, @PathVariable Integer band) {
        return spotsDAO.getCountryByBand(callsign, band);
    }
}
