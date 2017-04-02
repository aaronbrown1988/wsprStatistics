package net.aaronbrown.wsprstatistics.services;

import net.aaronbrown.wsprstatistics.dao.SpotsDAO;
import net.aaronbrown.wsprstatistics.dto.CSVWSPRSpotDTO;
import net.aaronbrown.wsprstatistics.models.WSPRSpot;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipInputStream;

@Service
public class SpotLoadingService {

    @Autowired
    private SpotsDAO spotsDAO;

    public List<Thread> processAll() {
        //TODO
//        final List<Thread> workers = new ArrayList<>();
//        final Integer counter = 1;
//        File folder = new File("/home/aaron/Downloads/");
//        final List<File> listOfFiles = Arrays.asList(folder.listFiles());
//        for (final File file : listOfFiles) {
//            if (file.getName().contains("wsprspots")) {
//                Thread worker = new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        System.out.println("Spawing Thread ("+Thread.currentThread().getId()+")for file " + counter + " of" + listOfFiles.size() + " : " + file.getName());
//                        loadData(file.getName());
//                        workers.remove(Thread.currentThread());
//                    }
//                });
//                workers.add(worker);
//                worker.start();
//
//            }
//        }
//        return workers;
        return null;
    }

    public void loadData(String filename) {
        Integer counter = 0;
        Date start = new Date();
        try {
            ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream("/home/aaron/Downloads/" + filename));
            zipInputStream.getNextEntry();
            Reader in = new InputStreamReader(zipInputStream);
            Iterable<CSVRecord> records = CSVFormat.RFC4180.parse(in);

            for (CSVRecord record : records) {
                WSPRSpot currentSpot = CSVWSPRSpotDTO.convertToWspr(record);
//                spotsRepository.save(currentSpot);
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