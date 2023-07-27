package com.dbx.word_string_puzzle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * could be more delicate
 * */
class WordGraphTest {
    @Test
    public void areWordsAdjacentTest1() {
        Boolean actualResult = WordGraph.areWordsAdjacent("test1", "test2");
        assertEquals(true, actualResult);
    }
    @Test
    public void areWordsAdjacentTest2() {
        Boolean actualResult = WordGraph.areWordsAdjacent("test1", "test1");
        assertEquals(true, actualResult);
    }
    @Test
    public void areWordsAdjacentTest3() {
        Boolean actualResult = WordGraph.areWordsAdjacent("test1", "Pest1");
        assertEquals(true, actualResult);
    }
    @Test
    public void areWordsAdjacentTest4() {
        Boolean actualResult = WordGraph.areWordsAdjacent("test1", "test1_somethingelse");
        assertEquals(false, actualResult);
    }
}