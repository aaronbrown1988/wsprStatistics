package net.aaronbrown.wsprstatistics.services;

import com.google.cloud.bigquery.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by aaron on 6/03/17.
 */
@Service
public class BigQueryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BigQueryService.class);


    private BigQuery bigQuery;

    public BigQueryService() {
        bigQuery =
                new BigQueryOptions.DefaultBigqueryFactory().create(BigQueryOptions.getDefaultInstance());
    }


    public void avgDistanceByTimeForBand(Integer band) {
        String queryString = "SELECT hour(Timestamp) as hour,avg(Distance) " +
                "FROM [dataproc-fun:wsprnet.all_wsprnet_data] " +
                "where Band=" + band.toString() + " group by hour order by hour";

        QueryRequest queryRequest =
                QueryRequest.newBuilder(queryString)
                        //.addNamedParameter("band", QueryParameterValue.int64(band))
                        // Standard SQL syntax is required for parameterized queries.
                        // See: https://cloud.google.com/bigquery/sql-reference/
                        .setUseLegacySql(true)
                        .build();
        // Execute the query.
        QueryResult result = runQuery(queryRequest);
        Iterator<List<FieldValue>> iter = result.iterateAll();

        while (iter.hasNext()) {
            List<FieldValue> row = iter.next();
            System.out.printf(
                    "%s: %g\n",
                    row.get(0).getStringValue(),
                    row.get(1).getDoubleValue());
        }
    }

    private QueryResult runQuery(QueryRequest queryRequest) {
        QueryResponse response = bigQuery.query(queryRequest);

        try {
            // Wait for the job to finish (if the query takes more than 10 seconds to complete).
            while (!response.jobCompleted()) {
                Thread.sleep(1000);
                response = bigQuery.getQueryResults(response.getJobId());
            }
        } catch (InterruptedException exp) {
            LOGGER.error("I got interrupted waiting for the query to return", exp);
        }

        if (response.hasErrors()) {
            throw new RuntimeException(
                    response
                            .getExecutionErrors()
                            .stream()
                            .map(BigQueryError::getMessage)
                            .collect(Collectors.joining("\n")));
        }

        QueryResult result = response.getResult();
        Iterator<List<FieldValue>> iter = result.iterateAll();

        while (iter.hasNext()) {
            List<FieldValue> row = iter.next();
            System.out.printf(
                    "%s: %g\n",
                    row.get(0).getStringValue(),
                    row.get(1).getDoubleValue());
        }
        return result;
    }

}
