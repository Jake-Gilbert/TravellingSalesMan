package com.ACO;

import java.util.ArrayList;

public class Pheromone {

    public double[][] pheromoneForEachCityPair;
    //A pheromone object will be created for each connected city
    int cityOne;
    int cityTwo;

    public Pheromone(int tourLength, double initialPheromoneValue) {
        pheromoneForEachCityPair = initialisePheromoneArray(tourLength, initialPheromoneValue);
    }

    private double[][] initialisePheromoneArray(int tourLength, double pheromoneValue) {
        double[][] temporaryArray = new double[tourLength][tourLength];
        for (int i = 0; i <= tourLength - 1; i++) {
            for (int j = 0; j <= tourLength - 1; j++) {
                temporaryArray[i][j] = pheromoneValue;
            }
        }
        return temporaryArray;
    }

    //Returns pheromone level for specified city pairing
    public double lookUpPheromonePair(int i, int j) {
        return pheromoneForEachCityPair[i][j];
    }

    public void updatePheromonePair(int i, int j, double updatedValue) {
        pheromoneForEachCityPair[i][j] *= updatedValue;
    }


    private void updatePheromone(int[] route, double q) {
        for (int i = 0; i < route.length - 1; i++) {
            double updateEachCityPair = q / route.length;
            updatePheromonePair(route[i], route[i + 1], updateEachCityPair);
            //pheromoneForEachCityPair[i][i + 1] += q / route.length;
        }
    }

        //t (i, j) (t + 1) = p * t (i, j) (t)
        public void pheromoneDecay ( double rateOfEvaporation){
            for (int i = 0; i <= pheromoneForEachCityPair.length - 1; i++) {
                for (int j = 0; j <= pheromoneForEachCityPair.length - 1; j++) {
                    pheromoneForEachCityPair[i][j] *= rateOfEvaporation;
                }
            }
        }

        //Pheromone for each city pair is increased by q / L where L is equal to tour length
        public void updatePheromonesForRoute (Ant[]swarm,double q){
            // pheromoneLevelForCityPair *= q / routeSize;
            for (Ant ant : swarm) {
                if (ant != null) {
                    updatePheromone(ant.tour, q);
                }
            }
        }
    }

