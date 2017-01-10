package net.aaronbrown.wsprstatistics.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 *
 */
@Entity
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String countryCode;

    private String countryName;

    private String startPrefix;

    private String endPrefix;

    private Integer startHash;

    private Integer endHash;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getStartPrefix() {
        return startPrefix;
    }

    public void setStartPrefix(String startPrefix) {
        this.startPrefix = startPrefix;
    }

    public String getEndPrefix() {
        return endPrefix;
    }

    public void setEndPrefix(String endPrefix) {
        this.endPrefix = endPrefix;
    }

    public Integer getStartHash() {
        return startHash;
    }

    public void setStartHash(Integer startHash) {
        this.startHash = startHash;
    }

    public Integer getEndHash() {
        return endHash;
    }

    public void setEndHash(Integer endHash) {
        this.endHash = endHash;
    }
}
