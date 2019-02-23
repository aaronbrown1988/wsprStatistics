package net.aaronbrown.wsprstatistics.dao;

import com.google.cloud.bigquery.FieldValue;
import com.google.cloud.bigquery.FieldValueList;
import com.google.cloud.bigquery.TableResult;
import net.aaronbrown.wsprstatistics.models.Statistics;
import net.aaronbrown.wsprstatistics.services.BigQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by aaron on 1/04/17.
 */
@Component
public class SpotsDAO {

    @Autowired
    private BigQueryService bigQueryService;

    public Map<String, Statistics> statisticsByBand(String callSign) {
        String queryString = "SELECT band,avg(distance), max(distance),min(distance),count(spot_id),variance(distance) " +
                "FROM `wsprstats-163301.wspr_data.spot_table` " +
                "where callsign='" + callSign + "' and band > 0 group by band";
        TableResult result = bigQueryService.runQuery(queryString);
        return getStringStatisticsMap(result);
    }

    public Map<String, Statistics> statisticsByHour(String callSign, Integer band, Date start, Date end) {
        String queryString = "SELECT hour(timestamp) as h,avg(distance), max(distance),min(distance),count(spot_id),variance(distance) " +
                "FROM `wsprstats-163301.wspr_data.spot_table` " +
                "where callsign='" + callSign + "' and band=" + band + " and timestamp between " + start + " and " + end + " group by h";

        TableResult result = bigQueryService.runQuery(queryString);
        return getStringStatisticsMap(result);
    }

    public Date lastUpdated() {
        Date updated = null;
        String queryString = "SELECT max(Timestamp)" +
                "FROM `wsprstats-163301.wspr_data.spot_table` ";
        TableResult result = bigQueryService.runQuery(queryString);
        if (result.getTotalRows() != 1) {
            updated = null;
        } else {
            Iterable<FieldValueList> iter = result.iterateAll();
            for (List<FieldValue> record : iter) {
                updated = new Date(record.get(0).getTimestampValue() / 1000);
            }
        }
        return updated;
    }


    public Map<String, Double> getCountryByBand(String callsign, Integer band) {
        HashMap<String, Double> countries = new HashMap<>();
        String bandCriteria = (band != null) ? " and Band = " + band.toString() + " " : "";
        String queryString = "select case when\n"+
                "ca is not null then\n"+
                "ca\n"+
                "when cb is not null then\n"+
                "cb\n"+
                "when cc is not null then\n"+
                "cc\n"+
                "when cd is not null then\n"+
                "cd\n"+
                "else 'Unknown'end as country, count(spot_id) as total\n"+
                "from\n"+
                "(select * from (select spot_id, substr(reporter,0,1) as a,substr(reporter,0,2)as b,substr(reporter,0,3)as c,substr(reporter,0,4)as d \n"+
                "from `wsprstats-163301.wspr_data.spot_table` where callsign ='" + callsign + "'  " + bandCriteria + " ) as data\n"+
                "left join (select country as ca, prefix as pa from `wsprstats-163301.reference_data.big_cty`) as ref on data.a = ref.pa\n"+
                "left join (select country as cb, prefix as pb from `wsprstats-163301.reference_data.big_cty`) as ref1 on data.b = ref1.pb \n"+
                "left join (select country as cc, prefix as pc from `wsprstats-163301.reference_data.big_cty`) as ref2 on data.c = ref2.pc\n"+
                "left join (select country as cd, prefix as pd from `wsprstats-163301.reference_data.big_cty`) as ref3 on data.d = ref3.pd)\n"+
                "group by country order by total desc";

        TableResult result = bigQueryService.runQuery(queryString);

        Iterable<FieldValueList> iter = result.iterateAll();
        for (List<FieldValue> record : iter) {
            countries.put(record.get(0).getStringValue(), record.get(1).getDoubleValue());
        }

        return countries;
    }

    private Map<String, Statistics> getStringStatisticsMap(TableResult result) {
        HashMap<String, Statistics> stats = new HashMap<>();

        if (result == null ) {
            return stats;
        }
        Iterable<FieldValueList> iter = result.iterateAll();



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
