package com.dbx.word_string_puzzle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class WordStringPuzzleApplication {
    public static void main(String[] args) {
        SpringApplication.run(WordStringPuzzleApplication.class, args);

        //List<String> inputWordList = obtainInputWords();
        //buildWordStringGraph(inputWordList);

        String word1 = "kutyus";
        String word2 = "kutyás";

        Boolean neighboor = WordGraph.areWordsAdjacent(word1, word2);
        System.out.println("these words: " + word1 + " and " + word2 + " are" + (neighboor ? " " : " NOT ") + "neighboors.");

    }

    private static List<String> obtainInputWords() {
        // TODO error check
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please provide the word list separated by one space character: ");
        String scannedLine = scanner.nextLine();
        //System.out.println("Scanned line: " + scannedLine);

        List<String> wordList = new ArrayList<>(Arrays.asList(scannedLine.split("\\s")));
        System.out.println("Scanned words (" + wordList.size() + " piece(s)):");
        for(String word : wordList) {
            System.out.println(word);
        }

        return wordList;
    }
}
