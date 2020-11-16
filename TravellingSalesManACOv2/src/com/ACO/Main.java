package com.ACO;

import javax.print.DocFlavor;
import java.util.*;

public class Main {
    private static final Map<Integer, City> GRAPH = initialiseGraph();
    static Pheromone pheromone;
    public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the tour length for the ants to construct");
            int tourLength = scanner.nextInt();
            pheromone = new Pheromone(tourLength, 0.015);
           // pheromone.pheromoneDecay(0.5);
             System.out.println("Enter pheromoneDecay");
             double pheromoneDecay = scanner.nextDouble();
            runACO(tourLength, pheromoneDecay, 100.0);



    }
    //Suggestion: put it all in one package
    //Run the Ant Colony Optimisation process:
    private static void runACO(int tourLength, double pheromoneDecay, double q){
        int a = 0;
        int b = 0;
        int swarmSize = 100;
        Ant[] swarm = new Ant[swarmSize];
        while (a < 100) {
            for (int i = 0; i < swarmSize - 1; i++) {
                Ant ant = new Ant(GRAPH, tourLength, pheromone);
                swarm[i]  = ant;
            }
            getBestAntOfGeneration(swarm);
            pheromone.pheromoneDecay(pheromoneDecay);
            pheromone.updatePheromonesForRoute(swarm, q);
            a++;
        }
    }

    private static void getBestAntOfGeneration(Ant[] swarm) {
        Ant bestAnt = swarm[0];
        for (Ant ant : swarm) {
            if (ant != null) {
                if (ant.getCostOfRoute(ant.tour) < bestAnt.getCostOfRoute(bestAnt.tour)) {
                    bestAnt = ant;
                }
            }
        }
        System.out.println("Best route of this generation is:");
        System.out.println(Arrays.toString(bestAnt.tour) + " cost: " + bestAnt.getCostOfRoute(bestAnt.tour));
    }

    private static Map<Integer, City> initialiseGraph() {
        FileReaderClass fileReaderClass = new FileReaderClass();
        return fileReaderClass.populateMap();
    }


}

