package net.aaronbrown.wsprstatistics.dto;

import net.aaronbrown.wsprstatistics.models.WSPRSpot;
import org.apache.commons.csv.CSVRecord;

import java.util.Date;

public class CSVWSPRSpotDTO {
    public static WSPRSpot convertToWspr(CSVRecord record) {
        Integer i = 0;
        WSPRSpot currentSpot = new WSPRSpot();
        currentSpot.setSpotID(Long.parseLong(record.get(0)));
        currentSpot.setSpotTime(new Date(Long.parseLong(record.get(++i))));
        currentSpot.setReporter(record.get(++i));
        currentSpot.setReportersLocator(record.get(++i));
        currentSpot.setSnr(Integer.parseInt(record.get(++i)));
        currentSpot.setFrequency(Double.parseDouble(record.get(++i)));
        currentSpot.setCallsign(record.get(++i));
        currentSpot.setTxLocator(record.get(++i));
        currentSpot.setTxPower(Double.parseDouble(record.get(++i)));
        currentSpot.setDrift(Double.parseDouble(record.get(++i)));
        currentSpot.setDistance(Double.parseDouble(record.get(++i)));
        currentSpot.setAzimuth(Double.parseDouble(record.get(++i)));
        currentSpot.setBand(Integer.parseInt(record.get(++i)));
//        currentSpot.setVersion(record.get(++i));
        ++i;
        currentSpot.setCode(record.get(++i));
        return currentSpot;
    }
}