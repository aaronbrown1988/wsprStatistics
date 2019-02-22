package net.aaronbrown.wsprstatistics.dao;

import com.google.cloud.bigquery.FieldValue;
import com.google.cloud.bigquery.FieldValueList;
import com.google.cloud.bigquery.TableResult;
import net.aaronbrown.wsprstatistics.models.Country;
import net.aaronbrown.wsprstatistics.services.BigQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
        String queryString = "SELECT country,count(*) FROM [wsprstats-163301:reference_data.big_cty]  group by country";

        TableResult result = bigQueryService.runQuery(queryString);

        List<String> countryList = new ArrayList<>();
        Iterable<FieldValueList> iter = result.iterateAll();
        for (List<FieldValue> record : iter) {
            if (!record.get(0).isNull()) {
                countryList.add(record.get(0).getStringValue());
            }
        }
        return countryList;

    }

    public Map<String,Double> bandForCountry(String tx, String rx) {
        //TODO determine the correct query for here
        String queryString = "Select * from [wsprstats-163301]";

        return null;
    }

    public void save(Country country) {
    }

}
