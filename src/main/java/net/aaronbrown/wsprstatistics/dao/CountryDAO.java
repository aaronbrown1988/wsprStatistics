package net.aaronbrown.wsprstatistics.dao;

import com.google.cloud.bigquery.FieldValue;
import com.google.cloud.bigquery.QueryRequest;
import com.google.cloud.bigquery.QueryResult;
import net.aaronbrown.wsprstatistics.models.Country;
import net.aaronbrown.wsprstatistics.services.BigQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by aaron on 1/04/17.
 */
@Component
public class CountryDAO {

    @Autowired
    private BigQueryService bigQueryService;

    private static final Logger LOG = Logger.getLogger("CountryDao");

    public List<String> countryList() {
        String queryString = "SELECT country,count(*) FROM [wsprstats-163301:callsign_country.big_cty]  group by country";

        QueryRequest queryRequest =
                QueryRequest.newBuilder(queryString)
                        //.addNamedParameter("band", QueryParameterValue.int64(band))
                        // Standard SQL syntax is required for parameterized queries.
                        // See: https://cloud.google.com/bigquery/sql-reference/
                        .setUseLegacySql(true)
                        .build();
        // Execute the query.
        QueryResult result = bigQueryService.runQuery(queryRequest);

        List<String> countryList = new ArrayList<>();
        Iterator<List<FieldValue>> iter = result.iterateAll();
        while (iter.hasNext()) {
            List<FieldValue> record = iter.next();
            if (!record.get(0).isNull()) {
                countryList.add(record.get(0).getStringValue());
            }
        }
        return countryList;

    }

    public void save(Country country) {
    }

}
