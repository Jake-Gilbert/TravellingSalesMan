import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;

public class Salesman {

    static Map<Integer, City> cities = new HashMap<>();
    Random random = new Random();

    public Salesman(Map<Integer, City> cityMap) {
        cities = cityMap;
    }

    public City getCity(int cityNumber) {
        return cities.get(cityNumber);
    }

    public double difference(int city1, int city2) {
        City a = getCity(city1);
        City b = getCity(city2);
        return Math.sqrt(Math.pow(b.getX() - a.getX(),2) + Math.pow(b.getY() - a.getY(),2));
    }

    //Generates a tour of a certain length
    public ArrayList<Integer> generateRoute(int length) {
        ArrayList<Integer> tour = new ArrayList<>();
        for (int i = 0; i < length - 1; i++) {
            tour.add(i);
        }
        Collections.shuffle(tour);
        tour.add(tour.size(), tour.get(0));
        return tour;
    }


    public void randomRoutes(int cityLength, int toursGenerated) throws InterruptedException {

            ArrayList<Integer> temp = new ArrayList<>();
            ArrayList<Integer> best = generateRoute(cityLength);
            for (int i = 0; i < toursGenerated; i++) {
                temp = generateRoute(cityLength);
                if (getCostOfRoute(temp) < getCostOfRoute(best)) {
                    best = temp;
                }
                System.out.print("Tour: " + temp.toString() + " Cost: " + getCostOfRoute(temp));
                System.out.println();
                System.out.println();
            }
        System.out.println();
        System.out.println("Shortest route is " + best.toString() + " Cost: " + getCostOfRoute(best));

    }

    public double getCostOfRoute(ArrayList<Integer> route)  {
        double total = 0.0;
        for (int i = 0; i < route.size() - 1; i++) {
            total += difference(route.get(i) , route.get(i + 1 ));
        }
        BigDecimal bd = new BigDecimal(total);
        bd = bd.round(new MathContext(3));
        total = bd.doubleValue();
        return total;
    }





}


