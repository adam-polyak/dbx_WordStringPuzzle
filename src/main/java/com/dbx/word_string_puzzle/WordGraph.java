package com.dbx.word_string_puzzle;

/**
 * Graph related classes and methods<br>
 * @author Ádám Polyák <adam.polyak.email at gmail.com>
 * */
public class WordGraph {
    private static final Boolean debugPrint = false;


    /**
     * Returns if two words are adjacent so they can be joined one after another.
     * @param word1   first word
     * @param word2   second word
     * @return true if the words are adjacent, false otherwise
     * */
    public static Boolean areWordsAdjacent(String word1, String word2) {
        return (calculateLevenshteinDistance(word1, word2, true) <= 1);
    }

    /**
     * Levenshtein distance is the number of the single-character differences between two words (insertion, deletion or
     * substitution differences).<br>
     * It is the general form of Hamming distance, but able to compare words with different lengths.<br>
     * The Limited parameter is able to limit the calculation up to maximum 2 differences, if there is no need for
     * bigger values, so we can save a nice bunch of computing resources.<br>
     *
     * @param word1   first word
     * @param word2   second word
     * @param limited limits the calculation up to maximum 2 differences
     * @return the Levenshtein distance between the two strings (maximum 2 if limited is true)
     * @see <a href="https://en.wikipedia.org/wiki/Levenshtein_distance">Levenshtein distance</a>
     * @see <a href="https://en.wikipedia.org/wiki/Hamming_distance">Hamming distance</a>
     * @see <a href="https://www.baeldung.com/java-levenshtein-distance">Levenshtein Distance in Java using Dynamic
     * Programming</a>
     */
    private static int calculateLevenshteinDistance(String word1, String word2, Boolean limited) {
        int minDistance;
        int m = word1.length();
        int n = word2.length();

        if(limited && Math.abs(m - n) >= 2) {
            return 2;
        }

        int[][] matrix = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            matrix[i][0] = i;
        }
        for (int j = 0; j <= n; j++) {
            matrix[0][j] = j;
        }

        for (int i = 1; i <= m; i++) {
            minDistance = Integer.MAX_VALUE;
            for (int j = 1; j <= n; j++) {
                int distance = (word1.charAt(i - 1) == word2.charAt(j - 1)) ? 0 : 1;
                matrix[i][j] = Math.min(Math.min(matrix[i - 1][j] + 1, matrix[i][j - 1] + 1), matrix[i - 1][j - 1] + distance);
                if (debugPrint) System.out.print(matrix[i][j] + "\t");

                // check if execution is limited and return if the distance is already greater than 2
                if (limited && matrix[i][j] < minDistance) {
                    minDistance = matrix[i][j];
                }
            }
            if (debugPrint) System.out.println();
            if (limited && minDistance >= 2) {
                return 2;
            }

        }
        return matrix[m][n];
    }


    /**
     * Levenshtein distance is the number of the single-character differences between two words (insertion, deletion or
     * substitution differences).<br>
     * It is the general form of Hamming distance, but able to compare words with different lengths.<br>
     *
     * @param word1   first word
     * @param word2   second word
     * @return the Levenshtein distance between the two strings
     * @see <a href="https://en.wikipedia.org/wiki/Levenshtein_distance">Levenshtein distance</a>
     * @see <a href="https://en.wikipedia.org/wiki/Hamming_distance">Hamming distance</a>
     * @see <a href="https://www.baeldung.com/java-levenshtein-distance">Levenshtein Distance in Java using Dynamic
     * Programming</a>
     */
    private static int calculateLevenshteinDistance(String word1, String word2) {
        return calculateLevenshteinDistance(word1, word2, false);
    }
}