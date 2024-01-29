package com.example.demo2;

import com.example.demo2.Sportsevents.Sportsevents;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() throws Exception{

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://odds.p.rapidapi.com/v4/sports?all=true"))
                .header("X-RapidAPI-Key", "8caf149c5amsh8345060bd49ad26p11ad0cjsn359ef318cd0a")
                .header("X-RapidAPI-Host", "odds.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        String jsonData = response.body(); // Replace [...] with your JSON data

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Convert JSON array to List of SportsEvent objects
            List<Sportsevents> sportsEvents = Arrays.asList(objectMapper.readValue(jsonData, Sportsevents[].class));

            // Now you can work with the parsed data
            for (Sportsevents event : sportsEvents) {
                System.out.println("Key: " + event.getKey());
                System.out.println("Group: " + event.getGroup());
                System.out.println("Title: " + event.getTitle());
                System.out.println("Description: " + event.getDescription());
                System.out.println("Active: " + event.isActive());
                System.out.println("Has Outrights: " + event.isHasOutrights());
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
