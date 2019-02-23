package net.aaronbrown.wsprstatistics.services;

import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryOptions;
import com.google.cloud.bigquery.QueryJobConfiguration;
import com.google.cloud.bigquery.TableResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by aaron on 6/03/17.
 */
@Service
public class BigQueryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BigQueryService.class);


    private BigQuery bigQuery;

    public BigQueryService() {
        bigQuery =
                BigQueryOptions.getDefaultInstance().getService();
    }


    public QueryJobConfiguration buildQuery(String queryString) {
		QueryJobConfiguration queryRequest =
        QueryJobConfiguration.newBuilder(queryString)
                        .setUseLegacySql(false)
                        .build();
		return queryRequest;
	}

    public TableResult runQuery(String query) {
        try {
            QueryJobConfiguration queryRequest = buildQuery(query);
            return bigQuery.query(queryRequest);
        } catch (Exception e) {
            return null;
        }
    }

}
