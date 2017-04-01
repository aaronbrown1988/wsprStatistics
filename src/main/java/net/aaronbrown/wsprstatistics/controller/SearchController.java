package net.aaronbrown.wsprstatistics.controller;

/**
 * Created by aaron on 13/12/16.
 */

import net.aaronbrown.wsprstatistics.dao.SpotsDAO;
import net.aaronbrown.wsprstatistics.models.WSPRSpot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    @Autowired
    private SpotsDAO spotsDAO;

    @RequestMapping("/call/{callsign}")
    public List<WSPRSpot> searchCall(@PathVariable String callsign) {
        return (spotsDAO.findByCallsign(callsign));
    }

    @RequestMapping("/call/{callsign}/{page}")
    public List<WSPRSpot> searchCall(@PathVariable String callsign, @PathVariable Integer page) {
        return (spotsDAO.findByCallsign(callsign, page));
    }

}
