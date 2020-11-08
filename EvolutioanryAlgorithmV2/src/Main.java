import java.util.*;

public class Main {
    static Map<Integer, City> cities = new HashMap<>();
    public static void main(String[] args) {
        populateMap();
        Population population = new Population(100, 17);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of cycles");
        int numberOfCycles = scanner.nextInt();
        simulate(population, numberOfCycles);
    }

    //Runs until the number of generations have been depleted
    public static void simulate(Population population, int numberOfGenerations) {
        int currentGeneration = 0;
        while (currentGeneration < numberOfGenerations) {
                population = replaceWithNewGeneration(population);
                getBestOfGeneration(population);
                currentGeneration++;
        }
    }

    private static Population replaceWithNewGeneration(Population population) {
        ArrayList<Tour> newGenerationTour = new ArrayList<>();
        for (int i = 0; i < population.population.size() / 2 ; i++) {

            ParentSelection parentSelection = new ParentSelection(population.population, 5, cities);
            ArrayList<Tour> parents = parentSelection.getParents();
            Recombination recombination = new Recombination(parents.get(0), parents.get(1), cities);
            newGenerationTour.add(recombination.offsprings.get(0));
            newGenerationTour.add(recombination.offsprings.get(1));
        }
        Population population1 = new Population(100, newGenerationTour.size());
        population1.population = newGenerationTour;
        return population1;
    }

    private static void getBestOfGeneration(Population population) {
        Tour best = population.population.get(0);
        for (int i = 1; i < population.population.size() - 1; i++) {
            if (population.population.get(i).getCostOfRoute(cities) < best.getCostOfRoute(cities)) {
                best = population.population.get(i);
            }
        }
        System.out.println("Best tour of the generation is: ");
        System.out.println(Arrays.toString(best.tour));
        System.out.println("Cost: " + best.getCostOfRoute(cities));
    }

    //Populates the graph with cities taken from the CSV file
    private static void populateMap() {
        FileReader fileReader = new FileReader();
        cities = fileReader.populateMap();
    }


}




