package com.example;

import java.io.IOException;
import java.text.DecimalFormat;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class StatisticController {

    @FXML
    Label played, win_p, one, two, three, four, five, six;

    public GameStatistics statistics = new GameStatistics();

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    public void initialize() throws IOException {
        // Initialize statistics
        String filePath = "C:/Users/ivano/OneDrive/桌面/CSCE 314/Wordle/wordle/src/main/resources/com/example/savedFile.txt";
        try {
            StatisticsLoader.loadStatistics(statistics, filePath);
            updateScreen(); // Update the UI with loaded statistics
        } catch (IOException e) {
            e.printStackTrace(); // Handle file loading error
        }
    }

    @FXML
    private void updateScreen() {
        DecimalFormat df = new DecimalFormat("#.#");
        played.setText(String.valueOf((int) statistics.getTotalPlayed()));
        win_p.setText(String.valueOf(df.format(statistics.getWinCount() / statistics.getTotalPlayed() * 100)));
        one.setText(String.valueOf(statistics.getOneCount()));
        two.setText(String.valueOf(statistics.getTwoCount()));
        three.setText(String.valueOf(statistics.getThreeCount()));
        four.setText(String.valueOf(statistics.getFourCount()));
        five.setText(String.valueOf(statistics.getFiveCount()));
        six.setText(String.valueOf(statistics.getSixCount()));
    }

    @FXML
    private void saveStatisticsToFile() {
        try {
            StatisticsLoader.saveStatistics(statistics, "C:/Users/ivano/OneDrive/桌面/CSCE 314/Wordle/wordle/src/main/resources/com/example/savedFile.txt");
        } catch (IOException e) {
            e.printStackTrace(); // Handle file saving error
        }
    }




}
