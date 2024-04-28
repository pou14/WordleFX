package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class randomWord extends SecondaryController{

    public static List<String> words;

    // Constructor to initialize the words list
    public randomWord() {
        randomWord.words = new ArrayList<>();
    }
    
    // Load words from a file into the words list
    @SuppressWarnings("resource")
    public void loadWordsFromFile() throws IOException {
        BufferedReader reader = null;

        reader = new BufferedReader(new FileReader("C:/Users/ivano/OneDrive/桌面/CSCE 314/Wordle/wordle/src/main/resources/com/example/words.txt"));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] lineWords = line.split("\\s+");

                for (String word : lineWords) {
                    if (!word.isEmpty()) {
                        words.add(word);
                    }
                }
            }
    }

    public String getRandomWord() {
        if (words.isEmpty()) {
            throw new IllegalStateException("Word list is empty. Load words first.");
        }
        Random random = new Random();
        int index = random.nextInt(words.size());
        return words.get(index);
    }

    public static boolean isValidWord(String guessedWord) {
        return words.contains(guessedWord);
    }
}