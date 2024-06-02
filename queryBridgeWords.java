package org.example;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class queryBridgeWords{
    public queryBridgeWords() {

    }

    public static void main(String[] args) {
        String filePath = "C:\\Users\\17527\\IdeaProjects\\SWE_lab01\\target\\classes\\org\\example\\input.txt";  // Replace with your input file path
        String processedText = TextProcessor.processText(filePath);
        String[] words = TextProcessor.splitIntoWords(processedText);
        StrGraph myGraph = new StrGraph();
        myGraph.readStrArr(words);
        myGraph.display();
        query("seek", "to", myGraph.list);
        query("to", "explore", myGraph.list);
        query("explore", "new", myGraph.list);
        query("new", "and", myGraph.list);
        query("and", "exciting", myGraph.list);
        query("exciting", "synergies", myGraph.list);
        String[] modifiedText = generateNewText("Seek to explore new and exciting synergies", myGraph.list);
        for (int i = 0; i < modifiedText.length; i++) {
            System.out.print(modifiedText[i] + " ");
        }

        getShortestPath(myGraph, "to", "and");
        
        return;
    }
    public static String[] query(String s1,String s2, Map<String, Map<String, Integer>> myMap) {
        if (!myMap.containsKey(s1) || !myMap.containsKey(s2)) {
            System.out.print("No bridge words from " + s1 + " to " + s2 + "!\n");
            return new String[0];
        }

        ArrayList<String>  ret = new ArrayList<>();
        Map<String, Integer> subMap = myMap.get(s1);
        for (String s: subMap.keySet()) {
            if (myMap.get(s).containsKey(s2)) {
                ret.add(s);
            }
        }
        if (ret.isEmpty()) {
            System.out.print("No bridge words from " + s1 + " to " + s2 + "!\n");
        }else{
            System.out.print("The bridge words from " + s1 + " to " + s2 + " are: ");
            int num = ret.size();
            if (num == 1) {
                System.out.print(ret.get(0) + ".\n");
            }else {
                for (int i = 0; i < num - 1; i++) {
                    System.out.print(ret.get(i) + ", ");
                }
                    System.out.print("and " + ret.get(num-1) + ".\n");
            }
        }
        return ret.toArray(new String[0]);
    }

    public static String[] generateNewText(String inputString, Map<String, Map<String, Integer>> mymap) {
        String[] inputText = TextProcessor.splitIntoWords(inputString);
        ArrayList<String>  ret = new ArrayList<>();
        for (int i = 0; i < inputText.length - 1; i++) {
            String s1 = inputText[i];
            String s2 = inputText[i+1];
            String[] bridgeWords = query(s1, s2, mymap);
            if (bridgeWords.length == 0) {
                ret.add(s1);
//                ret.add(s2);
            } else {
                ret.add(s1);
                ret.add(bridgeWords[0]);
//                ret.add(s2);
            }
        }
        ret.add(inputText[inputText.length - 1]);
        return ret.toArray(new String[0]);
    }
}
//modify1
//modify on C4