package com.dbx.word_string_puzzle;

public class WordGraph {

    /**
     * Levenshtein distance is the number of the single-character differences between two words (insertion, deletions or
     * substitutions).
     * Levenshtein distance is the general form of Hamming distance to compare words with different length.
     * */
    public static int calculateLevenshteinDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        int[][] matrix = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            matrix[i][0] = i;
        }

        for (int j = 0; j <= n; j++) {
            matrix[0][j] = j;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int distance = (word1.charAt(i - 1) == word2.charAt(j - 1)) ? 0 : 1;
                matrix[i][j] = Math.min(Math.min(matrix[i - 1][j] + 1, matrix[i][j - 1] + 1), matrix[i - 1][j - 1] + distance);

            }
        }

        return matrix[m][n];
    }
}