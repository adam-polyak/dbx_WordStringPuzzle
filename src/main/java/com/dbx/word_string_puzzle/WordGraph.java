package com.dbx.word_string_puzzle;

import java.util.ArrayList;
import java.util.List;


/**
 * A class to store a graph edge
 */
class Edge
{
    int source, dest;

    public Edge(int source, int dest)
    {
        this.source = source;
        this.dest = dest;
    }
}

/**
 * A class to represent a graph object
 */
class Graph
{
    // A list of lists to represent an adjacency list
    List<List<Integer>> adjList = null;

    // Constructor
    Graph(List<Edge> edges, int n)
    {
        adjList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        // add edges to the undirected graph
        for (Edge edge: edges)
        {
            int src = edge.source;
            int dest = edge.dest;

            adjList.get(src).add(dest);
            adjList.get(dest).add(src);
        }
    }
}


/**
 * Graph related classes and methods<br>
 * @author Ádám Polyák <adam.polyak.email at gmail.com>
 * */
public class WordGraph {
    private static final Boolean debugPrint = true;

    public static void hamiltonianPaths(Graph graph, int v, boolean[] visited,
                                        List<Integer> path, int n, List<List<Integer>> resultList)

    {
        // if all the vertices are visited, then the Hamiltonian path exists
        if (path.size() == n)
        {
            // print the Hamiltonian path
            if(debugPrint) System.out.println(path);
            List<Integer> newPath = new ArrayList<Integer>(path);
            resultList.add(newPath);
            return;
        }

        // Check if every edge starting from vertex `v` leads
        // to a solution or not
        for (int w: graph.adjList.get(v))
        {
            // process only unvisited vertices as the Hamiltonian
            // path visit each vertex exactly once
            if (!visited[w])
            {
                visited[w] = true;
                path.add(w);

                // check if adding vertex `w` to the path leads
                // to the solution or not
                hamiltonianPaths(graph, w, visited, path, n, resultList);

                // backtrack
                visited[w] = false;
                path.remove(path.size() - 1);
            }
        }
    }

    public static List<List<Integer>> findHamiltonianPaths(Graph graph, int n)
    {
        List<List<Integer>> resultDoubleList = new ArrayList<>();
        // start with every node
        for (int start = 0; start < n; start++)
        {
            // add starting node to the path
            List<Integer> path = new ArrayList<>();
            path.add(start);

            // mark the start node as visited
            boolean[] visited = new boolean[n];
            visited[start] = true;

            hamiltonianPaths(graph, start, visited, path, n, resultDoubleList);
        }

        return resultDoubleList;
    }

    public static void mainn(List<String> nodes)
    {
        List<Edge> edges = new ArrayList<Edge>();
        for (int i = 0; i < nodes.size(); i++) {
            for (int j = 0; j < i; j++) {
                boolean isAdjacent = areWordsAdjacent(nodes.get(i), nodes.get(j));
                if (isAdjacent) {
                    edges.add(new Edge(i, j));
                }
            }
        }

        // total number of nodes in the graph (labelled from 0 to 3)
        int n = nodes.size();

        // build a graph from the given edges
        Graph graph = new Graph(edges, n);
        List<List<Integer>> resultList = findHamiltonianPaths(graph, n);

        if(debugPrint) System.out.println("resultList:\n" + resultList);
        List<Integer> firstResult = new ArrayList<>();
        if(!resultList.isEmpty()) {
            firstResult = resultList.get(0);
        }
        for (int i = 0; i < firstResult.size(); i++) {
            System.out.print(nodes.get(firstResult.get(i)) + " ");
        }
    }

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
                //if (debugPrint) System.out.print(matrix[i][j] + "\t");

                // check if execution is limited and return if the distance is already greater than 2
                if (limited && matrix[i][j] < minDistance) {
                    minDistance = matrix[i][j];
                }
            }
            //if (debugPrint) System.out.println();
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