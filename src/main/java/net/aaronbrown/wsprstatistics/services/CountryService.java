package net.aaronbrown.wsprstatistics.services;

import net.aaronbrown.wsprstatistics.dto.CSVCountryDTO;
import net.aaronbrown.wsprstatistics.entity.Country;
import net.aaronbrown.wsprstatistics.repository.CountryRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 *
 * This service is to help to look up the country a callsign belongs to
 *
 */
@Service
public class CountryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryService.class);

    @Autowired
    private CountryRepository countryRepository;

    public String getCountryNameForCall(String callsign) {
            Country country = countryRepository.getCountryForPrefix(getPrefixCode(callsign));
            if (country == null) {
                LOGGER.info("No country found for callsign:"+callsign);
            }
            return country.getCountryName();
    }

    public void loadCountryData(String filename) {
        try {
            FileInputStream inputFile = new FileInputStream(filename);
            Reader in = new InputStreamReader(inputFile);
            Iterable<CSVRecord> records = CSVFormat.RFC4180.parse(in);
            for (CSVRecord record : records) {
               countryRepository.save(CSVCountryDTO.countryFromCSV(record));
            }
        } catch (Exception e) {
            LOGGER.error("Error loading Country data file in "+e.getMessage(), e);
        }
    }

    /**
     * Return the hashcode of the prefix of the callsign
     * @param prefix
     * @return an intoeger representing the prefix.
     */
    private Integer getPrefixCode(String prefix) {
        if (prefix.length() > 3 ) {
            prefix = prefix.substring(0,3);
        }

        return prefix.hashCode();
    }

}
