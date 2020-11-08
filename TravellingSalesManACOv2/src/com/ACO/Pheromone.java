package com.ACO;

public class Pheromone {

    double pheromoneLevelForCityPair;
    //A pheromone object will be created for each connected city
    public Pheromone(int i, int j) {
        pheromoneLevelForCityPair = initialisePheromone(i , j);
    }

    private double initialisePheromone(int i, int j) {
        return 0.015;
    }

    private double updatePheromone(double x, double y) {
        return 0.1;
    }

    //t (i, j) (t + 1) = p * t (i, j) (t)
    public void pheromoneDecay(double rateOfEvaporation) {
        pheromoneLevelForCityPair *= rateOfEvaporation;
    }

    //Pheromone for each city pair is increased by q / L where L is equal to tour length
    public void updatePheromonesForRoute(int routeSize, double q) {
        pheromoneLevelForCityPair *= q / routeSize;
    }
}
