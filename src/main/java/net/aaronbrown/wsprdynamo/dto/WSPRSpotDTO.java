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
}