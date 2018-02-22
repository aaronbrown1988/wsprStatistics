package net.aaronbrown.wsprstatistics.services;

import com.google.cloud.bigquery.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

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


    public QueryRequest buildQuery(String queryString) {
		QueryRequest queryRequest =
                QueryRequest.newBuilder(queryString)
                        .setUseLegacySql(true)
                        .build();
		return queryRequest;
	}

    public QueryResult runQuery(String query) {
        QueryRequest queryRequest = buildQuery(query);
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
                    response.getExecutionErrors().toString());
        }

        QueryResult result = response.getResult();

        return result;
    }

}
