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
                BigQueryOptions.getDefaultInstance().getService();
    }


    public QueryJobConfiguration buildQuery(String queryString) {
		QueryJobConfiguration queryRequest =
        QueryJobConfiguration.newBuilder(queryString)
                        .setUseLegacySql(true)
                        .build();
		return queryRequest;
	}

    public TableResult runQuery(String query) throws InterruptedException{
        QueryJobConfiguration queryRequest = buildQuery(query);
    
        return bigQuery.query(queryRequest);
    }

}
