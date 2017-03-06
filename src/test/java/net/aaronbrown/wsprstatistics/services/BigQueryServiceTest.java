package net.aaronbrown.wsprstatistics.services;

import org.junit.Before;
import org.junit.Test;


/**
 * Created by aaron on 6/03/17.
 */
public class BigQueryServiceTest {
    private BigQueryService bigQueryService;

    @Before
    public void setUp() throws Exception {
        bigQueryService = new BigQueryService();
    }

    @Test
    public void avgDistanceByTimeForBand() throws Exception {
        bigQueryService.avgDistanceByTimeForBand(7);
    }

}