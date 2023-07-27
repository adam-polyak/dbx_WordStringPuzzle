package com.dbx.word_string_puzzle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

 import java.util.ArrayList;
import java.util.List;

import static com.dbx.word_string_puzzle.WordGraph.*;

/**
 * Word String Puzzle Handler<br>
 * For more information read the README.md<br>
 * @author Ádám Polyák <adam.polyak.email at gmail.com>
 * */
@SpringBootApplication
public class WordStringPuzzleApplication {
    public static void main(String[] args) {
        SpringApplication.run(WordStringPuzzleApplication.class, args);

        List<String> inputWordList = InputHandler.obtainInputWords();
        List<List<Integer>> resultList = seekingForHamiltonPaths(inputWordList);
        printFirstResult(inputWordList, resultList);
    }

    private static void printFirstResult(List<String> nodes, List<List<Integer>> resultList) {
        if(resultList.isEmpty()) {
            System.err.print("hiba: a megadott szavakból nem lehetséges szóláncot építeni!");
            return;
        }
        List<Integer> firstResult = resultList.get(0);
        for (int i = 0; i < firstResult.size(); i++) {
            System.out.print(nodes.get(firstResult.get(i)) + " ");
        }
    }

}
