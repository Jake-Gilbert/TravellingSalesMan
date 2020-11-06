import java.util.ArrayList;
import java.util.Map;

public class Population {

    private ArrayList<ArrayList<Integer>> population;

    public Population(int size) {
        population = initialisePopulation(size);
    }

    public ArrayList<ArrayList<Integer>> initialisePopulation(int size) {
            ArrayList<ArrayList<Integer>> temp = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Tour tour = new Tour();
                temp.add(tour.generateRoute(17));
            }
            return temp;
    }

    public ArrayList<ArrayList<Integer>> getPopulation() {
        return population;
    }

    public void printPopulation(Map<Integer, City> cityMap) {
         Tour tour = new Tour();
        for (int i = 0; i < population.size(); i++) {
            System.out.println("index: " + (i+1));

            System.out.println(tour.getCostOfRoute(cityMap, population.get(i)));
            //cost.getCostOfRoute(population.get(i)));
            System.out.println(population.get(i).toString());
            System.out.println();
        }
    }

}
