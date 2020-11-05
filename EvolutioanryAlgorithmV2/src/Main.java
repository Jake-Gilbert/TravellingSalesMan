
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Main {
    static Map<Integer, City> cities = new HashMap<>();
    static Random random = new Random();

    public static void main(String[] args) {
        populateMap();
        for (int i = 0; i < 5; i++) {
            Tour tour = new Tour(17);
            Cost cost = new Cost(cities);
            System.out.println(tour.getTour().toString());
            System.out.println(cost.getCostOfRoute(tour.getTour()));
            System.out.println();
        }

    }


    //Populates the graph with cities taken from the CSV file
    private static void populateMap() {
        FileReader fileReader = new FileReader();
        cities = fileReader.populateMap();
    }


}




