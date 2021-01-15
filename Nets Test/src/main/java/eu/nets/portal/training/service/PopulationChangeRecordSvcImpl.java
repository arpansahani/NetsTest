package eu.nets.portal.training.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import eu.nets.portal.training.dto.PopulationRecord;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class PopulationChangeRecordSvcImpl implements PopulationChangeRecordSvc{

    String applicationWelcomeText = "Welcome to demo test application implemented by Arpan. This is a welcome message from service";
    String remoteAPIUrl = "http://data.ssb.no/api/v0/dataset/1106.csv?lang=en";

    @Override
    public String getWelcomeMessage() {
        return applicationWelcomeText;
    }

    @Override
    public ArrayList<PopulationRecord> getAllPopulationChangeList() {
        ArrayList populationChangeList = new ArrayList();
        try {
            URL csvRemoteApi = new URL(remoteAPIUrl);
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(csvRemoteApi.openStream()));
            List<String[]> populationChangeCSVData = new CSVReader(streamReader).readAll();
               if (!CollectionUtils.isEmpty(populationChangeCSVData))
                    populationChangeList = populationChangeCSVData.stream().skip(1).map(record -> new PopulationRecord(record[0],
                      record[1], record[2], Integer.parseInt(record[3]))).collect(Collectors.toCollection((Supplier<ArrayList>) ArrayList::new));
        } catch (MalformedURLException urlEx) {
            Logger.getLogger(PopulationChangeRecordSvc.class.getName()).log(Level.SEVERE, null, urlEx);
        } catch (IOException ioEx) {
            Logger.getLogger(PopulationChangeRecordSvc.class.getName()).log(Level.SEVERE, null, ioEx);
        } catch (CsvException csvEx) {
            Logger.getLogger(PopulationChangeRecordSvc.class.getName()).log(Level.SEVERE, null, csvEx);
        }
        return populationChangeList;
        }
    }