package net.aaronbrown.wsprstatistics.models;


import java.util.Date;

/**
 * Created by aaron on 12/5/2016.
 */

public class WSPRSpot {

    private Long spotID;
    private Date spotTime;
    private String reporter;
    private String reportersLocator;
    private Integer snr;
    private double frequency;
    private String callsign;
    private String txLocator;
    private Double txPower;
    private Double drift;
    private double distance;
    private double azimuth;
    private Integer band;
    private String version;
    private String code;

    public Long getSpotID() {
        return spotID;
    }

    public void setSpotID(Long spotID) {
        this.spotID = spotID;
    }

    public Date getSpotTime() {
        return spotTime;
    }

    public void setSpotTime(Date spotTime) {
        this.spotTime = spotTime;
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public String getReportersLocator() {
        return reportersLocator;
    }

    public void setReportersLocator(String reportersLocator) {
        this.reportersLocator = reportersLocator;
    }

    public Integer getSnr() {
        return snr;
    }

    public void setSnr(Integer snr) {
        this.snr = snr;
    }

    public double getFrequency() {
        return frequency;
    }

    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }

    public String getCallsign() {
        return callsign;
    }

    public void setCallsign(String callsign) {
        this.callsign = callsign;
    }

    public String getTxLocator() {
        return txLocator;
    }

    public void setTxLocator(String txLocator) {
        this.txLocator = txLocator;
    }

    public Double getTxPower() {
        return txPower;
    }

    public void setTxPower(Double txPower) {
        this.txPower = txPower;
    }

    public Double getDrift() {
        return drift;
    }

    public void setDrift(Double drift) {
        this.drift = drift;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getAzimuth() {
        return azimuth;
    }

    public void setAzimuth(double azimuth) {
        this.azimuth = azimuth;
    }

    public Integer getBand() {
        return band;
    }

    public void setBand(Integer band) {
        this.band = band;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "WSPRSpot{" +
                "spotID=" + spotID +
                ", spotTime=" + spotTime +
                ", reporter='" + reporter + '\'' +
                ", reportersLocator='" + reportersLocator + '\'' +
                ", snr=" + snr +
                ", frequency=" + frequency +
                ", callsign='" + callsign + '\'' +
                ", txLocator='" + txLocator + '\'' +
                ", txPower=" + txPower +
                ", drift=" + drift +
                ", distance=" + distance +
                ", azimuth=" + azimuth +
                ", band=" + band +
                ", version='" + version + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
