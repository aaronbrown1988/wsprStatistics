package net.aaronbrown.wsprstatistics.repository;

import net.aaronbrown.wsprstatistics.models.WSPRSpot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by aaron on 29/12/16.
 */
@Repository
public interface SpotsRepository extends CrudRepository<WSPRSpot, Long> {

    List<WSPRSpot> findByCallsign(String callSign);


}
