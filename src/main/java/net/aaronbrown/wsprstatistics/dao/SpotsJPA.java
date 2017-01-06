package net.aaronbrown.wsprstatistics.dao;

import net.aaronbrown.wsprstatistics.models.WSPRSpot;
import net.aaronbrown.wsprstatistics.repository.SpotsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by aaron on 29/12/16.
 */
@Component
public class SpotsJPA implements SpotsDao {

    @Autowired
    private SpotsRepository repository;

    @Override
    public List<WSPRSpot> getSpotsForCall(String callsign) {
        return repository.findByCallsign(callsign);
    }

    @Override
    public void addSpot(WSPRSpot spot) {
        repository.save(spot);
    }
}
