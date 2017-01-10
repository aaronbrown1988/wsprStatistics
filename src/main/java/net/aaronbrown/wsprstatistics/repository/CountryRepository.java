package net.aaronbrown.wsprstatistics.repository;

import net.aaronbrown.wsprstatistics.entity.Country;

/**
 * Callsign Repository holds data about which callsign map to which countries.
 *
 */
public interface  CountryRepository extends CrudRepository<Country, Long> {

    @Query("select dxcc from DXCC dxcc where startHash >= :prefix and endHash<=:prefix")
    public Country getCountryForPrefix(@Param(":prefix") Integer prefix);

}
