package net.aaronbrown.wsprstatistics.repository;

import net.aaronbrown.wsprstatistics.entity.WSPRSpot;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by aaron on 29/12/16.
 */
@Repository
public interface SpotsRepository extends CrudRepository<WSPRSpot, Long> {

    List<WSPRSpot> findByCallsign(String callSign);

    Page<WSPRSpot> findByCallsign(String callSign, Pageable pageable);

    List<WSPRSpot> findByCallsignAndBand(String callsign, Integer band);


//    @Query("select band,avg(distance) from wsprspot where callsign =:call and spot_time between :startDate and :endDate group by band ")
//    HashMap<Integer, Double> averageDistanceByBandBetweenDatesForCall(@Param("call") String callsign, @Param("startDate") LocalDate start, @Param("endDate") LocalDate end);

}
