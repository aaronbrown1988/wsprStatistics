package net.aaronbrown.wsprstatistics.services;

import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryOptions;
import com.google.cloud.bigquery.Job;
import com.google.cloud.bigquery.JobInfo;
import com.google.cloud.bigquery.LoadJobConfiguration;
import com.google.cloud.bigquery.QueryJobConfiguration;
import com.google.cloud.bigquery.TableId;
import com.google.cloud.bigquery.TableResult;
import com.google.cloud.bigquery.BigQuery.JobOption;
import com.google.cloud.bigquery.JobInfo.WriteDisposition;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BigQueryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BigQueryService.class);

    @Value("${dataset}")
    private String dataset;

    private BigQuery bigQuery;

    public BigQueryService() {
        bigQuery = BigQueryOptions.getDefaultInstance().getService();

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

    public void runLoadJob(String bucket, String filename, String table ) throws Exception{
        LoadJobConfiguration config = LoadJobConfiguration.newBuilder(TableId.of("wspr-data", table), "gcs://"+bucket+"/"+filename)
            .setAutodetect(true)
            .setWriteDisposition(WriteDisposition.WRITE_APPEND)
            .build();

        JobInfo loadRequest = Job.newBuilder(config).build();
        bigQuery.create(loadRequest);
    }

}
