import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Main {
    static Map<Integer, City> cities = new HashMap<>();
    static Random random = new Random();

    public static void main(String[] args) {
        populateMap();
        Population population = new Population(100);
        ParentSelection parentSelection = new ParentSelection(population.getPopulation(), 10, cities);


    }


    //Populates the graph with cities taken from the CSV file
    private static void populateMap() {
        FileReader fileReader = new FileReader();
        cities = fileReader.populateMap();
    }


}




