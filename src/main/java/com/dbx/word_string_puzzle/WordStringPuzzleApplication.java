package com.dbx.word_string_puzzle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

import static com.dbx.word_string_puzzle.InputHandler.*;

/**
 * Word String Puzzle Handler<br>
 * For more information read the README.md<br>
 * @author Ádám Polyák <adam.polyak.email at gmail.com>
 * */
@SpringBootApplication
public class WordStringPuzzleApplication {
    public static void main(String[] args) {
        SpringApplication.run(WordStringPuzzleApplication.class, args);

        List<String> inputWordList = obtainInputWords();
        //buildWordStringGraph(inputWordList);
    }

}
