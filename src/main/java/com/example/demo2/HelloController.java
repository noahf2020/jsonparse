package com.example.demo2;

import com.example.demo2.Sportsevents.Sportsevents;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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

    public TextField sportsTeam;
    @FXML
    protected void onHelloButtonClick() throws Exception{

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://odds.p.rapidapi.com/v4/sports/americanfootball_nfl/odds?regions=us&oddsFormat=decimal&markets=spreads&dateFormat=iso"))
                .header("X-RapidAPI-Key", "8caf149c5amsh8345060bd49ad26p11ad0cjsn359ef318cd0a")
                .header("X-RapidAPI-Host", "odds.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());


        String jsonData = response.body(); // Replace [...] with your JSON data




        try {
            // Convert JSON array to List of SportsEvent objects
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonData);

            // Now you can navigate through the JsonNode to access specific elements
            // For example, to access the "sport_title" of the first item in the array:
            String sportTitle = jsonNode.get(0).get("sport_title").asText();
            System.out.println("Sport Title: " + sportTitle);
            JsonNode bookmakersArray = jsonNode.get(0).get("bookmakers");

            for (JsonNode bookmaker : bookmakersArray) {
                // Access bookmaker properties
                String bookmakerKey = bookmaker.get("key").asText();
                String bookmakerTitle = bookmaker.get("title").asText();
                String lastUpdate = bookmaker.get("last_update").asText();

                System.out.println("Bookmaker Key: " + bookmakerKey);
                System.out.println("Bookmaker Title: " + bookmakerTitle);
                System.out.println("Last Update: " + lastUpdate);
            }


            } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
