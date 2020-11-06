import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class Population {

    private ArrayList<Integer[]> population;

    public Population(int size, int tourLength) {
        population = initialisePopulation(size, tourLength);
    }

    //Initialises population based on the size of the pop and the route length
    public ArrayList<Integer[]> initialisePopulation(int popSize, int tourLength) {
            ArrayList<Integer[]> temp = new ArrayList<>();
            for (int i = 0; i < popSize; i++) {
                Tour tour = new Tour();
                temp.add(tour.generateRoute(tourLength));
            }
            return temp;
    }

    public ArrayList<Integer[]> getPopulation() {
        return population;
    }

    public void printPopulation(Map<Integer, City> cityMap) {
         Tour tour = new Tour();
        for (int i = 0; i < population.size(); i++) {
            System.out.println("index: " + (i+1));
            System.out.println(tour.getCostOfRoute(cityMap, population.get(i)));
            //cost.getCostOfRoute(population.get(i)));
            System.out.println(Arrays.toString(population.get(i)));
            System.out.println();
        }
    }

}
