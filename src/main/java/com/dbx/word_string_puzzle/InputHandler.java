package com.dbx.word_string_puzzle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Input handler methods<br>
 * @author Ádám Polyák <adam.polyak.email at gmail.com>
 * */
public class InputHandler {

    /**
     * obtain the input words
     * @return String List of input words
     * */
    public static List<String> obtainInputWords() {
        Scanner scanner = chooseScannerSource();
        String scannedLine = scanner.nextLine();
        System.out.println("Scanned line: " + scannedLine);

        List<String> wordList = new ArrayList<>(Arrays.asList(scannedLine.split("\\s")));
        System.out.println("Scanned words (" + wordList.size() + " piece(s)):");
        for(String word : wordList) {
            System.out.println(word);
        }

        return wordList;
    }

    /**
     * Offers the possibility to the User to choose to scan input from File (1) or from console (2)
     * @return Scanner object with the chosen input
     * */
    private static Scanner chooseScannerSource() {
        Scanner scanner = new Scanner(System.in);
        boolean sourceLocatedSuccessfully = false;
        do {
            System.out.println("Please press 1 or 2 to choose from these options:" +
                    "\n1) open a file" +
                    "\n2) provide words manually");
            String chosenOption = scanner.nextLine();
            switch(chosenOption) {
                case "1": {
                    System.out.println("Please provide the location of the input file!");
                    String fileLocation = scanner.nextLine();
                    File inputFile = new File(fileLocation);
                    try {
                        scanner = new Scanner(inputFile);
                        System.out.println("File ('" + inputFile + "') successfully scanned!");
                        sourceLocatedSuccessfully = true;
                    } catch (FileNotFoundException e) {
                        System.out.println("File not found!");
                    }
                    break;
                }
                case "2": {
                    System.out.println("Please provide the list of words separated by one space character: ");
                    scanner = new Scanner(System.in);
                    sourceLocatedSuccessfully = true;
                    break;
                }
                default: {
                    System.out.println("Please press '1' or '2' to choose!");
                    break;
                }
            }
        } while (!sourceLocatedSuccessfully);
        return scanner;
    }
}
