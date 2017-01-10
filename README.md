# wsprStatistics

[![Build Status](https://travis-ci.org/aaronbrown1988/wsprStatistics.svg?branch=master)](https://travis-ci.org/aaronbrown1988/wsprStatistics)

A project manuplating historic wspr spots to produce interesting statistics and graphs

To run the project locally without tomcat run: 
    
    mvn spring-boot:run
    
Alternatively a WAR file can be produced using

    mvn clean package
    
Data can be loaded in to mysql directly using the following SQL statement:

    load data concurrent local infile 'wsprspots-2017-01.csv' replace into table wsprDB.wsprspot fields terminated by ',' (spotid,@spot,reporter,reporters_Locator,snr,frequency,callsign,tx_Locator,tx_power,drift,distance,azimuth,band,version,code) set spot_time=from_unixtime(@spot);
