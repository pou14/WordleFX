package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.Node;

public class SecondaryController extends StatisticController{
        
        private String word; //word to guess
        private String guessedWords = ""; //word guessed by the user
        private int multiplier = 0;
        public int round = 1;
        private int index = 1;
        private int inputC = 0;
        private Boolean newline = false;
        private Boolean max = false;
        private Boolean fifth = false;
        public Boolean win = false;
        public Boolean lose = false;
        protected static char[][] gameData = new char[6][5]; //2D array to store the game data, shadow of the word
        protected static char[] inGameMove = new char[75]; //array to store the word

    //keyboard
    @FXML
    private Button q, w, e, r, t, y, u, i, o, p,
                    a, s, d, f, g, h, j, k, l,
                    z, x, c, v, b, n, m, enter, del;

    //wordblank
    @FXML
    private Button t1, t2, t3, t4, t5, t6, t7, t8, t9,
                    t10, t11, t12, t13, t14, t15, t16, t17, t18,
                    t19, t20, t21, t22, t23, t24, t25, t26, t27, t28,
                    t29, t30;

    @FXML
    private ImageView stat;
                    
    @FXML
    private Label winText, loseText, sentance, words, error; 
    
    //initialize the game
    @FXML
    public void initialize() throws IOException {
        //initialize the game, picking new word
        randomWord rw = new randomWord();
        try {
            rw.loadWordsFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        word = rw.getRandomWord();
        error.setText("You got\nthis!");
        StatisticsLoader.loadStatistics(statistics, "C:/Users/ivano/OneDrive/桌面/CSCE 314/Wordle/wordle/src/main/resources/com/example/savedFile.txt");
    
    }

    //when pressing home
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    //when pressing statistics
    @FXML
    private void switchToFinal() throws IOException {
        App.setRoot("finalStatistics");
    }
        
    @FXML
    private void reset() throws IOException{
        //board reset
        for(int i = 1; i <= 30; ++i){
            Button letterButton = (Button) getButtonById("t" + i);
            letterButton.setText("");
        }
        
        //keyboard reset
        q.setDisable(false); w.setDisable(false); e.setDisable(false); r.setDisable(false); 
        t.setDisable(false); y.setDisable(false); u.setDisable(false); i.setDisable(false); 
        o.setDisable(false); p.setDisable(false); a.setDisable(false); s.setDisable(false);
        d.setDisable(false); f.setDisable(false); g.setDisable(false); h.setDisable(false);
        j.setDisable(false); k.setDisable(false); l.setDisable(false); z.setDisable(false);
        x.setDisable(false); c.setDisable(false); v.setDisable(false); b.setDisable(false);
        n.setDisable(false); m.setDisable(false); enter.setDisable(false); del.setDisable(false);

        //color reset
        q.setStyle("-fx-background-color:  #f5f1e8"); w.setStyle("-fx-background-color:  #f5f1e8");
        e.setStyle("-fx-background-color:  #f5f1e8"); r.setStyle("-fx-background-color:  #f5f1e8");
        t.setStyle("-fx-background-color:  #f5f1e8"); y.setStyle("-fx-background-color:  #f5f1e8");
        u.setStyle("-fx-background-color:  #f5f1e8"); i.setStyle("-fx-background-color:  #f5f1e8");
        o.setStyle("-fx-background-color:  #f5f1e8"); p.setStyle("-fx-background-color:  #f5f1e8");
        a.setStyle("-fx-background-color:  #f5f1e8"); s.setStyle("-fx-background-color:  #f5f1e8");
        d.setStyle("-fx-background-color:  #f5f1e8"); f.setStyle("-fx-background-color:  #f5f1e8");
        g.setStyle("-fx-background-color:  #f5f1e8"); h.setStyle("-fx-background-color:  #f5f1e8");
        j.setStyle("-fx-background-color:  #f5f1e8"); k.setStyle("-fx-background-color:  #f5f1e8");
        l.setStyle("-fx-background-color:  #f5f1e8"); z.setStyle("-fx-background-color:  #f5f1e8");
        x.setStyle("-fx-background-color:  #f5f1e8"); c.setStyle("-fx-background-color:  #f5f1e8");
        v.setStyle("-fx-background-color:  #f5f1e8"); b.setStyle("-fx-background-color:  #f5f1e8");
        n.setStyle("-fx-background-color:  #f5f1e8"); m.setStyle("-fx-background-color:  #f5f1e8");

        for(int i = 1; i <= 30; ++i){
            Button letterButton = (Button) getButtonById("t" + i);
            letterButton.setStyle("-fx-background-color:  #f5f1e8");
        }

        multiplier = 0;
        round = 1;
        index = 1;
        inputC = 0;
        guessedWords = "";
        max = false;
        fifth = false;
        newline = false;
        win = false;
        lose = false;
        stat.visibleProperty().set(false);
        loseText.visibleProperty().set(false);
        winText.visibleProperty().set(false);
        sentance.visibleProperty().set(false);
        words.setText("");
        words.visibleProperty().set(false);
        error.setText("You got\nthis!");

        initialize();
    }

    @FXML
    private void input(ActionEvent event){
        if (event.getSource() instanceof Button) {
            error.setText("You got\nthis!");
            Button clickedButton = (Button) event.getSource();
            String buttonText = clickedButton.getText();

            Button letterButton = (Button) getButtonById("t" + (index)); 

            if(index > 30){
                newline = true;
                error.setText("word\nlimit\nreached!");
                return;
            }

            if (letterButton.getText().equals("") && !max) {
                newline = false;
                inGameMove[inputC++] = buttonText.charAt(0);
                letterButton.setText(buttonText);
                index++;
            }

            if(index % 5 == 1){
                error.setText("word\nlimit\nreached!");
                max = true;
                fifth = true;
            }
        }
    }

    @FXML
    private void del(){
        if(index % 5 == 1 && !fifth){
            error.setText("Fill in \na blank!");
            return; 
        }

        if(!newline){
            error.setText("You got\nthis!");
            index--;
            max = false;
            fifth = false;
            Button letterButton = (Button) getButtonById("t" + (index));
            letterButton.setText("");
        }
        
    }

    @FXML
    private void check() throws IOException{
        if(!win && !lose){
            error.setText("You got\nthis!");
            //check if the word is correct
            if(index % 5 != 1){
                error.setText("Fill in \nall\nblanks!");
                return; //call error log, ask them to fill it all out
            }

            guessedWords = "";
            for(int i = 0; i < 5; ++i){
                Button letterButton = (Button) getButtonById("t" + (i + multiplier + 1)); // Retrieve button by ID
                String letter = letterButton.getText().toLowerCase(); // Get the letter from the button
                if(letter.equals("")){
                    error.setText("Fill in \nall\nblanks!");
                    return;
                }
                guessedWords += letter;
            }

            //check if the word is in the list
            if(!randomWord.isValidWord(guessedWords)){
                error.setText("Word not\n in list!");
                return;
            }

            int correct = 0;
            char [] wordArray = word.toCharArray();
            for (int i = 0; i < 5; i++) {
                Button letterButton = (Button) getButtonById("t" + (i + multiplier + 1) ); // Retrieve button by ID
                String letter = letterButton.getText().toLowerCase(); // Get the letter from the button

                if(letter.equals("")){
                    error.setText("Fill in \nall\nblanks!");
                    return;
                }else if(letter.equals(String.valueOf(wordArray[i]))) {
                    letterButton.setStyle("-fx-background-color: #a7b594"); // Correct position
                    getButtonById(letter).setStyle("-fx-background-color: #a7b594");
                    correct++;
                }else if(word.contains(letter)) {
                    letterButton.setStyle("-fx-background-color: #c9c297"); // Correct letter but wrong position
                    getButtonById(letter).setStyle("-fx-background-color: #c9c297");
                }else{
                    letterButton.setStyle("-fx-background-color: #adb3af"); // Wrong letter
                    getButtonById(letter).setStyle("-fx-background-color: #adb3af");
                    getButtonById(letter).setDisable(true);
                }
                gameData[round - 1][i] = letter.charAt(0);

            }

            if(correct == 5){
                //win
                stat.visibleProperty().set(true);
                winText.visibleProperty().set(true);
                sentance.visibleProperty().set(true);
                words.setText(word);
                words.visibleProperty().set(true);

                newline = true;
                win = true;
                statistics.setWinCount(statistics.getWinCount() + 1);

                if(round == 1){
                    statistics.setOneCount(statistics.getOneCount() + 1);
                }else if(round == 2){
                    statistics.setTwoCount(statistics.getTwoCount() + 1);
                }else if(round == 3){
                    statistics.setThreeCount(statistics.getThreeCount() + 1);
                }else if(round == 4){
                    statistics.setFourCount(statistics.getFourCount() + 1);
                }else if(round == 5){
                    statistics.setFiveCount(statistics.getFiveCount() + 1);
                }else if(round == 6){
                    statistics.setSixCount(statistics.getSixCount() + 1);
                }

                statistics.setTotalPlayed(statistics.getTotalPlayed() + 1);
                StatisticsLoader.saveStatistics(statistics, "C:/Users/ivano/OneDrive/桌面/CSCE 314/Wordle/wordle/src/main/resources/com/example/savedFile.txt");
                StatisticsLoader.saveLog("C:/Users/ivano/OneDrive/桌面/CSCE 314/Wordle/wordle/src/main/resources/com/example/gameLog.txt");
                return;
            }

            if(round == 6){
                //lose
                stat.visibleProperty().set(true);
                loseText.visibleProperty().set(true);
                sentance.visibleProperty().set(true);
                words.setText(word);
                words.visibleProperty().set(true);

                newline = true;
                lose = true;
                statistics.setTotalPlayed(statistics.getTotalPlayed() + 1);
                StatisticsLoader.saveStatistics(statistics, "C:/Users/ivano/OneDrive/桌面/CSCE 314/Wordle/wordle/src/main/resources/com/example/savedFile.txt");
                StatisticsLoader.saveLog("C:/Users/ivano/OneDrive/桌面/CSCE 314/Wordle/wordle/src/main/resources/com/example/gameLog.txt");
                return;
            }

            round += 1;
            multiplier += 5;
            max = false;
            newline = true;
        }else{
            error.setText("No more\ninput!");
        }
    }

    @FXML
    private void saveFile() throws IOException{
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:/Users/ivano/OneDrive/桌面/CSCE 314/Wordle/wordle/src/main/resources/com/example/savedLog.txt"))) {
            
            //saving statistics
            writer.write(multiplier + " ");
            writer.write(round + " ");
            writer.write(index + " ");
            writer.newLine();

            //saving guessing word
            writer.write(word);
            writer.newLine();
            
            //saving board
            int mult = 0;
            for(int i = 0; i < 6; i++){
                for(int j = 0; j < 5; j++){
                    Button letterButton = (Button) getButtonById("t" + (j + mult + 1));
                    String style = letterButton.getStyle();
                    int index = style.indexOf("#");
                    int endIndex = style.indexOf(";", index);

                    if(letterButton.getText().equals("")){
                        writer.write("empty" + " ");
                    }else{
                        writer.write(letterButton.getText() + " ");
                    }
                    
                    if(endIndex == -1){
                        writer.write(style.substring(index));
                    }else{
                        writer.write(style.substring(index, endIndex));
                    }
                    writer.newLine();
                }
                writer.newLine();
                mult += 5;
            }


        }
        error.setText("File\nsaved!");
    }

    @FXML
    private void loadFile() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:/Users/ivano/OneDrive/桌面/CSCE 314/Wordle/wordle/src/main/resources/com/example/savedLog.txt"))) {

            //setting stats
            String[] stat = reader.readLine().split(" ");
            multiplier = Integer.parseInt(stat[0]);
            round = Integer.parseInt(stat[1]);
            index = Integer.parseInt(stat[2]);

            max = false;
            fifth = false;
            newline = false;
            win = false;
            lose = false;

            //setting word
            word = reader.readLine();

            //setting board
            int lineCount = 0;
            int column = 0;
            int row = 0;

            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    String[] tokens = line.split(" ");
                    int tokenL = tokens.length - 1; // Index of the last token
                    Button letterButton = (Button) getButtonById("t" + (column + 1 + row));
                    String colorString = tokens[tokenL];
                    
                    if (tokens[0].startsWith("empty")) {
                        letterButton.setText("");
                    } else {
                        letterButton.setText(tokens[0]); // Set the text from the first token
                        String buttonText = letterButton.getText().toLowerCase();
                        getButtonById(buttonText).setStyle("-fx-background-color: " + colorString);
                        if(getButtonById(buttonText).getStyle().equals("-fx-background-color: #adb3af")){
                            getButtonById(buttonText).setDisable(true);
                        }
                    }

                    letterButton.setStyle("-fx-background-color: " + colorString);
                    column++;
                    lineCount++;
                    
        
                    if (lineCount % 5 == 0) {
                        row = row + 5;
                        column = 0;
                    }
                }
            }
        }
        error.setText("File\nloaded!");
    }
    
    //get button by id
    private Node getButtonById(String id) {
        Parent root = q.getScene().getRoot();
        return root.lookup("#" + id);
    }
}