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

        QueryRequest queryRequest =
                QueryRequest.newBuilder(queryString)
                        //.addNamedParameter("band", QueryParameterValue.int64(band))
                        // Standard SQL syntax is required for parameterized queries.
                        // See: https://cloud.google.com/bigquery/sql-reference/
                        .setUseLegacySql(true)
                        .build();
        // Execute the query.
        QueryResult result = bigQueryService.runQuery(queryRequest);
        assertFalse(result.toString().isEmpty());
    }

}