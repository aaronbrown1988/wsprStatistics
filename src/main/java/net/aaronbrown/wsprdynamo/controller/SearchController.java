package net.aaronbrown.wsprdynamo.controller;

/**
 * Created by aaron on 13/12/16.
 */

import net.aaronbrown.wsprdynamo.dao.SpotsDao;
import net.aaronbrown.wsprdynamo.models.WSPRSpot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    @Autowired
    private SpotsDao spotsDao;

        @RequestMapping("/call/{callsign}")
        public List<WSPRSpot> searchCall(@PathVariable String callsign) {
            return (spotsDao.getSpotsForCall(callsign));
        }

}
