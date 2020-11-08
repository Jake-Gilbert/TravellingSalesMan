package com.ACO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
    final static Map<Integer, City> GRAPH = initialiseGraph();
    static Pheromone[][] pheromoneArray;
    public static void main(String[] args) {
            int tourLength = GRAPH.size();
        //Suggestion: put it all in one package
            pheromoneArray = initialisePheromoneArray(4);
            int a = 0;
            ArrayList<Ant> swarm = new ArrayList<>();
            while (a <= 3) {
                Ant ant = new Ant(GRAPH, tourLength);
                swarm.add(ant);
                System.out.println(swarm.get(a).tour.toString());
                System.out.println(swarm.get(a).getCostOfRoute(GRAPH));
                a++;
            }
        System.out.println();

    }
    // alpha = 1.0 beta = 5.0 parameters of program
    //t (i, j) pheremone at edge i -> j
    //n i t (t) - heuristic information about edge i -> j


    private static Pheromone[][] initialisePheromoneArray(int tourLength) {
        Pheromone[][] temporaryArray = new Pheromone[tourLength][tourLength];
        for (int i = 0; i <= tourLength - 1; i++) {
            for (int j = 0; j <= tourLength - 1; j++) {
                temporaryArray[i][j] = new Pheromone(i, j);
            }
        }
        return temporaryArray;
    }


    private static Map<Integer, City> initialiseGraph() {
        HashMap<Integer, City> graph = new HashMap<>();
        graph.put(0, new City(38.24,	20.42));
        graph.put(1, new City(39.57,	26.15));
        graph.put(2, new City(40.56,	25.32));
        graph.put(3, new City(36.26,	23.12));
        return graph;
    }


}

