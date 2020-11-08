package com.ACO;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Random;

public class Ant {
    ArrayList<Integer> tour;
    public Ant(Map<Integer, City> graph, int tourLength) {
        tour = generateRoute(graph, tourLength);
        System.out.println();
    }

    //Generates an entire route starting from a random city
    private ArrayList<Integer> generateRoute(Map<Integer, City> graph, int tourLength){
        Random random = new Random();
        ArrayList<Integer> tour = new ArrayList<>(tourLength + 1);
        int tourStart = random.nextInt(4);
        int numberOfCitiesRemaining = tourLength - 1;
        tour.add(tourStart);
        int pointer = 0;

        while (numberOfCitiesRemaining > 0) {
            int cityToAdd = random.nextInt(4);
            for (int i = 0; i < tourLength - 1; i++) {
                if (numberOfCitiesRemaining < 0) {
                    break;
                }
                if (pointer > tour.size() - 1) {
                    pointer = 0;
                    continue;
                }
                if (difference(graph, tour.get(pointer), cityToAdd) > difference(graph, tour.get(pointer), i) && !tour.contains(i)) {
                    cityToAdd = i;
                    tour.add(cityToAdd);
                    pointer++;
                    numberOfCitiesRemaining--;
                }
                else if (!tour.contains(cityToAdd)) {
                    tour.add(cityToAdd);
                    pointer++;
                    numberOfCitiesRemaining--;
                }

            }
        }
        tour.add(tour.size(), tour.get(0));
        return tour;
    }




    public double getCostOfRoute(Map<Integer, City> cityMap) {
        double total = 0.0;
        for (int i = 0; i < tour.size() - 1; i++) {
            total += difference(cityMap, tour.get(i), tour.get(i+1));
        }
        BigDecimal bd = new BigDecimal(total);
        bd = bd.round(new MathContext(3));
        total = bd.doubleValue();
        return total;
    }

    public double difference (Map<Integer, City> cityMap, int city1, int city2){
        City a = getCity(cityMap, city1);
        City b = getCity(cityMap, city2);
        return Math.sqrt(Math.pow(b.getX() - a.getX(), 2) + Math.pow(b.getY() - a.getY(), 2));
    }

    public City getCity (Map<Integer, City> cityMap,  int cityNumber){
        return cityMap.get(cityNumber);
    }

}

