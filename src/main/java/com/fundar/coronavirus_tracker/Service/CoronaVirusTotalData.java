package com.fundar.coronavirus_tracker.Service;


import com.fundar.coronavirus_tracker.Entity.LocationStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoronaVirusTotalData {
    public static  String CORONA_VIRUS_DATA_TOTAL = "https://raw.githubusercontent.com/nytimes/covid-19-data/master/live/us.csv";
    private List<LocationStats> totalStats = new ArrayList<> ();
    public List<LocationStats> getTotalStats () {
        return totalStats;
    }

    public String HttpRequestCsv() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient ();
        HttpRequest request = HttpRequest.newBuilder ().uri(URI.create (CORONA_VIRUS_DATA_TOTAL))
                .build ();
//        HttpResponse<String> response = client.send (request, HttpResponse.BodyHandlers.ofString ());

        return client.send (request, HttpResponse.BodyHandlers.ofString ()).body ();
    }


    @PostConstruct
    @Scheduled (cron = "1 * * * * * ") // Execute the First Hours of Every Day
    public void fetchCoronaVirusData() throws IOException, InterruptedException {

        List<LocationStats> UpdatedStats = new ArrayList<> ();
        StringReader csvReader = new StringReader (HttpRequestCsv ());
        Iterable<CSVRecord> csvRecord = CSVFormat.DEFAULT.withFirstRecordAsHeader ().parse (csvReader);
        for(CSVRecord csvRecords : csvRecord){
            LocationStats locationStats = new LocationStats ();
            String totalCases = csvRecords.get ("cases");
            String totaldeaths = csvRecords.get ("deaths");


            locationStats.setTotalCases (totalCases);
            locationStats.setTotalDeaths (totaldeaths);


//            System.out.println (locationStats);

            UpdatedStats.add (locationStats);


        }
        this.totalStats = UpdatedStats;
    }

}
