package com.example.demo2;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

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
    public TableView mainTable;
    public TextField sportsTeam;

    public ChoiceBox selectionBox;

    public void initialize() throws Exception {

        TableColumn<SportEvent, String> Game = new TableColumn<>("Game");
        Game.setCellValueFactory(new PropertyValueFactory<SportEvent, String>("Game"));

        TableColumn<SportEvent, String> Time = new TableColumn<>("Time");
        Time.setCellValueFactory(new PropertyValueFactory<SportEvent, String>("Time"));

        TableColumn<SportEvent, String> site = new TableColumn<>("Site");
        site.setCellValueFactory(new PropertyValueFactory<SportEvent, String>("Site"));

        TableColumn<SportEvent, String> favoritedTeam = new TableColumn<>("Favorited Team");
        favoritedTeam.setCellValueFactory(new PropertyValueFactory<SportEvent, String>("FavoritedTeam"));

        TableColumn<SportEvent, String> AveragedOdds = new TableColumn<>("Averaged Odds");
        AveragedOdds.setCellValueFactory(new PropertyValueFactory<SportEvent, String>("AvgOdds"));

        TableColumn<SportEvent, String> HomeTeam = new TableColumn<>("Home Team");
        HomeTeam.setCellValueFactory(new PropertyValueFactory<SportEvent, String>("HomeTeam"));

        mainTable.getColumns().add(Game);
        mainTable.getColumns().add(Time);
        mainTable.getColumns().add(site);
        mainTable.getColumns().add(favoritedTeam);
        mainTable.getColumns().add(AveragedOdds);
        mainTable.getColumns().add(HomeTeam);





        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://odds.p.rapidapi.com/v4/sports?all=true"))
                .header("X-RapidAPI-Key", "8caf149c5amsh8345060bd49ad26p11ad0cjsn359ef318cd0a")
                .header("X-RapidAPI-Host", "odds.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(response.body());

        for (JsonNode node : jsonNode) {
            String key = node.get("key").asText();
            String group = node.get("group").asText();
            String title = node.get("title").asText();
            String description = node.get("description").asText();
            boolean active = node.get("active").asBoolean();
            boolean hasOutrights = node.get("has_outrights").asBoolean();
            if (group.equals("American Football")){
                selectionBox.getItems().add(key);
            }

        }


        selectionBox.setOnAction((event) -> {
            int selectedIndex = selectionBox.getSelectionModel().getSelectedIndex();
            Object selectedItem = selectionBox.getSelectionModel().getSelectedItem();

            System.out.println("Selection made: [" + selectedIndex + "] " + selectedItem);
            System.out.println("   ChoiceBox.getValue(): " + selectionBox.getValue());
            try {
                onHelloButtonClick((String) selectionBox.getValue());
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Sports Odds");
                alert.setHeaderText("An error has been encountered - Try a different League");//from   www  .  ja va  2 s  .com

                alert.showAndWait();
               // throw new RuntimeException(e);

            }
        });

    }
    @FXML
    protected void onHelloButtonClick(String key) throws Exception{

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://odds.p.rapidapi.com/v4/sports/"+ key + "/odds?regions=us&oddsFormat=decimal&markets=spreads&dateFormat=iso"))
                .header("X-RapidAPI-Key", "8caf149c5amsh8345060bd49ad26p11ad0cjsn359ef318cd0a")
                .header("X-RapidAPI-Host", "odds.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());


        String jsonData = response.body();




        try {
            // Convert JSON array to List of SportsEvent objects
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonData);

            String sportTitle = jsonNode.get(0).get("sport_title").asText();
            String homeTeam = jsonNode.get(0).get("home_team").asText();
            String awayTeam = jsonNode.get(0).get("away_team").asText();
            String commenceTime = jsonNode.get(0).get("commence_time").asText();

            System.out.println("Sport Title: " + sportTitle + " " + homeTeam + " vs " + awayTeam +" @ " + commenceTime);
            String gameName =  homeTeam + " vs " + awayTeam;
            JsonNode bookmakersArray = jsonNode.get(0).get("bookmakers");

            for (JsonNode bookmaker : bookmakersArray) {
                // Access bookmaker properties
                String bookmakerTitle = bookmaker.get("title").asText();
                JsonNode marketsArray = bookmaker.get("markets");
                for (JsonNode market : marketsArray) {
                    // Access outcomes array
                    JsonNode outcomesArray = market.get("outcomes");
                    double outcomePrice = 0;
                    double outcomePoint = 0;

                    String outcomeName = null;

                    // Iterate through each outcome
                    for (JsonNode outcome : outcomesArray) {
                        // Access outcome properties

                         outcomeName = outcome.get("name").asText() + outcomeName;
                         outcomePrice = outcome.get("price").asDouble() + outcomePrice;
                         outcomePoint = outcome.get("point").asDouble();

                    }

                    double avgodds =  outcomePrice/2;



                    SportEvent newEvent = new SportEvent(gameName, commenceTime, bookmakerTitle, "", String.valueOf(avgodds), homeTeam);
                    mainTable.getItems().add(newEvent);
                    System.out.println( bookmakerTitle + " Outcome Name: " + outcomeName + " Avg outcome Price: " + avgodds + " Outcome Point: " + outcomePoint);


                }



            }


            } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("DevLaunch Dialog");
            alert.setHeaderText("An error has been encountered");
            alert.setContentText("Ff");//from   www  .  ja va  2 s  .com

            alert.showAndWait();
            e.printStackTrace();
        }
    }  private static String findFavoriteTeam(double oddsTeam1, double oddsTeam2) {
        if (oddsTeam1 < oddsTeam2) {
            return "Team 1"; // Replace with the actual name of the first team
        } else if (oddsTeam2 < oddsTeam1) {
            return "Team 2"; // Replace with the actual name of the second team
        } else {
            return "No clear favorite"; // Both teams have equal odds
        }
    }
}
