package com.ACO;

import com.sun.source.tree.TryTree;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;

public class Ant {
    int[] tour;
    Pheromone pheromone;
    private final Map<Integer, City> GRAPH;
    public Ant(Map<Integer, City> graph, int tourLength, Pheromone pheromone) {
        GRAPH = graph;
        this.pheromone = pheromone;
        tour = generateRoute(tourLength);
    }

    //Generates an entire route starting from a random city
    private int[] generateRoute(int tourLength){
        Random random = new Random();
        int[] tour = new int[tourLength];
        ArrayList<Integer> tabuList = new ArrayList<>();
        int tourStart = random.nextInt(tourLength - 1);
        int numberOfCitiesRemaining = tourLength - 2;
        tour[0] = tourStart;
        tabuList.add(tourStart);
        int previousCity = tourStart;
        int pointer = 1;
        while (numberOfCitiesRemaining > 0) {
            int cityToAdd = chooseCity(previousCity, tabuList, tourLength);
            tour[pointer] = cityToAdd;
            tabuList.add(cityToAdd);
            previousCity = cityToAdd;
            pointer++;
            numberOfCitiesRemaining--;
        }


        tour[tourLength - 1]  = tour[0];
        return tour;
    }


            // alpha = 1.0 beta = 5.0 parameters of program
            //t (i, j) pheremone at edge i -> j
            //n i t (t) - heuristic information about edge i -> j
            //n = 1 / difference (i , j)

        private int chooseCity(int currentCity, ArrayList<Integer> tabuList, int tourLength) {
            Map<Integer, Double> validCityPool = new HashMap<>();
            ArrayList<Integer> safeCities = new ArrayList<>();
            int nextCityToBeAdded = 0;
            //Generates the safeCities collection to be used to influence ant's decision
            int pointer = 0;
            for(int i = 0; i < tourLength - 1; i++) {
                if (!tabuList.contains(i)) {
                    safeCities.add(i);
                    pointer++;
                }

            }
            if (safeCities.size() > 1) {
                for (int j = 0; j <= safeCities.size() - 1; j++) {
                    double heuristicDistance;

                    if (j > 0) {

                        double previousValue = 0;
                        for (int i = j - 1; i > -1; i--) {
                            if (validCityPool.get(i) != null) {
                                previousValue = validCityPool.get(i);
                                break;
                            }
                        }
                        validCityPool.put(safeCities.get(j), (Math.pow(getHeuristicDistance(currentCity, safeCities.get(j)), 5.0) * Math.pow(getPheromonePair(currentCity, safeCities.get(j)), 1.0)) + previousValue);
                    }
                    else {
                        validCityPool.put(safeCities.get(j), Math.pow(getHeuristicDistance(currentCity, safeCities.get(j)), 5.0) * (Math.pow(getPheromonePair(currentCity, safeCities.get(j)), 1.0)));
                    }

                }
                if (validCityPool.size() > 0) {
                    nextCityToBeAdded = getNextCity(validCityPool);
                }
                if (nextCityToBeAdded < 0) {
                    nextCityToBeAdded = getNextCity(validCityPool);
                }

                //pheromone at city * 1/ difference(i, j);

                return nextCityToBeAdded;
            }
           return safeCities.get(0);

    }
    private double getHeuristicDistance(int currentCity, int safeCities) {
        if (currentCity == safeCities) {
            return Double.MIN_VALUE;
        }
        else {
            double heuristicDifference;
            double check = difference(currentCity, safeCities);
            if (check > 0) {
                heuristicDifference = 1 / check;
            }
            else {
                heuristicDifference = Double.MIN_VALUE;
            }
            return heuristicDifference;
        }
    }

    private double getPheromonePair(int currentCity, int cityToCheck) {
        if (currentCity == cityToCheck) {
            return Double.MIN_VALUE;
        }
        else {
            return pheromone.lookUpPheromonePair(currentCity, cityToCheck);
        }
    }
    public int getNextCity(Map<Integer, Double> validCityPool){
        Random random = new Random();
        double randComparisonValue = random.nextDouble();
        randComparisonValue *= (double) validCityPool.values().toArray()[validCityPool.size() - 1];
        for (int city : validCityPool.keySet()) {
            if (validCityPool.get(city) >= randComparisonValue) {
                return city;
            }
        }
        return -1;
    }


    public double getCostOfRoute(int[] tour) {
        double total = 0.0;
        for (int i = 0; i < tour.length - 1; i++) {
            total += difference(tour[i], tour[i + 1]);
        }
        BigDecimal bd = new BigDecimal(total);
        bd = bd.round(new MathContext(3));
        total = bd.doubleValue();
        return total;
    }

    public double difference (int city1, int city2){
        City a = getCity(city1);
        City b = getCity(city2);
        if (a == b) {
            return Double.MIN_VALUE;
        }
        else {
            try {
                return Math.sqrt(Math.pow(b.getX() - a.getX(), 2) + Math.pow(b.getY() - a.getY(), 2));
            }
            catch (Exception e) {
                //System.out.println(city1 + " " + city2);
            }
        }
        return Double.MIN_VALUE;
    }

    public City getCity (int cityNumber){
        return GRAPH.get(cityNumber);
    }

}

