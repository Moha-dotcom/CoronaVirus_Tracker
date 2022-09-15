package com.fundar.coronavirus_tracker.Service;

import com.fundar.coronavirus_tracker.Entity.LocationStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoronavirusDataService {

    public static  String CORONA_VIRUS_DATA_URL = "https://raw.githubusercontent.com/nytimes/covid-19-data/master/live/us-counties.csv";
    private List<LocationStats> allLocationsats = new ArrayList<> ();

    public List<LocationStats> getAllLocationsats () {
        return allLocationsats;
    }



    public String HttpRequestCsv() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient ();
        HttpRequest request = HttpRequest.newBuilder ().uri(URI.create (CORONA_VIRUS_DATA_URL))
                .build ();
//        HttpResponse<String> response = client.send (request, HttpResponse.BodyHandlers.ofString ());

        return client.send (request, HttpResponse.BodyHandlers.ofString ()).body ();
    }

    @PostConstruct
//    @Scheduled(cron = "* * 1 * * * ") // Execute the First Hours of Every Day
    public void fetchCoronaVirusData() throws IOException, InterruptedException {

        List<LocationStats> UpdatedStats = new ArrayList<> ();
        StringReader csvReader = new StringReader (HttpRequestCsv ());
        Iterable<CSVRecord> csvRecord = CSVFormat.DEFAULT.withFirstRecordAsHeader ().parse (csvReader);
        for(CSVRecord csvRecords : csvRecord){
           LocationStats locationStats = new LocationStats ();
            LocalDate date = LocalDate.parse (csvRecords.get ("date"));
            String county = csvRecords.get ("county");
            String state = csvRecords.get ("state");
            String zips = csvRecords.get ("fips");
            String cases = csvRecords.get ("cases");
            String deaths = csvRecords.get ("deaths");

            locationStats.setDate (date);
            locationStats.setCounty (county);
            locationStats.setState (state);
            locationStats.setFips (zips);
            locationStats.setCases (cases);
            locationStats.setDeaths (deaths);

            System.out.println (locationStats);

            UpdatedStats.add (locationStats);


        }
        this.allLocationsats = UpdatedStats;
    }

    @PostConstruct
    public long totalNumberOfDeaths() throws IOException, InterruptedException {

//        List<String> plea = new ArrayList<> ();
//        StringReader csvReader = new StringReader (HttpRequestCsv ());
//        Iterable<CSVRecord> csvRecord = CSVFormat.DEFAULT.withFirstRecordAsHeader ().parse (csvReader);
//
//        for (CSVRecord csvRecords : csvRecord) {
//            String death =  (csvRecords.get ("deaths"));
//            plea.add (death);
//        }
                long totalDeath = 290000;

                return totalDeath;







    }
}
