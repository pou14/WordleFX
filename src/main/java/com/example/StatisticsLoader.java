package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class StatisticsLoader extends SecondaryController {

    public static void loadStatistics(GameStatistics statistics, String filePath) throws IOException{
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Parse each line to update statistics
                String[] parts = line.split(" ");
                if (parts.length >= 2) {
                    String key = parts[0].trim();
                    double value = Double.parseDouble(parts[1].trim());

                    switch (key) {
                        case "winCount":
                            statistics.setWinCount(value);
                            break;
                        case "totalPlayed":
                            statistics.setTotalPlayed(value);
                            break;
                        case "oneCount":
                            statistics.setOneCount((int) value);
                            break;
                        case "twoCount":
                            statistics.setTwoCount((int) value);
                            break;
                        case "threeCount":
                            statistics.setThreeCount((int) value);
                            break;
                        case "fourCount":
                            statistics.setFourCount((int) value);
                            break;
                        case "fiveCount":
                            statistics.setFiveCount((int) value);
                            break;
                        case "sixCount":
                            statistics.setSixCount((int) value);
                            break;
                    }
                }
            }
        }
    }

    public static void saveStatistics(GameStatistics statistics, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("winCount " + statistics.getWinCount() + "\n");
            writer.write("totalPlayed " + statistics.getTotalPlayed() + "\n");
            writer.write("oneCount " + statistics.getOneCount() + "\n");
            writer.write("twoCount " + statistics.getTwoCount() + "\n");
            writer.write("threeCount " + statistics.getThreeCount() + "\n");
            writer.write("fourCount " + statistics.getFourCount() + "\n");
            writer.write("fiveCount " + statistics.getFiveCount() + "\n");
            writer.write("sixCount " + statistics.getSixCount() + "\n");
        }
    }

    public static void saveLog(String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Final Input: \n");
            for(int i = 0; i < 6; ++i){
                writer.write("[");
                for(int j = 0; j < 4; ++j){
                    writer.write(gameData[i][j] + ", ");
                }
                writer.write(gameData[i][4] + "]\n");
            }
            writer.write("\n");
            writer.write("\n");
            writer.write("Game Log: \n");

            char[][] outputArray = new char[15][5];
            int index = 0;
            for (int row = 0; row < 15; row++) {
                for (int col = 0; col < 5; col++) {
                    if (index < inGameMove.length) {
                        outputArray[row][col] = inGameMove[index];
                        index++;
                    } else {
                        // Fill remaining cells with spaces if inGameMove array is exhausted
                        outputArray[row][col] = ' ';
                    }
                }
            }

            for (int row = 0; row < 15; row++) {
                writer.write("[");
                for (int col = 0; col < 5; col++) {
                    writer.write(outputArray[row][col]);
                    if (col < 5 - 1) {
                       writer.write(", ");
                    }
                }
                writer.write("]\n");
            }


        }
    }
}
