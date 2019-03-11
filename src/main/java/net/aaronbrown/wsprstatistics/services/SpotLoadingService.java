package net.aaronbrown.wsprstatistics.services;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.cloud.storage.Acl;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.cloud.storage.Acl.Role;
import com.google.cloud.storage.Acl.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SpotLoadingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpotLoadingService.class);
    private Storage storage = null;


    @Autowired
    private BigQueryService bigQueryService;


    @Value("${dataUrl}")
    private String dataUrl;

    @Value("${tempBucket}")
    private String bucketName;

    @Value("${spotTable}")
    private String spotTable;


    public SpotLoadingService() {
        storage = StorageOptions.getDefaultInstance().getService();
    }

    public void getSpots(LocalDate when) throws Exception {
        String month = String.format("%02d", when.getMonthValue());
        String year = String.format("%04d", when.getYear());

        String filename = "wsprspots-"+year+"-"+month+".csv.gz";


        URL obj = new URL(dataUrl+filename);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        if (con.getResponseCode() != 200 ) {
            throw new RuntimeException("Couldn't download "+ dataUrl+filename);
        }
     
        // the inputstream is closed by default, so we don't need to close it here
        BlobInfo blobInfo =
            storage.create(
                BlobInfo
                    .newBuilder(bucketName, filename)
                    // Modify access list to allow all users with link to read file
                    .setAcl(new ArrayList<>(Arrays.asList(Acl.of(User.ofAllUsers(), Role.READER))))
                    .build(),
                con.getInputStream());


        bigQueryService.runLoadJob( bucketName,filename, "spot-data");

    }
}