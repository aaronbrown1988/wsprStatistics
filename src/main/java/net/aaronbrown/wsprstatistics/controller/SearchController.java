package net.aaronbrown.wsprstatistics.controller;

/**
 * Created by aaron on 13/12/16.
 */

import net.aaronbrown.wsprstatistics.models.WSPRSpot;
import net.aaronbrown.wsprstatistics.repository.SpotsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    @Autowired
    private SpotsRepository spotsRepository;

    @RequestMapping("/call/{callsign}")
    public List<WSPRSpot> searchCall(@PathVariable String callsign) {
        return (spotsRepository.findByCallsign(callsign));
    }

}
