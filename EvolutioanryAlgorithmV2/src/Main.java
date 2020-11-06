import java.util.*;

public class Main {
    static Map<Integer, City> cities = new HashMap<>();
    static Random random = new Random();
    static Tour tour = new Tour();
    public static void main(String[] args) {
        populateMap();
        Population population = new Population(100, 17);


        for (int i = 0; i < 25; i++) {
            ParentSelection parentSelection = new ParentSelection(population.getPopulation(), 5, cities);
            ArrayList<Integer[]> parents = parentSelection.getParents();
            Recombination recombination = new Recombination(parents.get(0), parents.get(1));
        }
    }


    //Populates the graph with cities taken from the CSV file
    private static void populateMap() {
        FileReader fileReader = new FileReader();
        cities = fileReader.populateMap();
    }


}




