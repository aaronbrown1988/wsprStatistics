package net.aaronbrown.wsprstatistics.repository;

import net.aaronbrown.wsprstatistics.entity.Country;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Callsign Repository holds data about which callsign map to which countries.
 *
 */
@Repository
public interface  CountryRepository extends CrudRepository<Country, Long> {

    @Query("select country from Country country where country.startHash >= :prefix and country.endHash<=:prefix")
    Country getCountryForPrefix(@Param("prefix") Integer prefix);

}
