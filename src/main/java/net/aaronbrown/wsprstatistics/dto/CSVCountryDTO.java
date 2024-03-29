package net.aaronbrown.wsprstatistics.dto;

import net.aaronbrown.wsprstatistics.models.Country;
import org.apache.commons.csv.CSVRecord;

/**
 *
 *
 */
public class CSVCountryDTO {

    public static Country countryFromCSV(CSVRecord record) {
        if (record == null) {
            return null;
        }
        Integer i = 0;
        Country country = new Country();
        country.setCountryName(record.get(i));
        country.setCountryCode(record.get(++i));
        country.setStartPrefix(record.get(++i));
        country.setEndPrefix(record.get(++i));
        country.setStartHash(country.getStartPrefix().hashCode());
        country.setEndHash(country.getEndPrefix().hashCode());
        return country;
    }
}
