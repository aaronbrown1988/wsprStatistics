package net.aaronbrown.wsprstatistics.dao;

import com.google.cloud.bigquery.FieldValue;
import com.google.cloud.bigquery.QueryRequest;
import com.google.cloud.bigquery.QueryResult;
import net.aaronbrown.wsprstatistics.models.Statistics;
import net.aaronbrown.wsprstatistics.models.WSPRSpot;
import net.aaronbrown.wsprstatistics.services.BigQueryService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by aaron on 1/04/17.
 */
@Component
public class SpotsDAO {

    @Resource
    private BigQueryService bigQueryService;

    public List<WSPRSpot> findByCallsign(String callSign) {
        return findByCallsign(callSign, true);
    }


    public List<WSPRSpot> findByCallsign(String callSign, Boolean limit) {
        String queryString = "SELECT * " +
                "FROM [dataproc-fun:wsprnet.all_wsprnet_data] " +
                "where Call_Sign='" + callSign + "'";
        if (limit != false) {
            queryString = queryString + "  LIMT TO 1000";
        }

        QueryRequest queryRequest =
                QueryRequest.newBuilder(queryString)
                        //.addNamedParameter("band", QueryParameterValue.int64(band))
                        // Standard SQL syntax is required for parameterized queries.
                        // See: https://cloud.google.com/bigquery/sql-reference/
                        .setUseLegacySql(true)
                        .build();
        // Execute the query.
        QueryResult result = bigQueryService.runQuery(queryRequest);
        Iterator<List<FieldValue>> iter = result.iterateAll();

        List<WSPRSpot> spots = new ArrayList<>();
        Integer i = 0;

        while (iter.hasNext()) {
            List<FieldValue> record = iter.next();
            WSPRSpot currentSpot = new WSPRSpot();
            currentSpot.setSpotID(record.get(0).getLongValue());
            currentSpot.setSpotTime(new Date(Long.parseLong(record.get(++i).getStringValue())));
            currentSpot.setReporter(record.get(++i).getStringValue());
            currentSpot.setReportersLocator(record.get(++i).getStringValue());
            currentSpot.setSnr(Integer.parseInt(record.get(++i).getStringValue()));
            currentSpot.setFrequency(Double.parseDouble(record.get(++i).getStringValue()));
            currentSpot.setCallsign(record.get(++i).getStringValue());
            currentSpot.setTxLocator(record.get(++i).getStringValue());
            currentSpot.setTxPower(Double.parseDouble(record.get(++i).getStringValue()));
            currentSpot.setDrift(Double.parseDouble(record.get(++i).getStringValue()));
            currentSpot.setDistance(Double.parseDouble(record.get(++i).getStringValue()));
            currentSpot.setAzimuth(Double.parseDouble(record.get(++i).getStringValue()));
            currentSpot.setBand(Integer.parseInt(record.get(++i).getStringValue()));
        }
        return spots;
    }

    public List<WSPRSpot> findByCallsign(String callSign, Integer page) {
        return null;
    }

    public List<WSPRSpot> findByCallsignAndBand(String callsign, Integer band) {

        return null;
    }

    public Map<String, Statistics> statisticsByBand(String callSign) {
        String queryString = "SELECT band,avg(distance), max(distance),min(distance),count(spot_id),variance(distance) " +
                "FROM [dataproc-fun:wsprnet.all_wsprnet_data] " +
                "where Call_Sign='" + callSign + "' group by band";

        QueryRequest queryRequest =
                QueryRequest.newBuilder(queryString)
                        //.addNamedParameter("band", QueryParameterValue.int64(band))
                        // Standard SQL syntax is required for parameterized queries.
                        // See: https://cloud.google.com/bigquery/sql-reference/
                        .setUseLegacySql(true)
                        .build();
        // Execute the query.
        QueryResult result = bigQueryService.runQuery(queryRequest);
        return getStringStatisticsMap(result);
    }

    public Map<String, Statistics> statisticsByHour(String callSign, Integer band, Date start, Date end) {
        String queryString = "SELECT hour(spot_time) as h,avg(distance), max(distance),min(distance),count(spot_id),variance(distance) " +
                "FROM [dataproc-fun:wsprnet.all_wsprnet_data] " +
                "where Call_Sign='" + callSign + "' and band=" + band + " and spot_time between " + start + " and " + end + " group by h";

        QueryRequest queryRequest =
                QueryRequest.newBuilder(queryString)
                        //.addNamedParameter("band", QueryParameterValue.int64(band))
                        // Standard SQL syntax is required for parameterized queries.
                        // See: https://cloud.google.com/bigquery/sql-reference/
                        .setUseLegacySql(true)
                        .build();
        // Execute the query.
        QueryResult result = bigQueryService.runQuery(queryRequest);
        return getStringStatisticsMap(result);
    }

    public Date lastUpdated() {
        Date updated;
        String queryString = "SELECT max(Timestamp)" +
                "FROM [dataproc-fun:wsprnet.all_wsprnet_data] ";

        QueryRequest queryRequest =
                QueryRequest.newBuilder(queryString)
                        //.addNamedParameter("band", QueryParameterValue.int64(band))
                        // Standard SQL syntax is required for parameterized queries.
                        // See: https://cloud.google.com/bigquery/sql-reference/
                        .setUseLegacySql(true)
                        .build();
        // Execute the query.
        QueryResult result = bigQueryService.runQuery(queryRequest);
        if (result.getTotalRows() != 1) {
            updated = null;
        } else {
            Iterator<List<FieldValue>> iter = result.iterateAll();
            List<FieldValue> record = iter.next();
            updated = new Date(record.get(0).getTimestampValue());
        }
        return updated;
    }



    private Map<String, Statistics> getStringStatisticsMap(QueryResult result) {
        Iterator<List<FieldValue>> iter = result.iterateAll();

        HashMap<String, Statistics> stats = new HashMap<>();

        while (iter.hasNext()) {
            List<FieldValue> record = iter.next();
            Statistics current = new Statistics();
            if (!record.get(0).isNull()) {
                current.setAverage(record.get(1).isNull() ? 0 : record.get(1).getDoubleValue());
                current.setMax(record.get(2).isNull() ? 0 : record.get(2).getDoubleValue());
                current.setMin(record.get(3).isNull() ? 0 : record.get(3).getDoubleValue());
                current.setCount(record.get(4).isNull() ? 0 : record.get(4).getDoubleValue());
                current.setVarience(record.get(5).isNull() ? 0 : record.get(5).getDoubleValue());
                stats.put(record.get(0).getStringValue(), current);
            }

        }
        return stats;
    }


}
