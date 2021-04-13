package com.covid.Nepal.services;

import com.covid.Nepal.models.LocationStats;
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
import java.util.ArrayList;
import java.util.List;

@Service
public class CoronaVirusDataService {
    private static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
    private List<LocationStats> allStats = new ArrayList<LocationStats>();

    // creating get method to achieve all the value that are in allstats nad autowiring to HomeController
    public List<LocationStats> getAllStats() {
        return allStats;
    }

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchData() throws IOException, InterruptedException {
        List<LocationStats> newStats = new ArrayList<LocationStats>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(VIRUS_DATA_URL)).build();

        HttpResponse httpResponse = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        //System.out.println(httpResponse.body());
        StringReader csvBodyReader = new StringReader((String) httpResponse.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
        for (CSVRecord record : records) {
            LocationStats locationStats = new LocationStats();
            locationStats.setState(record.get("Province/State"));
            locationStats.setCountry(record.get("Country/Region"));
            int totalLatestReport = Integer.parseInt(record.get(record.size() - 1));
            int prevDayReport = Integer.parseInt(record.get(record.size() - 2));
            locationStats.setTotalConfirmedCases(totalLatestReport);
            locationStats.setDiffWithPrevDay(totalLatestReport - prevDayReport);

            // locationStats.toString();
            newStats.add(locationStats);
        }
        this.allStats = newStats;

    }
}
