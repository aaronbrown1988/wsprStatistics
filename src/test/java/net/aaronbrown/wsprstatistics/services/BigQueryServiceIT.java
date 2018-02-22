package net.aaronbrown.wsprstatistics.services;

import com.google.cloud.bigquery.QueryRequest;
import com.google.cloud.bigquery.QueryResult;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertFalse;


/**
 * Created by aaron on 6/03/17.
 */
@Ignore
public class BigQueryServiceIT {
    private BigQueryService bigQueryService;

    @Before
    public void setUp() throws Exception {
        bigQueryService = new BigQueryService();
    }

    @Test
    public void runQuery() throws Exception {
        String queryString = "SELECT band,avg(distance), max(distance),min(distance),count(spot_id),variance(distance) " +
                "FROM [dataproc-fun:wsprnet.all_wsprnet_data] " +
                "where Call_Sign='M1GEO' group by band";

        // Execute the query.
        QueryResult result = bigQueryService.runQuery(queryString);
        assertFalse(result.toString().isEmpty());
    }

}