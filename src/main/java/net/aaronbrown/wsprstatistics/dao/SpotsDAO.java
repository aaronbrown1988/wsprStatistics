package net.aaronbrown.wsprstatistics.dao;

import com.google.cloud.bigquery.FieldValue;
import com.google.cloud.bigquery.FieldValueList;
import com.google.cloud.bigquery.TableResult;

import net.aaronbrown.wsprstatistics.models.Statistics;
import net.aaronbrown.wsprstatistics.models.WSPRSpot;
import net.aaronbrown.wsprstatistics.services.BigQueryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by aaron on 1/04/17.
 */
@Component
public class SpotsDAO {

    @Autowired
    private BigQueryService bigQueryService;

    public Map<String, Statistics> statisticsByBand(String callSign) {
        String queryString = "SELECT band,avg(distance), max(distance),min(distance),count(spot_id),variance(distance) " +
                "FROM [dataproc-fun:wsprnet.all_wsprnet_data] " +
                "where Call_Sign='" + callSign + "' and band > 0 group by band";
                TableResult result = bigQueryService.runQuery(queryString);
        return getStringStatisticsMap(result);
    }

    public Map<String, Statistics> statisticsByHour(String callSign, Integer band, Date start, Date end) {
        String queryString = "SELECT hour(spot_time) as h,avg(distance), max(distance),min(distance),count(spot_id),variance(distance) " +
                "FROM [dataproc-fun:wsprnet.all_wsprnet_data] " +
                "where Call_Sign='" + callSign + "' and band=" + band + " and spot_time between " + start + " and " + end + " group by h";

        TableResult result = bigQueryService.runQuery(queryString);
        return getStringStatisticsMap(result);
    }

    public Date lastUpdated() {
        Date updated;
        String queryString = "SELECT max(Timestamp)" +
                "FROM [dataproc-fun:wsprnet.all_wsprnet_data] ";
        TableResult result = bigQueryService.runQuery(queryString);
        if (result.getTotalRows() != 1) {
            updated = null;
        } else {
            Iterable<FieldValueList> iter = result.iterateAll();
            List<FieldValue> record = iter.
            updated = new Date(record.get(0).getTimestampValue() / 1000);
        }
        return updated;
    }


    public Map<String, Double> getCountryByBand(String callsign, Integer band) {
        HashMap<String, Double> countries = new HashMap<>();
        String bandCriteria = (band != null) ? " and Band = " + band.toString() + " " : "";
        String queryString = "select case when\n" +
                "ref.country is not null then\n" +
                "ref.country\n" +
                "when ref1.country is not null then\n" +
                "ref1.country\n" +
                "when ref2.country is not null then\n" +
                "ref2.country\n" +
                "when ref3.country is not null then\n" +
                "ref3.country\n" +
                " else 'Unknown'" +
                "end as country, count(data.spot_id)\n" +
                "from (select * from (select spot_id, substr(reporter,0,1) as a,substr(reporter,0,2)as b,substr(reporter,0,3)as c,substr(reporter,0,4)as d \n" +
                "from [dataproc-fun:wsprnet.all_wsprnet_data] where call_sign = '" + callsign + "'  " + bandCriteria + " ) as data\n" +
                "left join [wsprstats-163301:callsign_country.big_cty] as ref on data.a = ref.prefix\n" +
                "left join [wsprstats-163301:callsign_country.big_cty] as ref1 on data.b = ref1.prefix \n" +
                "left join [wsprstats-163301:callsign_country.big_cty] as ref2 on data.c = ref2.prefix\n" +
                "left join [wsprstats-163301:callsign_country.big_cty] as ref3 on data.d = ref3.prefix) group by country order by f0_ desc";

                TableResult result = bigQueryService.runQuery(queryString);

                Iterable<FieldValueList> iter = result.iterateAll();

        while (iter.hasNext()) {
            List<FieldValue> record = iter.next();
            countries.put(record.get(0).getStringValue(), record.get(1).getDoubleValue());
        }

        return countries;
    }

    private Map<String, Statistics> getStringStatisticsMap(TableResult result) {
        Iterable<FieldValueList> iter = result.iterateAll();

        HashMap<String, Statistics> stats = new HashMap<>();

        for (FieldValueList row : iter) {
            Statistics current = new Statistics();
                current.setAverage(row.get(1).isNull() ? 0 : row.get(1).getDoubleValue());
                current.setMax(row.get(2).isNull() ? 0 : row.get(2).getDoubleValue());
                current.setMin(row.get(3).isNull() ? 0 : row.get(3).getDoubleValue());
                current.setCount(row.get(4).isNull() ? 0 : row.get(4).getDoubleValue());
                current.setVarience(row.get(5).isNull() ? 0 : row.get(5).getDoubleValue());
                stats.put(row.get(0).getStringValue(), current);
            }
        return stats;
    }


}
