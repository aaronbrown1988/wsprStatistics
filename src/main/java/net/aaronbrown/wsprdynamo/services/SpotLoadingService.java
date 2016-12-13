package net.aaronbrown.wsprdynamo.services;

import com.amazonaws.services.dynamodbv2.document.Table;
import net.aaronbrown.wsprdynamo.dao.SpotsDao;
import net.aaronbrown.wsprdynamo.dto.CSVWSPRSpotDTO;
import net.aaronbrown.wsprdynamo.dto.WSPRSpotDTO;
import net.aaronbrown.wsprdynamo.models.WSPRSpot;
import net.aaronbrown.wsprdynamo.wsprLoader;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipInputStream;

@Service
public class SpotLoadingService {

    @Autowired
    private SpotsDao spotsDao;



    public List<Thread> processAll() {
        List<Thread> workers = new ArrayList<>();
        Integer counter = 1;
        File folder = new File("C:\\Users\\aaron\\Downloads\\spots2016");
        List<File> listOfFiles = Arrays.stream(folder.listFiles()).filter(p -> p.getName().contains("wsprspots") && p.getName().contains("zip")).collect(Collectors.toList());
        for (File file : listOfFiles) {
            if (file.getName().contains("wsprspots")) {
                Thread worker = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("Spawing Thread ("+Thread.currentThread().getId()+")for file " + counter + " of" + listOfFiles.size() + " : " + file.getName());
                        loadData(file.getName());
                        workers.remove(Thread.currentThread());
                    }
                });
                workers.add(worker);
                worker.start();

            }
        }
        return workers;
    }

    public void loadData(String filename) {
        Integer counter = 0;
        Date start = new Date();
        try {
            ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream("C:\\Users\\aaron\\Downloads\\spots2016\\" + filename));
            zipInputStream.getNextEntry();
            Reader in = new InputStreamReader(zipInputStream);
            Iterable<CSVRecord> records = CSVFormat.RFC4180.parse(in);

            for (CSVRecord record : records) {
                WSPRSpot currentSpot = CSVWSPRSpotDTO.convertToWspr(record);
                spotsDao.addSpot(currentSpot);
                counter++;
                if (counter % 1000 == 0) {
                    System.out.println(filename+" : " + counter);
                }
            }
            Date end = new Date();
            System.out.println(filename+" :  "+ counter + "..Done in " + (end.getTime() - start.getTime()) + "millis!");
            zipInputStream.closeEntry();
            zipInputStream.close();
        } catch (Exception e) {
            System.err.println("Unable to parse CSV");
            System.err.println(e.getMessage());
            e.printStackTrace();
        }


    }
}