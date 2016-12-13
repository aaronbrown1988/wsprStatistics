package net.aaronbrown.wsprdynamo.dto;

import com.amazonaws.services.dynamodbv2.document.Item;
import net.aaronbrown.wsprdynamo.models.WSPRSpot;

import java.time.ZoneOffset;

public class WSPRSpotDTO {
    public static Item convertToItem(WSPRSpot currentSpot) {
        return new Item()
                .withPrimaryKey("callsign", currentSpot.getCallsign(),
                        "spotID", currentSpot.getSpotID())
                .with("date", currentSpot.getSpotTime().toEpochSecond(ZoneOffset.UTC))
                .with("reporter", currentSpot.getReporter())
                .with("reportersLocator", currentSpot.getReporterslocator())
                .with("snr", currentSpot.getSnr())
                .with("frequency", currentSpot.getFrequency())
                .with("txLocator", currentSpot.getTxLocator())
                .with("txPower", currentSpot.getTxPower())
                .with("distance", currentSpot.getDistance())
                .with("azimuth", currentSpot.getAzimuth())
                .with("band", currentSpot.getBand())
//        .with("version",currentSpot.getVersion()!=null&&!currentSpot.getVersion().isEmpty()?currentSpot.getVersion(): " ")
                .with("code", !currentSpot.getCode().isEmpty() ? currentSpot.getCode() : " ");
    }

    public static WSPRSpot convertToSpot(Item item) {
        WSPRSpot spot = new WSPRSpot();
        spot.setCallsign(item.getString("callsign"));
        spot.setSpotID(item.getLong("spotID"));
        spot.setReporter(item.getString("reporter"));
        spot.setReporterslocator(item.getString("reportersLocation"));
        spot.setSnr(item.getInt("snr"));
        spot.setFrequency(item.getDouble("frequency"));
        spot.setTxLocator(item.getString("txLocator"));
        spot.setTxPower(item.getDouble("txPower"));
        spot.setDistance(item.getDouble("distance"));
        spot.setAzimuth(item.getDouble("azimuth"));
        spot.setBand(item.getInt("band"));
        spot.setCode(item.getString("code"));
        return spot;
    }
}