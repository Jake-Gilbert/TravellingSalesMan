import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    static Map<Integer, City> cities = new HashMap<>();
    public static void main(String[] args) {
        populateMap();
        Population population = new Population(100, 17);
       // Scanner scanner = new Scanner(System.in);
       // System.out.println("Enter the number of cycles");
        //int numberOfCycles = scanner.nextInt();
       // simulate(population.population, numberOfCycles);

        for (int i = 0; i < 25; i++) {
            ParentSelection parentSelection = new ParentSelection(population.population, 5, cities);
            ArrayList<Tour> parents = parentSelection.getParents();
            Recombination recombination = new Recombination(parents.get(0), parents.get(1));

            System.out.println(Arrays.toString(recombination.offsprings.get(0).tour));
            System.out.println(Arrays.toString(recombination.offsprings.get(1).tour));
        }

    }

    public static void simulate(ArrayList<Tour> population, int numberOfGenerations) {
       // int currentGeneration = 0;
        //while (currentGeneration < population.size()) {
            //ParentSelection parentSelection = new ParentSelection(population, 5,  cities);
            //Recombination recombination=  new Recombination();


           // currentGeneration++;
       // }
    }

    //Populates the graph with cities taken from the CSV file
    private static void populateMap() {
        FileReader fileReader = new FileReader();
        cities = fileReader.populateMap();
    }


}




