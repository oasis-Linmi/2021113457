package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TextProcessor {

    public static void main(String[] args) {
        String filePath = "C:\\Users\\17527\\IdeaProjects\\SWE_lab01\\target\\classes\\org\\example\\input.txt";  // Replace with your input file path
        String processedText = processText(filePath);
        String[] words = splitIntoWords(processedText);

        // Print the words array
        for (String word : words) {
            System.out.println(word);
        }
    }


    public static String processText(String filePath) {
        StringBuilder text = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                text.append(line).append(" ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Replace non-letter characters with spaces
        String processedText = text.toString().replaceAll("[^A-Za-z]", " ");

        // Convert to lowercase
        processedText = processedText.toLowerCase();

        // Replace multiple spaces with a single space
        processedText = processedText.replaceAll("\\s+", " ").trim();

        return processedText;
    }

    public static String[] splitIntoWords(String text) {
        return text.split(" ");
    }


}

