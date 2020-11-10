import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Random;

public class Main {

    static Map<Integer, City> cities ;
    static Random random = new Random();


    public static double difference(int city1, int city2){
        City a = getCity(city1, cities);
        City b = getCity(city2, cities);
        return Math.sqrt(Math.pow(b.getX() - a.getX(),2) + Math.pow(b.getY() - a.getY(),2));
    }
    public static City getCity(int cityNumber, Map<Integer, City> cities) {
        return cities.get(cityNumber);
    }

    public static double getCostOfRoute(ArrayList<Integer> route)  {
        double total = 0.0;
        for (int i = 0; i < route.size() - 1; i++) {
            total += difference(route.get(i) , route.get(i + 1 ));
        }
        BigDecimal bd = new BigDecimal(total);
        bd = bd.round(new MathContext(3));
        total = bd.doubleValue();
        return total;
    }


    public static ArrayList<Integer> generateRoute(int length) {
        ArrayList<Integer> tour = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            tour.add(i);
        }
        Collections.shuffle(tour);
        tour.add(tour.size(), tour.get(0));
        return tour;
    }


    //This method will randomly search the city for the specified duration
    public static ArrayList<Integer> randomSearchTimeLimited(int timeAllocated) throws InterruptedException {
        ArrayList<Integer> best = new ArrayList<>();
        for (int i = 0; i < timeAllocated; i++) {
            ArrayList<Integer> temp = generateRoute(17);
            System.out.println(temp);
            if (best.isEmpty()) {
                best = temp;
            }
            else {
                if (getCostOfRoute(best) > getCostOfRoute(temp)) {
                    best = temp;
                }
            }
            System.out.println(temp);
            System.out.println("cost of route is " + getCostOfRoute(temp));
            System.out.println();
            Thread.sleep(1000);
        }
        System.out.println("The best route is " + best.toString() + " costing: " + getCostOfRoute(best));
        return best;

    }

    public static void twoOptForDuration(int length, int timeAllocated) throws InterruptedException {
            ArrayList<Integer> best = new ArrayList<>();
            ArrayList<ArrayList<Integer>> neighbourhoods = new ArrayList<>();
            boolean optimal = false;
            for (int i = 0; i < timeAllocated; i++) {
                ArrayList<Integer> temp = generateRoute(length);
                neighbourhoods = twoOpt(temp);

                if (best.isEmpty()) {
                        best = temp;
                }
                while (!optimal) {
                         if (getCostOfRoute(temp) > getCostOfRoute(bestNeighbourhood(neighbourhoods))) {
                             temp = bestNeighbourhood(neighbourhoods);
                             neighbourhoods = twoOpt(temp);
                         }
                         if (getCostOfRoute(temp) <= getCostOfRoute(bestNeighbourhood(neighbourhoods))) {
                             optimal = true;
                         }
                }
                optimal = false;
                System.out.println(temp + "cost: " + getCostOfRoute(temp));
                System.out.println(temp + " cost: " + getCostOfRoute(temp));
                System.out.println();
                if(getCostOfRoute(best) > getCostOfRoute(temp)) {
                        best = temp;
                }


                Thread.sleep(100);
            }
        System.out.println();
        System.out.println("Best is " + best.toString() + " with a shockingly low cost of " + getCostOfRoute(best) + "!");
    }
    public static ArrayList<ArrayList<Integer>> twoOpt(ArrayList<Integer> tour) {
        ArrayList<ArrayList<Integer>> neighbourhoods = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<>();
        int tempInt = 0;
        for (int i = 0; i <= tour.size() - 1; i++) {
            for (int j = i + 1; j < tour.size() - 1; j++) {
                temp = twoOptSwap(tour, i, j);
                neighbourhoods.add(temp);
            }
        }
        return neighbourhoods;
    }


    public static ArrayList<Integer> twoOptSwap(ArrayList<Integer> route, int i, int k) {

        ArrayList<Integer> temp = new ArrayList<>();
        for (int x = 0; x <=  i - 1; x++) {
            temp.add(route.get(x));
        }
        for (int y = k; y >= i; y--) {
                temp.add(route.get(y));

        }
        for (int z = k + 1; z < route.size(); z++) {
            temp.add(route.get(z));
        }
        //System.out.println("temp :" + temp);
        return temp;
    }

    public static ArrayList<Integer> bestNeighbourhood (ArrayList<ArrayList<Integer>> neighbourhoods) {
        ArrayList<Integer> temp;
        ArrayList<Integer> best = new ArrayList<>();
        for (int i = 0; i < neighbourhoods.size(); i++) {
            temp = neighbourhoods.get(i);

            if (temp.get(temp.size() - 1).equals(temp.get(0))) {
                if (best.isEmpty()) {
                    best = temp;
                } else if (getCostOfRoute(best) > getCostOfRoute(temp)) {
                    best = temp;
                }


            }
        }
            return best;

    }

    public static void main(String[] args) throws InterruptedException {
        populateMap();
        twoOptForDuration(16, 40);
    }



        private static void populateMap() {
            FileReader fileReader = new FileReader();
            cities = fileReader.populateMap();
        }







}


